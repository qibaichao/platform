package com.cyou.utils;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class LargeFileSpliter {
    private String largeFilePath;
    private File largeFile;
    private int totalLine;   // 总行数

    private Map<Integer, BlockingQueue<String>> lineQueueMap;   // key为小文件索引，value为小文件包含的行的阻塞队列
    private List<Thread> writeSplitThreads;   // 写小文件的线程

    public static void main(String[] args) throws FileNotFoundException {
        LargeFileSpliter largeFileSpliter = new LargeFileSpliter("f:/test/Share_0007_CMBC.20180314");
        largeFileSpliter.multiThreadSplit(5, 500000);
    }

    /**
     * 构造函数
     *
     * @param largeFilePath 大文件路径
     */
    public LargeFileSpliter(String largeFilePath) {
        if (largeFilePath == null) {
            throw new IllegalArgumentException("largeFilePath should not null");
        }
        this.largeFile = new File(largeFilePath);
        if (!largeFile.exists()) {
            throw new IllegalArgumentException("large file not exist");
        }

        this.largeFilePath = largeFilePath;
        this.totalLine = getLineNum(largeFile);
        System.out.println("总行数为：" + totalLine);
    }

    /**
     * 多线程读取文件的每一行到多个队列（一个split文件一个队列），然后每个队列对应一个写线程
     *
     * @param readThreadNum
     * @param linesPerSplit
     */
    public void multiThreadSplit(int readThreadNum, int linesPerSplit) {
        int numOfSplits = getNumOfSplits(totalLine, linesPerSplit);
        lineQueueMap = new HashMap<Integer, BlockingQueue<String>>(numOfSplits);

        /*
         * 创建阻塞队列缓冲
         */
        writeSplitThreads = new ArrayList<Thread>(numOfSplits);
        for (int i = 0; i < numOfSplits; i++) {
            BlockingQueue<String> lineQueue = new LinkedBlockingQueue<String>(1000);   // 队列用于存放每个小文件中的行
            lineQueueMap.put(i, lineQueue);
        }

        /*
         * 创建读线程池并提交读任务
         */
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(largeFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("large file not exist", e);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        ExecutorService readThreadPool = Executors.newFixedThreadPool(readThreadNum);
        for (int i = 0; i < readThreadNum; i++) {
            readThreadPool.submit(new ReadLineRunnable(reader, lineQueueMap, linesPerSplit));
        }

        /*
         * 创建多个写线程并启动，每个写线程对应一个子文件
         */
        for (int i = 0; i < numOfSplits; i++) {
            Thread writeLineThread = new Thread(new WriteLineRunnable(assembleSplitFilePath(i), lineQueueMap.get(i)));
            writeSplitThreads.add(writeLineThread);
            writeLineThread.start();   // 启动写线程
        }

        /*
         * 等待大文件读取完毕后，关闭读线程池和reader
         */
        while (!ReadLineRunnable.isReadCompleted()) {   // 等待整个大文件读取完毕
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readThreadPool.shutdown();
        try {
            reader.close();   // 大文件全部读取完毕后才能关闭reader
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String assembleSplitFilePath(int splitIdx) {
        int lastDotIdx = this.largeFilePath.lastIndexOf(".");
        String fileName = this.largeFilePath.substring(0, lastDotIdx);
        String fileFormat = this.largeFilePath.substring(lastDotIdx + 1);
        return fileName + "." + fileFormat + "-" + splitIdx;
    }

    private int getNumOfSplits(int totalLine, int linesPerSplit) {
        return totalLine % linesPerSplit == 0 ? totalLine / linesPerSplit : totalLine / linesPerSplit + 1;
    }

    public static int getLineNum(File file) {
        InputStream is = null;
        int lineNum = 0;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            byte[] c = new byte[2048];
            int readChars = 0;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++lineNum;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lineNum;
    }
}