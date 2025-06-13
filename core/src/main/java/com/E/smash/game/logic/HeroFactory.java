package com.E.smash.game.logic;

import com.E.smash.game.models.Hero;
import com.E.smash.game.models.Skill;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

public class HeroFactory {

    // Metode helper untuk membuat animasi dari satu baris sprite sheet
    private static Animation<TextureRegion> createAnimationFromSheet(AssetManager assets, String textureName, int frameWidth, int frameHeight, int row, int frameCount, float frameDuration) {
        Texture sheet = assets.get(textureName, Texture.class);
        TextureRegion[][] tmp = TextureRegion.split(sheet, frameWidth, frameHeight);
        TextureRegion[] frames = new TextureRegion[frameCount];

        // Ambil frame dari baris (row) yang ditentukan
        for (int i = 0; i < frameCount; i++) {
            frames[i] = tmp[row][i];
        }

        return new Animation<>(frameDuration, frames);
    }

    // Modifikasi metode ini untuk menerima AssetManager
    public static List<Hero> getAllHeroes(AssetManager assets) {
        List<Hero> heroes = new ArrayList<>();
        final int FRAME_WIDTH = 48; // Perkiraan ukuran frame, mungkin perlu disesuaikan
        final int FRAME_HEIGHT = 48; // Perkiraan ukuran frame, mungkin perlu disesuaikan

        // KNIGHT
        Hero knight = new Hero("Knight", 120, 30);
        knight.addSkill(new Skill("Slash", "A basic sword attack", 20, 0));
        knight.addSkill(new Skill("Shield Bash", "A powerful attack that stuns", 35, 15));
        // Buat animasi idle untuk Knight (misal, baris 0, 4 frame)
        knight.idleAnimation = createAnimationFromSheet(assets, "Human_Knight.png", FRAME_WIDTH, FRAME_HEIGHT, 0, 4, 0.2f); //
        heroes.add(knight);

        // ARCHER
        Hero archer = new Hero("Archer", 90, 50);
        archer.addSkill(new Skill("Precise Shot", "A carefully aimed shot", 25, 10));
        archer.addSkill(new Skill("Arrow Rain", "Hits all enemies", 15, 30));
        // Buat animasi idle untuk Archer (misal, baris 0, 4 frame)
        archer.idleAnimation = createAnimationFromSheet(assets, "Human_Archer.png", FRAME_WIDTH, FRAME_HEIGHT, 0, 4, 0.2f); //
        heroes.add(archer);

        // Anda bisa hapus Mage untuk sementara jika belum ada asetnya

        return heroes;
    }
}
