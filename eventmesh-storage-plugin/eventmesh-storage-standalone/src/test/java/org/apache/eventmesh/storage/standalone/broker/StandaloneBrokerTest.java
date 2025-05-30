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

<<<<<<< HEAD
import static org.apache.eventmesh.storage.standalone.TestUtils.OFF_SET;
import static org.apache.eventmesh.storage.standalone.TestUtils.TEST_TOPIC;
import static org.apache.eventmesh.storage.standalone.TestUtils.createDefaultCloudEvent;

import org.apache.eventmesh.storage.standalone.broker.model.MessageEntity;

import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.atomic.AtomicLong;
=======
import static org.apache.eventmesh.storage.standalone.TestUtils.TEST_TOPIC;
import static org.apache.eventmesh.storage.standalone.TestUtils.createDefaultCloudEvent;
import static org.apache.eventmesh.storage.standalone.TestUtils.createSubscribe;

import org.apache.eventmesh.storage.standalone.broker.model.MessageEntity;
import org.apache.eventmesh.storage.standalone.broker.task.Subscribe;
>>>>>>> upstream/master

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.cloudevents.CloudEvent;

public class StandaloneBrokerTest {

<<<<<<< HEAD
=======

    public StandaloneBroker getStandaloneBroker() {
        StandaloneBroker instance = StandaloneBroker.getInstance();
        Subscribe subscribe = createSubscribe(instance);
        subscribe.subscribe();
        return instance;
    }

>>>>>>> upstream/master
    @Test
    public void testGetInstance() {
        Assertions.assertNotNull(StandaloneBroker.getInstance());
    }

    @Test
    public void testCreateTopicIfAbsent() {
<<<<<<< HEAD
        StandaloneBroker instance = StandaloneBroker.getInstance();
        Pair<MessageQueue, AtomicLong> pair = instance.createTopicIfAbsent(TEST_TOPIC);
=======
        StandaloneBroker instance = getStandaloneBroker();
        Channel pair = instance.createTopicIfAbsent(TEST_TOPIC);
>>>>>>> upstream/master
        Assertions.assertNotNull(pair);
    }

    @Test
    public void testPutMessage() throws InterruptedException {
<<<<<<< HEAD
        StandaloneBroker instance = StandaloneBroker.getInstance();
=======
        StandaloneBroker instance = getStandaloneBroker();
>>>>>>> upstream/master
        CloudEvent cloudEvent = createDefaultCloudEvent();
        MessageEntity messageEntity = instance.putMessage(TEST_TOPIC, cloudEvent);
        Assertions.assertNotNull(messageEntity);
    }

<<<<<<< HEAD
    @Test
    public void testTakeMessage() throws InterruptedException {
        StandaloneBroker instance = StandaloneBroker.getInstance();
        CloudEvent cloudEvent = createDefaultCloudEvent();
        instance.putMessage(TEST_TOPIC, cloudEvent);
        CloudEvent message = instance.takeMessage(TEST_TOPIC);
        Assertions.assertNotNull(message);
    }

    @Test
    public void testGetMessage() throws InterruptedException {
        StandaloneBroker instance = StandaloneBroker.getInstance();
        CloudEvent cloudEvent = createDefaultCloudEvent();
        instance.putMessage(TEST_TOPIC, cloudEvent);
        CloudEvent cloudEventResult = instance.getMessage(TEST_TOPIC);
        Assertions.assertNotNull(cloudEventResult);
    }

    @Test
    public void testMessageWithOffSet() throws InterruptedException {
        StandaloneBroker instance = StandaloneBroker.getInstance();
        CloudEvent cloudEvent = createDefaultCloudEvent();
        instance.putMessage(TEST_TOPIC, cloudEvent);
        CloudEvent cloudEventResult = instance.getMessage(TEST_TOPIC, OFF_SET);
        Assertions.assertNotNull(cloudEventResult);
    }

    @Test
    public void testCheckTopicExist() throws InterruptedException {
        StandaloneBroker instance = StandaloneBroker.getInstance();
=======

    @Test
    public void testCheckTopicExist() throws InterruptedException {
        StandaloneBroker instance = getStandaloneBroker();
>>>>>>> upstream/master
        CloudEvent cloudEvent = createDefaultCloudEvent();
        instance.putMessage(TEST_TOPIC, cloudEvent);
        boolean exists = instance.checkTopicExist(TEST_TOPIC);
        Assertions.assertTrue(exists);
    }

<<<<<<< HEAD
    @Test
    public void testDeleteTopicIfExist() throws InterruptedException {
        StandaloneBroker instance = StandaloneBroker.getInstance();
        CloudEvent cloudEvent = createDefaultCloudEvent();
        instance.putMessage(TEST_TOPIC, cloudEvent);
        instance.deleteTopicIfExist(TEST_TOPIC);
        boolean exists = instance.checkTopicExist(TEST_TOPIC);
        Assertions.assertFalse(exists);
    }
=======
>>>>>>> upstream/master
}
