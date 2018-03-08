/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.cyou.common;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class RMQNormalListner2 implements MessageListenerConcurrently {
    private ConsumeConcurrentlyStatus consumeStatus = ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                    ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt msg : msgs) {
            String message = new String(msg.getBody());
            System.out.println(message);
        }
        return consumeStatus;
    }
}
