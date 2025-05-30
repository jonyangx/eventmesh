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

import static org.apache.eventmesh.common.Constants.HTTP;

import org.apache.eventmesh.api.meta.dto.EventMeshRegisterInfo;
import org.apache.eventmesh.api.meta.dto.EventMeshUnRegisterInfo;
import org.apache.eventmesh.common.exception.EventMeshException;
import org.apache.eventmesh.common.protocol.http.common.RequestCode;
import org.apache.eventmesh.common.utils.IPUtils;
<<<<<<< HEAD
import org.apache.eventmesh.common.utils.LogUtils;
=======
>>>>>>> upstream/master
import org.apache.eventmesh.metrics.api.MetricsPluginFactory;
import org.apache.eventmesh.metrics.api.MetricsRegistry;
import org.apache.eventmesh.runtime.acl.Acl;
import org.apache.eventmesh.runtime.configuration.EventMeshHTTPConfiguration;
import org.apache.eventmesh.runtime.constants.EventMeshConstants;
import org.apache.eventmesh.runtime.core.consumer.SubscriptionManager;
import org.apache.eventmesh.runtime.core.protocol.http.consumer.ConsumerManager;
<<<<<<< HEAD
import org.apache.eventmesh.runtime.core.protocol.http.processor.AdminMetricsProcessor;
=======
>>>>>>> upstream/master
import org.apache.eventmesh.runtime.core.protocol.http.processor.BatchSendMessageProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.BatchSendMessageV2Processor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.CreateTopicProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.DeleteTopicProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.HandlerService;
import org.apache.eventmesh.runtime.core.protocol.http.processor.HeartBeatProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.LocalSubscribeEventProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.LocalUnSubscribeEventProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.QuerySubscriptionProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.RemoteSubscribeEventProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.RemoteUnSubscribeEventProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.ReplyMessageProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.SendAsyncEventProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.SendAsyncMessageProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.SendAsyncRemoteEventProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.SendSyncMessageProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.SubscribeProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.processor.UnSubscribeProcessor;
<<<<<<< HEAD
import org.apache.eventmesh.runtime.core.protocol.http.processor.WebHookProcessor;
import org.apache.eventmesh.runtime.core.protocol.http.producer.ProducerManager;
import org.apache.eventmesh.runtime.core.protocol.http.push.HTTPClientPool;
import org.apache.eventmesh.runtime.core.protocol.http.retry.HttpRetryer;
import org.apache.eventmesh.runtime.meta.MetaStorage;
import org.apache.eventmesh.runtime.metrics.http.HTTPMetricsServer;
import org.apache.eventmesh.webhook.receive.WebHookController;
=======
import org.apache.eventmesh.runtime.core.protocol.http.push.HTTPClientPool;
import org.apache.eventmesh.runtime.core.protocol.http.retry.HttpRetryer;
import org.apache.eventmesh.runtime.core.protocol.producer.ProducerManager;
import org.apache.eventmesh.runtime.meta.MetaStorage;
import org.apache.eventmesh.runtime.metrics.http.EventMeshHttpMetricsManager;
>>>>>>> upstream/master

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
<<<<<<< HEAD
import java.util.concurrent.ThreadPoolExecutor;
=======
>>>>>>> upstream/master

import org.assertj.core.util.Lists;

import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.RateLimiter;

<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;

=======
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


>>>>>>> upstream/master
/**
 * Add multiple managers to the underlying server
 */
@Slf4j
<<<<<<< HEAD
=======
@Getter
>>>>>>> upstream/master
public class EventMeshHTTPServer extends AbstractHTTPServer {

    private final EventMeshServer eventMeshServer;
    private final EventMeshHTTPConfiguration eventMeshHttpConfiguration;

    private final MetaStorage metaStorage;
<<<<<<< HEAD
    private final Acl acl;
    private final EventBus eventBus = new EventBus();

    private ConsumerManager consumerManager;
    private ProducerManager producerManager;
    private SubscriptionManager subscriptionManager;

    private HttpRetryer httpRetryer;

    private transient RateLimiter msgRateLimiter;
    private transient RateLimiter batchRateLimiter;

    private final transient HTTPClientPool httpClientPool = new HTTPClientPool(10);

