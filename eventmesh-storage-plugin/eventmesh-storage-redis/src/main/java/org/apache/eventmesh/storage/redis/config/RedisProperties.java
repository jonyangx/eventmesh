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

package org.apache.eventmesh.storage.redis.config;

import org.apache.eventmesh.common.config.Config;
<<<<<<< HEAD
import org.apache.eventmesh.common.config.ConfigFiled;

import java.util.Properties;
=======
import org.apache.eventmesh.common.config.ConfigField;
>>>>>>> upstream/master

import lombok.Data;

@Data
@Config(prefix = "eventMesh.server.redis", path = "classPath://redis-client.properties")
public class RedisProperties {

    /**
     * The redis server configuration to be used.
     */
<<<<<<< HEAD
    @ConfigFiled(field = "serverType")
=======
    @ConfigField(field = "serverType")
>>>>>>> upstream/master
    private ServerType serverType = ServerType.SINGLE;

    /**
     * The master server name used by Redis Sentinel servers and master change monitoring task.
     */
<<<<<<< HEAD
    @ConfigFiled(field = "serverMasterName")
=======
    @ConfigField(field = "serverMasterName")
>>>>>>> upstream/master
    private String serverMasterName = "master";

    /**
     * The address of the redis server following format -- host1:port1,host2:port2,……
     */
<<<<<<< HEAD
    @ConfigFiled(field = "serverAddress")
    private String serverAddress;
=======
    @ConfigField(field = "serverAddress")
    private String serverAddress = "redis://127.0.0.1:6379";
>>>>>>> upstream/master

    /**
     * The password for redis authentication.
     */
<<<<<<< HEAD
    @ConfigFiled(field = "serverPassword")
=======
    @ConfigField(field = "serverPassword")
>>>>>>> upstream/master
    private String serverPassword;

    /**
     * The redisson options, redisson properties prefix is `eventMesh.server.redis.redisson`
     */
<<<<<<< HEAD
    @ConfigFiled(field = "redisson")
    private Properties redissonProperties;
=======
    @ConfigField(field = "redisson.threads")
    private int redissonThreads = 16;

    @ConfigField(field = "redisson.nettyThreads")
    private int redissonNettyThreads = 32;
>>>>>>> upstream/master

    public enum ServerType {
        SINGLE,
        CLUSTER,
        SENTINEL
    }
}
