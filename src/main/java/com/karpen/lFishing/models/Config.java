package com.karpen.lFishing.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Config {
    private double defaultChance;
    private double normalChance;
    private double epicChance;
    private double mifikChance;
    private double legendChance;

    private boolean usingChatty;
    private String chat;

    private boolean luckChances;
    private double defaultLuckChance;
    private double normalLuckChance;
    private double epicLuckChance;
    private double mifikLuckChance;
    private double legendLuckChance;

    private String defaultMsg;
    private String normalMsg;
    private String epicMsg;
    private String mifikMsg;
    private String legendMsg;

    private String skinDefault;
    private String skinNormal;
    private String skinEpic;
    private String skinMifik;
    private String skinLegend;

    private String chatEpic;
    private String chatMifik;
    private String chatLegend;

    private String errArgs;
    private String reloadDone;

    private String defaultName;
    private String normalName;
    private String epicName;
    private String mifikName;
    private String legendName;

    public boolean getLuck(){
        return luckChances;
    }

    public boolean getChatty() {
        return usingChatty;
    }
}
