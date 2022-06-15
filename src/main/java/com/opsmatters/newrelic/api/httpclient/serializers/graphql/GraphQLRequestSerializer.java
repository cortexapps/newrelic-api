package com.opsmatters.newrelic.api.httpclient.serializers.graphql;

import com.google.gson.*;
import com.opsmatters.newrelic.api.model.graphql.AccountLookupResponse;
import com.opsmatters.newrelic.api.model.graphql.GraphQLRequest;

import java.lang.reflect.Type;

/**
 * Serializer class for GraphQLResponse.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class GraphQLRequestSerializer implements JsonSerializer<GraphQLRequest>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the specified type.
     * @param request The request being serialized
     * @param type The type of the Object to deserialize to
     * @param context The JSON serialization context
     * @return The JSON data that was serialized
     */
    @Override
    public JsonElement serialize(GraphQLRequest request, Type type, JsonSerializationContext context)
    {
        return gson.toJsonTree(request, type);
    }
}
