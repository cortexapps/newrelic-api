package com.opsmatters.newrelic.api.httpclient.deserializers.graphql;

import com.google.gson.*;
import com.opsmatters.newrelic.api.model.graphql.NrqlErrorResponse;
import com.opsmatters.newrelic.api.model.graphql.NrqlQueryResponse;
import com.opsmatters.newrelic.api.model.graphql.NrqlSuccessResponse;

import java.lang.reflect.Type;

/**
 * Deserializer class for GraphQLResponse.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class NrqlQueryResponseDeserializer implements JsonDeserializer<NrqlQueryResponse>
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
    public NrqlQueryResponse deserialize(JsonElement element, Type type, JsonDeserializationContext context)
            throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        JsonElement data = obj.get("data");
        JsonElement errors = obj.get("errors");
        if(data != null && data.isJsonObject())
            return gson.fromJson(data, NrqlSuccessResponse.class);
        else if(errors != null && obj.isJsonObject()) {
            return gson.fromJson(obj, NrqlErrorResponse.class);
        }
        return null;
    }
}
