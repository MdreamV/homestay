package com.jtw.homestay.utils;

import java.util.UUID;

public final class UUIDUtils {
    private UUIDUtils(){}
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(getUuid());
    }
}
