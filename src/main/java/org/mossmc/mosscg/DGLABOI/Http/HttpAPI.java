package org.mossmc.mosscg.DGLABOI.Http;

import com.alibaba.fastjson.JSONObject;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

public class HttpAPI {
    public static String APIAddress = "https://dungeon-server.com:8445";

    public static void initAPI() {
        System.setProperty("sun.net.client.defaultConnectTimeout", "3000");
        System.setProperty("sun.net.client.defaultReadTimeout", "3000");
        System.setProperty("http.keepAlive", "false");
    }

    public static JSONObject getReturnData(String request,JSONObject data) throws Exception{
        URL targetURL = new URL(APIAddress + "/" + request);
        HttpURLConnection connection = (HttpURLConnection) targetURL.openConnection();
        connection.setRequestProperty("Connection", "close");
        connection.setRequestProperty("User-Agent", "application/x-www-form-urlencoded");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));
        writer.write(data.toString());
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;
        StringBuilder readInfo = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            readInfo.append(inputLine);
        }
        reader.close();
        return JSONObject.parseObject(readInfo.toString());
    }
}
