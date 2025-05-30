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

package org.apache.eventmesh.connector.jdbc.table.type;

<<<<<<< HEAD
=======
import org.apache.eventmesh.connector.jdbc.type.eventmesh.BooleanEventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.BytesEventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.DateEventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.DateTimeEventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.DecimalEventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.Float32EventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.Float64EventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.Int16EventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.Int32EventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.Int64EventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.Int8EventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.StringEventMeshDataType;
import org.apache.eventmesh.connector.jdbc.type.eventmesh.TimeEventMeshDataType;

>>>>>>> upstream/master
import java.util.HashMap;
import java.util.Map;

public final class EventMeshTypeNameConverter {

<<<<<<< HEAD
    private static Map<EventMeshDataType<?>, String> PRIMITIVE_TYPE_MAP = new HashMap<>(32);

    static {
        PRIMITIVE_TYPE_MAP.put(PrimitiveType.STRING_TYPE, "string");
        PRIMITIVE_TYPE_MAP.put(PrimitiveType.BOOLEAN_TYPE, "bool");
        PRIMITIVE_TYPE_MAP.put(PrimitiveType.BYTE_TYPE, "byte");
        PRIMITIVE_TYPE_MAP.put(PrimitiveType.SHORT_TYPE, "short");
        PRIMITIVE_TYPE_MAP.put(PrimitiveType.INT_TYPE, "int");
        PRIMITIVE_TYPE_MAP.put(PrimitiveType.LONG_TYPE, "long");
        PRIMITIVE_TYPE_MAP.put(PrimitiveType.FLOAT_TYPE, "float");
        PRIMITIVE_TYPE_MAP.put(PrimitiveType.DOUBLE_TYPE, "double");
        PRIMITIVE_TYPE_MAP.put(PrimitiveType.VOID_TYPE, "void");
        PRIMITIVE_TYPE_MAP.put(CalendarType.LOCAL_DATE_TYPE, "LocalDate");
        PRIMITIVE_TYPE_MAP.put(CalendarType.LOCAL_TIME_TYPE, "LocalTime");
        PRIMITIVE_TYPE_MAP.put(CalendarType.LOCAL_DATE_TIME_TYPE, "LocalDateTime");
        PRIMITIVE_TYPE_MAP.put(PrimitiveByteArrayType.BYTES_TYPE, "bytes");

        PRIMITIVE_TYPE_MAP.put(PrimitiveArrayType.STRING_ARRAY_TYPE, "string-array");
        PRIMITIVE_TYPE_MAP.put(PrimitiveArrayType.BOOLEAN_ARRAY_TYPE, "bool-array");
        PRIMITIVE_TYPE_MAP.put(PrimitiveArrayType.BYTE_ARRAY_TYPE, "byte-array");
        PRIMITIVE_TYPE_MAP.put(PrimitiveArrayType.SHORT_ARRAY_TYPE, "short-array");
        PRIMITIVE_TYPE_MAP.put(PrimitiveArrayType.INT_ARRAY_TYPE, "int-array");
        PRIMITIVE_TYPE_MAP.put(PrimitiveArrayType.LONG_ARRAY_TYPE, "long-array");
        PRIMITIVE_TYPE_MAP.put(PrimitiveArrayType.FLOAT_ARRAY_TYPE, "float-array");
        PRIMITIVE_TYPE_MAP.put(PrimitiveArrayType.DOUBLE_ARRAY_TYPE, "double-array");
    }

    public static String ofName(EventMeshDataType<?> type) {
        String typeName = PRIMITIVE_TYPE_MAP.get(type);
        if (typeName == null && (type instanceof DecimalType)) {
            DecimalType decimalType = (DecimalType) type;
            return String.format("decimal(%s,%s)", decimalType.getScale(), decimalType.getPrecision());
        }
        return typeName;
=======
    private static Map<String, EventMeshDataType> PRIMITIVE_TYPE_MAP = new HashMap<>(32);

    static {
        PRIMITIVE_TYPE_MAP.put(BooleanEventMeshDataType.INSTANCE.getName(), BooleanEventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(Float32EventMeshDataType.INSTANCE.getName(), Float32EventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(Float64EventMeshDataType.INSTANCE.getName(), Float64EventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(Int8EventMeshDataType.INSTANCE.getName(), Int8EventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(Int16EventMeshDataType.INSTANCE.getName(), Int16EventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(Int32EventMeshDataType.INSTANCE.getName(), Int32EventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(Int64EventMeshDataType.INSTANCE.getName(), Int64EventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(StringEventMeshDataType.INSTANCE.getName(), StringEventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(BytesEventMeshDataType.INSTANCE.getName(), BytesEventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(DateEventMeshDataType.INSTANCE.getName(), DateEventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(TimeEventMeshDataType.INSTANCE.getName(), TimeEventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(DateTimeEventMeshDataType.INSTANCE.getName(), DateTimeEventMeshDataType.INSTANCE);
        PRIMITIVE_TYPE_MAP.put(DecimalEventMeshDataType.INSTANCE.getName(), DecimalEventMeshDataType.INSTANCE);
    }

    public static EventMeshDataType ofEventMeshDataType(String dataType) {
        return PRIMITIVE_TYPE_MAP.get(dataType);
>>>>>>> upstream/master
    }

}
