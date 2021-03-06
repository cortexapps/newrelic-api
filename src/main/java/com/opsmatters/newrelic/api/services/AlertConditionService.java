/*
 * Copyright 2018 Gerald Curley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.opsmatters.newrelic.api.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import com.google.common.base.Optional;
import com.opsmatters.newrelic.api.NewRelicClient;
import com.opsmatters.newrelic.api.model.alerts.conditions.AlertCondition;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for alert conditions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AlertConditionService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public AlertConditionService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of alert conditions for the given query parameters.
     * @param queryParams The query parameters
     * @return The set of alert conditions
     */
    public Collection<AlertCondition> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/alerts_conditions.json", null, queryParams, ALERT_CONDITIONS).get();
    }

    /**
     * Returns the set of alert conditions for the given policy id.
     * @param policyId The id of the alert policy to return the conditions for
     * @return The set of alert conditions
     */
    public Collection<AlertCondition> list(long policyId)
    {
        return list(filters().policyId(policyId).build());
    }

    /**
     * Returns the set of alert conditions for the given policy id and name.
     * @param policyId The id of the alert policy to return the conditions for
     * @param name The name of the conditions
     * @return The set of alert conditions
     */
    public Collection<AlertCondition> list(long policyId, String name)
    {
        List<AlertCondition> ret = new ArrayList<AlertCondition>();
        Collection<AlertCondition> conditions = list(policyId);
        for(AlertCondition condition : conditions)
        {
            if(condition.getName().equals(name))
                ret.add(condition);
        }
        return ret;
    }

    /**
     * Returns the alert condition with the given id.
     * <P>
     * This is needed because the API does not contain an operation to get a condition using the id directly.
     * @param policyId The id of the policy the condition belongs to
     * @param conditionId The id of the alert condition to return
     * @return The alert condition
     */
    public Optional<AlertCondition> show(long policyId, long conditionId)
    {
        Optional<AlertCondition> ret = Optional.absent();
        Collection<AlertCondition> conditions = list(policyId);
        for(AlertCondition condition : conditions)
        {
            if(condition.getId() == conditionId)
                ret = Optional.of(condition);
        }
        return ret;
    }
   
    /**
     * Creates the given alert condition.
     * @param policyId The id of the policy to add the alert condition to
     * @param condition The alert condition to create
     * @return The alert condition that was created
     */
    public Optional<AlertCondition> create(long policyId, AlertCondition condition)
    {
        return HTTP.POST(String.format("/v2/alerts_conditions/policies/%d.json", policyId), condition, ALERT_CONDITION);
    }

    /**
     * Updates the given alert condition.
     * @param condition The alert condition to update
     * @return The alert condition that was updated
     */
    public Optional<AlertCondition> update(AlertCondition condition)
    {
        return HTTP.PUT(String.format("/v2/alerts_conditions/%d.json", condition.getId()), condition, ALERT_CONDITION);
    }

    /**
     * Deletes the alert condition with the given id.
     * @param conditionId The id of the alert condition to delete
     * @return This object
     */
    public AlertConditionService delete(long conditionId)
    {
        HTTP.DELETE(String.format("/v2/alerts_conditions/%d.json", conditionId));       
        return this;
    }

    /**
     * Returns a builder for the alert condition filters.
     * @return The builder instance.
     */
    public static FilterBuilder filters()
    {
        return new FilterBuilder();
    }

    /**
     * Builder to make filter construction easier.
     */
    public static class FilterBuilder
    {
        private QueryParameterList filters = new QueryParameterList();

        /**
         * Adds the policyId filter to the filters.
         * @param policyId The policyId to filter on
         * @return This object
         */
        public FilterBuilder policyId(long policyId)
        {
            if(policyId > 0L)
                filters.add("policy_id", policyId);
            return this;
        }

        /**
         * Adds the page filter to the filters.
         * @param page The page to filter on
         * @return This object
         */
        public FilterBuilder page(int page)
        {
            if(page >= 0)
                filters.add("page", page);
            return this;
        }

        /**
         * Returns the configured filters
         * @return The filters
         */
        public List<String> build()
        {
            return filters;
        }
    }
}
