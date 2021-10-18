package com.welcomeToJeju.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Collection;

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

public class RequestAgent {

	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";

	String ip;
	int port;

	String status;
	String jsonData;

	public RequestAgent(String ip, int port) throws Exception {
		this.ip = ip;
		this.port = port;
	}

	public void request(String command, Object value) throws Exception {
		try (Socket socket = new Socket(ip, port);
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			out.println(command);

			if (value != null) {
				out.println(new Gson().toJson(value));
			} else {
				out.println();
			}
			out.flush();

			status = in.readLine();
			jsonData = in.readLine();

		}
	}

	public String getStatus() {
		return status;
	}

	public <T> T getObject(Class<T> type) {
		return new Gson().fromJson(jsonData, type);
	}

	public <E> Collection<E> getObjects(Class<E> domainType) {
		Type type = TypeToken.getParameterized(Collection.class, domainType).getType();
		return new Gson().fromJson(jsonData, type);
	}

}
