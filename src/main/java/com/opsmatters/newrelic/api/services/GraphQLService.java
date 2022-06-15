package com.opsmatters.newrelic.api.services;

import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.exceptions.ErrorResponseException;
import com.opsmatters.newrelic.api.model.ErrorDetail;
import com.opsmatters.newrelic.api.model.ErrorDetails;
import com.opsmatters.newrelic.api.model.graphql.*;

import java.util.stream.Collectors;

/**
 * The set of operations used for GraphQL queries.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class GraphQLService extends BaseFluent {
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the GraphQL operations
     */
    public GraphQLService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the GraphQL response
     * @param query The search query to execute
     * @return The query data
     */
    public Optional<EntityLookupResponse> searchEntities(String query)
    {
        GraphQLRequest request = GraphQLRequest.from(query);
        return HTTP.POST("/graphql", request, ENTITY_LOOKUP);
    }

    /**
     * Returns the GraphQL response
     * @return The query data
     */
    public Optional<AccountLookupResponse> searchAccounts()
    {
        GraphQLRequest request = GraphQLRequest.from(constructAccountLookupQuery());
        return HTTP.POST("/graphql", request, ACCOUNT_LOOKUP);
    }

    /**
     * Returns the GraphQL response
     * @param query The NRQL query to execute
     * @return The query data
     */
    public Optional<NrqlQueryResponse> runNrql(int accountId, String query)
    {
        GraphQLRequest request = GraphQLRequest.from(constructNrqlQuery(accountId, query));
        return HTTP.POST("/graphql", request, NRQL_QUERY);
    }

    private String constructNrqlQuery(int accountId, String query) {
        return "{" +
                "  actor {" +
                "    account(id: " + accountId + ") {" +
                "      nrql(query: \"" + query + "\") {" +
                "        results" +
                "      }" +
                "    }" +
                "  }" +
                "}";
    }

    private String constructAccountLookupQuery() {
        return "{" +
                "  actor {" +
                "    organization {" +
                "      accountManagement {" +
                "        managedAccounts {" +
                "          id" +
                "        }" +
                "      }" +
                "    }" +
                "  }" +
                "}";
    }
}
