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
package io.rhiot.component.bluetooth;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.impl.DefaultScheduledPollConsumer;

import java.util.List;

public class BluetoothConsumer extends DefaultScheduledPollConsumer {

    public BluetoothConsumer(DefaultEndpoint defaultEndpoint, Processor processor) {
        super(defaultEndpoint, processor);
    }

    @Override
    protected int poll() throws Exception {
        log.debug("Started Bluetooth consumer poll.");

        List<BluetoothDevice> bluetoothDevices = getEndpoint().getBluetoothDevicesProvider().bluetoothDevices();
        Exchange exchange = ExchangeBuilder.anExchange(getEndpoint().getCamelContext()).withBody(bluetoothDevices).build();
        exchange.setFromEndpoint(getEndpoint());
        getProcessor().process(exchange);
        return bluetoothDevices.isEmpty() ? 0 : 1;
    }

    @Override
    public BluetoothEndpoint getEndpoint() {
        return (BluetoothEndpoint) super.getEndpoint();
    }
}