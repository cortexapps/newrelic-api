package com.opsmatters.newrelic.api;

import com.opsmatters.newrelic.api.httpclient.HttpClientProvider;
import com.opsmatters.newrelic.api.httpclient.PersonalKeyHttpClientProvider;
import com.opsmatters.newrelic.api.httpclient.QueryKeyHttpClientProvider;
import com.opsmatters.newrelic.api.services.GraphQLService;

import java.util.logging.Logger;

/**
 * Client used to invoke NerdGraph operations using Graph QL.
 *
 * @author Nikhil Unni (cortexapps)
 */
public class NerdGraphApi extends NewRelicClient {

    private static final Logger logger = Logger.getLogger(NerdGraphApi.class.getName());

    /**
     * The default hostname for NerdGraph
     */
    public static final String DEFAULT_HOST = "api.newrelic.com";

    /**
     * Default constructor.
     */
    public NerdGraphApi()
    {
        setHostname(DEFAULT_HOST);
    }

    /**
     * Constructor that takes a hostname, port and provider.
     * @param hostname The hostname of the server
     * @param port The port of the server
     * @param provider The HTTP client provider
     */
    public NerdGraphApi(String hostname, int port, HttpClientProvider provider)
    {
        super(hostname, port, provider);
    }

    /**
     * Sets the name of the host to connect to.
     * <P>
     * The default hostname is "insights-api.newrelic.com".
     * @param hostname The name of the host
     */
    public void setHostname(String hostname)
    {
        super.setHostname(hostname);
    }

    /**
     * Returns the operations related to GraphQL queries
     * @return The GraphQL query service
     */
    public GraphQLService graphQL()
    {
        checkInitialize();
        return new GraphQLService(httpContext, this);
    }

    /**
     * Returns a builder for the NerdGraphApi.
     * @return The builder instance.
     */
    public static NerdGraphApi.Builder builder()
    {
        return new NerdGraphApi.Builder();
    }

    /**
     * Builder to make NerdGraphApi construction easier.
     */
    public static class Builder
    {
        private String hostname = DEFAULT_HOST;
        private int port = DEFAULT_PORT;
        private HttpClientProvider provider = new QueryKeyHttpClientProvider("");

        /**
         * Default constructor.
         */
        public Builder()
        {
            hostname(DEFAULT_HOST);
        }

        /**
         * Sets the name of the host to connect to.
         * <P>
         * The default hostname is "api.newrelic.com".
         * @param hostname The name of the host
         * @return This object
         */
        public NerdGraphApi.Builder hostname(String hostname)
        {
            this.hostname = hostname;
            return this;
        }

        /**
         * Sets the port of the host to connect to.
         * <P>
         * The default port is 443.
         * @param port The port of the host
         * @return This object
         */
        public NerdGraphApi.Builder port(int port)
        {
            this.port = port;
            return this;
        }

        /**
         * Sets the personal key used to authenticate the connection.
         * @param key The personal key
         * @return This object
         */
        public NerdGraphApi.Builder personalKey(String key)
        {
            this.provider = new PersonalKeyHttpClientProvider(key);
            return this;
        }

        /**
         * Returns the configured Insights API client instance
         * @return The Insights API client instance
         */
        public NerdGraphApi build()
        {
            return new NerdGraphApi(hostname, port, provider);
        }
    }
}
