package com.opsmatters.newrelic.api.httpclient.serializers.graphql;

import com.google.gson.*;
import com.opsmatters.newrelic.api.model.graphql.GraphQLResponse;

import java.lang.reflect.Type;

/**
 * Serializer class for GraphQLResponse.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class GraphQLResponseSerializer implements JsonSerializer<GraphQLResponse>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the specified type.
     * @param response The response being serialized
     * @param type The type of the Object to deserialize to
     * @param context The JSON serialization context
     * @return The JSON data that was serialized
     */
    @Override
    public JsonElement serialize(GraphQLResponse response, Type type, JsonSerializationContext context)
    {
        JsonElement element = gson.toJsonTree(response, type);
        JsonObject obj = new JsonObject();
        obj.add("data", element);
        return obj;
    }
}
