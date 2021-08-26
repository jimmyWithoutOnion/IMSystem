#include "com_huawei_kunpengimsystem_utils_NativeUtil.h"
#include "FileCrc32.h"
#include "Sha256.h"

#include <string>
#include <cstdint>
#include <iostream>
#include <cstring>
#include <ctime>

static const int CPU_CURRENT = 2400;       // 设置为当前cpu的主频 MHz
static const int CPU_EXTERNAL_CLOCK = 100; // 100MHz
static const int RATIO_HMZ_TO_HZ = 1E6;    // 1MHz=1E6Hz

static const Sha256 sha256;
static FileCrc32 fileCrc32;

jstring str2jstring(JNIEnv *env, const char *pat)
{
    //定义java String类 strClass
    jclass strClass = env->FindClass("Ljava/lang/String;");
    //获取String(byte[],String)的构造器,用于将本地byte[]数组转换为一个新String
    jmethodID ctorID = env->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
    //建立byte数组
    jbyteArray bytes = env->NewByteArray(strlen(pat));
    //将char* 转换为byte数组
    env->SetByteArrayRegion(bytes, 0, strlen(pat), (jbyte *)pat);
    // 设置String, 保存语言类型,用于byte数组转换至String时的参数
    jstring encoding = env->NewStringUTF("GB2312");
    //将byte数组转换为java String,并输出
    return (jstring)env->NewObject(strClass, ctorID, bytes, encoding);
}

std::string jstring2str(JNIEnv *env, jstring jstr)
{
    char *rtn = NULL;
    jclass clsstring = env->FindClass("java/lang/String");
    jstring strencode = env->NewStringUTF("GB2312");
    jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray)env->CallObjectMethod(jstr, mid, strencode);
    jsize alen = env->GetArrayLength(barr);
    jbyte *ba = env->GetByteArrayElements(barr, JNI_FALSE);
    if (alen > 0)
    {
        rtn = (char *)malloc(alen + 1);
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    env->ReleaseByteArrayElements(barr, ba, 0);
    std::string stemp(rtn);
    free(rtn);
    return stemp;
}

std::string GetMessageSha256Digest(const std::string &message)
{
    Sha256 sha256;
    return sha256.GetHexMessageDiges(message);
}

JNIEXPORT jstring JNICALL Java_com_huawei_kunpengimsystem_utils_NativeUtil_getSha256Digest(JNIEnv *env, jobject obj, jstring jstr)
{
    std::string message = jstring2str(env, jstr);
    std::string digest = sha256.GetHexMessageDiges(message);
    return str2jstring(env, digest.c_str());
}

JNIEXPORT jstring JNICALL Java_com_huawei_kunpengimsystem_utils_NativeUtil_getCrc32Digest(JNIEnv *env, jobject obj, jstring jstr)
{
    std::string filePath = jstring2str(env, jstr);
    std::string crc = fileCrc32.GetFileCrc(filePath);
    return str2jstring(env, crc.c_str());
}

JNIEXPORT jstring JNICALL Java_com_huawei_kunpengimsystem_utils_NativeUtil_getCpuClocks(JNIEnv *env, jobject obj)
{
    uint64_t clocks;
#ifdef __x86_64__
    uint32_t lo, hi;
    __asm__ __volatile__("rdtsc"
                         : "=a"(lo), "=d"(hi));
    clocks = (uint64_t)hi << 32 | lo;
#elif defined __aarch64__
    __asm__ __volatile__("mrs %0, cntvct_el0"
                         : "=r"(clocks));
    clocks = clocks * CPU_CURRENT / CPU_EXTERNAL_CLOCK;
#else
    clocks = clock() / CLOCKS_PER_SEC * CPU_CURRENT * RATIO_HMZ_TO_HZ;
#endif
    std::string strClocks = std::to_string(clocks);
    return str2jstring(env, strClocks.c_str());
}
