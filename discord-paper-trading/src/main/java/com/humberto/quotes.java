package com.humberto;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


// quote of the day based on the current market status

public class quotes {
    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.api-ninjas.com/v2/randomquotes?categories=wisdom,success"))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the response status code
        if (response.statusCode() == 200) {
            System.out.println("API Response: " + response.body());
            // Here you would parse the JSON body to use the data
            String body = response.body();
            ObjectMapper mapper = new ObjectMapper();

            // Parse JSON array into a List of Maps
            List<Map<String, Object>> list = mapper.readValue(body, new TypeReference<List<Map<String, Object>>>(){});

            // First element of the array
            // this is going to be pass on to discord before any call to keep morale up
            // TO DO: Send to Discord
            Map<String, Object> first = list.get(0);

            Object quote = first.get("quotes");
            Object something = first.get("something");
            System.out.print("\n");
            System.out.printf("%s by %s", quote, something);

        } 
        else {
            System.out.println("API request failed with status code: " + response.statusCode());
        }
    }
}