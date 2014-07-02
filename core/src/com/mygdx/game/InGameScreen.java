package com.mygdx.game;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InGameScreen implements com.badlogic.gdx.Screen {
	
	public enum PlayState{NewGame, InPlay, EndGame}
	PlayState playState;
	
    MyGame game; // Note it's "MyGame" not "Game"
    OrthographicCamera camera;
    
    Texture inPlayBack;
    Sprite inPlayBackSp;
    Texture newGameText;
    Sprite newGameTextSp;
    Texture endGameText;
    Sprite endGameTextSp;
    ColorButton[] colorButtons;
    ColorHolder colorHolder;
    
    //For Debug
    BitmapFont font;
    
    boolean oldButtonPressed;
    boolean initNewGame;
    
    
        
   // constructor to keep a reference to the main Game class
     public InGameScreen(MyGame game){
             this.game = game;
             playState = PlayState.NewGame;
             game.score = 0;
             
             camera = new OrthographicCamera();
             camera.setToOrtho(false, 360, 600);
             
             inPlayBack = new Texture(Gdx.files.internal("Images/InPlayBack.png"));
             inPlayBackSp = new Sprite(inPlayBack, 0, 0, inPlayBack.getWidth(), inPlayBack.getHeight());
             newGameText = new Texture(Gdx.files.internal("Images/NewGameText.png"));
             newGameTextSp = new Sprite(newGameText, 0, 0, newGameText.getWidth(), newGameText.getHeight());
             endGameText = new Texture(Gdx.files.internal("Images/EndGameText.png"));
             endGameTextSp = new Sprite(endGameText, 0, 0, endGameText.getWidth(), endGameText.getHeight());
             
             oldButtonPressed = true;
             initNewGame = true;
             
             //Init buttons & set positions
             colorButtons = new ColorButton[6];
             int tempButton = 0;
             for(int i = 0; i < 2; i++){
            	 for(int j = 0; j < 3; j++){
            		 int tileID = i + j;
            		 colorButtons[tempButton] = new ColorButton(tileID, i, j);
            		 tempButton++;
            	 }
             }
             colorHolder = new ColorHolder();
             
             //Debug
             font  = new BitmapFont();
     }

     @Override
     public void render(float delta) {
    	 
    	updateState();             	
    	
    	updateDraw();          
    	
     }

	private void updateState() {
		//Update Game Vars
    	switch(playState){
    	
	   		case NewGame:
	   			//Reset Game Variables
	   			if(initNewGame){
	   				colorHolder.NewBackColor();
	   				initNewGame = false;
	   			}
	   			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && oldButtonPressed == false){
	   				reshuffleDeck();
	   				playState = PlayState.InPlay;	   				
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
	   			break;
	   			
	   		case InPlay:
	   			
	   			/*
	   			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && oldButtonPressed == false){
	   				reshuffleDeck();	   	
	   				game.score++;
	                oldButtonPressed = true;
	            }
	            else{
	          	  if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
	          		  oldButtonPressed = true;
	          	  }
	          	  else{
	          		  oldButtonPressed = false;
	          	  }
	            }*/
	   			
	   			
	   			buttonLoop: for (int i = 0; i < 6; i++) {
	   				
	   			    int scrH = Gdx.graphics.getHeight();
	          	  
	   				if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && oldButtonPressed == false && 
	   						Gdx.input.getX() > colorButtons[i].tileBounds.x && 
	   						Gdx.input.getX() < (colorButtons[i].tileBounds.x + colorButtons[i].tileBounds.getWidth()) &&
	   						Gdx.input.getY() < (scrH - colorButtons[i].tileBounds.y) && 
	   						Gdx.input.getY() > (scrH - (colorButtons[i].tileBounds.y + colorButtons[i].tileBounds.getHeight()))){
	   					
	   					if(colorButtons[i].correctButton == true){
	   						game.score++;
	   						reshuffleDeck();	   						
	   						oldButtonPressed = true;
	   						break buttonLoop;
	   					}
	   					else{
	   						playState = PlayState.EndGame;
	   		                oldButtonPressed = true;
	   					}
	   					
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
	   			
	   			break;
	   			
	   		case EndGame:
	   			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && oldButtonPressed == false){
	   				initNewGame = true;
	   				game.score = 0;
	   				playState = PlayState.NewGame;
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
	   			//**Allow transitioon back to main menu***
	   			break;
	   			
	   		default:
	   			break;
	   			
    	}
	}

	private void reshuffleDeck() {
		colorHolder.NewBackColor();
		colorHolder.ShuffleColors();
		
		for(int i = 0; i < 6; i++) {
			if(colorHolder.buttonColors[i] == colorHolder.pastColor){
				colorButtons[i].correctButton = true;
			}
			else{
				colorButtons[i].correctButton = false;
			}
		}
	}
	
	private void updateDraw() {
		//Update camera & spritebatch
          Gdx.gl.glClearColor(0, 0, 0, 1);
          Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);        
          camera.update();
          game.batch.setProjectionMatrix(camera.combined);        
          game.batch.begin();
          
          switch(playState){
          
          case NewGame:
        	  
        	  	inPlayBackSp.setColor(colorHolder.gameColors[colorHolder.backColor]);
        	  	inPlayBackSp.draw(game.batch);
        	  	newGameTextSp.setColor(1, 1, 1, 1);
        	  	newGameTextSp.setPosition((Gdx.graphics.getWidth() / 2) - (newGameText.getWidth() / 2),
        	  								(Gdx.graphics.getHeight() / 2) - (newGameText.getHeight() / 2));
        	  	newGameTextSp.draw(game.batch);
        	  	
	   			break;
	   			
	   		case InPlay:
	   			
	   			inPlayBackSp.setColor(colorHolder.gameColors[colorHolder.backColor]);
        	  	inPlayBackSp.draw(game.batch);
	   			
	   			for(int i = 0; i < 6; i++){
	   						   				
	   				int tempInt = colorHolder.buttonColors[i];
	   				Color tempColor = colorHolder.gameColors[tempInt];
	   				game.batch.setColor(tempColor);
	   				game.batch.draw(colorButtons[i].colorTile, colorButtons[i].tileBounds.x, colorButtons[i].tileBounds.y);
	   			}
	   			
	   			//Debug for tiles
	   			for(int i = 0; i < 6; i++){
	   				
	   				String tileTruth = Boolean.toString(colorButtons[i].correctButton);
	   				font.draw(game.batch, tileTruth, colorButtons[i].tileBounds.x + 50, colorButtons[i].tileBounds.y + 25);
	   				
	   				String tileNumber = Integer.toString(colorHolder.buttonColors[i]);
	   				font.draw(game.batch, tileNumber, colorButtons[i].tileBounds.x + 50, colorButtons[i].tileBounds.y + 50);
	   				
	   				String tileX = Integer.toString((int)colorButtons[i].tileBounds.x);
	   				font.draw(game.batch, tileX, colorButtons[i].tileBounds.x + 40, colorButtons[i].tileBounds.y + 75);
	   				String tileY = Integer.toString((int)colorButtons[i].tileBounds.y);
	   				font.draw(game.batch, tileY, colorButtons[i].tileBounds.x + 60, colorButtons[i].tileBounds.y + 75);
	   					   					   				
	   				int scrH = Gdx.graphics.getHeight();
	   				if(Gdx.input.getX() > colorButtons[i].tileBounds.x && 
	   						Gdx.input.getX() < (colorButtons[i].tileBounds.x + colorButtons[i].tileBounds.getWidth()) &&
	   						Gdx.input.getY() < (scrH - colorButtons[i].tileBounds.y) && 
	   						Gdx.input.getY() > (scrH - (colorButtons[i].tileBounds.y + colorButtons[i].tileBounds.getHeight()))){
	   					font.draw(game.batch, "Over", colorButtons[i].tileBounds.x + 50, colorButtons[i].tileBounds.y + 100);
	   				}
	   			}
	   			
	   			//**Add padding to the numbers later***
	   			font.draw(game.batch, "Score:", 25, 500);
	   			String scoreNumber = Integer.toString(score);
	   			font.draw(game.batch, scoreNumber, 75, 500);
	   			
	   			//Background IDs
	   			String backNumber = Integer.toString(colorHolder.backColor);
	   			String pastNumber = Integer.toString(colorHolder.pastColor);
	   			font.draw(game.batch, backNumber, 25, 25);
	   			font.draw(game.batch, pastNumber, 50, 25);
	   			
	   			//Mouse Coords
	   			font.draw(game.batch, "MouseX:", 200, 550);
	   			font.draw(game.batch, "MouseY:", 200, 500);
	   			String mouseX = Integer.toString(Gdx.input.getX());
	   			String mouseY = Integer.toString(Gdx.input.getY());
	   			font.draw(game.batch, mouseX, 250, 550);
	   			font.draw(game.batch, mouseY, 250, 500);
	   			String scrY = Integer.toString((int)(Gdx.graphics.getHeight() - Gdx.input.getY()));
	   			font.draw(game.batch, "ScreenY:", 200, 525);
	   			font.draw(game.batch, scrY, 250, 525);
	   			
	   			//Debug Pressed oldButtonPressed
	   			String tileTruth = Boolean.toString(oldButtonPressed);
	   			font.draw(game.batch, "Pressed:", 200, 575);
	   			font.draw(game.batch, tileTruth, 250, 575);
	   			
	   			break;
	   			
	   		case EndGame:
	   			
	   			inPlayBackSp.setColor(colorHolder.gameColors[colorHolder.backColor]);
        	  	inPlayBackSp.draw(game.batch);
        	  	endGameTextSp.setColor(1, 1, 1, 1);
        	  	endGameTextSp.setPosition((Gdx.graphics.getWidth() / 2) - (endGameText.getWidth() / 2),
							(Gdx.graphics.getHeight() / 2) - (endGameText.getHeight() / 2));
        	  	endGameTextSp.draw(game.batch);

	   			break;
          
          	default:
          		break;
          
          }

          game.batch.end();
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
