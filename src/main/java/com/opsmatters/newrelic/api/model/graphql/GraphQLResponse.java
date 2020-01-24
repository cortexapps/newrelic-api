package com.opsmatters.newrelic.api.model.graphql;

/**
 * Represents Graph QL response data.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class GraphQLResponse {

    private Actor actor;

    public GraphQLResponse() {
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }


    public class Actor {
        private Account account;

        public Actor() {
        }

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }
    }

    public class Account {
        private Nrql nrql;

        public Account() {
        }

        public Nrql getNrql() {
            return nrql;
        }

        public void setNrql(Nrql nrql) {
            this.nrql = nrql;
        }
    }

    public class Nrql {
        private String embeddedChartUrl;

        public Nrql()
        {
        }

        public String getEmbeddedChartUrl() {
            return embeddedChartUrl;
        }

        public void setEmbeddedChartUrl(String embeddedChartUrl) {
            this.embeddedChartUrl = embeddedChartUrl;
        }
    }
}
