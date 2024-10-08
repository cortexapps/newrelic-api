package com.opsmatters.newrelic.api.model.graphql;

public class GraphQLRequest {

    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public static GraphQLRequest from(String query) {
        GraphQLRequest out = new GraphQLRequest();
        out.setQuery(query);
        return out;
    }
}
