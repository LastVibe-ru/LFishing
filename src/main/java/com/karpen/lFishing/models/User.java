package com.karpen.lFishing.models;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter
@Setter
public class User {
    private Player player;

    private int defaultCount;
    private int normalCount;
    private int epicCount;
    private int mifikCount;
    private int legendCount;
}
