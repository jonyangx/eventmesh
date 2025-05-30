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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.eventmesh.storage.standalone.broker;

import org.apache.eventmesh.storage.standalone.broker.model.MessageEntity;
import org.apache.eventmesh.storage.standalone.broker.model.TopicMetadata;
<<<<<<< HEAD
import org.apache.eventmesh.storage.standalone.broker.task.HistoryMessageClear;
import org.apache.eventmesh.storage.standalone.broker.task.HistoryMessageClearTask;

import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import io.cloudevents.CloudEvent;

/**
 * This broker used to store event, it just support standalone mode, you shouldn't use this module in production environment
 */
public class StandaloneBroker {

    private final ConcurrentHashMap<TopicMetadata, MessageQueue> messageContainer;

    // todo: move the offset manage to consumer
    private final ConcurrentHashMap<TopicMetadata, AtomicLong> offsetMap;

    private StandaloneBroker() {
        this.messageContainer = new ConcurrentHashMap<>();
        this.offsetMap = new ConcurrentHashMap<>();
        startHistoryMessageCleanTask();
    }

    public ConcurrentHashMap<TopicMetadata, MessageQueue> getMessageContainer() {
        return this.messageContainer;
    }

    public ConcurrentHashMap<TopicMetadata, AtomicLong> getOffsetMap() {
        return this.offsetMap;
    }

