package com.opsmatters.newrelic.api.services;

import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.exceptions.ErrorResponseException;
import com.opsmatters.newrelic.api.model.ErrorDetail;
import com.opsmatters.newrelic.api.model.ErrorDetails;
import com.opsmatters.newrelic.api.model.graphql.*;

import java.util.List;
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
     * Returns the EntityAccountResponse
     * @param entityName The entity name to search for
     * @return The account data
     */
    public Optional<EntityAccountLookupResponse> searchEntityAccounts(String entityName)
    {
        GraphQLRequest request = GraphQLRequest.from(constructEntityAccountLookupQuery(entityName));
        return HTTP.POST("/graphql", request, ENTITY_ACCOUNT_LOOKUP);
    }

    /**
     * Returns the EntityAccountResponse
     * @param entityNames The entity names to search for
     * @return The account data
     */
    public Optional<EntityAccountLookupResponse> searchEntitiesAccounts(List<String> entityNames)
    {
        GraphQLRequest request = GraphQLRequest.from(constructEntitiesAccountLookupQuery(entityNames));
        return HTTP.POST("/graphql", request, ENTITY_ACCOUNT_LOOKUP);
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

    /**
     * Returns the SloDefinitionResponse
     * @param newRelicEntityGuid The entity guid to search for
     * @return The query data
     */
    public Optional<SloDefinitionResponse> searchSLODefinitions(String newRelicEntityGuid)
    {
        GraphQLRequest request = GraphQLRequest.from(constructSLODefinitionQuery(newRelicEntityGuid));
        return HTTP.POST("/graphql", request, SLO_DEFINITION);
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

    private String constructEntityAccountLookupQuery(String entityName) {
        return "{" +
                "  actor {" +
                "    entitySearch(query: \"name = '" + entityName + "'\") {" +
                "      results {" +
                "        entities {" +
                "          account {" +
                "            id" +
                "          }" +
                "        }" +
                "      }" +
                "    }" +
                "  }" +
                "}";
    }

    private String constructEntitiesAccountLookupQuery(List<String> entityNames) {
        return "{" +
                "  actor {" +
                "    entitySearch(query: \"name in (" + entityNames.stream().collect(Collectors.joining(",", "'", "'")) + ")\") {" +
                "      results {" +
                "        entities {" +
                "          account {" +
                "            id" +
                "          }" +
                "        }" +
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

    private String constructSLODefinitionQuery(String guid) {
        return "{" +
               "  actor {" +
               "    entities(guids: \"" + guid + "\") {" +
               "      serviceLevel {" +
               "        indicators {" +
               "          guid" +
               "          name" +
               "          objectives {" +
               "            target" +
               "            timeWindow {" +
               "              rolling {" +
               "                count" +
               "                unit" +
               "              }" +
               "            }" +
               "          }" +
               "          resultQueries {" +
               "            indicator {" +
               "              nrql" +
               "            }" +
               "          }" +
               "        }" +
               "      }" +
               "    }" +
               "  }" +
              "}";
    }
}
