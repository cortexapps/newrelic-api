package com.opsmatters.newrelic.api.model.graphql;

import java.util.List;

/**
 * Represents Graph QL response data.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class EntityAccountLookupResponse {

    private Actor actor;

    public EntityAccountLookupResponse() {
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }


    public class Actor {
        private EntitySearch entitySearch;

        public EntitySearch getEntitySearch() {
            return entitySearch;
        }

        public void setEntitySearch(EntitySearch entitySearch) {
            this.entitySearch = entitySearch;
        }
    }

    public class EntitySearch {
        private Results results;

        public Results getResults() {
            return results;
        }

        public void setResults(Results results) {
            this.results = results;
        }
    }

    public class Results {
        private List<Entity> entities;

        public List<Entity> getEntities() {
            return entities;
        }

        public void setEntities(List<Entity> entities) {
            this.entities = entities;
        }
    }

    public class Entity {
        private Account account;

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }
    }

    public class Account {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
