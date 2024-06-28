package com.opsmatters.newrelic.api.httpclient.deserializers.graphql;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.opsmatters.newrelic.api.model.graphql.SloDefinitionResponse;

import java.lang.reflect.Type;

public class SloDefinitionResponseDeserializer implements JsonDeserializer<SloDefinitionResponse> {

    private static Gson gson = new Gson();

    @Override
    public SloDefinitionResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        JsonElement data = obj.get("data");
        if (data != null && data.isJsonObject())
            return gson.fromJson(data, SloDefinitionResponse.class);
        return null;
    }
}
