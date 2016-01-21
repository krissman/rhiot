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
package io.rhiot.cloudplatform.service.mailbox;

import io.rhiot.cloudplatform.encoding.spi.PayloadEncoding;
import org.apache.camel.ProducerTemplate;

import java.util.Map;

import static io.rhiot.cloudplatform.service.mailbox.spring.MailboxConstants.inbox;

public class DefaultMailboxService implements MailboxService {

    private final ProducerTemplate producerTemplate;

    private final PayloadEncoding payloadEncoding;

    private final MailStore mailStore;

    public DefaultMailboxService(ProducerTemplate producerTemplate, PayloadEncoding payloadEncoding, MailStore mailStore) {
        this.producerTemplate = producerTemplate;
        this.payloadEncoding = payloadEncoding;
        this.mailStore = mailStore;
    }

    @Override
    public void outbox(String receiver, Map<String, Object> mail) {
        mailStore.save(receiver, mail);
        producerTemplate.sendBody("amqp:" + inbox(receiver), payloadEncoding.encode(mail));
    }

}