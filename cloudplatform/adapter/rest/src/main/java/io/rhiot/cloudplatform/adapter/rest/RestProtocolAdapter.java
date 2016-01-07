/**
 * Licensed to the Rhiot under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.rhiot.cloudplatform.adapter.rest;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.camel.Exchange.CONTENT_TYPE;
import static org.apache.camel.Exchange.HTTP_URI;
import static org.apache.commons.lang3.StringUtils.removeEnd;

public class RestProtocolAdapter extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(RestProtocolAdapter.class);

    int port;

    public RestProtocolAdapter(int port) {
        this.port = port;
    }

    // Routes

    @Override
    public void configure() throws Exception {
        LOG.debug("Started REST data stream source at port {}.", port);

        from("netty4-http:http://0.0.0.0:" + port + "/?matchOnUriPrefix=true").
                setHeader(CONTENT_TYPE).constant("application/json").
                process( exc -> {
                    String requestUri = exc.getIn().getHeader(HTTP_URI, String.class);
                    String trimmedUri = removeEnd(requestUri, "/");
                    String busChannel = trimmedUri.substring(1).replaceAll("\\/", ".");
                    exc.setProperty("target", "amqp:" + busChannel);
                }).toD("${property.target}");
    }

}