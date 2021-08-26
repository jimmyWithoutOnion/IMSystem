package com.huawei.kunpengimsystem.utils;

public class NativeUtil {
    public native String getSha256Digest(String message);
    public native String getCrc32Digest(String filePath);
    public native String getCpuClocks();
    
}
