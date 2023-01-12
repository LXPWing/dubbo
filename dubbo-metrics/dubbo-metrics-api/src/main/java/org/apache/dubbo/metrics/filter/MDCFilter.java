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

package org.apache.dubbo.metrics.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.constants.MetricsConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.logger.MDC;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.model.ScopeModelAware;

@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER}, value = MetricsConstants.TRACE_START_MDC, order = -1)
public class MDCFilter implements Filter, ScopeModelAware {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Class<?>[] interfaces = logger.getClass().getInterfaces();
        for (Class<?> cls : interfaces) {
            if(cls == MDC.class) {
                // TODO mdc注入
            }
        }

        return invoker.invoke(invocation);
    }

    protected void inject(String key, Object val) {

    }
}
