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

import org.apache.dubbo.common.metrics.collector.DefaultMetricsCollector;
import org.apache.dubbo.common.metrics.collector.MetricsCollector;
import org.apache.dubbo.common.metrics.event.MetricsEvent;
import org.apache.dubbo.common.metrics.listener.MetricsListener;
import org.apache.dubbo.common.metrics.model.sample.MetricSample;
import org.apache.dubbo.config.MetricsConfig;
import org.apache.dubbo.config.context.ConfigManager;
import org.apache.dubbo.config.nested.AggregationConfig;
import org.apache.dubbo.metrics.aggregate.TimeWindowCounter;
import org.apache.dubbo.metrics.metadata.event.MetaDataEvent;
import org.apache.dubbo.metrics.metadata.model.MetaDataMetric;
import org.apache.dubbo.rpc.model.ApplicationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AggregateMetaDataMetricsCollector implements MetricsCollector, MetricsListener {

    private int bucketNum;

    private int timeWindowSeconds;

    private final Map<MetaDataMetric, TimeWindowCounter> totalMetaData = new ConcurrentHashMap<>();

    private final Map<MetaDataMetric, TimeWindowCounter> succeedMetaData = new ConcurrentHashMap<>();

    private final Map<MetaDataMetric, TimeWindowCounter> failedMetaData = new ConcurrentHashMap<>();

    private final ApplicationModel applicationModel;

    private static final Integer DEFAULT_COMPRESSION = 100;

    private static final Integer DEFAULT_BUCKET_NUM = 10;

    private static final Integer DEFAULT_TIME_WINDOW_SECONDS = 120;

    public AggregateMetaDataMetricsCollector(ApplicationModel applicationModel) {
        this.applicationModel = applicationModel;
        ConfigManager configManager = applicationModel.getApplicationConfigManager();
        MetricsConfig config = configManager.getMetrics().orElse(null);
        if (config != null && config.getAggregation() != null && Boolean.TRUE.equals(config.getAggregation().getEnabled())) {
            // only registered when aggregation is enabled.
            registerListener();

            AggregationConfig aggregation = config.getAggregation();
            this.bucketNum = aggregation.getBucketNum() == null ? DEFAULT_BUCKET_NUM : aggregation.getBucketNum();
            this.timeWindowSeconds = aggregation.getTimeWindowSeconds() == null ? DEFAULT_TIME_WINDOW_SECONDS : aggregation.getTimeWindowSeconds();
        }
    }

    private void registerListener() {
        applicationModel.getBeanFactory().getBean(MetaDataMetricsCollector.class).addListener(this);
    }


    @Override
    public List<MetricSample> collect() {
        List<MetricSample> list = new ArrayList<>();
        collectMetaData(list);

        return list;
    }

    private void collectMetaData(List<MetricSample> list) {

    }

    @Override
    public void onEvent(MetricsEvent event) {
        if(event instanceof MetaDataEvent) {
            onMetaDataEvent((MetaDataEvent) event);
        }
    }

    public void onMetaDataEvent(MetaDataEvent event) {
        MetaDataMetric metric = (MetaDataMetric) event.getSource();
        MetaDataEvent.Type type = event.getType();
        TimeWindowCounter counter = null;
        switch (type) {
            case TOTAL:
                break;
            case SUCCEED:
                break;
            case FAILED:
                break;
        }

        if (counter != null) {
            counter.increment();
        }
    }
}
