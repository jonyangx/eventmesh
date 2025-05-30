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

package org.apache.eventmesh.runtime.boot;

import org.apache.eventmesh.common.ThreadPoolFactory;
import org.apache.eventmesh.runtime.configuration.EventMeshHTTPConfiguration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

<<<<<<< HEAD
=======
import lombok.Getter;

>>>>>>> upstream/master
public class HTTPThreadPoolGroup implements ThreadPoolGroup {

    private final EventMeshHTTPConfiguration eventMeshHttpConfiguration;

<<<<<<< HEAD
    private ThreadPoolExecutor batchMsgExecutor;
    private ThreadPoolExecutor sendMsgExecutor;
    private ThreadPoolExecutor remoteMsgExecutor;
    private ThreadPoolExecutor replyMsgExecutor;
    private ThreadPoolExecutor pushMsgExecutor;
    private ThreadPoolExecutor clientManageExecutor;
    private ThreadPoolExecutor runtimeAdminExecutor;
    private ThreadPoolExecutor webhookExecutor;
=======
    @Getter
    private ThreadPoolExecutor batchMsgExecutor;
    @Getter
    private ThreadPoolExecutor sendMsgExecutor;
    @Getter
    private ThreadPoolExecutor remoteMsgExecutor;
    @Getter
    private ThreadPoolExecutor replyMsgExecutor;
    @Getter
    private ThreadPoolExecutor pushMsgExecutor;
    @Getter
    private ThreadPoolExecutor clientManageExecutor;
>>>>>>> upstream/master

    public HTTPThreadPoolGroup(EventMeshHTTPConfiguration eventMeshHttpConfiguration) {
        this.eventMeshHttpConfiguration = eventMeshHttpConfiguration;
    }

    @Override
    public void initThreadPool() {

        batchMsgExecutor = ThreadPoolFactory.createThreadPoolExecutor(
            eventMeshHttpConfiguration.getEventMeshServerBatchMsgThreadNum(),
            eventMeshHttpConfiguration.getEventMeshServerBatchMsgThreadNum(),
            new LinkedBlockingQueue<>(eventMeshHttpConfiguration.getEventMeshServerBatchBlockQSize()),
            "eventMesh-batchMsg", true);

        sendMsgExecutor = ThreadPoolFactory.createThreadPoolExecutor(
            eventMeshHttpConfiguration.getEventMeshServerSendMsgThreadNum(),
            eventMeshHttpConfiguration.getEventMeshServerSendMsgThreadNum(),
            new LinkedBlockingQueue<>(eventMeshHttpConfiguration.getEventMeshServerSendMsgBlockQSize()),
            "eventMesh-sendMsg", true);

        remoteMsgExecutor = ThreadPoolFactory.createThreadPoolExecutor(
            eventMeshHttpConfiguration.getEventMeshServerRemoteMsgThreadNum(),
            eventMeshHttpConfiguration.getEventMeshServerRemoteMsgThreadNum(),
            new LinkedBlockingQueue<>(eventMeshHttpConfiguration.getEventMeshServerRemoteMsgBlockQSize()),
            "eventMesh-remoteMsg", true);

        pushMsgExecutor = ThreadPoolFactory.createThreadPoolExecutor(
            eventMeshHttpConfiguration.getEventMeshServerPushMsgThreadNum(),
            eventMeshHttpConfiguration.getEventMeshServerPushMsgThreadNum(),
            new LinkedBlockingQueue<>(eventMeshHttpConfiguration.getEventMeshServerPushMsgBlockQSize()),
            "eventMesh-pushMsg", true);

        clientManageExecutor = ThreadPoolFactory.createThreadPoolExecutor(
            eventMeshHttpConfiguration.getEventMeshServerClientManageThreadNum(),
            eventMeshHttpConfiguration.getEventMeshServerClientManageThreadNum(),
            new LinkedBlockingQueue<>(eventMeshHttpConfiguration.getEventMeshServerClientManageBlockQSize()),
            "eventMesh-clientManage", true);

<<<<<<< HEAD
        // The runtimeAdminExecutor here is for the runtime.admin package and has nothing to do with the eventmesh-admin module.
        runtimeAdminExecutor = ThreadPoolFactory.createThreadPoolExecutor(
            eventMeshHttpConfiguration.getEventMeshServerAdminThreadNum(),
            eventMeshHttpConfiguration.getEventMeshServerAdminThreadNum(),
            new LinkedBlockingQueue<>(50), "eventMesh-runtime-admin", true);

        replyMsgExecutor = ThreadPoolFactory.createThreadPoolExecutor(
            eventMeshHttpConfiguration.getEventMeshServerReplyMsgThreadNum(),
            eventMeshHttpConfiguration.getEventMeshServerReplyMsgThreadNum(),
            new LinkedBlockingQueue<>(100),
            "eventMesh-replyMsg", true);

        webhookExecutor = ThreadPoolFactory.createThreadPoolExecutor(
            eventMeshHttpConfiguration.getEventMeshServerWebhookThreadNum(),
            eventMeshHttpConfiguration.getEventMeshServerWebhookThreadNum(),
            new LinkedBlockingQueue<>(100), "eventMesh-webhook", true);
=======
        replyMsgExecutor = ThreadPoolFactory.createThreadPoolExecutor(
            eventMeshHttpConfiguration.getEventMeshServerReplyMsgThreadNum(),
            eventMeshHttpConfiguration.getEventMeshServerReplyMsgThreadNum(),
            new LinkedBlockingQueue<>(100), "eventMesh-replyMsg", true);
>>>>>>> upstream/master
    }

    @Override
    public void shutdownThreadPool() {
        if (batchMsgExecutor != null) {
            batchMsgExecutor.shutdown();
        }
<<<<<<< HEAD
        if (runtimeAdminExecutor != null) {
            runtimeAdminExecutor.shutdown();
        }
=======
>>>>>>> upstream/master
        if (clientManageExecutor != null) {
            clientManageExecutor.shutdown();
        }
        if (sendMsgExecutor != null) {
            sendMsgExecutor.shutdown();
        }
        if (remoteMsgExecutor != null) {
            remoteMsgExecutor.shutdown();
        }
        if (pushMsgExecutor != null) {
            pushMsgExecutor.shutdown();
        }
        if (replyMsgExecutor != null) {
            replyMsgExecutor.shutdown();
        }
    }
<<<<<<< HEAD

    public ThreadPoolExecutor getBatchMsgExecutor() {
        return batchMsgExecutor;
    }

    public ThreadPoolExecutor getSendMsgExecutor() {
        return sendMsgExecutor;
    }

    public ThreadPoolExecutor getRemoteMsgExecutor() {
        return remoteMsgExecutor;
    }

    public ThreadPoolExecutor getReplyMsgExecutor() {
        return replyMsgExecutor;
    }

    public ThreadPoolExecutor getPushMsgExecutor() {
        return pushMsgExecutor;
    }

    public ThreadPoolExecutor getClientManageExecutor() {
        return clientManageExecutor;
    }

    public ThreadPoolExecutor getRuntimeAdminExecutor() {
        return runtimeAdminExecutor;
    }

    public ThreadPoolExecutor getWebhookExecutor() {
        return webhookExecutor;
    }
=======
>>>>>>> upstream/master
}
