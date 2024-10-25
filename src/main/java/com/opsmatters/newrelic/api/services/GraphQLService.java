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
     * Returns the GraphQL response
     * @param query The NRQL query to execute
     * @param timeout The NRQL query execution timeout
     * @return The query data
     */
    public Optional<NrqlQueryResponse> runNrql(int accountId, String query, int timeout)
    {
        GraphQLRequest request = GraphQLRequest.from(constructNrqlQueryWithTimeout(accountId, query, timeout));
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

    /**
     * Returns the ThirdPartyMetricsResponse
     * @param guids The entity guid to search for
     * @return The metrics data
     */
    public Optional<ThirdPartyMetricsResponse> searchThirdPartyMetrics(List<String> guids)
    {
        GraphQLRequest request = GraphQLRequest.from(constructThirdPartyMetrics(guids));
        return HTTP.POST("/graphql", request, THIRD_PARTY_METRICS_RESPONSE);
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

    private String constructNrqlQueryWithTimeout(int accountId, String query, int timeout) {
        return "{" +
                "  actor {" +
                "    account(id: " + accountId + ") {" +
                "      nrql(query: \"" + query + "\", timeout: " + timeout + ") {" +
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
               "      tags {" +
               "        key" +
               "        values" +
               "      }" +
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

    private String constructThirdPartyMetrics(List<String> guids) {
        return "{" +
               "  actor {" +
               "    entities(guids: [" + guids.stream().collect(Collectors.joining(",", "\"", "\"")) + "]) {" +
               "      ... on ThirdPartyServiceEntity {" +
               "        guid" +
               "        name" +
               "        goldenMetrics {" +
               "          metrics {" +
               "            query" +
               "            name" +
               "            metricName" +
               "            title" +
               "            unit" +
               "          }" +
               "        }" +
               "      }" +
               "    }" +
               "  }" +
               "}";
    }
}
