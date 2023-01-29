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

package org.apache.dubbo.metrics.metadata.collector;

import org.apache.dubbo.common.metrics.collector.MetricsCollector;
import org.apache.dubbo.common.metrics.listener.MetricsListener;
import org.apache.dubbo.common.metrics.model.MetricsKey;
import org.apache.dubbo.common.metrics.model.sample.GaugeMetricSample;
import org.apache.dubbo.common.metrics.model.sample.MetricSample;
import org.apache.dubbo.metrics.metadata.event.MetaDataEvent;
import org.apache.dubbo.metrics.metadata.stat.MetaDataMetricsComposite;
import org.apache.dubbo.metrics.metadata.stat.MetricsStatHandler;
import org.apache.dubbo.rpc.model.ApplicationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.apache.dubbo.common.metrics.model.MetricsCategory.METADATA;

// TODO 1.元数据中心接口总数 2.注册成功的接口数 3.注册失败的接口数
public class MetaDataMetricsCollector implements MetricsCollector {

    private AtomicBoolean collectEnabled = new AtomicBoolean(false);

    private final List<MetricsListener> listeners = new ArrayList<>();

    private final ApplicationModel applicationModel;

    private final MetaDataMetricsComposite stats;

    public MetaDataMetricsCollector(ApplicationModel applicationModel) {
        this.applicationModel = applicationModel;
        this.stats = new MetaDataMetricsComposite(applicationModel.getApplicationName(), this);
    }

    @Override
    public List<MetricSample> collect() {
        List<MetricSample> list = new ArrayList<>();
        collectMetaData(list);

        return list;
    }

    public void increaseTotalMetadata(String revision, String interfaceName, String group, String version) {
        doExecute(MetaDataEvent.Type.TOTAL, statHandler -> {
            statHandler.increase(revision, interfaceName, group, version);
        });
    }

    public void increaseSucceedMetaData(String revision, String interfaceName, String group, String version) {
        doExecute(MetaDataEvent.Type.SUCCEED, statHandler -> {
            statHandler.increase(revision, interfaceName, group, version);
        });
    }

    public void increaseFailedMetaData(String revision, String interfaceName, String group, String version) {
        doExecute(MetaDataEvent.Type.FAILED, statHandler -> {
            statHandler.increase(revision, interfaceName, group, version);
        });
    }

    private void collectMetaData(List<MetricSample> list) {
        doExecute(MetaDataEvent.Type.TOTAL, MetricsStatHandler::get).filter(e -> !e.isEmpty())
            .ifPresent(map -> map.forEach((k, v) -> list.add(new GaugeMetricSample(MetricsKey.METRIC_METADATA_TOTAL, k.getTags(), METADATA, v::get))));

        doExecute(MetaDataEvent.Type.SUCCEED, MetricsStatHandler::get).filter(e -> !e.isEmpty())
            .ifPresent(map -> map.forEach((k, v) -> list.add(new GaugeMetricSample(MetricsKey.METRIC_METADATA_SUCCEED, k.getTags(), METADATA, v::get))));

        doExecute(MetaDataEvent.Type.FAILED, MetricsStatHandler::get).filter(e -> !e.isEmpty())
            .ifPresent(map -> map.forEach((k, v) -> list.add(new GaugeMetricSample(MetricsKey.METRIC_METADATA_FAILED, k.getTags(), METADATA, v::get))));
    }

    public void addListener(MetricsListener listener) {
        listeners.add(listener);
    }

    public Boolean isCollectEnabled() {
        return collectEnabled.get();
    }

    public void setCollectEnabled(Boolean collectEnabled) {
        this.collectEnabled.compareAndSet(isCollectEnabled(), collectEnabled);
    }

    public List<MetricsListener> getListeners() {
        return listeners;
    }

    public ApplicationModel getApplicationModel() {
        return applicationModel;
    }

    private <T> Optional<T> doExecute(MetaDataEvent.Type type, Function<MetricsStatHandler,T> statExecutor) {
        if(isCollectEnabled()) {
            MetricsStatHandler handler = stats.getHandler(type);
            T result =  statExecutor.apply(handler);
            return Optional.ofNullable(result);
        }

        return Optional.empty();
    }

    private void doExecute(MetaDataEvent.Type type, Consumer<MetricsStatHandler> statExecutor) {
        if (isCollectEnabled()) {
            MetricsStatHandler handler = stats.getHandler(type);
            statExecutor.accept(handler);
        }
    }
}
