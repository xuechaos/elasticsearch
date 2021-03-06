/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.client;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.elasticsearch.client.rollup.DeleteRollupJobRequest;
import org.elasticsearch.client.rollup.GetRollupCapsRequest;
import org.elasticsearch.client.rollup.GetRollupJobRequest;
import org.elasticsearch.client.rollup.PutRollupJobRequest;
import org.elasticsearch.client.rollup.StartRollupJobRequest;

import java.io.IOException;

import static org.elasticsearch.client.RequestConverters.REQUEST_BODY_CONTENT_TYPE;
import static org.elasticsearch.client.RequestConverters.createEntity;

final class RollupRequestConverters {

    private RollupRequestConverters() {
    }

    static Request putJob(final PutRollupJobRequest putRollupJobRequest) throws IOException {
        String endpoint = new RequestConverters.EndpointBuilder()
            .addPathPartAsIs("_xpack", "rollup", "job")
            .addPathPart(putRollupJobRequest.getConfig().getId())
            .build();
        Request request = new Request(HttpPut.METHOD_NAME, endpoint);
        request.setEntity(createEntity(putRollupJobRequest, REQUEST_BODY_CONTENT_TYPE));
        return request;
    }

    static Request startJob(final StartRollupJobRequest startRollupJobRequest) throws IOException {
        String endpoint = new RequestConverters.EndpointBuilder()
            .addPathPartAsIs("_xpack", "rollup", "job")
            .addPathPart(startRollupJobRequest.getJobId())
            .addPathPartAsIs("_start")
            .build();
        Request request = new Request(HttpPost.METHOD_NAME, endpoint);
        return request;
    }

    static Request getJob(final GetRollupJobRequest getRollupJobRequest) {
        String endpoint = new RequestConverters.EndpointBuilder()
            .addPathPartAsIs("_xpack", "rollup", "job")
            .addPathPart(getRollupJobRequest.getJobId())
            .build();
        return new Request(HttpGet.METHOD_NAME, endpoint);
    }

    static Request deleteJob(final DeleteRollupJobRequest deleteRollupJobRequest) throws IOException {
        String endpoint = new RequestConverters.EndpointBuilder()
            .addPathPartAsIs("_xpack", "rollup", "job")
            .addPathPart(deleteRollupJobRequest.getId())
            .build();
        Request request = new Request(HttpDelete.METHOD_NAME, endpoint);
        request.setEntity(createEntity(deleteRollupJobRequest, REQUEST_BODY_CONTENT_TYPE));
        return request;
    }

    static Request getRollupCaps(final GetRollupCapsRequest getRollupCapsRequest) throws IOException {
        String endpoint = new RequestConverters.EndpointBuilder()
            .addPathPartAsIs("_xpack", "rollup", "data")
            .addPathPart(getRollupCapsRequest.getIndexPattern())
            .build();
        Request request = new Request(HttpGet.METHOD_NAME, endpoint);
        request.setEntity(createEntity(getRollupCapsRequest, REQUEST_BODY_CONTENT_TYPE));
        return request;
    }
}
