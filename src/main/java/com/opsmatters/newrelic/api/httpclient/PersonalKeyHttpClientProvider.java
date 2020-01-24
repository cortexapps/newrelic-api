/*
 * Copyright 2018 Gerald Curley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.opsmatters.newrelic.api.httpclient;

import com.opsmatters.newrelic.api.httpclient.filters.PersonalKeyFilter;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HTTP client provider to attach a personal key used for authentication.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class PersonalKeyHttpClientProvider implements HttpClientProvider
{
    private static final Logger logger = Logger.getLogger(PersonalKeyHttpClientProvider.class.getName());

    private String personalKey;

    /**
     * Constructor that takes a personal key.
     * @param personalKey The personal key
     */
    public PersonalKeyHttpClientProvider(String personalKey)
    {
        this.personalKey = personalKey;
    }

    /**
     * Returns <CODE>true</CODE> if the provider should use https.
     * @return <CODE>true</CODE> if the provider should use https
     */
    @Override
    public boolean useSsl()
    {
        return true;
    }

    /**
     * Returns the HTTP client.
     * @return The HTTP client
     */
    @Override
    public Client getClient()
    {
        ClientConfig config = new ClientConfig();
        config.register(GsonMessageBodyHandler.class);
        Client client = ClientBuilder.newClient(config);
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true); // To support PATCH method
        client.register(new PersonalKeyFilter(this.personalKey));
        if(logger.isLoggable(Level.FINE))
            client.register(new LoggingFeature(logger, Level.FINE, LoggingFeature.Verbosity.PAYLOAD_TEXT, 8192));
        return client;
    }
}