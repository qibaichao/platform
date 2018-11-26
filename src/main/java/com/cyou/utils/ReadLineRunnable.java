package com.cyou.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReadLineRunnable implements Runnable {
    private BufferedReader reader;
    private Map<Integer, BlockingQueue<String>> lineQueueMap;
    private int linesPerSplit;

    private static final Object SEQUENTIAL_READ_AND_UPDATE_LOCK = new Object();   // 顺序读取行并更新行计数锁
    private static long lineNumCounter = 0L;   // 行计数器
    private static final AtomicBoolean READ_COMPLETED = new AtomicBoolean(false);

    public ReadLineRunnable(BufferedReader reader, Map<Integer, BlockingQueue<String>> lineQueueMap,
                            int linesPerSplit) {
        this.reader = reader;
        this.lineQueueMap = lineQueueMap;
        this.linesPerSplit = linesPerSplit;
    }


    public void run() {
        while (true) {
            synchronized (SEQUENTIAL_READ_AND_UPDATE_LOCK) {
                String currentLine = safeReadLine(reader);   // 当前行
                if (currentLine != null) {
                    int splitIdx = getSplitIdx(++lineNumCounter, linesPerSplit);
                    BlockingQueue<String> lineQueue = lineQueueMap.get(splitIdx);
                    enqueue(lineQueue, currentLine);
                    /*System.out.println("读线程【" + Thread.currentThread().getName() + "】读取行：" + currentLineNum
                            + "，行内容：" + currentLine + " => 阻塞队列序号：" + splitIdx);*/
                } else {
                    READ_COMPLETED.set(true);
//                  System.out.println("读取整个大文件完成！");
                    break;
                }
            }
        }
    }

    public static boolean isReadCompleted() {
        return READ_COMPLETED.get();
    }

    private void enqueue(BlockingQueue<String> lineQueue, String currentLine) {
        try {
            lineQueue.put(currentLine);
        } catch (InterruptedException e) {
            throw new RuntimeException("exception occurs when enqueue", e);
        }
    }

    /**
     * 获取Split索引
     *
     * @param currentLineNum
     * @param linesPerSplit
     * @return
     */
    private int getSplitIdx(long currentLineNum, int linesPerSplit) {
        return (int) ((currentLineNum - 1) / linesPerSplit);
    }

    /**
     * 安全读文件，即处理了IO异常
     *
     * @param reader
     * @return
     */
    private String safeReadLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("exception occurs when read file", e);
        }
    }
}