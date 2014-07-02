package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ColorButton extends Actor{

    Texture colorTile;
    Sprite colorTileSp;
    
    int tileID;
    
    public Rectangle tileBounds;
    
    //Is this button the 'Correct' button to pick
    public boolean correctButton;
    
    public ColorButton(int ID, int xPos, int yPos){
    	
    	tileID = ID;
    	
        colorTile = new Texture(Gdx.files.internal("Images/ColorTile.png"));               
        tileBounds = new Rectangle(45 + (xPos * 150), 45 + (yPos * 150), 115, 115);
        colorTileSp = new Sprite(colorTile, (int)tileBounds.x, (int)tileBounds.y, colorTile.getWidth(), colorTile.getHeight());
        
        correctButton = false;
    }
	
    public void draw(SpriteBatch batch, Color tileColor){
    	colorTileSp.setColor(tileColor);
    	colorTileSp.draw(batch);
    }
    
    public void updateInput(){
    	
    }
}
