package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game implements ApplicationListener{
	 
	SpriteBatch batch;

    IntroScreen introScreen;
    MenuScreen menuScreen;
    InGameScreen inGameScreen;
    
    int score;
    int highScore;
    //Preferences scorePref = Gdx.app.getPreferences("HighScore");

   @Override
     public void create() {
	   
	   batch = new SpriteBatch();
	   
	   introScreen = new IntroScreen(this);
	   menuScreen = new MenuScreen(this);
	   inGameScreen = new InGameScreen(this);
	   
	   setScreen(introScreen);  
	   
	   //fileHandler(0);
     }
   /*
   public void fileHandler(int readOrWrite){
	   
	    	switch(readOrWrite){

	    	case 0:
	    		highScore = scorePref.getInteger("hs");
	    		break;
	    		
	    	case 1:
	    		scorePref.putInteger("hs", highScore);
	    		break;
	    	default:
	    		break;
	    	}
	    }	 */   
}

