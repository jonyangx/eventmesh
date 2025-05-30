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
import java.sql.Types;

>>>>>>> upstream/master
/**
 * see {@link java.sql.SQLType}
 */
public enum SQLType {

<<<<<<< HEAD
    /**
     * Identifies the generic SQL type {@code TINYINT}.
     */
    TINYINT,
    /**
     * Identifies the generic SQL type {@code SMALLINT}.
     */
    SMALLINT,
    /**
     * Identifies the generic SQL type {@code INTEGER}.
     */
    INTEGER,
    /**
     * Identifies the generic SQL type {@code BIGINT}.
     */
    BIGINT,
    /**
     * Identifies the generic SQL type {@code FLOAT}.
     */
    FLOAT,
=======
    BIT(Types.BIT),

    /**
     * Identifies the generic SQL type {@code TINYINT}.
     */
    TINYINT(Types.TINYINT),
    /**
     * Identifies the generic SQL type {@code SMALLINT}.
     */
    SMALLINT(Types.SMALLINT),
    /**
     * Identifies the generic SQL type {@code INTEGER}.
     */
    INTEGER(Types.INTEGER),
    /**
     * Identifies the generic SQL type {@code BIGINT}.
     */
    BIGINT(Types.BIGINT),
    /**
     * Identifies the generic SQL type {@code FLOAT}.
     */
    FLOAT(Types.FLOAT),
>>>>>>> upstream/master

    /**
     * Identifies the generic SQL type {@code DOUBLE}.
     */
<<<<<<< HEAD
    DOUBLE,
=======
    DOUBLE(Types.DOUBLE),
>>>>>>> upstream/master

    /**
     * Identifies the generic SQL type {@code DECIMAL}.
     */
<<<<<<< HEAD
    DECIMAL,
=======
    DECIMAL(Types.DECIMAL),
    NUMERIC(Types.NUMERIC),
    REAL(Types.REAL),
>>>>>>> upstream/master

    /**
     * Identifies the generic SQL type {@code DATE}.
     */
<<<<<<< HEAD
    DATE,
    /**
     * Identifies the generic SQL type {@code TIME}.
     */
    TIME,
    /**
     * Identifies the generic SQL type {@code TIMESTAMP}.
     */
    TIMESTAMP,
    /**
     * Identifies the generic SQL type {@code BINARY}.
     */
    BINARY,
=======
    DATE(Types.DATE),
    /**
     * Identifies the generic SQL type {@code TIME}.
     */
    TIME(Types.TIME),
    /**
     * Identifies the generic SQL type {@code TIMESTAMP}.
     */
    TIMESTAMP(Types.TIMESTAMP),

    TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE),

    /**
     * Identifies the generic SQL type {@code BINARY}.
     */
    BINARY(Types.BINARY),
>>>>>>> upstream/master

    /**
     * Identifies the generic SQL value {@code NULL}.
     */
<<<<<<< HEAD
    NULL,
=======
    NULL(Types.NULL),
>>>>>>> upstream/master

    /**
     * Identifies the generic SQL type {@code ARRAY}.
     */
<<<<<<< HEAD
    ARRAY,
=======
    ARRAY(Types.ARRAY),
>>>>>>> upstream/master

    /**
     * Identifies the generic SQL type {@code BOOLEAN}.
     */
<<<<<<< HEAD
    BOOLEAN,

    /**
     * EventMesh generic SQL type
     */
    ROW,

    MAP,

    STRING
=======
    BOOLEAN(Types.BOOLEAN),

    STRING(Types.VARCHAR);

    private int type;

    SQLType(int type) {
        this.type = type;
    }

    public int ofType() {
        return type;
    }
>>>>>>> upstream/master
}
