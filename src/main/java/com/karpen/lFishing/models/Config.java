package com.karpen.lFishing.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Config {
    private int defaultChance;
    private int normalChance;
    private int epicChance;

    private String defaultMsg;
    private String normalMsg;
    private String epicMsg;

    private String errArgs;
    private String reloadDone;

    private String defaultName;
    private String normalName;
    private String epicName;
}
