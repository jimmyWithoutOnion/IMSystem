cmake_minimum_required(VERSION 2.8)
# project name and language
project(MyJni LANGUAGES CXX)

# set source 
set(SRC_LIST 
solution.cpp 
Sha256.cpp 
FileCrc32.cpp 
com_huawei_kunpengimsystem_utils_NativeUtil.h 
Sha256.h 
FileCrc32.h
)

# find java jin include path
find_path(JNIPATH NAMES jni.h)
if(JNIPATH)
    message("The path include jni.h is: ${JNIPATH}")
    include_directories(${JNIPATH} ${JNIPATH}/linux)
else()
    message(FATAL_ERROR "not find jni.h")
endif()


# detect the architecture of processor
message("${CMAKE_HOST_SYSTEM_PROCESSOR} architecture detected")
if(CMAKE_HOST_SYSTEM_PROCESSOR MATCHES "aarch64")
    add_compile_options(-march=armv8-a+crc)
endif()

# target libxx.so
add_library(MyJni SHARED ${SRC_LIST})

#set install path libxx.so
set(CMAKE_INSTALL_PREFIX /usr/local/)
install(TARGETS MyJni
        LIBRARY DESTINATION mylib
       )