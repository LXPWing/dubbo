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

package org.apache.dubbo.metrics.metadata.model;

import java.util.Objects;

public class MetaDataMetric {
    private String applicationName;

    private String revision;

    private String interfaceName;

    private String methodName;

    private String group;

    private String version;

    public MetaDataMetric() {}

    public MetaDataMetric(String applicationName, String revision, String interfaceName, String methodName, String group, String version) {
        this.applicationName = applicationName;
        this.revision = revision;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.group = group;
        this.version = version;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetaDataMetric that = (MetaDataMetric) o;
        return Objects.equals(applicationName, that.applicationName) && Objects.equals(revision, that.revision) && Objects.equals(interfaceName, that.interfaceName) && Objects.equals(methodName, that.methodName) && Objects.equals(group, that.group) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationName, revision, interfaceName, methodName, group, version);
    }

    @Override
    public String toString() {
        return "MetaDataMetric{" +
            "applicationName='" + applicationName + '\'' +
            ", revision='" + revision + '\'' +
            ", interfaceName='" + interfaceName + '\'' +
            ", methodName='" + methodName + '\'' +
            ", group='" + group + '\'' +
            ", version='" + version + '\'' +
            '}';
    }
}
