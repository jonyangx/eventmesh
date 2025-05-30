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

package org.apache.eventmesh.connector.spring.source.connector;

<<<<<<< HEAD
import org.apache.eventmesh.connector.spring.source.config.SpringSourceConfig;
=======
import static org.mockito.Mockito.doReturn;

import org.apache.eventmesh.common.config.connector.spring.SpringSourceConfig;
>>>>>>> upstream/master
import org.apache.eventmesh.openconnect.offsetmgmt.api.data.ConnectRecord;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
<<<<<<< HEAD
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
=======
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
>>>>>>> upstream/master

@ExtendWith(MockitoExtension.class)
public class SpringSourceConnectorTest {

<<<<<<< HEAD
    @Spy
=======
>>>>>>> upstream/master
    private SpringSourceConnector connector;

    @Test
    public void testSpringSourceConnector() throws Exception {
<<<<<<< HEAD
=======
        ConfigurableApplicationContext context = Mockito.mock(ConfigurableApplicationContext.class);
        ConfigurableEnvironment environment = Mockito.mock(ConfigurableEnvironment.class);
        doReturn(new MutablePropertySources()).when(environment).getPropertySources();
        doReturn(environment).when(context).getEnvironment();
        connector = new SpringSourceConnector();
        connector.setApplicationContext(context);
>>>>>>> upstream/master
        SpringSourceConfig sourceConfig = new SpringSourceConfig();
        connector.init(sourceConfig);
        connector.start();
        final int count = 5;
        final String message = "testMessage";
        writeMockedRecords(count, message);
        List<ConnectRecord> connectRecords = connector.poll();
        Assertions.assertEquals(count, connectRecords.size());
        for (int i = 0; i < connectRecords.size(); i++) {
            Object actualMessage = String.valueOf(connectRecords.get(i).getData());
            String expectedMessage = "testMessage" + i;
            Assertions.assertEquals(expectedMessage, actualMessage);
        }
    }

<<<<<<< HEAD
    private void writeMockedRecords(int count, String message) throws Exception {
=======
    private void writeMockedRecords(int count, String message) {
>>>>>>> upstream/master
        for (int i = 0; i < count; i++) {
            connector.send(message + i);
        }
    }
}
