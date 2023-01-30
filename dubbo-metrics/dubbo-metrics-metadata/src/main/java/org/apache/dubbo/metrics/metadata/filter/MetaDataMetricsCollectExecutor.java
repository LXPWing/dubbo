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

package org.apache.dubbo.metrics.metadata.filter;

import org.apache.dubbo.metrics.metadata.collector.MetaDataMetricsCollector;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.RpcInvocation;

import static org.apache.dubbo.common.constants.MetricsConstants.METRIC_FILTER_START_TIME;
import static org.apache.dubbo.rpc.support.RpcUtils.isGenericCall;

public class MetaDataMetricsCollectExecutor {
    private final MetaDataMetricsCollector collector;

    private final Invocation invocation;

    private String interfaceName;

    private String revision;

    private String group;

    private String version;

    public MetaDataMetricsCollectExecutor(MetaDataMetricsCollector collector, Invocation invocation) {
        init(invocation);

        this.collector = collector;

        this.invocation = invocation;
    }

    public void beforeExecute() {
        invocation.put(METRIC_FILTER_START_TIME, System.currentTimeMillis());
    }

    public void postExecute() {

    }

    public void throwExecute() {

    }

    // TODO 完成filter解析
    private void init(Invocation invocation) {
        String serviceUniqueName = invocation.getTargetServiceUniqueName();
        String methodName = invocation.getMethodName();
        if (invocation instanceof RpcInvocation
            && isGenericCall(((RpcInvocation) invocation).getParameterTypesDesc(), methodName)
            && invocation.getArguments() != null
            && invocation.getArguments().length == 3) {
            methodName = ((String) invocation.getArguments()[0]).trim();
        }
        String group = null;
        String interfaceAndVersion;
        String[] arr = serviceUniqueName.split("/");
        if (arr.length == 2) {
            group = arr[0];
            interfaceAndVersion = arr[1];
        } else {
            interfaceAndVersion = arr[0];
        }

        String[] ivArr = interfaceAndVersion.split(":");
        String interfaceName = ivArr[0];
        String version = ivArr.length == 2 ? ivArr[1] : null;

        this.group = group;
        this.version = version;
    }
}
