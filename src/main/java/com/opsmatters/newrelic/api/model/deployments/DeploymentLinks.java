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

package com.opsmatters.newrelic.api.model.deployments;

/**
 * Represents a set of New Relic deployment links.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class DeploymentLinks
{
    private Long application;
  
    /**
     * Default constructor.
     */
    public DeploymentLinks()
    {
    }

    /**
     * Sets the application of the deployment.
     * @param application The application of the deployment
     */
    public void setApplication(Long application)
    {
        this.application = application;
    }

    /**
     * Returns the application of the deployment.
     * @return The application of the deployment
     */
    public Long getApplication()
    {
        return application;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "DeploymentLinks [application="+application+"]";
    }
}