package com.cyou.utils;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws Exception {
        /* 
        *将一个文件分割为多个文件,然后再组合成一个文件 
        * 找到文件,读入一个1M的buffer中,然后写入一个Part文件中,循环此操作直到文件读取完毕 
        */

        String sourceFilePath = "f:/test/Share_0007_CMBC.20180314";
        int partFileLength = 1024 * 1024 * 100;//指定分割的子文件大小为1M
        splitFile(sourceFilePath, partFileLength);//将文件分割
    }


    public static void splitFile(String sourceFilePath, int partFileLength) throws Exception {
        File sourceFile = null;
        File targetFile = null;
        InputStream ips = null;
        OutputStream ops = null;
        OutputStream configOps = null;//该文件流用于存储文件分割后的相关信息，包括分割后的每个子文件的编号和路径,以及未分割前文件名  
        Properties partInfo = null;//properties用于存储文件分割的信息  
        byte[] buffer = null;
        int partNumber = 1;
        sourceFile = new File(sourceFilePath);//待分割文件  
        ips = new FileInputStream(sourceFile);//找到读取源文件并获取输入流  
        buffer = new byte[partFileLength];//开辟缓存空间
        int tempLength = 0;
        partInfo = new Properties();//key:1开始自动编号 value:文件路径  

        while ((tempLength = ips.read(buffer, 0, partFileLength)) != -1) {
            String targetFilePath = "f:/test/Share_0007_CMBC.20180314" + (partNumber);//分割后的文件路径+文件名
            partInfo.setProperty((partNumber++) + "", targetFilePath);//将相关信息存储进properties
            targetFile = new File(targetFilePath);
            ops = new FileOutputStream(targetFile);//分割后文件  
            ops.write(buffer, 0, tempLength);//将信息写入碎片文件

            ops.close();//关闭碎片文件  
        }
        partInfo.setProperty("name", sourceFile.getName());//存储源文件名
        ips.close();//关闭源文件流
    }
} 