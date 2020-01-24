package com.opsmatters.newrelic.api.httpclient.deserializers.graphql;

import com.google.gson.*;
import com.opsmatters.newrelic.api.model.graphql.GraphQLResponse;

import java.lang.reflect.Type;

/**
 * Deserializer class for GraphQLResponse.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class GraphQLResponseDeserializer implements JsonDeserializer<GraphQLResponse>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to
     * @param context The JSON deserialization context
     * @return The GraphQL response
     */
    @Override
    public GraphQLResponse deserialize(JsonElement element, Type type, JsonDeserializationContext context)
            throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        JsonElement data = obj.get("data");
        if(data != null && data.isJsonObject())
            return gson.fromJson(data, GraphQLResponse.class);
        return null;
    }
}
