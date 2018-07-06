package com.marco.util;

import java.util.Random;

/**
 * Created by landun on 2018/7/5.
 */
public class StringUtil {
    private static char[] alpha = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R'
            ,'S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m'
            ,'n','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0'};

    public static String getRamdomImgCode(int length){
        StringBuilder builder = new StringBuilder();
        int alpahLength = alpha.length;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alpahLength);
            builder.append(alpha[index]);
        }
        return builder.toString();
    }

}
