package com.karpen.lFishing.models;

import com.google.gson.JsonObject;

public class PlayerTop {
    private String playerName;
    private int score;

    public PlayerTop(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score++;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("playerName", playerName);
        json.addProperty("score", score);
        return json;
    }

    public static PlayerTop fromJson(JsonObject json) {
        String playerName = json.get("playerName").getAsString();
        int score = json.get("score").getAsInt();
        return new PlayerTop(playerName, score);
    }
}

