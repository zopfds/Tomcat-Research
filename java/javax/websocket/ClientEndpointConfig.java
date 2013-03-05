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
package javax.websocket;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface ClientEndpointConfig extends EndpointConfig {

    List<String> getPreferredSubprotocols();

    List<Extension> getExtensions();

    public Configurator getConfigurator();

    public final class Builder {

        private static final Configurator DEFAULT_CONFIGURATOR =
                new Configurator() {};


        public static Builder create() {
            return new Builder();
        }


        private Builder() {
            // Hide default constructor
        }

        private Configurator configurator = DEFAULT_CONFIGURATOR;
        private List<String> preferredSubprotocols = Collections.EMPTY_LIST;
        private List<Extension> extensions = Collections.EMPTY_LIST;
        private List<Encoder> encoders = Collections.EMPTY_LIST;
        private List<Decoder> decoders = Collections.EMPTY_LIST;


        public ClientEndpointConfig build() {
            return new DefaultClientEndpointConfig(preferredSubprotocols,
                    extensions, encoders, decoders, configurator);
        }


        public Builder configurator(Configurator configurator) {
            if (configurator == null) {
                this.configurator = DEFAULT_CONFIGURATOR;
            } else {
                this.configurator = configurator;
            }
            return this;
        }


        public Builder preferredSubprotocols(
                List<String> preferredSubprotocols) {
            if (preferredSubprotocols == null ||
                    preferredSubprotocols.size() == 0) {
                this.preferredSubprotocols = Collections.EMPTY_LIST;
            } else {
                this.preferredSubprotocols =
                        Collections.unmodifiableList(preferredSubprotocols);
            }
            return this;
        }


        public Builder extensions(
                List<Extension> extensions) {
            if (extensions == null || extensions.size() == 0) {
                this.extensions = Collections.EMPTY_LIST;
            } else {
                this.extensions = Collections.unmodifiableList(extensions);
            }
            return this;
        }


        public Builder encoders(List<Encoder> encoders) {
            if (encoders == null || encoders.size() == 0) {
                this.encoders = Collections.EMPTY_LIST;
            } else {
                this.encoders = Collections.unmodifiableList(encoders);
            }
            return this;
        }


        public Builder decoders(List<Decoder> decoders) {
            if (decoders == null || decoders.size() == 0) {
                this.decoders = Collections.EMPTY_LIST;
            } else {
                this.decoders = Collections.unmodifiableList(decoders);
            }
            return this;
        }
    }


    public abstract class Configurator {

        /**
         * Provides the client with a mechanism to inspect and/or modify the headers
         * that are sent to the server to start the WebSocket handshake.
         *
         * @param headers   The HTTP headers
         */
        public void beforeRequest(Map<String, List<String>> headers) {
            // NO-OP
        }

        /**
         * Provides the client with a mechanism to inspect the handshake response
         * that is returned from the server.
         *
         * @param handshakeResponse The response
         */
        public void afterResponse(HandshakeResponse handshakeResponse) {
            // NO-OP
        }
    }
}