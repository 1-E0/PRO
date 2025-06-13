package com.E.smash.game.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    public String name;
    public int maxHp;
    public int currentHp;
    public int maxMana;
    public int currentMana;
    public List<Skill> skills;

    // Field untuk menyimpan animasi idle hero
    public Animation<TextureRegion> idleAnimation;

    public Hero(String name, int hp, int mana) {
        this.name = name;
        this.maxHp = hp;
        this.currentHp = hp;
        this.maxMana = mana;
        this.currentMana = mana;
        this.skills = new ArrayList<>();
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }
}
