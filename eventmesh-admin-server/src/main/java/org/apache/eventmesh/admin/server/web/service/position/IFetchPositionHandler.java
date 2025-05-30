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

<<<<<<<< HEAD:eventmesh-common/src/main/java/org/apache/eventmesh/common/protocol/tcp/OPStatus.java
package org.apache.eventmesh.common.protocol.tcp;

public enum OPStatus {

    SUCCESS(0, "success"),
    FAIL(1, "fail"),
    ACL_FAIL(2, "aclFail"),
    TPS_OVERLOAD(3, "tpsOverload");

    OPStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final Integer code;

    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

========
package org.apache.eventmesh.admin.server.web.service.position;

import org.apache.eventmesh.common.protocol.grpc.adminserver.Metadata;
import org.apache.eventmesh.common.remote.offset.RecordPosition;
import org.apache.eventmesh.common.remote.request.FetchPositionRequest;

import java.util.List;

/**
 * IFetchPositionHandler
 */
public interface IFetchPositionHandler {

    List<RecordPosition> handler(FetchPositionRequest request, Metadata metadata);
>>>>>>>> upstream/master:eventmesh-admin-server/src/main/java/org/apache/eventmesh/admin/server/web/service/position/IFetchPositionHandler.java
}
