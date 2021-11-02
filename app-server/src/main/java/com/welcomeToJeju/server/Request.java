package com.welcomeToJeju.server;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.welcomeToJeju.moj.domain.Report;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.ReportUser;

public class Request {
  String command;
  String jsonData;
  Map<String,String> params;

  public Request(String command, String jsonData) {
    this.command = command;
    this.jsonData = jsonData;
  }

  public String getCommand() {
    return command;
  }

  public <T> T getObject(Class<T> type) {
    return new Gson().fromJson(jsonData, type);
  }

  public static Collection<Report> getReportInheritedChildObjects(String jsonStr,String classAttribute) {
    Gson gson = new GsonBuilder()
        .registerTypeAdapter(Report.class, new JsonDeserializer<Report>() {
          @Override
          public Report deserialize(JsonElement json, Type typeOfT,
              JsonDeserializationContext context) throws JsonParseException {

            JsonObject jsonObject = json.getAsJsonObject();

            if (jsonObject.get(classAttribute) != null) {
              return context.deserialize(jsonObject, ReportTheme.class);
            } else {
              return context.deserialize(jsonObject, ReportUser.class);
            }
          }
        })
        .create();
    Type collectionType = TypeToken.getParameterized(Collection.class, Report.class).getType();
    return gson.fromJson(jsonStr, collectionType);
  }

  @SuppressWarnings("unchecked")
  public String getParameter(String name) {
    try {
      if (params == null) {
        params = new Gson().fromJson(jsonData, Map.class);
      }
      return params.get(name);

    } catch (Exception e) {
      return null;
    }
  }

}
