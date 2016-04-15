package com.gcm.pusher;

import com.gcm.pusher.device.Device;
import com.gcm.pusher.log.Log;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alex on 14/04/16.
 */
public class Pusher {

    public static final String API_KEY = "AIzaSyCNjvf5jFNYWbZOnF58e3GEeyDhErLh4Zk";

    /**
     * Invia una notifica push a un device
     */
    public static void push(Device device, String msg){
        String resp="";

        try {

            // prepara il payload di dati del messaggio
            JSONObject jData = new JSONObject();
            jData.put("messagetype", "alarm");
            jData.put("timestamp", "0000000000");
            jData.put("details", msg);

            // Prepara un JSON con il contenuto del messaggio GCM - a chi mandare cosa
            JSONObject jGcmData = new JSONObject();
            String token = device.getToken();
            jGcmData.put("to", token);
            jGcmData.put("data", jData);

            // Crea una connessione per inviare la richiesta del messaggio GCM
            URL url = new URL("https://gcm-http.googleapis.com/gcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + getApiKey());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Invia il messaggio GCM
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());

            // Legge la risposta GCM
            InputStream inputStream = conn.getInputStream();
            resp = IOUtils.toString(inputStream);
            Log.d("PUSH",resp);

        } catch (Exception e) {

            resp=e.getMessage();
            Log.e("PUSH",resp);

            e.printStackTrace();
        }

    }



    private static String getApiKey(){
        return API_KEY;
    }



}
