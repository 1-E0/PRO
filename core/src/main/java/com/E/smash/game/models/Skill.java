package com.E.smash.game.models; // <- PACKAGE BARU

public class Skill {
    public String name;
    public String description;
    public int damage;
    public int manaCost;

    public Skill(String name, String description, int damage, int manaCost) {
        this.name = name;
        this.description = description;
        this.damage = damage;
        this.manaCost = manaCost;
    }
}
