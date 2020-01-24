package com.opsmatters.newrelic.api.services;

import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.graphql.GraphQLResponse;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * The set of operations used for GraphQL queries.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class GraphQLService extends BaseFluent {
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the Graph QL operations
     */
    public GraphQLService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the GraphQL response
     * @param query The NRQL query to execute
     * @return The query data
     */
    public Optional<GraphQLResponse> query(String query)
    {
        String requestPayload = String.format("{\"query\" : \"%s\"}", StringEscapeUtils.escapeJson(query));
        return HTTP.POST("/graphql", requestPayload, GRAPH_QL);
    }
}
