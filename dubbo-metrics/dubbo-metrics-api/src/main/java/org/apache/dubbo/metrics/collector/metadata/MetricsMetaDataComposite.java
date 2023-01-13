package org.apache.dubbo.metrics.collector.metadata;

import org.apache.dubbo.common.metrics.collector.DefaultMetricsCollector;
import org.apache.dubbo.common.metrics.listener.MetricsListener;
import org.apache.dubbo.common.metrics.model.MethodMetric;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;

public class MetricsMetaDataComposite {

    private final Map<MethodMetric, AtomicLong> lastRT = new ConcurrentHashMap<>();

    private final Map<MethodMetric, LongAccumulator> minRT  = new ConcurrentHashMap<>();

    private final Map<MethodMetric, LongAccumulator> maxRT  = new ConcurrentHashMap<>();

    private final Map<MethodMetric, AtomicLong> avgRT = new ConcurrentHashMap<>();

    private final Map<MethodMetric, AtomicLong> totalRT = new ConcurrentHashMap<>();

    private final Map<MethodMetric, AtomicLong> rtCount = new ConcurrentHashMap<>();
    private final String applicationName;
    private final List<MetricsListener> listeners;
    private DefaultMetricsCollector collector;

    public MetricsMetaDataComposite(String applicationName, DefaultMetricsCollector collector) {
        this.applicationName = applicationName;
        this.listeners = collector.getListener();
        this.collector = collector;
    }
}
