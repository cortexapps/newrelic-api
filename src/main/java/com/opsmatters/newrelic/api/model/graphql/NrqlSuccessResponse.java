package com.opsmatters.newrelic.api.model.graphql;

import com.google.gson.JsonArray;

/**
 * Represents GraphQL response data.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class NrqlSuccessResponse extends NrqlQueryResponse {

    private Actor actor;

    public NrqlSuccessResponse() {
        super(true);
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }


    public class Actor {

        private Account account;

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }
    }

    public class Account {

        private Nrql nrql;

        public Nrql getNrql() {
            return nrql;
        }

        public void setNrql(Nrql nrql) {
            this.nrql = nrql;
        }
    }

    public class Nrql {

        private JsonArray results;

        public JsonArray getResults() {
            return results;
        }

        public void setResults(JsonArray results) {
            this.results = results;
        }
    }
}
