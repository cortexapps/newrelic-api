package com.opsmatters.newrelic.api.model.graphql;

import java.util.List;

public class SloDefinitionResponse {
    private Actor actor;

    public SloDefinitionResponse() {
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
        private ServiceLevel serviceLevel;

        public ServiceLevel getServiceLevel() {
            return serviceLevel;
        }

        public void setServiceLevel(ServiceLevel serviceLevel) {
            this.serviceLevel = serviceLevel;
        }
    }

    public class ServiceLevel {
        private List<Indicator> indicators;

        public List<Indicator> getIndicators() {
            return indicators;
        }

        public void setIndicators(List<Indicator> indicators) {
            this.indicators = indicators;
        }
    }

    public class Indicator {
        private String name;
        private List<Objective> objectives;
        public ResultQueries resultQueries;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Objective> getObjectives() {
            return objectives;
        }

        public void setObjectives(List<Objective> objectives) {
            this.objectives = objectives;
        }

        public ResultQueries getResultQueries() {
            return resultQueries;
        }

        public void setResultQueries(ResultQueries resultQueries) {
            this.resultQueries = resultQueries;
        }
    }

    public class Objective {
        private Double target;
        private TimeWindow timeWindow;

        public Double getTarget() {
            return target;
        }

        public void setTarget(Double target) {
            this.target = target;
        }

        public TimeWindow getTimeWindow() {
            return timeWindow;
        }

        public void setTimeWindow(TimeWindow timeWindow) {
            this.timeWindow = timeWindow;
        }
    }

    public class TimeWindow {
        private Rolling rolling;

        public Rolling getRolling() {
            return rolling;
        }

        public void setRolling(Rolling rolling) {
            this.rolling = rolling;
        }
    }

    public class Rolling {
        private Integer count;
        private String unit;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    public class ResultQueries {
        private QueryIndicator indicator;

        public QueryIndicator getIndicator() {
            return indicator;
        }

        public void setIndicator(QueryIndicator indicator) {
            this.indicator = indicator;
        }
    }

    public class QueryIndicator {
        private String nrql;

        public String getNrql() {
            return nrql;
        }

        public void setNrql(String nrql) {
            this.nrql = nrql;
        }
    }
}
