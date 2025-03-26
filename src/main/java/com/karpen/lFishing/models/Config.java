package com.karpen.lFishing.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Config {
    private int defaultChance;
    private int normalChance;
    private int epicChance;
    private int mifikChance;
    private int legendChance;

    private String defaultMsg;
    private String normalMsg;
    private String epicMsg;
    private String mifikMsg;
    private String legendMsg;

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
