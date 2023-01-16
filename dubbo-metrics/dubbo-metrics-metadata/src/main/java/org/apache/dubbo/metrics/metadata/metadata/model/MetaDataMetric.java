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

package org.apache.dubbo.metrics.metadata.metadata.model;

import java.util.Objects;

public class MetaDataMetric {
    private String address;

    private Integer port;

    private String group;

    private String revision;

    public MetaDataMetric(String address, Integer port, String group, String version) {
        this.address = address;
        this.port = port;
        this.group = group;
        this.revision = version;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetaDataMetric that = (MetaDataMetric) o;
        return Objects.equals(address, that.address) && Objects.equals(port, that.port) && Objects.equals(group, that.group) && Objects.equals(revision, that.revision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, port, group, revision);
    }

    @Override
    public String toString() {
        return "MetaDataMetric{" +
            "address='" + address + '\'' +
            ", port=" + port +
            ", group='" + group + '\'' +
            ", revision='" + revision + '\'' +
            '}';
    }
}
