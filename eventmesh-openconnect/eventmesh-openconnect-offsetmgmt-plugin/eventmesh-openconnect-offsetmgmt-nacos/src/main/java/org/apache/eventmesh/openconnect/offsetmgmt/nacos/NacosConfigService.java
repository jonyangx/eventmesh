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

package org.apache.eventmesh.openconnect.offsetmgmt.nacos;

<<<<<<< HEAD
import org.apache.eventmesh.openconnect.offsetmgmt.api.config.OffsetStorageConfig;
import org.apache.eventmesh.openconnect.offsetmgmt.api.data.RecordOffset;
import org.apache.eventmesh.openconnect.offsetmgmt.api.storage.ConnectorRecordPartition;
=======
import org.apache.eventmesh.common.config.connector.offset.OffsetStorageConfig;
import org.apache.eventmesh.common.remote.offset.RecordOffset;
import org.apache.eventmesh.common.remote.offset.RecordPartition;
>>>>>>> upstream/master
import org.apache.eventmesh.openconnect.offsetmgmt.api.storage.KeyValueStore;
import org.apache.eventmesh.openconnect.offsetmgmt.api.storage.MemoryBasedKeyValueStore;
import org.apache.eventmesh.openconnect.offsetmgmt.api.storage.OffsetManagementService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NacosConfigService implements OffsetManagementService {

    @Getter
    private String serverAddr;

    @Getter
    private String dataId;

    @Getter
    private String group;

    private ConfigService configService;

    private Listener listener;

<<<<<<< HEAD
    public KeyValueStore<ConnectorRecordPartition, RecordOffset> positionStore;
=======
    public KeyValueStore<RecordPartition, RecordOffset> positionStore;
>>>>>>> upstream/master

    @Override
    public void start() {
        try {
            configService.addListener(dataId, group, listener);
        } catch (NacosException e) {
            log.error("nacos start error", e);
        }
    }

    // merge the updated connectorRecord & recordOffset to memory store
<<<<<<< HEAD
    public void mergeOffset(ConnectorRecordPartition connectorRecordPartition, RecordOffset recordOffset) {
        if (null == connectorRecordPartition || connectorRecordPartition.getPartition().isEmpty()) {
            return;
        }
        if (positionStore.getKVMap().containsKey(connectorRecordPartition)) {
            RecordOffset existedOffset = positionStore.getKVMap().get(connectorRecordPartition);
            // update
            if (!recordOffset.equals(existedOffset)) {
                positionStore.put(connectorRecordPartition, recordOffset);
            }
        } else {
            // add new position
            positionStore.put(connectorRecordPartition, recordOffset);
=======
    public void mergeOffset(RecordPartition recordPartition, RecordOffset recordOffset) {
        if (recordPartition == null) {
            return;
        }
        if (positionStore.getKVMap().containsKey(recordPartition)) {
            RecordOffset existedOffset = positionStore.getKVMap().get(recordPartition);
            // update
            if (!recordOffset.equals(existedOffset)) {
                positionStore.put(recordPartition, recordOffset);
            }
        } else {
            // add new position
            positionStore.put(recordPartition, recordOffset);
>>>>>>> upstream/master
        }
    }

    @Override
    public void stop() {
        configService.removeListener(dataId, group, listener);
    }

    @Override
    public void configure(OffsetStorageConfig config) {

    }

    // only file based storage need to imply
    @Override
    public void persist() {

    }

    @Override
    public void load() {

    }

    @Override
    public void synchronize() {
        try {
<<<<<<< HEAD
            Map<ConnectorRecordPartition, RecordOffset> recordMap = positionStore.getKVMap();

            List<Map<String, Object>> recordToSyncList = new ArrayList<>();
            for (Map.Entry<ConnectorRecordPartition, RecordOffset> entry : recordMap.entrySet()) {
                Map<String, Object> synchronizeMap = new HashMap<>();
                synchronizeMap.put("connectorRecordPartition", entry.getKey());
=======
            Map<RecordPartition, RecordOffset> recordMap = positionStore.getKVMap();

            List<Map<String, Object>> recordToSyncList = new ArrayList<>();
            for (Map.Entry<RecordPartition, RecordOffset> entry : recordMap.entrySet()) {
                Map<String, Object> synchronizeMap = new HashMap<>();
                synchronizeMap.put("recordPartition", entry.getKey());
>>>>>>> upstream/master
                synchronizeMap.put("recordOffset", entry.getValue());
                recordToSyncList.add(synchronizeMap);
            }
            log.info("start publish config: dataId={}|group={}|value={}", dataId, group, recordToSyncList);
            configService.publishConfig(dataId, group, JacksonUtils.toJson(recordToSyncList));
        } catch (NacosException e) {
            throw new RuntimeException("Nacos Service publish config error", e);
        }
    }

    @Override
<<<<<<< HEAD
    public Map<ConnectorRecordPartition, RecordOffset> getPositionMap() {
        // get from memory storage first
        if (positionStore.getKVMap() == null || positionStore.getKVMap().isEmpty()) {
            try {
                Map<ConnectorRecordPartition, RecordOffset> configMap = JacksonUtils.toObj(configService.getConfig(dataId, group, 5000L),
                    new TypeReference<Map<ConnectorRecordPartition, RecordOffset>>() {
                    });
=======
    public Map<RecordPartition, RecordOffset> getPositionMap() {
        // get from memory storage first
        if (positionStore.getKVMap() == null || positionStore.getKVMap().isEmpty()) {
            try {
                Map<RecordPartition, RecordOffset> configMap = JacksonUtils.toObj(configService.getConfig(dataId, group, 5000L),
                    new TypeReference<Map<RecordPartition, RecordOffset>>() {
                    });
                positionStore.putAll(configMap);
>>>>>>> upstream/master
                log.info("nacos position map {}", configMap);
                return configMap;
            } catch (NacosException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("memory position map {}", positionStore.getKVMap());
        return positionStore.getKVMap();
    }

    @Override
<<<<<<< HEAD
    public RecordOffset getPosition(ConnectorRecordPartition partition) {
        // get from memory storage first
        if (positionStore.get(partition) == null) {
            try {
                Map<ConnectorRecordPartition, RecordOffset> recordMap = JacksonUtils.toObj(configService.getConfig(dataId, group, 5000L),
                    new TypeReference<Map<ConnectorRecordPartition, RecordOffset>>() {
=======
    public RecordOffset getPosition(RecordPartition partition) {
        // get from memory storage first
        if (positionStore.get(partition) == null) {
            try {
                Map<RecordPartition, RecordOffset> recordMap = JacksonUtils.toObj(configService.getConfig(dataId, group, 5000L),
                    new TypeReference<Map<RecordPartition, RecordOffset>>() {
>>>>>>> upstream/master
                    });
                log.info("nacos record position {}", recordMap.get(partition));
                return recordMap.get(partition);
            } catch (NacosException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("memory record position {}", positionStore.get(partition));
        return positionStore.get(partition);
    }

    @Override
<<<<<<< HEAD
    public void putPosition(Map<ConnectorRecordPartition, RecordOffset> positions) {
=======
    public void putPosition(Map<RecordPartition, RecordOffset> positions) {
>>>>>>> upstream/master
        positionStore.putAll(positions);
    }

    @Override
<<<<<<< HEAD
    public void putPosition(ConnectorRecordPartition partition, RecordOffset position) {
=======
    public void putPosition(RecordPartition partition, RecordOffset position) {
>>>>>>> upstream/master
        positionStore.put(partition, position);
    }

    @Override
<<<<<<< HEAD
    public void removePosition(List<ConnectorRecordPartition> partitions) {
        if (null == partitions) {
            return;
        }
        for (ConnectorRecordPartition partition : partitions) {
=======
    public void removePosition(List<RecordPartition> partitions) {
        if (partitions == null) {
            return;
        }
        for (RecordPartition partition : partitions) {
>>>>>>> upstream/master
            positionStore.remove(partition);
        }
    }

    @Override
    public void initialize(OffsetStorageConfig config) {
        this.serverAddr = config.getOffsetStorageAddr();
        this.dataId = config.getExtensions().get("dataId");
        this.group = config.getExtensions().get("group");
        this.positionStore = new MemoryBasedKeyValueStore<>();
        try {
            configService = NacosFactory.createConfigService(serverAddr);
        } catch (NacosException e) {
            log.error("nacos init error", e);
        }
        this.listener = new Listener() {

            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("nacos config service receive configInfo: {}", configInfo);
                List<Map<String, Object>> recordOffsetList = JacksonUtils.toObj(configInfo,
                    new TypeReference<List<Map<String, Object>>>() {
                    });

                for (Map<String, Object> recordPartitionOffsetMap : recordOffsetList) {
<<<<<<< HEAD
                    ConnectorRecordPartition connectorRecordPartition = JacksonUtils.toObj(
                        JacksonUtils.toJson(recordPartitionOffsetMap.get("connectorRecordPartition")),
                        ConnectorRecordPartition.class);
                    RecordOffset recordOffset = JacksonUtils.toObj(JacksonUtils.toJson(recordPartitionOffsetMap.get("recordOffset")),
                        RecordOffset.class);
                    // update the offset in memory store
                    mergeOffset(connectorRecordPartition, recordOffset);
=======
                    RecordPartition recordPartition = JacksonUtils.toObj(
                        JacksonUtils.toJson(recordPartitionOffsetMap.get("recordPartition")),
                        RecordPartition.class);
                    RecordOffset recordOffset = JacksonUtils.toObj(JacksonUtils.toJson(recordPartitionOffsetMap.get("recordOffset")),
                        RecordOffset.class);
                    // update the offset in memory store
                    mergeOffset(recordPartition, recordOffset);
>>>>>>> upstream/master
                }
            }
        };

    }
}
