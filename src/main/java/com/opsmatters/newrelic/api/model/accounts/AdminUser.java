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

package com.opsmatters.newrelic.api.model.accounts;

/**
 * Represents a New Relic account admin user.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class AdminUser extends User
{
    private String state;

    /**
     * Default constructor.
     */
    public AdminUser()
    {
    }
    
    /**
     * Returns the state of the user.
     * @return The state of the user
     */
    public String getState()
    {
        return state;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "AdminUser ["+super.toString()
            +", state="+state
            +"]";
    }
}