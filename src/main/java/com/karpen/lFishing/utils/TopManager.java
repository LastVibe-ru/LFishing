package com.karpen.lFishing.utils;

import com.google.gson.*;
import com.karpen.lFishing.models.PlayerTop;

import java.io.*;
import java.util.*;

public class TopManager {
    private List<PlayerTop> topList;
    private File file;

    public TopManager(File file) {
        this.topList = new ArrayList<>();
        this.file = file;

        loadTopFromFile();
    }

    private void loadTopFromFile() {
        try (Reader reader = new FileReader(file)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement element : jsonArray) {
                PlayerTop playerTop = PlayerTop.fromJson(element.getAsJsonObject());
                topList.add(playerTop);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTopToFile() {
        JsonArray jsonArray = new JsonArray();
        for (PlayerTop playerTop : topList) {
            jsonArray.add(playerTop.toJson());
        }
        try (Writer writer = new FileWriter(file)) {
            new Gson().toJson(jsonArray, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void increaseScore(String playerName) {
        for (PlayerTop playerTop : topList) {
            if (playerTop.getPlayerName().equalsIgnoreCase(playerName)) {
                playerTop.increaseScore();
                sortTopList();
                saveTopToFile();
                return;
            }
        }

        topList.add(new PlayerTop(playerName, 1));
        sortTopList();
        saveTopToFile();
    }

    private void sortTopList() {
        topList.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
    }

    public List<PlayerTop> getTop(int count) {
        return topList.subList(0, Math.min(count, topList.size()));
    }
}
