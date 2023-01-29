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

package org.apache.dubbo.metrics.metadata.stat;

import org.apache.dubbo.common.metrics.event.MetricsEvent;
import org.apache.dubbo.common.metrics.event.RequestEvent;
import org.apache.dubbo.common.metrics.listener.MetricsListener;
import org.apache.dubbo.metrics.metadata.collector.MetaDataMetricsCollector;
import org.apache.dubbo.metrics.metadata.event.MetaDataEvent;
import org.apache.dubbo.metrics.metadata.model.MetaDataMetric;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;

public class MetaDataMetricsComposite {
    public Map<MetaDataEvent.Type, MetricsStatHandler> stats = new ConcurrentHashMap<>();

    private final Map<MetaDataMetric, AtomicLong> lastRT = new ConcurrentHashMap<>();

    private final Map<MetaDataMetric, LongAccumulator> minRT  = new ConcurrentHashMap<>();

    private final Map<MetaDataMetric, LongAccumulator> maxRT  = new ConcurrentHashMap<>();

    private final Map<MetaDataMetric, AtomicLong> avgRT = new ConcurrentHashMap<>();

    private final Map<MetaDataMetric, AtomicLong> totalRT = new ConcurrentHashMap<>();

    private final Map<MetaDataMetric, AtomicLong> rtCount = new ConcurrentHashMap<>();

    private final String applicationName;

    private final List<MetricsListener> listeners;

    private MetaDataMetricsCollector collector;

    public MetaDataMetricsComposite(String applicationName, MetaDataMetricsCollector collector) {
        this.applicationName = applicationName;
        this.listeners = collector.getListeners();
        this.collector = collector;
        this.init();
    }

    public MetricsStatHandler getHandler(MetaDataEvent.Type statType) {
        return stats.get(statType);
    }

    private void init() {
        stats.put(MetaDataEvent.Type.TOTAL, new MateDataMetricsStatHandler(applicationName){
            @Override
            public void doNotify(MetaDataMetric metric) {
                publishEvent(new MetaDataEvent(metric, MetaDataEvent.Type.TOTAL));
            }
        });

        stats.put(MetaDataEvent.Type.SUCCEED, new MateDataMetricsStatHandler(applicationName){
            @Override
            public void doNotify(MetaDataMetric metric) {
                publishEvent(new MetaDataEvent(metric, MetaDataEvent.Type.SUCCEED));
            }
        });

        stats.put(MetaDataEvent.Type.FAILED, new MateDataMetricsStatHandler(applicationName){
            @Override
            public void doNotify(MetaDataMetric metric) {
                publishEvent(new MetaDataEvent(metric, MetaDataEvent.Type.FAILED));
            }
        });
    }

    private void publishEvent(MetricsEvent event) {
        for (MetricsListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
