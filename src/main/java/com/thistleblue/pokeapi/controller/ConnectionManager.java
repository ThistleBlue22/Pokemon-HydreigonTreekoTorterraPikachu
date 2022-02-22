package com.thistleblue.pokeapi.controller;

import com.thistleblue.pokeapi.model.Pokemon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class ConnectionManager {


    public static void urlSetup(){

    Random random = new Random();

    HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + random.nextInt(989))).build();
    pokemonAssignment(httpRequest);
    }

    private static void pokemonAssignment(HttpRequest httpRequest){
        HttpResponse<String> response;
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            StringBuilder sb = new StringBuilder();
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);

//            String pokemonType = pokemonType(sb, jsonObject);
//            sb.delete(0, sb.length());
//
//            String pokemonStats = pokemonStats(sb, jsonObject);
//            sb.delete(0, sb.length());

            String pokemonAbilities = pokemonAbilities(sb, jsonObject);



//            Pokemon pokemon = new Pokemon(jsonObject.get("id"), jsonObject.get("name"),
//                    jsonObject.get("height"), jsonObject.get("weight"), pokemonType, );
        } catch (IOException | InterruptedException | ParseException e) {
            e.printStackTrace();
        }

    }




    private static String pokemonType(StringBuilder sb, JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("types");
        sb.append("Types: ");

        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get("type");
            sb.append(jsonObject2.get("name"));
            if (i < jsonArray.size()-1){
                sb.append(", ");
            }
            else
                sb.append(".");
        }
        return sb.toString();
    }

    private static String pokemonStats(StringBuilder sb, JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("stats");
        sb.append("Stats: [");

        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get("stat");
            sb.append(jsonObject2.get("name")).append(": ").append(jsonObject1.get("base_stat"));
            if (i < jsonArray.size()-1){
                sb.append(", ");
            }
            else
                sb.append(".");
        }

        sb.append("]");
        return sb.toString();
    }

    private static String pokemonAbilities(StringBuilder sb, JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("stats");
        sb.append("Stats: [");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);


        }


        sb.append("]");
        return sb.toString();
    }

}
