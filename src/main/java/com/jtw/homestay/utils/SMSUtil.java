package com.jtw.homestay.utils;

import java.util.Random;

public class SMSUtil {

    /**
     * 生成短信验证码
     * @return
     */
    public static String genCode(){
        String verifyCode = String
                .valueOf(new Random().nextInt(899999) + 100000);
        return  verifyCode;
    }

    public static void main(String[] args) {
        System.out.println(SMSUtil.genCode());
    }
}
