package com.opsmatters.newrelic.api.model.graphql;

import java.util.List;

public class ThirdPartyMetricsResponse {
    private Actor actor;

    public ThirdPartyMetricsResponse() {
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public class Actor {
        private List<Entity> entities;

        public List<Entity> getEntities() {
            return entities;
        }

        public void setEntities(List<Entity> entities) {
            this.entities = entities;
        }
    }

    public class Entity {
        private String guid;
        private String name;
        private GoldenMetrics goldenMetrics;

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

        public GoldenMetrics getGoldenMetrics() {
            return goldenMetrics;
        }

        public void setGoldenMetrics(GoldenMetrics goldenMetrics) {
            this.goldenMetrics = goldenMetrics;
        }
    }

    public class GoldenMetrics {
        private List<Metrics> metrics;

        public List<Metrics> getMetrics() {
            return metrics;
        }

        public void setMetrics(List<Metrics> metrics) {
            this.metrics = metrics;
        }
    }

    public class Metrics {
        private String metricName;
        private String name;
        private String query;
        private String title;
        private String unit;

        public String getMetricName() {
            return metricName;
        }

        public void setMetricName(String metricName) {
            this.metricName = metricName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
