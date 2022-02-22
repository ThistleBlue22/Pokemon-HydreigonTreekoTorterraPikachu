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

            String pokemonType = pokemonType(sb, jsonObject);
            sb.delete(0, sb.length());

            String pokemonStats = pokemonStats(sb, jsonObject);
            sb.delete(0, sb.length());

            String pokemonAbilities = pokemonAbilities(sb, jsonObject);



            Pokemon pokemon = new Pokemon(Math.toIntExact((Long) jsonObject.get("id")), (String) jsonObject.get("name"),
                    Math.toIntExact((Long) jsonObject.get("height")), Math.toIntExact((Long) jsonObject.get("weight")),
                    pokemonType, pokemonStats, pokemonAbilities);

            System.out.println(pokemon);
        } catch (IOException | InterruptedException | ParseException e) {
            e.printStackTrace();
        }

    }




    private static String pokemonType(StringBuilder sb, JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("types");

        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get("type");
            sb.append(jsonObject2.get("name"));
            if (i < jsonArray.size()-1){
                sb.append(", ");
            }

        }
        return sb.toString();
    }

    private static String pokemonStats(StringBuilder sb, JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("stats");
        sb.append("[");

        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get("stat");
            sb.append(jsonObject2.get("name")).append(": ").append(jsonObject1.get("base_stat"));
            if (i < jsonArray.size()-1){
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    private static String pokemonAbilities(StringBuilder sb, JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("abilities");
        sb.append("[");
        String isHidden;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get("ability");

            if (jsonObject1.get("is_hidden").toString().contains("true"))
                isHidden = "This is a hidden ability";
            else
                isHidden = "This is not a hidden ability";

            sb.append(jsonObject2.get("name")).append(" (").append(isHidden).append(")");

            if (i < jsonArray.size()-1){
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

}