    public static StandaloneBroker getInstance() {
        return StandaloneBrokerInstanceHolder.instance;
=======
import org.apache.eventmesh.storage.standalone.broker.task.Subscribe;

import java.util.concurrent.ConcurrentHashMap;

import io.cloudevents.CloudEvent;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * This broker used to store event, it just support standalone mode, you shouldn't use this module in production environment
 */
@Slf4j
public class StandaloneBroker {

    // message source by topic
    @Getter
    private final ConcurrentHashMap<TopicMetadata, Channel> messageContainer;

    @Getter
    private final ConcurrentHashMap<TopicMetadata, Subscribe> subscribeContainer;

    private StandaloneBroker() {
        this.messageContainer = new ConcurrentHashMap<>();
        this.subscribeContainer = new ConcurrentHashMap<>();
    }


    public static StandaloneBroker getInstance() {
        return StandaloneBrokerInstanceHolder.INSTANCE;
>>>>>>> upstream/master
    }

    /**
     * put message
     *
     * @param topicName topic name
     * @param message   message
<<<<<<< HEAD
     * @throws InterruptedException
     */
    public MessageEntity putMessage(String topicName, CloudEvent message) throws InterruptedException {
        Pair<MessageQueue, AtomicLong> pair = createTopicIfAbsent(topicName);
        AtomicLong topicOffset = pair.getRight();
        MessageQueue messageQueue = pair.getLeft();

        MessageEntity messageEntity = new MessageEntity(
            new TopicMetadata(topicName), message, topicOffset.getAndIncrement(), System.currentTimeMillis());
        messageQueue.put(messageEntity);

        return messageEntity;
    }

=======
     */
    public MessageEntity putMessage(String topicName, CloudEvent message) {
        TopicMetadata topicMetadata = new TopicMetadata(topicName);
        if (!messageContainer.containsKey(topicMetadata)) {
            throw new RuntimeException(String.format("The topic:%s is not created", topicName));
        }
        Channel channel = messageContainer.get(topicMetadata);
        if (channel.isClosed()) {
            throw new RuntimeException(String.format("The topic:%s is not subscribed", topicName));
        }
        MessageEntity messageEntity = new MessageEntity(new TopicMetadata(topicName), message);
        channel.getProvider().onData(messageEntity);
        return messageEntity;
    }

    public Channel createTopic(String topicName) {
        TopicMetadata topicMetadata = new TopicMetadata(topicName);
        return messageContainer.computeIfAbsent(topicMetadata, k -> new Channel(topicMetadata));
    }

>>>>>>> upstream/master
    /**
     * Get the message, if the queue is empty then await
     *
     * @param topicName
     */
    public CloudEvent takeMessage(String topicName) throws InterruptedException {
<<<<<<< HEAD
        TopicMetadata topicMetadata = new TopicMetadata(topicName);
        return messageContainer.computeIfAbsent(topicMetadata, k -> new MessageQueue()).take().getMessage();
=======
        return null;
>>>>>>> upstream/master
    }

    /**
     * Get the message, if the queue is empty return null
     *
     * @param topicName
     */
    public CloudEvent getMessage(String topicName) {
<<<<<<< HEAD
        TopicMetadata topicMetadata = new TopicMetadata(topicName);
        MessageEntity head = messageContainer.computeIfAbsent(topicMetadata, k -> new MessageQueue()).getHead();
        if (head == null) {
            return null;
        }
        return head.getMessage();
=======
        return null;
>>>>>>> upstream/master
    }

    /**
     * Get the message by offset
     *
     * @param topicName topic name
     * @param offset    offset
     * @return CloudEvent
     */
    public CloudEvent getMessage(String topicName, long offset) {
<<<<<<< HEAD
        TopicMetadata topicMetadata = new TopicMetadata(topicName);
        MessageEntity messageEntity = messageContainer.computeIfAbsent(topicMetadata, k -> new MessageQueue()).getByOffset(offset);
        if (messageEntity == null) {
            return null;
        }
        return messageEntity.getMessage();
    }

    private void startHistoryMessageCleanTask() {
        HistoryMessageClear historyMessageClear = new HistoryMessageClear(messageContainer);
        Thread thread = new Thread(new HistoryMessageClearTask(historyMessageClear));
        thread.setDaemon(true);
        thread.setName("StandaloneBroker-HistoryMessageCleanTask");
        thread.start();
    }
=======
        return null;
    }

>>>>>>> upstream/master

    public boolean checkTopicExist(String topicName) {
        return messageContainer.containsKey(new TopicMetadata(topicName));
    }

    /**
     * if the topic does not exist, create the topic
     *
     * @param topicName topicName
<<<<<<< HEAD
     * @return messageQueue and offset
     */
    public Pair<MessageQueue, AtomicLong> createTopicIfAbsent(String topicName) {
        TopicMetadata topicMetadata = new TopicMetadata(topicName);
        MessageQueue messageQueue = messageContainer.computeIfAbsent(topicMetadata, k -> new MessageQueue());
        AtomicLong offset = offsetMap.computeIfAbsent(topicMetadata, k -> new AtomicLong());
        return Pair.of(messageQueue, offset);
=======
     * @return Channel
     */
    public Channel createTopicIfAbsent(String topicName) {
        return createTopic(topicName);
>>>>>>> upstream/master
    }

    /**
     * if the topic exists, delete the topic
     *
     * @param topicName topicName
     */
    public void deleteTopicIfExist(String topicName) {
        TopicMetadata topicMetadata = new TopicMetadata(topicName);
<<<<<<< HEAD
        messageContainer.remove(topicMetadata);
    }

    public void updateOffset(TopicMetadata topicMetadata, long offset) {
        offsetMap.computeIfPresent(topicMetadata, (k, v) -> {
            v.set(offset);
            return v;
        });
    }

    private static class StandaloneBrokerInstanceHolder {

        private static final StandaloneBroker instance = new StandaloneBroker();
    }
}
=======
        Channel channel = createTopicIfAbsent(topicName);
        channel.shutdown();
        messageContainer.remove(topicMetadata);
    }

    public void subscribed(String topicName, Subscribe subscribe) {
        TopicMetadata topicMetadata = new TopicMetadata(topicName);
        if (subscribeContainer.containsKey(topicMetadata)) {
            log.warn("the topic:{} already subscribed", topicName);
            return;
        }
        Channel channel = getMessageContainer().get(topicMetadata);
        if (channel == null) {
            log.warn("the topic:{} is not created", topicName);
            return;
        }
        channel.setEventHandler(subscribe);
        channel.start();
        subscribeContainer.put(topicMetadata, subscribe);
    }


    private static class StandaloneBrokerInstanceHolder {

        private static final StandaloneBroker INSTANCE = new StandaloneBroker();
    }
}
>>>>>>> upstream/master
