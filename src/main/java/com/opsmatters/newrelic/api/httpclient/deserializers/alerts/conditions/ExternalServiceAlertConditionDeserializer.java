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

package com.opsmatters.newrelic.api.httpclient.deserializers.alerts.conditions;

import java.lang.reflect.Type;
import com.google.gson.*;
import com.opsmatters.newrelic.api.model.alerts.conditions.ExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.ApmExternalServiceAlertCondition;
import com.opsmatters.newrelic.api.model.alerts.conditions.MobileExternalServiceAlertCondition;

/**
 * Deserializer class for external service alert conditions.
 * 
 * @author Gerald Curley (opsmatters)
 */
public class ExternalServiceAlertConditionDeserializer implements JsonDeserializer<ExternalServiceAlertCondition>
{
    private static Gson gson = new Gson();

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the specified type.
     * @param element The Json data being deserialized
     * @param type The type of the Object to deserialize to 
     * @param context The JSON deserialization context
     * @return The alert condition 
     */
    @Override
    public ExternalServiceAlertCondition deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException
    {
        JsonObject obj = element.getAsJsonObject();
        JsonElement condition = obj.get("external_service_condition");
        if(condition != null && condition.isJsonObject())
        {
            JsonElement conditionType = condition.getAsJsonObject().get("type");
            if(conditionType != null)
            {
                switch(ExternalServiceAlertCondition.ConditionType.fromValue(conditionType.getAsString()))
                {
                    case APM:
                        return gson.fromJson(condition, ApmExternalServiceAlertCondition.class);
                    case MOBILE:
                        return gson.fromJson(condition, MobileExternalServiceAlertCondition.class);
                }
            }
        }
        return null;
    }
}
