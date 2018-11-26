package com.cyou.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
 
/**
 * 写小文件Runnable
 * @author will
 *
 */
public class WriteLineRunnable implements Runnable {
    private String splitFilePath;
    private BlockingQueue<String> lineQueue;
    private AtomicBoolean firstTake = new AtomicBoolean(true);
     
    public WriteLineRunnable(String splitFilePath, BlockingQueue<String> lineQueue) {
        this.splitFilePath = splitFilePath;
        this.lineQueue = lineQueue;
    }
 
    @Override
    public void run() {
        File splitFile = new File(splitFilePath);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(splitFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         
        while(true) {
            try {
                List<String> linesCache = new ArrayList<String> ();
                String line = lineQueue.poll(100, TimeUnit.MILLISECONDS);   // 空闲等待最多100ms
                if(line == null && firstTake.get()) {
                    continue;
                }
                else if(line == null && !firstTake.get()) {   // 这种情况认为处理完了该小文件包含的所有行
                    break;
                }
                else {
                    firstTake.set(false);
                     
                    linesCache.add(line);
                    lineQueue.drainTo(linesCache);
                     
                    StringBuilder linesBuilder = new StringBuilder();
                    int linesCacheSize = linesCache.size();
                    for(int i = 0; i < linesCacheSize; i++) {
                        linesBuilder.append(linesCache.get(i) + "\r\n");
                    }
                    writer.write(linesBuilder.toString());
                    writer.flush();
//                  System.out.println("写线程【" + splitFilePath + "】写入：【\r\n" + linesBuilder.toString() + "】");
                }
            } catch(Exception e) {
                e.printStackTrace();
                break;
            }
        }
         
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("exception occurs when try to close writer", e);
        }
        System.out.println("写线程【" + splitFilePath + "】写入完成！");
    }
}