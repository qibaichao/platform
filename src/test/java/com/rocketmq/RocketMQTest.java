package com.rocketmq;

import com.cyou.BaseTest;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by qibaichao on 2018/1/3.
 */
public class RocketMQTest extends BaseTest {
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Test
    public void send() {
        Message msg = null;
        try {
            for(int i=0;i<10;i++){
                msg = new Message("TopicTest", "tag", ("了解").getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */);
                SendResult sendResult = defaultMQProducer.send(msg);
                System.out.printf("%s%n", sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

    @Test
    public void consumer() {
        Message msg = null;
        try {
           Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
