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
import org.apache.dubbo.common.metrics.model.sample.MetricSample;
import org.apache.dubbo.rpc.model.ApplicationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MetaDataMetricsCollector implements MetricsCollector {

    private AtomicBoolean collectEnabled = new AtomicBoolean(false);

    private final List<MetricsListener> listeners = new ArrayList<>();

    private final ApplicationModel applicationModel;

    public MetaDataMetricsCollector(ApplicationModel applicationModel) {
        this.applicationModel = applicationModel;
    }

    @Override
    public List<MetricSample> collect() {
        List<MetricSample> list = new ArrayList<>();
        collectMetrics(list);

        return list;
    }

    public void increaseTotalConnect() {

    }

    public void increaseSucceedConnect() {

    }

    public void increaseFailConnect() {

    }

    public void increaseHitCount() {

    }

    private void collectMetrics(List<MetricSample> list) {

    }

    public void addListener(MetricsListener listener) {
        listeners.add(listener);
    }

    public AtomicBoolean getCollectEnabled() {
        return collectEnabled;
    }

    public void setCollectEnabled(AtomicBoolean collectEnabled) {
        this.collectEnabled = collectEnabled;
    }

    public List<MetricsListener> getListeners() {
        return listeners;
    }

    public ApplicationModel getApplicationModel() {
        return applicationModel;
    }
}
