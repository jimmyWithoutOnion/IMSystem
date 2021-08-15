#include "com_huawei_kunpengimsystem_utils_NativeUtil.h"
#include "FileCrc32.h"
#include "Sha256.h"

#include <string>
#include <cstdint>
#include <iostream>
#include <cstring>

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

JNIEXPORT jstring JNICALL Java_com_huawei_kunpengimsystem_utils_NativeUtil_getSha256Digest
    (JNIEnv *env, jobject obj, jstring jstr)
{
    std::string message = jstring2str(env, jstr);
    std::string digest = sha256.GetHexMessageDiges(message);
    return str2jstring(env, digest.c_str());
}

JNIEXPORT jstring JNICALL Java_com_huawei_kunpengimsystem_utils_NativeUtil_getCrc32Digest
    (JNIEnv *env, jobject obj, jstring jstr)
{
    std::string filePath = jstring2str(env, jstr);
    std::string crc = fileCrc32.GetFileCrc(filePath);
    return str2jstring(env, crc.c_str());
}
