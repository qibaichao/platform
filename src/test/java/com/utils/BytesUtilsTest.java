package com.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by qibaichao on 2018/7/12.
 */
public class BytesUtilsTest {

    public static void main(String args[]){

//        System.out.println(BytesUtils.toBytes("marketing-service:172.16.3.121").length);

        System.out.println("---->"+System.getProperty("java.class.path"));

        File file =new File("aaa");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {

        }

    }
}
