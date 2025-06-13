package com.E.smash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.E.smash.screens.MainMenuScreen; // <- IMPORT BARU

public class smash extends Game {
    public SpriteBatch batch;
    public AssetManager assets;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assets = new AssetManager();
        this.setScreen(new MainMenuScreen(this));
    }
    // ... (import lainnya)
    private void loadAssets() {
        // Ganti nama file placeholder dengan nama file yang benar
        assets.load("Human_Knight.png", Texture.class);
        assets.load("Human_Archer.png", Texture.class); //
        // Hapus "mage.png" jika Anda belum memiliki sprite sheet untuk itu
    }
// ...

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        assets.dispose();
        if (getScreen() != null) {
            getScreen().dispose();
        }
    }
}
