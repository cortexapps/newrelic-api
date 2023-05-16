package com.opsmatters.newrelic.api.httpclient.filters;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

/**
 * Filter to attach an API key used for authentication.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class PersonalKeyFilter implements ClientRequestFilter
{
    private String personalKey;

    /**
     * Constructor that takes a personal key.
     * @param personalKey The personal key
     */
    public PersonalKeyFilter(String personalKey)
    {
        this.personalKey = personalKey;
    }

    /**
     * Adds the personal key to the client request.
     * @param request The client request
     */
    public void filter(ClientRequestContext request) throws IOException
    {
        if(!request.getHeaders().containsKey("API-Key"))
            request.getHeaders().add("API-Key", this.personalKey);
    }
}
