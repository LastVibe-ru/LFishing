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
}
