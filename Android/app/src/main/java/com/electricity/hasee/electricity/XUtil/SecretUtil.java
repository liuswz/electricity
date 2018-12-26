package com.electricity.hasee.electricity.XUtil;

import android.provider.SyncStateContract;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecretUtil {



    /**

     * 传入字符串参数，返回MD5加密结果（小写）

     *

     * @param value 待加密的字符串

     * @return 加密结果

     */

    public static String getMD5Result(String value) {

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(value.getBytes("UTF-8"));

            byte[] result = md.digest();

            return getString(result);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();


        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();



        }
        return "";
    }




    private static String getString(byte[] result) {

        StringBuilder sb = new StringBuilder();

        for (byte b : result) {

            int i = b & 0xff;

            if (i <= 0xf) {

                sb.append(0);

            }

            sb.append(Integer.toHexString(i));

        }

        return sb.toString().toLowerCase();

    }
}
