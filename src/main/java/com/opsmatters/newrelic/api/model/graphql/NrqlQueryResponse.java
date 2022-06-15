package com.opsmatters.newrelic.api.model.graphql;

public abstract class NrqlQueryResponse {

    boolean isSuccess;

    public NrqlQueryResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
