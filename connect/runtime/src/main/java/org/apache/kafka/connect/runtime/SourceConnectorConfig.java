/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.connect.runtime;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.runtime.isolation.Plugins;

import java.util.HashMap;
import java.util.Map;

public class SourceConnectorConfig extends ConnectorConfig {

    private static ConfigDef config = ConnectorConfig.configDef();

    public static ConfigDef configDef() {
        return config;
    }

    public SourceConnectorConfig(Plugins plugins, Map<String, String> props) {
        super(plugins, config, props);
    }

    // Default configuration values for the producer
    public static final String PRODUCER_KEY_SERIALIZER_CLASS_CONFIG_DEFAULT = "org.apache.kafka.common.serialization.ByteArraySerializer";
    public static final String PRODUCER_VALUE_SERIALIZER_CLASS_CONFIG_DEFAULT = "org.apache.kafka.common.serialization.ByteArraySerializer";
    public static final String PRODUCER_REQUEST_TIMEOUT_MS_CONFIG_DEFAULT = Integer.toString(Integer.MAX_VALUE);
    public static final String PRODUCER_MAX_BLOCK_MS_CONFIG_DEFAULT = Long.toString(Long.MAX_VALUE);
    public static final String PRODUCER_ACKS_CONFIG_DEFAULT = "all";
    public static final String PRODUCER_MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION_DEFAULT = "1";
    public static final String PRODUCER_DELIVERY_TIMEOUT_MS_CONFIG_DEFAULT = Integer.toString(Integer.MAX_VALUE);

    public static final Map<String, Object> PRODUCER_DEFAULT_CONFIGS;
    static {
        PRODUCER_DEFAULT_CONFIGS = new HashMap<>();
        PRODUCER_DEFAULT_CONFIGS.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, PRODUCER_KEY_SERIALIZER_CLASS_CONFIG_DEFAULT);
        PRODUCER_DEFAULT_CONFIGS.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PRODUCER_VALUE_SERIALIZER_CLASS_CONFIG_DEFAULT);

        // These settings are designed to ensure there is no data loss. They *may* be overridden via configs passed to the
        // worker, but this may compromise the delivery guarantees of Kafka Connect.
        PRODUCER_DEFAULT_CONFIGS.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, PRODUCER_REQUEST_TIMEOUT_MS_CONFIG_DEFAULT);
        PRODUCER_DEFAULT_CONFIGS.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, PRODUCER_MAX_BLOCK_MS_CONFIG_DEFAULT);
        PRODUCER_DEFAULT_CONFIGS.put(ProducerConfig.ACKS_CONFIG, PRODUCER_ACKS_CONFIG_DEFAULT);
        PRODUCER_DEFAULT_CONFIGS.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, PRODUCER_MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION_DEFAULT);
        PRODUCER_DEFAULT_CONFIGS.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, PRODUCER_DELIVERY_TIMEOUT_MS_CONFIG_DEFAULT);
    }

    public static void main(String[] args) {
        System.out.println(config.toHtmlTable());
    }
}
