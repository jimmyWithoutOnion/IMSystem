cmake_minimum_required(VERSION 2.8)

project(MyJni)

include_directories("/usr/lib/jvm/java-11-openjdk-11.0.9.11-4.oe1.aarch64/include")
include_directories("/usr/lib/jvm/java-11-openjdk-11.0.9.11-4.oe1.aarch64/include/linux")

add_compile_options(-march=armv8-a+crc)

add_library(MyJni SHARED solution.cpp Sha256.cpp FileCrc32.cpp com_huawei_kunpengimsystem_utils_NativeUtil.h Sha256.h FileCrc32.h)
