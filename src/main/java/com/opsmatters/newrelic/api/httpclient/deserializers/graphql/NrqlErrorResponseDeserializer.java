package com.opsmatters.newrelic.api.httpclient.deserializers.graphql;

import com.google.gson.*;
import com.opsmatters.newrelic.api.model.graphql.AccountLookupResponse;
import com.opsmatters.newrelic.api.model.graphql.NrqlErrorResponse;

import java.lang.reflect.Type;

/**
 * Deserializer class for GraphQLResponse.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class NrqlErrorResponseDeserializer implements JsonDeserializer<NrqlErrorResponse>
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
    public NrqlErrorResponse deserialize(JsonElement element, Type type, JsonDeserializationContext context)
            throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        JsonElement data = obj.get("errors");
        if(data != null && obj.isJsonObject())
            return gson.fromJson(obj, NrqlErrorResponse.class);
        return null;
    }
}
