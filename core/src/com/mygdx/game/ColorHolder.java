package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class ColorHolder {

	Color[] gameColors;
	Color white;
	public int[] buttonColors;
	public int backColor;
	public int pastColor;
	Random random;
	
	public ColorHolder(){
		
		random = new Random();
		backColor = random.nextInt(12);
		buttonColors = new int[12];
		
		for(int i = 0; i < 12; i++){
			buttonColors[i] = i;
		}
		ShuffleColors();
		
		gameColors = new Color[]{
				//Aqua
				new Color(0, 1, 1, 1),
				//Chartreuse
				new Color(0.49f, 1, 0, 1),
				//DarkOrange
				new Color(1, 0.54f, 0, 1),
				//DeepPink
				new Color(1, 0.07f, 0.57f, 1),
				//DarkCyan
				new Color(0, 0.54f, 0.54f, 1),
				//Indigo
				new Color(0.29f, 0, 0.50f, 1),
				//Red
				new Color(1, 0, 0, 1),
				//SpringGreen
				new Color(0, 1, 0.49f, 1),
				//Yellow
				new Color(1, 1, 0, 1),
				//Violet
				new Color(0.93f, 0.50f, 0.93f, 1),
				//Salmon
				new Color(0.98f, 0.50f, 0.56f, 1),
				//Silver
				new Color(0.75f, 0.75f, 0.75f, 1)
			};
		
		white = new Color(0, 0, 0, 1);
		}
	
	public void NewBackColor(){
		pastColor = backColor;
		do{
		backColor = random.nextInt(12);	
		}while(pastColor == backColor);
		//***Stop colors from repeating too much***
	}
	
	public void ShuffleColors(){
		
		//Use fisher-yates algo to shuffle colors
		for(int i = 1; i < 13; i++){
			int j;
			
			do{
			j = random.nextInt(i + 1);
			}while(j == 0);
			
			j--;
			int k = i - 1;
			
			if(j != k){
				int temp = buttonColors[k];
				buttonColors[k] = buttonColors[j];
				buttonColors[j] = temp;
			}
		}
		
		//Place a tile equal to the Previous Color
		//First place past color to last position in array
		for(int i = 0; i < 12; i++){
			if(buttonColors[i] == pastColor){
				int temp2 = buttonColors[i];
				buttonColors[i] = buttonColors[11];
				buttonColors[11] = temp2;
			}
		}
		//Choose a random tile & swap in the past color
		//**Check the rnd later might not allow first tile to spawn***
		int rnd = random.nextInt(6);
		int temp3 = buttonColors[rnd];
		buttonColors[rnd] = buttonColors[11];
		buttonColors[11] = temp3;
		
		
		//If any of the first 6 color are the same as the current screen color switch it
		for(int i = 0; i < 5; i++){
			if(buttonColors[i] == backColor){
				int temp4 = buttonColors[i];
				buttonColors[i] = buttonColors[10];
				buttonColors[10] = temp4;
			}
		}				
	}
}
