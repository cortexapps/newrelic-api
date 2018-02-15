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
import com.opsmatters.newrelic.api.model.alerts.conditions.InfraAlertCondition;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for infrastructure alert conditions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class InfraAlertConditionService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public InfraAlertConditionService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of alert conditions for the given policy id.
     * @param policyId The id of the alert policy to return the conditions for
     * @return The set of alert conditions
     */
    public Collection<InfraAlertCondition> list(long policyId)
    {
        QueryParameterList queryParams = new QueryParameterList();
        queryParams.add("policy_id", policyId);
        return HTTP.GET("/v2/alerts/conditions", null, queryParams, INFRA_ALERT_CONDITIONS).get();
    }

    /**
     * Returns the set of alert conditions for the given policy id and name.
     * @param policyId The id of the alert policy to return the conditions for
     * @param name The name of the conditions
     * @return The set of alert conditions
     */
    public Collection<InfraAlertCondition> list(long policyId, String name)
    {
        List<InfraAlertCondition> ret = new ArrayList<InfraAlertCondition>();
        Collection<InfraAlertCondition> conditions = list(policyId);
        for(InfraAlertCondition condition : conditions)
        {
            if(condition.getName().equals(name))
                ret.add(condition);
        }
        return ret;
    }

    /**
     * Returns the infrastructure alert condition with the given id.
     * @param conditionId The id of the alert condition to return
     * @return The alert condition
     */
    public Optional<InfraAlertCondition> show(long conditionId)
    {
        return HTTP.GET(String.format("/v2/alerts/conditions/%d", conditionId), null, null, INFRA_ALERT_CONDITION);
    }
   
    /**
     * Creates the given infrastructure alert condition.
     * @param condition The alert condition to create
     * @return The alert condition that was created
     */
    public Optional<InfraAlertCondition> create(InfraAlertCondition condition)
    {
        return HTTP.POST("/v2/alerts/conditions", condition, INFRA_ALERT_CONDITION);
    }

    /**
     * Updates the given infrastructure alert condition.
     * @param condition The alert condition to update
     * @return The alert condition that was updated
     */
    public Optional<InfraAlertCondition> update(InfraAlertCondition condition)
    {
        return HTTP.PUT(String.format("/v2/alerts/conditions/%d", condition.getId()), condition, INFRA_ALERT_CONDITION);
    }

    /**
     * Deletes the infrastructure alert condition with the given id.
     * @param conditionId The id of the alert condition to delete
     * @return This object
     */
    public InfraAlertConditionService delete(long conditionId)
    {
        HTTP.DELETE(String.format("/v2/alerts/conditions/%d", conditionId));       
        return this;
    }
}
