package com.thistleblue.pokeapi.model;

import java.util.Arrays;

public class Pokemon {
    private int id;
    private String name;
    private int height;
    private int weight;
    private String types;
    private String stats;
    private String abilities;

    public Pokemon(int id, String name, int height, int weight, String types, String stats, String abilities) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.types = types;
        this.stats = stats;
        this.abilities = abilities;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType1() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }


    @Override
    public String toString() {
        return "ID: " + id +
                "\n Name: " + name +
                "\n Height: " + height +
                "\n Weight: " + weight +
                "\n Types: " + types +
                "\n Stats: " + stats +
                "\n Abilities: " + abilities;
    }
}
