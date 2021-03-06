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
import com.opsmatters.newrelic.api.model.transactions.KeyTransaction;
import com.opsmatters.newrelic.api.util.QueryParameterList;

/**
 * The set of operations used for key transactions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class KeyTransactionService extends BaseFluent
{
    /**
     * Constructor that takes a http context and API client.
     * @param httpContext The set of HTTP operations
     * @param client The client used to invoke the New Relic operations
     */
    public KeyTransactionService(HttpContext httpContext, NewRelicClient client)
    {
        super(httpContext, client);
    }

    /**
     * Returns the set of key transactions with the given query parameters.
     * @param queryParams The query parameters
     * @return The set of key transactions
     */
    public Collection<KeyTransaction> list(List<String> queryParams)
    {
        return HTTP.GET("/v2/key_transactions.json", null, queryParams, KEY_TRANSACTIONS).get();
    }

    /**
     * Returns the set of key transactions.
     * @return The set of key transactions
     */
    public Collection<KeyTransaction> list()
    {
        List<String> queryParams = null;
        return list(queryParams);
    }

    /**
     * Returns the set of key transactions for the given name.
     * @param name The name of the transactions
     * @return The set of transactions
     */
    public Collection<KeyTransaction> list(String name)
    {
        List<KeyTransaction> ret = new ArrayList<KeyTransaction>();
        Collection<KeyTransaction> transactions = list();
        for(KeyTransaction transaction : transactions)
        {
            if(name == null || transaction.getName().equals(name))
                ret.add(transaction);
        }
        return ret;
    }

    /**
     * Returns the key transaction for the given transaction id.
     * @param transactionId The id for the key transaction to return
     * @return The key transaction
     */
    public Optional<KeyTransaction> show(long transactionId)
    {
        return HTTP.GET(String.format("/v2/key_transactions/%d.json", transactionId), KEY_TRANSACTION);
    }

    /**
     * Returns a builder for the key transaction filters.
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
         * Adds the name filter to the filters.
         * @param name The name to filter on
         * @return This object
         */
        public FilterBuilder name(String name)
        {
            filters.add("filter[name]", name);
            return this;
        }

        /**
         * Adds the id filter to the filters.
         * @param ids The comma-separated list of ids to filter on
         * @return This object
         */
        public FilterBuilder ids(String ids)
        {
            filters.add("filter[ids]", ids);
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
