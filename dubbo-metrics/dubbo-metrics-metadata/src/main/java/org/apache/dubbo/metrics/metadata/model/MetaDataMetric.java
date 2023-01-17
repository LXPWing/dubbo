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
    private String app;

    private String revision;

    private String path;

    private String protocol;

    private String group;

    private String version;

    public MetaDataMetric() {

    }

    public MetaDataMetric(String app, String revision, String path, String protocol, String group, String version) {
        this.app = app;
        this.revision = revision;
        this.path = path;
        this.protocol = protocol;
        this.group = group;
        this.version = version;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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
        return Objects.equals(app, that.app) && Objects.equals(revision, that.revision) && Objects.equals(path, that.path) && Objects.equals(protocol, that.protocol) && Objects.equals(group, that.group) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(app, revision, path, protocol, group, version);
    }

    @Override
    public String toString() {
        return "MetaDataMetric{" +
            "app='" + app + '\'' +
            ", revision='" + revision + '\'' +
            ", path='" + path + '\'' +
            ", protocol='" + protocol + '\'' +
            ", group='" + group + '\'' +
            ", version='" + version + '\'' +
            '}';
    }
}
