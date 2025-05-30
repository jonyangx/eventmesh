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

package org.apache.eventmesh.runtime.core.protocol.http.processor;

import org.apache.eventmesh.common.Constants;
import org.apache.eventmesh.common.enums.ConnectionType;
import org.apache.eventmesh.common.protocol.http.HttpEventWrapper;
import org.apache.eventmesh.common.protocol.http.common.EventMeshRetCode;
import org.apache.eventmesh.common.utils.JsonUtils;
<<<<<<< HEAD
import org.apache.eventmesh.common.utils.LogUtils;
=======
>>>>>>> upstream/master
import org.apache.eventmesh.runtime.boot.HTTPTrace;
import org.apache.eventmesh.runtime.boot.HTTPTrace.TraceOperation;
import org.apache.eventmesh.runtime.common.EventMeshTrace;
import org.apache.eventmesh.runtime.constants.EventMeshConstants;
import org.apache.eventmesh.runtime.core.protocol.http.async.AsyncContext;
<<<<<<< HEAD
import org.apache.eventmesh.runtime.metrics.http.HTTPMetricsServer;
=======
import org.apache.eventmesh.runtime.metrics.http.EventMeshHttpMetricsManager;
>>>>>>> upstream/master
import org.apache.eventmesh.runtime.util.HttpResponseUtils;
import org.apache.eventmesh.runtime.util.RemotingHelper;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
<<<<<<< HEAD
=======
import java.util.concurrent.Executor;
>>>>>>> upstream/master
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cloudevents.CloudEvent;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.ReferenceCountUtil;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandlerService {

<<<<<<< HEAD
    private final Logger httpLogger = LoggerFactory.getLogger(EventMeshConstants.PROTOCOL_HTTP);
=======
    private static final Logger HTTP_LOGGER = LoggerFactory.getLogger(EventMeshConstants.PROTOCOL_HTTP);
>>>>>>> upstream/master

    private final Map<String, ProcessorWrapper> httpProcessorMap = new ConcurrentHashMap<>();

    @Setter
<<<<<<< HEAD
    private HTTPMetricsServer metrics;
=======
    private EventMeshHttpMetricsManager metrics;
>>>>>>> upstream/master

    @Setter
    private HTTPTrace httpTrace;

    public DefaultHttpDataFactory defaultHttpDataFactory = new DefaultHttpDataFactory(false);

    public void init() {
        log.info("HandlerService start ");
    }

<<<<<<< HEAD
    public void register(HttpProcessor httpProcessor, ThreadPoolExecutor threadPoolExecutor) {
=======
    public void register(HttpProcessor httpProcessor, Executor threadPoolExecutor) {
>>>>>>> upstream/master
        for (String path : httpProcessor.paths()) {
            this.register(path, httpProcessor, threadPoolExecutor);
        }
    }

<<<<<<< HEAD
    public void register(String path, HttpProcessor httpProcessor, ThreadPoolExecutor threadPoolExecutor) {
=======
    public void register(HttpProcessor httpProcessor) {
        for (String path : httpProcessor.paths()) {
            this.register(path, httpProcessor, httpProcessor.executor());
        }
    }

    public void register(String path, HttpProcessor httpProcessor, Executor threadPoolExecutor) {
>>>>>>> upstream/master

        if (httpProcessorMap.containsKey(path)) {
            throw new RuntimeException(String.format("HandlerService path %s repeat, repeat processor is %s ",
                path, httpProcessor.getClass().getSimpleName()));
        }
        ProcessorWrapper processorWrapper = new ProcessorWrapper();
<<<<<<< HEAD
        processorWrapper.threadPoolExecutor = threadPoolExecutor;
=======
        processorWrapper.executor = threadPoolExecutor;
>>>>>>> upstream/master
        if (httpProcessor instanceof AsyncHttpProcessor) {
            processorWrapper.async = (AsyncHttpProcessor) httpProcessor;
        }
        processorWrapper.httpProcessor = httpProcessor;
        processorWrapper.traceEnabled = httpProcessor.getClass().getAnnotation(EventMeshTrace.class).isEnable();
        httpProcessorMap.put(path, processorWrapper);
        log.info("path is {}  processor name is {}", path, httpProcessor.getClass().getSimpleName());
    }

    public boolean isProcessorWrapper(HttpRequest httpRequest) {
        return Objects.nonNull(this.getProcessorWrapper(httpRequest));
    }

    private ProcessorWrapper getProcessorWrapper(HttpRequest httpRequest) {
        String uri = httpRequest.uri();
        for (Entry<String, ProcessorWrapper> e : httpProcessorMap.entrySet()) {
            if (uri.startsWith(e.getKey())) {
                return e.getValue();
            }
        }
        return null;
    }

    /**
     * @param httpRequest
     */
    public void handler(ChannelHandlerContext ctx, HttpRequest httpRequest, ThreadPoolExecutor asyncContextCompleteHandler) {

        ProcessorWrapper processorWrapper = getProcessorWrapper(httpRequest);
        if (Objects.isNull(processorWrapper)) {
            this.sendResponse(ctx, httpRequest, HttpResponseUtils.createNotFound());
            return;
        }
        TraceOperation traceOperation = httpTrace.getTraceOperation(httpRequest, ctx.channel(), processorWrapper.traceEnabled);
        try {
            HandlerSpecific handlerSpecific = new HandlerSpecific();
            handlerSpecific.request = httpRequest;
            handlerSpecific.ctx = ctx;
            handlerSpecific.traceOperation = traceOperation;
            handlerSpecific.asyncContext = new AsyncContext<>(new HttpEventWrapper(), null, asyncContextCompleteHandler);
<<<<<<< HEAD
            processorWrapper.threadPoolExecutor.execute(handlerSpecific);
=======
            processorWrapper.executor.execute(handlerSpecific);
>>>>>>> upstream/master
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            this.sendResponse(ctx, httpRequest, HttpResponseUtils.createInternalServerError());
        }
    }

    private void sendResponse(ChannelHandlerContext ctx, HttpRequest request, HttpResponse response) {
        this.sendPersistentResponse(ctx, request, response, true);
    }

    /**
     * persistent connection
     */
    private void sendPersistentResponse(ChannelHandlerContext ctx, HttpRequest httpRequest, HttpResponse response, boolean isClose) {
        ReferenceCountUtil.release(httpRequest);
<<<<<<< HEAD
        ctx.writeAndFlush(response).addListener((ChannelFutureListener) f -> {
            if (!f.isSuccess()) {
                httpLogger.warn("send response to [{}] fail, will close this channel",
                    RemotingHelper.parseChannelRemoteAddr(f.channel()));
                if (isClose) {
                    f.channel().close();
                }
            }
=======
        ctx.channel().eventLoop().execute(() -> {
            ctx.writeAndFlush(response).addListener((ChannelFutureListener) f -> {
                if (!f.isSuccess()) {
                    HTTP_LOGGER.warn("send response to [{}] fail, will close this channel",
                        RemotingHelper.parseChannelRemoteAddr(f.channel()));
                    if (isClose) {
                        f.channel().close();
                    }
                }
            });
>>>>>>> upstream/master
        });
    }

    /**
     * short-lived connection
     */
    private void sendShortResponse(ChannelHandlerContext ctx, HttpRequest httpRequest, HttpResponse response) {
        ReferenceCountUtil.release(httpRequest);
<<<<<<< HEAD
        ctx.writeAndFlush(response).addListener((ChannelFutureListener) f -> {
            if (!f.isSuccess()) {
                httpLogger.warn("send response to [{}] with short-lived connection fail, will close this channel",
                    RemotingHelper.parseChannelRemoteAddr(f.channel()));
            }
        }).addListener(ChannelFutureListener.CLOSE);
=======
        ctx.channel().eventLoop().execute(() -> {
            ctx.writeAndFlush(response).addListener((ChannelFutureListener) f -> {
                if (!f.isSuccess()) {
                    HTTP_LOGGER.warn("send response to [{}] with short-lived connection fail, will close this channel",
                        RemotingHelper.parseChannelRemoteAddr(f.channel()));
                }
            }).addListener(ChannelFutureListener.CLOSE);
        });
>>>>>>> upstream/master
    }

    private HttpEventWrapper parseHttpRequest(HttpRequest httpRequest) throws IOException {
        HttpEventWrapper httpEventWrapper = new HttpEventWrapper();
        httpEventWrapper.setHttpMethod(httpRequest.method().name());
        httpEventWrapper.setHttpVersion(httpRequest.protocolVersion().protocolName());
        httpEventWrapper.setRequestURI(httpRequest.uri());

        // parse http header
        for (String key : httpRequest.headers().names()) {
            httpEventWrapper.getHeaderMap().put(key, httpRequest.headers().get(key));
        }

        final long bodyDecodeStart = System.currentTimeMillis();
        // parse http body
        FullHttpRequest fullHttpRequest = (FullHttpRequest) httpRequest;
        final Map<String, Object> bodyMap = new HashMap<>();
        if (HttpMethod.GET == fullHttpRequest.method()) {
            QueryStringDecoder getDecoder = new QueryStringDecoder(fullHttpRequest.uri());
            getDecoder.parameters().forEach((key, value) -> bodyMap.put(key, value.get(0)));
        } else if (HttpMethod.POST == fullHttpRequest.method()) {

            if (StringUtils.contains(httpRequest.headers().get("Content-Type"), ContentType.APPLICATION_JSON.getMimeType())) {
                int length = fullHttpRequest.content().readableBytes();
                if (length > 0) {
                    byte[] body = new byte[length];
                    fullHttpRequest.content().readBytes(body);
                    Optional
                        .ofNullable(JsonUtils.parseTypeReferenceObject(
                            new String(body, Constants.DEFAULT_CHARSET),
                            new TypeReference<Map<String, Object>>() {
                            }))
                        .ifPresent(bodyMap::putAll);
                }
            } else {
                HttpPostRequestDecoder decoder =
                    new HttpPostRequestDecoder(defaultHttpDataFactory, httpRequest);
                for (InterfaceHttpData parm : decoder.getBodyHttpDatas()) {
                    if (parm.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                        Attribute data = (Attribute) parm;
                        bodyMap.put(data.getName(), data.getValue());
                    }
                }
                decoder.destroy();
            }

        } else {
            throw new RuntimeException("UnSupported Method " + fullHttpRequest.method());
        }

        byte[] requestBody = Optional.ofNullable(JsonUtils.toJSONString(bodyMap))
            .map(s -> s.getBytes(StandardCharsets.UTF_8))
            .orElse(new byte[0]);

        httpEventWrapper.setBody(requestBody);

<<<<<<< HEAD
        metrics.getSummaryMetrics().recordDecodeTimeCost(System.currentTimeMillis() - bodyDecodeStart);
=======
        metrics.getHttpMetrics().recordDecodeTimeCost(System.currentTimeMillis() - bodyDecodeStart);
>>>>>>> upstream/master

        return httpEventWrapper;
    }

    @Getter
    @Setter
    class HandlerSpecific implements Runnable {

        private TraceOperation traceOperation;

        private ChannelHandlerContext ctx;

        private HttpRequest request;

        private HttpResponse response;

        private AsyncContext<HttpEventWrapper> asyncContext;

        private Throwable exception;

        long requestTime = System.currentTimeMillis();

        private Map<String, Object> traceMap;

        private CloudEvent ce;

        public void run() {
            String processorKey = "/";
            for (String eventProcessorKey : httpProcessorMap.keySet()) {
                if (request.uri().startsWith(eventProcessorKey)) {
                    processorKey = eventProcessorKey;
                    break;
                }
            }
            ProcessorWrapper processorWrapper = HandlerService.this.httpProcessorMap.get(processorKey);
            try {
<<<<<<< HEAD
                this.preHandler();
=======
>>>>>>> upstream/master
                if (processorWrapper.httpProcessor instanceof AsyncHttpProcessor) {
                    // set actual async request
                    HttpEventWrapper httpEventWrapper = parseHttpRequest(request);
                    this.asyncContext.setRequest(httpEventWrapper);
                    processorWrapper.async.handler(this, request);
                    return;
                }
                response = processorWrapper.httpProcessor.handler(request);

                if (processorWrapper.httpProcessor instanceof ShortHttpProcessor) {
<<<<<<< HEAD
                    this.postHandler(ConnectionType.SHORT_LIVED);
                    return;
                }
                this.postHandler(ConnectionType.PERSISTENT);
=======
                    this.postHandlerWithTimeCostRecord(ConnectionType.SHORT_LIVED);
                    return;
                }
                this.postHandlerWithTimeCostRecord(ConnectionType.PERSISTENT);
>>>>>>> upstream/master
            } catch (Throwable e) {
                exception = e;
                // todo: according exception to generate response
                this.response = HttpResponseUtils.createInternalServerError();
                this.error();
            }
        }

<<<<<<< HEAD
        private void postHandler(ConnectionType type) {
            metrics.getSummaryMetrics().recordHTTPRequest();
            LogUtils.debug(httpLogger, "{}", request);
=======
        private void postHandlerWithTimeCostRecord(ConnectionType type) {
            metrics.getHttpMetrics().recordHTTPReqResTimeCost(System.currentTimeMillis() - requestTime);
            HTTP_LOGGER.debug("{}", response);
            postHandler(type);
        }

        private void postHandler(ConnectionType type) {
            metrics.getHttpMetrics().recordHTTPRequest();
            HTTP_LOGGER.debug("{}", request);
>>>>>>> upstream/master
            if (Objects.isNull(response)) {
                this.response = HttpResponseUtils.createSuccess();
            }
            this.traceOperation.endTrace(ce);
            if (type == ConnectionType.PERSISTENT) {
                HandlerService.this.sendResponse(ctx, this.request, this.response);
            } else if (type == ConnectionType.SHORT_LIVED) {
                sendShortResponse(ctx, this.request, this.response);
            }
        }

<<<<<<< HEAD
        private void preHandler() {
            metrics.getSummaryMetrics().recordHTTPReqResTimeCost(System.currentTimeMillis() - requestTime);
            LogUtils.debug(httpLogger, "{}", response);
        }
=======
>>>>>>> upstream/master

        private void error() {
            log.error(this.exception.getMessage(), this.exception);
            this.traceOperation.exceptionTrace(this.exception, this.traceMap);
<<<<<<< HEAD
            metrics.getSummaryMetrics().recordHTTPDiscard();
            metrics.getSummaryMetrics().recordHTTPReqResTimeCost(System.currentTimeMillis() - requestTime);
            HandlerService.this.sendResponse(ctx, this.request, this.response);
        }

        public void setResponseJsonBody(String body) {
            this.sendResponse(HttpResponseUtils.setResponseJsonBody(body, ctx));
        }

        public void setResponseTextBody(String body) {
            this.sendResponse(HttpResponseUtils.setResponseTextBody(body, ctx));
        }

=======
            metrics.getHttpMetrics().recordHTTPDiscard();
            metrics.getHttpMetrics().recordHTTPReqResTimeCost(System.currentTimeMillis() - requestTime);
            HandlerService.this.sendResponse(ctx, this.request, this.response);
        }

>>>>>>> upstream/master
        public void sendResponse(HttpResponse response) {
            this.response = response;
            this.postHandler(ConnectionType.PERSISTENT);
        }

        public void sendResponse(Map<String, Object> responseHeaderMap, Map<String, Object> responseBodyMap) {
            try {
                HttpEventWrapper responseWrapper = asyncContext.getRequest().createHttpResponse(responseHeaderMap, responseBodyMap);
                asyncContext.onComplete(responseWrapper);
                this.response = asyncContext.getResponse().httpResponse();
                this.postHandler(ConnectionType.PERSISTENT);
            } catch (Exception e) {
                this.exception = e;
                // todo: according exception to generate response
                this.response = HttpResponseUtils.createInternalServerError();
                this.error();
            }

        }

        // for error response
        public void sendErrorResponse(EventMeshRetCode retCode, Map<String, Object> responseHeaderMap, Map<String, Object> responseBodyMap,
            Map<String, Object> traceMap) {
            this.traceMap = traceMap;
            try {
                responseBodyMap.put(EventMeshConstants.RET_CODE, retCode.getRetCode());
                responseBodyMap.put(EventMeshConstants.RET_MSG, retCode.getErrMsg());
                HttpEventWrapper responseWrapper = asyncContext.getRequest().createHttpResponse(responseHeaderMap, responseBodyMap);
                asyncContext.onComplete(responseWrapper);
                this.exception = new RuntimeException(retCode.getErrMsg());
                this.response = asyncContext.getResponse().httpResponse();
                this.error();
            } catch (Exception e) {
                this.exception = e;
                // todo: according exception to generate response
                this.response = HttpResponseUtils.createInternalServerError();
                this.error();
            }
        }

        /**
         * @param count
         */
        public void recordSendBatchMsgFailed(int count) {
<<<<<<< HEAD
            metrics.getSummaryMetrics().recordSendBatchMsgFailed(1);
=======
            metrics.getHttpMetrics().recordSendBatchMsgFailed(1);
>>>>>>> upstream/master
        }

    }

    private static class ProcessorWrapper {

<<<<<<< HEAD
        private ThreadPoolExecutor threadPoolExecutor;
=======
        private Executor executor;
>>>>>>> upstream/master

        private HttpProcessor httpProcessor;

        private AsyncHttpProcessor async;

        private boolean traceEnabled;
    }

}
