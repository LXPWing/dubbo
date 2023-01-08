package org.apache.dubbo.springboot.demo;

import io.micrometer.tracing.Tracer;

import java.io.Serializable;

public class Trace implements Serializable {
    private Tracer tracer;

    public Trace(Tracer tracer) {
        this.tracer = tracer;
    }

    public Tracer getTracer() {
        return tracer;
    }

    public void setTracer(Tracer tracer) {
        this.tracer = tracer;
    }
}
