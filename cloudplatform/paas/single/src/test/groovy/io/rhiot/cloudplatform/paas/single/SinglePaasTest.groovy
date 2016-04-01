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
package io.rhiot.cloudplatform.paas.single

import io.rhiot.cloudplatform.runtime.spring.test.CloudPlatformTest
import io.rhiot.cloudplatform.service.document.api.DocumentStore
import org.apache.commons.io.IOUtils
import org.junit.Test
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import static com.google.common.truth.Truth.assertThat
import static io.rhiot.cloudplatform.connector.Header.arguments
import static io.rhiot.utils.Uuids.uuid
import static org.mockito.Mockito.mock

@Configuration
class SinglePaasTest extends CloudPlatformTest {

    def document = uuid()

    @Bean
    DocumentStore document() {
        mock(DocumentStore.class)
    }

    // Tests

    @Test
    void shouldCountDocument() {
        def count = connector.fromBus('document.count', long.class, arguments(document))
        assertThat(count).isEqualTo(0)
    }

    @Test
    void shouldCountDocumentViaRestAdapter() {
        def responseBytes = IOUtils.toByteArray(new URL("http://localhost:8080/document/count/${document}"))
        def response = payloadEncoding.decode(responseBytes)
        assertThat(response).isEqualTo(0)
    }

}