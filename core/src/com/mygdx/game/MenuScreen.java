package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MenuScreen implements com.badlogic.gdx.Screen {
	
    MyGame game; // Note it's "MyGame" not "Game"
    OrthographicCamera camera;
    
    Texture menuBack;
    Texture menuTitle;
    Texture playButton;
    Texture scoreButton;
    
    Sprite playButtonSp;
    Sprite scoreButtonSp;
    
    boolean oldButtonPressed;

   // constructor to keep a reference to the main Game class
     public MenuScreen(MyGame game){
             this.game = game;
             
             camera = new OrthographicCamera();
             camera.setToOrtho(false, 360, 600);
             
             menuBack = new Texture(Gdx.files.internal("Images/MenuBack.png"));
             menuTitle = new Texture(Gdx.files.internal("Images/CulerTitle.png"));
             playButton = new Texture(Gdx.files.internal("Images/PlayButton.png"));
             scoreButton = new Texture(Gdx.files.internal("Images/ScoreButton.png"));
             
             playButtonSp = new Sprite(playButton, 90, 200, playButton.getWidth(), playButton.getHeight());
             scoreButtonSp = new Sprite(scoreButton, 90, 100, scoreButton.getWidth(), scoreButton.getHeight());
             
             oldButtonPressed = true;
     }
	
     @Override
     public void render(float delta) {
          
          //Update camera & spritebatch
          Gdx.gl.glClearColor(0, 0, 0, 1);
          Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
          camera.update();
          game.batch.setProjectionMatrix(camera.combined);          
          game.batch.begin();
          game.batch.draw(menuBack, 0, 0);
          game.batch.draw(menuTitle, (Gdx.graphics.getWidth() / 2) - (menuTitle.getWidth() / 2), 
        		  (int)(Gdx.graphics.getHeight() / 1.3) - (menuTitle.getHeight() / 2));
          game.batch.draw(playButtonSp, 0, 0);
          game.batch.draw(scoreButtonSp, 0, 0);
          game.batch.end();
          
          //Update Inputs
          if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && oldButtonPressed == false){
              game.setScreen(game.inGameScreen);
              oldButtonPressed = true;
          }
          else{
        	  if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
        	  oldButtonPressed = true;
        	  }
        	  else{
        		  oldButtonPressed = false;
        	  }
          }
     }


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
