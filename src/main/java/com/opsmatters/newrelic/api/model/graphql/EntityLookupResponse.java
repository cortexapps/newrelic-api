package com.opsmatters.newrelic.api.model.graphql;

import java.util.List;
import java.util.Locale;

/**
 * Represents Graph QL response data.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class EntityLookupResponse {

    private Actor actor;

    public EntityLookupResponse() {
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
        private List<Entity> entities;
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

        public List<Entity> getEntities() {
            return entities;
        }

        public void setEntities(List<Entity> entities) {
            this.entities = entities;
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
        private String name;
        private RelatedEntities relatedEntities;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public RelatedEntities getRelatedEntities() {
            return relatedEntities;
        }

        public void setRelatedEntities(RelatedEntities relatedEntities) {
            this.relatedEntities = relatedEntities;
        }
    }

    public class RelatedEntities {
        private List<RelatedEntitiesResults> results;
        public List<RelatedEntitiesResults> getResults() {
            return results;
        }

        public void setResults(List<RelatedEntitiesResults> results) {
            this.results = results;
        }
    }

    public class RelatedEntitiesResults {
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
        private List<EmbeddedEntityTag> tags;
        private Long accountId;
        private Long applicationId;

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

        public List<EmbeddedEntityTag> getTags() { return tags; }

        public void setTags(List<EmbeddedEntityTag> tags) { this.tags = tags; }

        public Long getAccountId() { return accountId; }

        public void setAccountId(Long accountId) { this.accountId = accountId; }

        public Long getApplicationId() { return applicationId; }

        public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }
    }

    public class EmbeddedEntityTag {
        private String key;
        private List<String> values;

        public String getKey() { return key; }

        public void setKey(String key) { this.key = key; }

        public List<String> getValues() { return values; }

        public void setValues(List<String> values) { this.values = values; }
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
        private String nextCursor;

        public List<EmbeddedEntity> getEntities() {
            return entities;
        }

        public void setEntities(List<EmbeddedEntity> entities) {
            this.entities = entities;
        }

        public String getNextCursor() {
            return nextCursor;
        }

        public void setNextCursor(String nextCursor) {
            this.nextCursor = nextCursor;
        }
    }
}
