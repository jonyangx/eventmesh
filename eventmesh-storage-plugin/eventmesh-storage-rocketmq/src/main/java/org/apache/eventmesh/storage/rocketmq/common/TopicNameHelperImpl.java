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

<<<<<<<< HEAD:eventmesh-common/src/main/java/org/apache/eventmesh/common/utils/TypeUtils.java
package org.apache.eventmesh.common.utils;

import java.util.HashSet;
import java.util.Set;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TypeUtils {

    public static <T> Set<T> castSet(Object obj, Class<T> clazz) {
        Set<T> result = new HashSet<>();
        if (obj instanceof Set<?>) {
            for (Object o : (Set<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
========
package org.apache.eventmesh.storage.rocketmq.common;

import org.apache.eventmesh.api.TopicNameHelper;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.MixAll;

public class TopicNameHelperImpl implements TopicNameHelper {

    @Override
    public boolean isRetryTopic(String retryTopic) {
        if (StringUtils.isBlank(retryTopic)) {
            return false;
        }
        return retryTopic.startsWith(MixAll.RETRY_GROUP_TOPIC_PREFIX);
>>>>>>>> upstream/master:eventmesh-storage-plugin/eventmesh-storage-rocketmq/src/main/java/org/apache/eventmesh/storage/rocketmq/common/TopicNameHelperImpl.java
    }
}
