package com.opsmatters.newrelic.api.model.graphql;

import java.util.List;

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
        private Entity entity;
        private EntitySearch entitySearch;

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }

        public Entity getEntity() {
            return entity;
        }

        public void setEntity(Entity entity) {
            this.entity = entity;
        }

        public EntitySearch getEntitySearch() {
            return entitySearch;
        }

        public void setEntitySearch(EntitySearch entitySearch) {
            this.entitySearch = entitySearch;
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
        private String embeddedChartUrl;

        public String getEmbeddedChartUrl() {
            return embeddedChartUrl;
        }

        public void setEmbeddedChartUrl(String embeddedChartUrl) {
            this.embeddedChartUrl = embeddedChartUrl;
        }
    }

    public class Entity {
        private List<Relationship> relationships;

        public List<Relationship> getRelationships() {
            return relationships;
        }

        public void setRelationships(List<Relationship> relationships) {
            this.relationships = relationships;
        }
    }

    public class Relationship {
        private Source source;
        private Source target;

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public Source getTarget() {
            return target;
        }

        public void setTarget(Source target) {
            this.target = target;
        }
    }

    public class Source {
        private EmbeddedEntity entity;

        public EmbeddedEntity getEntity() {
            return entity;
        }

        public void setEntity(EmbeddedEntity entity) {
            this.entity = entity;
        }
    }

    public class EmbeddedEntity {
        private String name;
        private String guid;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
        private List<EmbeddedEntity> entities;

        public List<EmbeddedEntity> getEntities() {
            return entities;
        }

        public void setEntities(List<EmbeddedEntity> entities) {
            this.entities = entities;
        }
    }
}
