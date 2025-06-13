package com.E.smash.screens;

import com.E.smash.game.models.Hero;
import com.E.smash.game.models.Skill;
import com.E.smash.smash;
import com.E.smash.ui.AnimatedActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {

    private final smash game;
    private final Stage stage;
    private final Skin skin;

    private final Hero player1Hero;
    private final Hero player2Hero;
    private int currentPlayerTurn = 1;

    // UI Elements
    private final Label infoLabel;
    private final Table actionPanel;
    private final ProgressBar p1HpBar, p1ManaBar, p2HpBar, p2ManaBar;

    public GameScreen(final smash game, Hero player1Hero, Hero player2Hero) {
        this.game = game;
        this.player1Hero = player1Hero;
        this.player2Hero = player2Hero;

        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this.stage);
        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        Table p1Table = createPlayerUI(player1Hero);
        p1HpBar = p1Table.findActor("hpBar");
        p1ManaBar = p1Table.findActor("manaBar");

        Table p2Table = createPlayerUI(player2Hero);
        p2HpBar = p2Table.findActor("hpBar");
        p2ManaBar = p2Table.findActor("manaBar");

        Table bottomTable = new Table();
        infoLabel = new Label("Player " + currentPlayerTurn + "'s Turn", skin, "subtitle");
        actionPanel = new Table();

        bottomTable.add(infoLabel).pad(20).row();
        bottomTable.add(actionPanel).expand().fill();

        root.add(p1Table).expand().fill();
        root.add(p2Table).expand().fill();
        root.row();
        root.add(bottomTable).colspan(2).expandX().fillX().height(Gdx.graphics.getHeight() * 0.4f);

        rebuildActionPanel();
    }

    private Table createPlayerUI(Hero hero) {
        Table playerTable = new Table();

        Label nameLabel = new Label(hero.name, skin, "subtitle");

        AnimatedActor heroActor = new AnimatedActor(hero.idleAnimation);

        ProgressBar hpBar = new ProgressBar(0, hero.maxHp, 1, false, skin, "default-horizontal");
        hpBar.setValue(hero.currentHp);
        hpBar.setName("hpBar");

        ProgressBar manaBar = new ProgressBar(0, hero.maxMana, 1, false, skin, "curved");
        manaBar.setValue(hero.currentMana);
        manaBar.setName("manaBar");

        playerTable.add(nameLabel).pad(10).row();
        playerTable.add(heroActor).size(128, 128).pad(10).row();
        playerTable.add(new Label("HP", skin)).pad(5);
        playerTable.add(hpBar).width(200).row();
        playerTable.add(new Label("Mana", skin)).pad(5);
        playerTable.add(manaBar).width(200).row();

        return playerTable;
    }

    private void rebuildActionPanel() {
        actionPanel.clear();

        Hero currentHero = (currentPlayerTurn == 1) ? player1Hero : player2Hero;
        infoLabel.setText("Player " + currentPlayerTurn + "'s Turn: " + currentHero.name);

        for (final Skill skill : currentHero.skills) {
            TextButton skillButton = new TextButton(skill.name + "\n(" + skill.manaCost + " Mana)", skin);

            skillButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Gdx.app.log("ACTION", currentHero.name + " used " + skill.name);
                    // Logika pertarungan akan diimplementasikan di sini
                }
            });
            actionPanel.add(skillButton).pad(10).uniform();
        }
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0.15f, 0.15f, 1f);

        p1HpBar.setValue(player1Hero.currentHp);
        p1ManaBar.setValue(player1Hero.currentMana);
        p2HpBar.setValue(player2Hero.currentHp);
        p2ManaBar.setValue(player2Hero.currentMana);

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
