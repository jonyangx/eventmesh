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

package org.apache.eventmesh.common.config.convert;

<<<<<<< HEAD
import org.apache.eventmesh.common.config.ConfigFiled;
=======
import org.apache.eventmesh.common.config.ConfigField;
>>>>>>> upstream/master

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**
 * convert convertInfo to obj
 *
 * @param <T> obj type
 */
public interface ConvertValue<T> {

    T convert(ConvertInfo convertInfo);

    /**
     * @return Whether can to process null values
     */
    default boolean canHandleNullValue() {
        return false;
    }

    /**
     * @return The value converter needs
     */
<<<<<<< HEAD
    default Object processFieldValue(ConvertInfo convertInfo, String key, ConfigFiled configFiled) {
=======
    default Object processFieldValue(ConvertInfo convertInfo, String key, ConfigField configField) {
>>>>>>> upstream/master
        Properties properties = convertInfo.getProperties();
        String value = properties.getProperty(key);

        if (Objects.isNull(value)) {
            return null;
        }

        value = value.trim();

<<<<<<< HEAD
        boolean findEnv = configFiled.findEnv();
        String fieldName = configFiled.field();
=======
        boolean findEnv = configField.findEnv();
        String fieldName = configField.field();
>>>>>>> upstream/master

        if (StringUtils.isBlank(value) && !StringUtils.isBlank(fieldName) && findEnv) {
            value = Optional.ofNullable(System.getProperty(fieldName)).orElse(System.getenv(fieldName));
        }

<<<<<<< HEAD
        if (StringUtils.isBlank(value) && configFiled.notEmpty()) {
=======
        if (StringUtils.isBlank(value) && configField.notEmpty()) {
>>>>>>> upstream/master
            throw new RuntimeException(key + " can't be empty!");
        }

        return value;
    }

    class DefaultConverter implements ConvertValue<Object> {

        @Override
        public Object convert(ConvertInfo convertInfo) {
            return null;
        }
    }
}