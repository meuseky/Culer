package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class IntroScreen implements com.badlogic.gdx.Screen {
	
    MyGame game; // Note it's "MyGame" not "Game"
    OrthographicCamera camera;
    
    Texture introBack;
    Texture introLogo;
    
    boolean oldButtonPressed;

   // constructor to keep a reference to the main Game class
     public IntroScreen(MyGame game){
             this.game = game;
             
             camera = new OrthographicCamera();
             camera.setToOrtho(false, 360, 600);
             
             introBack = new Texture(Gdx.files.internal("Images/IntroBack.png"));
             introLogo = new Texture(Gdx.files.internal("Images/MeuseLogo.png"));
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
          game.batch.draw(introBack, 0, 0);
          game.batch.draw(introLogo, (Gdx.graphics.getWidth() / 2) - (introLogo.getWidth() / 2), 
        		  (Gdx.graphics.getHeight() / 2) - (introLogo.getHeight() / 2));
          game.batch.end();
          
          //Update Inputs
          if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && oldButtonPressed == false){
              game.setScreen(game.menuScreen);
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
