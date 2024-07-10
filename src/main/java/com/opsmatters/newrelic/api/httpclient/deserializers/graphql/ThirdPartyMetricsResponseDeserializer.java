package com.opsmatters.newrelic.api.httpclient.deserializers.graphql;

import com.google.gson.*;
import com.opsmatters.newrelic.api.model.graphql.ThirdPartyMetricsResponse;

import java.lang.reflect.Type;

public class ThirdPartyMetricsResponseDeserializer implements JsonDeserializer<ThirdPartyMetricsResponse> {
    private static Gson gson = new Gson();

    @Override
    public ThirdPartyMetricsResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        JsonElement data = obj.get("data");
        if (data != null && data.isJsonObject())
            return gson.fromJson(data, ThirdPartyMetricsResponse.class);
        return null;
    }
}