    public EventMeshHTTPServer(final EventMeshServer eventMeshServer, final EventMeshHTTPConfiguration eventMeshHttpConfiguration) {

=======

    private final Acl acl;
    private final EventBus eventBus = new EventBus();
    private final transient HTTPClientPool httpClientPool = new HTTPClientPool(10);
    private ConsumerManager consumerManager;
    private ProducerManager producerManager;
    private SubscriptionManager subscriptionManager;
    private FilterEngine filterEngine;
    private TransformerEngine transformerEngine;
    private HttpRetryer httpRetryer;
    private transient RateLimiter msgRateLimiter;
    private transient RateLimiter batchRateLimiter;

    public EventMeshHTTPServer(final EventMeshServer eventMeshServer, final EventMeshHTTPConfiguration eventMeshHttpConfiguration) {
>>>>>>> upstream/master
        super(eventMeshHttpConfiguration.getHttpServerPort(),
            eventMeshHttpConfiguration.isEventMeshServerUseTls(),
            eventMeshHttpConfiguration);
        this.eventMeshServer = eventMeshServer;
        this.eventMeshHttpConfiguration = eventMeshHttpConfiguration;
        this.metaStorage = eventMeshServer.getMetaStorage();
        this.acl = eventMeshServer.getAcl();
<<<<<<< HEAD

    }

    public void init() throws Exception {
        LogUtils.info(log, "==================EventMeshHTTPServer Initialing==================");
=======
    }

    public void init() throws Exception {
        log.info("==================EventMeshHTTPServer Initialing==================");
>>>>>>> upstream/master
        super.init();

        msgRateLimiter = RateLimiter.create(eventMeshHttpConfiguration.getEventMeshHttpMsgReqNumPerSecond());
        batchRateLimiter = RateLimiter.create(eventMeshHttpConfiguration.getEventMeshBatchMsgRequestNumPerSecond());

        // The MetricsRegistry is singleton, so we can use factory method to get.
        final List<MetricsRegistry> metricsRegistries = Lists.newArrayList();
        Optional.ofNullable(eventMeshHttpConfiguration.getEventMeshMetricsPluginType()).ifPresent(
            metricsPlugins -> metricsPlugins.forEach(
                pluginType -> metricsRegistries.add(MetricsPluginFactory.getMetricsRegistry(pluginType))));

        httpRetryer = new HttpRetryer(this);

<<<<<<< HEAD
        super.setMetrics(new HTTPMetricsServer(this, metricsRegistries));
=======
        super.setEventMeshHttpMetricsManager(new EventMeshHttpMetricsManager(this, metricsRegistries));
>>>>>>> upstream/master
        subscriptionManager = new SubscriptionManager(eventMeshHttpConfiguration.isEventMeshServerMetaStorageEnable(), metaStorage);

        consumerManager = new ConsumerManager(this);
        consumerManager.init();

        producerManager = new ProducerManager(this);
        producerManager.init();

<<<<<<< HEAD
        super.setHandlerService(new HandlerService());
        super.getHandlerService().setMetrics(this.getMetrics());
=======
        filterEngine = new FilterEngine(metaStorage, producerManager, consumerManager);

        transformerEngine = new TransformerEngine(metaStorage, producerManager, consumerManager);

        super.setHandlerService(new HandlerService());
        super.getHandlerService().setMetrics(this.getEventMeshHttpMetricsManager());
>>>>>>> upstream/master

        // get the trace-plugin
        if (StringUtils.isNotEmpty(eventMeshHttpConfiguration.getEventMeshTracePluginType())
            && eventMeshHttpConfiguration.isEventMeshServerTraceEnable()) {
            super.setUseTrace(eventMeshHttpConfiguration.isEventMeshServerTraceEnable());
        }
        super.getHandlerService().setHttpTrace(new HTTPTrace(eventMeshHttpConfiguration.isEventMeshServerTraceEnable()));

        registerHTTPRequestProcessor();

<<<<<<< HEAD
        LogUtils.info(log, "==================EventMeshHTTPServer initialized==================");
=======
        log.info("==================EventMeshHTTPServer initialized==================");
>>>>>>> upstream/master
    }

    @Override
    public void start() throws Exception {
        super.start();
<<<<<<< HEAD
        this.getMetrics().start();
=======
        this.getEventMeshHttpMetricsManager().start();
>>>>>>> upstream/master

        consumerManager.start();
        producerManager.start();
        httpRetryer.start();
<<<<<<< HEAD
=======
        // filterEngine depend on metaStorage
        if (metaStorage.getStarted().get()) {
            filterEngine.start();
        }
>>>>>>> upstream/master

        if (eventMeshHttpConfiguration.isEventMeshServerMetaStorageEnable()) {
            this.register();
        }
<<<<<<< HEAD
        LogUtils.info(log, "==================EventMeshHTTPServer started==================");
=======
        log.info("==================EventMeshHTTPServer started==================");
>>>>>>> upstream/master
    }

    @Override
    public void shutdown() throws Exception {

        super.shutdown();

<<<<<<< HEAD
        this.getMetrics().shutdown();
=======
        this.getEventMeshHttpMetricsManager().shutdown();

        filterEngine.shutdown();

        transformerEngine.shutdown();
>>>>>>> upstream/master

        consumerManager.shutdown();

        httpClientPool.shutdown();

        producerManager.shutdown();

        httpRetryer.shutdown();

        if (eventMeshHttpConfiguration.isEventMeshServerMetaStorageEnable()) {
            this.unRegister();
        }
<<<<<<< HEAD
        LogUtils.info(log, "==================EventMeshHTTPServer shutdown==================");
=======
        log.info("==================EventMeshHTTPServer shutdown==================");
>>>>>>> upstream/master
    }

    /**
     * Related to the registry module
     */
    public boolean register() {
        boolean registerResult = false;
        try {
            final String endPoints = IPUtils.getLocalAddress()
                + EventMeshConstants.IP_PORT_SEPARATOR + eventMeshHttpConfiguration.getHttpServerPort();
            final EventMeshRegisterInfo eventMeshRegisterInfo = new EventMeshRegisterInfo();
            eventMeshRegisterInfo.setEventMeshClusterName(eventMeshHttpConfiguration.getEventMeshCluster());
            eventMeshRegisterInfo.setEventMeshName(eventMeshHttpConfiguration.getEventMeshName()
                + "-" + HTTP);
            eventMeshRegisterInfo.setEndPoint(endPoints);
            eventMeshRegisterInfo.setProtocolType(HTTP);
            registerResult = metaStorage.register(eventMeshRegisterInfo);
        } catch (Exception e) {
            log.error("eventMesh register to registry failed", e);
        }

        return registerResult;
    }

    /**
     * Related to the registry module
     */
    private void unRegister() {
        final String endPoints = IPUtils.getLocalAddress()
            + EventMeshConstants.IP_PORT_SEPARATOR + eventMeshHttpConfiguration.getHttpServerPort();
        final EventMeshUnRegisterInfo eventMeshUnRegisterInfo = new EventMeshUnRegisterInfo();
        eventMeshUnRegisterInfo.setEventMeshClusterName(eventMeshHttpConfiguration.getEventMeshCluster());
        eventMeshUnRegisterInfo.setEventMeshName(eventMeshHttpConfiguration.getEventMeshName());
        eventMeshUnRegisterInfo.setEndPoint(endPoints);
        eventMeshUnRegisterInfo.setProtocolType(HTTP);
        final boolean registerResult = metaStorage.unRegister(eventMeshUnRegisterInfo);
        if (!registerResult) {
            throw new EventMeshException("eventMesh fail to unRegister");
        }
    }

    private void registerHTTPRequestProcessor() throws Exception {
<<<<<<< HEAD
        HTTPThreadPoolGroup httpThreadPoolGroup = super.getHttpThreadPoolGroup();

        ThreadPoolExecutor batchMsgExecutor = httpThreadPoolGroup.getBatchMsgExecutor();
        final BatchSendMessageProcessor batchSendMessageProcessor = new BatchSendMessageProcessor(this);
        registerProcessor(RequestCode.MSG_BATCH_SEND.getRequestCode(), batchSendMessageProcessor, batchMsgExecutor);

        final BatchSendMessageV2Processor batchSendMessageV2Processor = new BatchSendMessageV2Processor(this);
        registerProcessor(RequestCode.MSG_BATCH_SEND_V2.getRequestCode(), batchSendMessageV2Processor,
            batchMsgExecutor);

        ThreadPoolExecutor sendMsgExecutor = httpThreadPoolGroup.getSendMsgExecutor();
        final SendSyncMessageProcessor sendSyncMessageProcessor = new SendSyncMessageProcessor(this);
        registerProcessor(RequestCode.MSG_SEND_SYNC.getRequestCode(), sendSyncMessageProcessor, sendMsgExecutor);

        final SendAsyncMessageProcessor sendAsyncMessageProcessor = new SendAsyncMessageProcessor(this);
        registerProcessor(RequestCode.MSG_SEND_ASYNC.getRequestCode(), sendAsyncMessageProcessor, sendMsgExecutor);

        final SendAsyncEventProcessor sendAsyncEventProcessor = new SendAsyncEventProcessor(this);
        this.getHandlerService().register(sendAsyncEventProcessor, sendMsgExecutor);

        ThreadPoolExecutor remoteMsgExecutor = httpThreadPoolGroup.getRemoteMsgExecutor();
        final SendAsyncRemoteEventProcessor sendAsyncRemoteEventProcessor = new SendAsyncRemoteEventProcessor(this);
        this.getHandlerService().register(sendAsyncRemoteEventProcessor, remoteMsgExecutor);

        ThreadPoolExecutor runtimeAdminExecutor = httpThreadPoolGroup.getRuntimeAdminExecutor();
        final AdminMetricsProcessor adminMetricsProcessor = new AdminMetricsProcessor(this);
        registerProcessor(RequestCode.ADMIN_METRICS.getRequestCode(), adminMetricsProcessor, runtimeAdminExecutor);

        ThreadPoolExecutor clientManageExecutor = httpThreadPoolGroup.getClientManageExecutor();
        final HeartBeatProcessor heartProcessor = new HeartBeatProcessor(this);
        registerProcessor(RequestCode.HEARTBEAT.getRequestCode(), heartProcessor, clientManageExecutor);

        final SubscribeProcessor subscribeProcessor = new SubscribeProcessor(this);
        registerProcessor(RequestCode.SUBSCRIBE.getRequestCode(), subscribeProcessor, clientManageExecutor);

        final LocalSubscribeEventProcessor localSubscribeEventProcessor = new LocalSubscribeEventProcessor(this);
        this.getHandlerService().register(localSubscribeEventProcessor, clientManageExecutor);

        final RemoteSubscribeEventProcessor remoteSubscribeEventProcessor = new RemoteSubscribeEventProcessor(this);
        this.getHandlerService().register(remoteSubscribeEventProcessor, clientManageExecutor);

        final UnSubscribeProcessor unSubscribeProcessor = new UnSubscribeProcessor(this);
        registerProcessor(RequestCode.UNSUBSCRIBE.getRequestCode(), unSubscribeProcessor, clientManageExecutor);

        final LocalUnSubscribeEventProcessor localUnSubscribeEventProcessor = new LocalUnSubscribeEventProcessor(this);
        this.getHandlerService().register(localUnSubscribeEventProcessor, clientManageExecutor);

        final RemoteUnSubscribeEventProcessor remoteUnSubscribeEventProcessor = new RemoteUnSubscribeEventProcessor(this);
        this.getHandlerService().register(remoteUnSubscribeEventProcessor, clientManageExecutor);

        ThreadPoolExecutor replyMsgExecutor = httpThreadPoolGroup.getReplyMsgExecutor();
        final ReplyMessageProcessor replyMessageProcessor = new ReplyMessageProcessor(this);
        registerProcessor(RequestCode.REPLY_MESSAGE.getRequestCode(), replyMessageProcessor, replyMsgExecutor);

        final CreateTopicProcessor createTopicProcessor = new CreateTopicProcessor(this);
        this.getHandlerService().register(createTopicProcessor, clientManageExecutor);

        final DeleteTopicProcessor deleteTopicProcessor = new DeleteTopicProcessor(this);
        this.getHandlerService().register(deleteTopicProcessor, clientManageExecutor);

        final QuerySubscriptionProcessor querySubscriptionProcessor = new QuerySubscriptionProcessor(this);
        this.getHandlerService().register(querySubscriptionProcessor, clientManageExecutor);

        registerWebhook();
    }

    private void registerWebhook() throws Exception {
        final WebHookProcessor webHookProcessor = new WebHookProcessor();
        final WebHookController webHookController = new WebHookController();

        webHookController.init();
        webHookProcessor.setWebHookController(webHookController);

        this.getHandlerService().register(webHookProcessor, super.getHttpThreadPoolGroup().getWebhookExecutor());
    }

    public SubscriptionManager getSubscriptionManager() {
        return subscriptionManager;
    }

    public ConsumerManager getConsumerManager() {
        return consumerManager;
    }

    public ProducerManager getProducerManager() {
        return producerManager;
    }

    public EventMeshHTTPConfiguration getEventMeshHttpConfiguration() {
        return eventMeshHttpConfiguration;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public HttpRetryer getHttpRetryer() {
        return httpRetryer;
    }

    public Acl getAcl() {
        return acl;
    }

    public EventMeshServer getEventMeshServer() {
        return eventMeshServer;
    }

    public RateLimiter getMsgRateLimiter() {
        return msgRateLimiter;
    }

    public RateLimiter getBatchRateLimiter() {
        return batchRateLimiter;
    }

    public MetaStorage getMetaStorage() {
        return metaStorage;
    }

    public HTTPClientPool getHttpClientPool() {
        return httpClientPool;
    }
}
=======
        final BatchSendMessageProcessor batchSendMessageProcessor = new BatchSendMessageProcessor(this);
        registerProcessor(RequestCode.MSG_BATCH_SEND.getRequestCode(), batchSendMessageProcessor);

        final BatchSendMessageV2Processor batchSendMessageV2Processor = new BatchSendMessageV2Processor(this);
        registerProcessor(RequestCode.MSG_BATCH_SEND_V2.getRequestCode(), batchSendMessageV2Processor);

        final SendSyncMessageProcessor sendSyncMessageProcessor = new SendSyncMessageProcessor(this);
        registerProcessor(RequestCode.MSG_SEND_SYNC.getRequestCode(), sendSyncMessageProcessor);

        final SendAsyncMessageProcessor sendAsyncMessageProcessor = new SendAsyncMessageProcessor(this);
        registerProcessor(RequestCode.MSG_SEND_ASYNC.getRequestCode(), sendAsyncMessageProcessor);

        final SendAsyncEventProcessor sendAsyncEventProcessor = new SendAsyncEventProcessor(this);
        this.getHandlerService().register(sendAsyncEventProcessor);

        final SendAsyncRemoteEventProcessor sendAsyncRemoteEventProcessor = new SendAsyncRemoteEventProcessor(this);
        this.getHandlerService().register(sendAsyncRemoteEventProcessor);

        final HeartBeatProcessor heartProcessor = new HeartBeatProcessor(this);
        registerProcessor(RequestCode.HEARTBEAT.getRequestCode(), heartProcessor);

        final SubscribeProcessor subscribeProcessor = new SubscribeProcessor(this);
        registerProcessor(RequestCode.SUBSCRIBE.getRequestCode(), subscribeProcessor);

        final LocalSubscribeEventProcessor localSubscribeEventProcessor = new LocalSubscribeEventProcessor(this);
        this.getHandlerService().register(localSubscribeEventProcessor);

        final RemoteSubscribeEventProcessor remoteSubscribeEventProcessor = new RemoteSubscribeEventProcessor(this);
        this.getHandlerService().register(remoteSubscribeEventProcessor);

        final UnSubscribeProcessor unSubscribeProcessor = new UnSubscribeProcessor(this);
        registerProcessor(RequestCode.UNSUBSCRIBE.getRequestCode(), unSubscribeProcessor);

        final LocalUnSubscribeEventProcessor localUnSubscribeEventProcessor = new LocalUnSubscribeEventProcessor(this);
        this.getHandlerService().register(localUnSubscribeEventProcessor);

        final RemoteUnSubscribeEventProcessor remoteUnSubscribeEventProcessor = new RemoteUnSubscribeEventProcessor(this);
        this.getHandlerService().register(remoteUnSubscribeEventProcessor);

        final ReplyMessageProcessor replyMessageProcessor = new ReplyMessageProcessor(this);
        registerProcessor(RequestCode.REPLY_MESSAGE.getRequestCode(), replyMessageProcessor);

        final CreateTopicProcessor createTopicProcessor = new CreateTopicProcessor(this);
        this.getHandlerService().register(createTopicProcessor);

        final DeleteTopicProcessor deleteTopicProcessor = new DeleteTopicProcessor(this);
        this.getHandlerService().register(deleteTopicProcessor);

        final QuerySubscriptionProcessor querySubscriptionProcessor = new QuerySubscriptionProcessor(this);
        this.getHandlerService().register(querySubscriptionProcessor);
    }
}
>>>>>>> upstream/master
