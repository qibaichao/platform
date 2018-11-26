package com.utils;

import com.cyou.utils.FileUtil;
import org.junit.Test;

import java.io.IOException;

public class FileTest {

    @Test
    public void writeFile() throws IOException, InterruptedException {

        int blockFileSize = 1024 * 1024 * 15;// 15M

        String fileName = "f:/test/Share_0007_CMBC.20180314";

        FileUtil fileUtil = new FileUtil();

        // 将origin.myfile拆分
        fileUtil.splitBySize(fileName, blockFileSize);



    }
}