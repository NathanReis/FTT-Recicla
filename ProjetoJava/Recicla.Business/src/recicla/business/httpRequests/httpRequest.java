/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.httpRequests;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author vitorlupinetti
 */
public class httpRequest {
    private  static String baseUrl = "http://25.101.216.49:8080/WEB-INF/webresources/";
    private static final String USER_AGENT = "Mozilla/5.0";
    
     public static String sendGet(String url) throws Exception {
 	String finalUrl = baseUrl + url;
        URL obj = new URL(finalUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + finalUrl);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();

        return response.toString();
 
    }
     
     public static String sendPost(String json, String url) throws Exception {

        try {
            // Cria um objeto HttpURLConnection:
            HttpURLConnection request = (HttpURLConnection) new URL(baseUrl + url).openConnection();

            try {
                // Define que a conexão pode enviar informações e obtê-las de volta:
                request.setDoOutput(true);
                request.setDoInput(true);

                // Define o content-type:
                request.setRequestProperty("Content-Type", "application/json");

                // Define o método da requisição:
                request.setRequestMethod("POST");

                // Conecta na URL:
                request.connect();

                // Escreve o objeto JSON usando o OutputStream da requisição:
                try (OutputStream outputStream = request.getOutputStream()) {
                    outputStream.write(json.getBytes("UTF-8"));
                }

                // Caso você queira usar o código HTTP para fazer alguma coisa, descomente esta linha.
                int response = request.getResponseCode();
                
                if(response != 200)
                {
                    return "invalid";
                }
                System.out.print(response);

                return readResponse(request);
            } finally {
                request.disconnect();
            }
        } catch (IOException ex) {
            throw new Exception(ex);
        }
    }
   
    private static String readResponse(HttpURLConnection request) throws IOException {
        ByteArrayOutputStream os;
        try (InputStream is = request.getInputStream()) {
            os = new ByteArrayOutputStream();
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        }
            return new String(os.toByteArray());
    }
    
}


