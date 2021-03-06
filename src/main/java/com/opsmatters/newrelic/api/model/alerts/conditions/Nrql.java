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

package com.opsmatters.newrelic.api.model.alerts.conditions;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a New Relic nrql.  
 * 
 * @author Gerald Curley (opsmatters)
 */
public class Nrql
{
    // The field names
    public static final String QUERY = "query";
    public static final String SINCE_VALUE = "since_value";

    private String query;

    @SerializedName("since_value")
    private String sinceValue;
    
    /**
     * Default constructor.
     */
    public Nrql()
    {
    }
    
    /**
     * Sets the query of the nrql.
     * @param query The query of the nrql
     */
    public void setQuery(String query)
    {
        this.query = query;
    }

    /**
     * Returns the query of the nrql.
     * @return The query of the nrql
     */
    public String getQuery()
    {
        return query;
    }

    /**
     * Sets the since value of the nrql in minutes.
     * @param sinceValue The since value of the nrql
     */
    public void setSinceValue(String sinceValue)
    {
        this.sinceValue = sinceValue;
    }

    /**
     * Sets the since value of the nrql in minutes.
     * @param sinceValue The since value of the nrql
     */
    public void setSinceValue(int sinceValue)
    {
        setSinceValue(Integer.toString(sinceValue));
    }

    /**
     * Returns the since value of the nrql.
     * @return The since value of the nrql
     */
    public String getSinceValue()
    {
        return sinceValue;
    }
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        return "Nrql [query="+query
            +", sinceValue="+sinceValue
            +"]";
    }

    /**
     * Returns a builder for the nrql.
     * @return The builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to make nrql construction easier.
     */
    public static class Builder
    {
        private Nrql nrql = new Nrql();

        /**
         * Sets the query of the nrql.
         * @param query The query of the nrql
         * @return This object
         */
        public Builder query(String query)
        {
            nrql.setQuery(query);
            return this;
        }

        /**
         * Sets the since value of the nrql in minutes.
         * @param sinceValue The since value of the nrql
         * @return This object
         */
        public Builder sinceValue(String sinceValue)
        {
            nrql.setSinceValue(sinceValue);
            return this;
        }

        /**
         * Sets the since value of the nrql in minutes.
         * @param sinceValue The since value of the nrql
         * @return This object
         */
        public Builder sinceValue(int sinceValue)
        {
            nrql.setSinceValue(sinceValue);
            return this;
        }

        /**
         * Returns the configured nrql instance
         * @return The nrql instance
         */
        public Nrql build()
        {
            return nrql;
        }
    }
}