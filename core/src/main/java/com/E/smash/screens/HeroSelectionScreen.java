package com.E.smash.screens;

import com.E.smash.game.logic.HeroFactory;
import com.E.smash.game.models.Hero;
import com.E.smash.smash;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.List;

public class HeroSelectionScreen implements Screen {

    private final smash game;
    private final Stage stage;
    private final Skin skin;

    private final List<Hero> availableHeroes;
    private Hero player1Hero;
    private Hero player2Hero;
    private int currentPlayer = 1;

    private final Label instructionLabel;

    public HeroSelectionScreen(final smash game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // Dapatkan daftar hero, sekarang dengan meneruskan AssetManager untuk memuat animasi
        this.availableHeroes = HeroFactory.getAllHeroes(game.assets);

        Table layout = new Table();
        layout.setFillParent(true);
        stage.addActor(layout);

        instructionLabel = new Label("Player 1: Choose Your Hero", skin, "subtitle");
        instructionLabel.setAlignment(Align.center);
        layout.add(instructionLabel).colspan(availableHeroes.size()).padBottom(40).expandX();
        layout.row();

        for (final Hero hero : availableHeroes) {
            TextButton heroButton = new TextButton(hero.name, skin);
            layout.add(heroButton).pad(10).width(150).height(50);

            heroButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (currentPlayer == 1) {
                        player1Hero = hero;
                        currentPlayer = 2;
                        instructionLabel.setText("Player 2: Choose Your Hero");
                        Gdx.app.log("SELECT", "Player 1 chose: " + hero.name);
                    } else if (currentPlayer == 2) {
                        player2Hero = hero;
                        Gdx.app.log("SELECT", "Player 2 chose: " + hero.name);
                        Gdx.app.log("NAVIGATE", "Both players selected. Starting GameScreen.");
                        game.setScreen(new GameScreen(game, player1Hero, player2Hero));
                        dispose();
                    }
                }
            });
        }
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
