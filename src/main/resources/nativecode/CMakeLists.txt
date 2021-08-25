cmake_minimum_required(VERSION 2.8)

# project name and language
project(MyJni LANGUAGES CXX)

include_directories("/usr/lib/jvm/java-11-openjdk-11.0.9.11-4.oe1.aarch64/include")
include_directories("/usr/lib/jvm/java-11-openjdk-11.0.9.11-4.oe1.aarch64/include/linux")

#set(SRC_LIST solution.cpp Sha256.cpp FileCrc32.cpp com_huawei_kunpengimsystem_utils_NativeUtil.h Sha256.h FileCrc32.h)

add_library(message-objs
    OBJECT
        solution.cpp 
        Sha256.cpp 
        FileCrc32.cpp 
        com_huawei_kunpengimsystem_utils_NativeUtil.h 
        Sha256.h 
        FileCrc32.h
    )

set_target_properties(message-objs
    PROPERTIES
        POSITION_INDEPENDENT_CODE 1
    )


add_library(message-shared
    SHARED
        $<TARGET_OBJECT:message-objs>
    )



add_compile_options(-march=armv8-a+crc)

#add_library(MyJni SHARED SRC_LIST)
