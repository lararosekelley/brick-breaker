/*
*    Brick Breaker, Version 1.1.1
*    By Ty-Lucas Kelley
*	
*	 **LICENSE**
*
*	 This file is a part of Brick Breaker.
*
*	 Brick Breaker is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    Brick Breaker is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with Brick Breaker.  If not, see <http://www.gnu.org/licenses/>.
*/

//This "Constants" interface contains constant values used in multiple classes.

//Imports
import java.awt.Color;

//Class definition
public interface Constants {
	//Window Size
	public static final int WINDOW_WIDTH = 500;
	public static final int WINDOW_HEIGHT = 500;

	//Lives
	public static final int MAX_LIVES = 5;
	public static final int MIN_LIVES = 0;

	//Ball
	public static final int BALL_WIDTH = 10;
	public static final int BALL_HEIGHT = 10;
	public static final int BALL_RIGHT_BOUND = 490;
	public static final int BALL_X_START = 245;
	public static final int BALL_Y_START = 245;

	//Paddle
	public static final int PADDLE_WIDTH = 70;
	public static final int PADDLE_HEIGHT = 10;
	public static final int PADDLE_RIGHT_BOUND = 430;
	public static final int PADDLE_X_START = 225;
	public static final int PADDLE_Y_START = 450;
	public static final int PADDLE_MIN = 35;
	public static final int PADDLE_MAX = 140;

	//Bricks
	public static final int BRICK_WIDTH = 50;
	public static final int BRICK_HEIGHT = 25;
	public static final int MAX_BRICKS = 50;
	public static final int NO_BRICKS = 0;

	//Brick Colors
	public static final Color BLUE_BRICK_ONE = new Color(0,0,255);
	public static final Color BLUE_BRICK_TWO = new Color(28,134,238);
	public static final Color BLUE_BRICK_THREE = new Color(0,191,255);
	public static final Color RED_BRICK_ONE = new Color(255,0,0);
	public static final Color RED_BRICK_TWO = new Color(255,106,106);
	public static final Color RED_BRICK_THREE = new Color(238,180,180);
	public static final Color PURPLE_BRICK_ONE = new Color(128,0,128);
	public static final Color PURPLE_BRICK_TWO = new Color(148,0,211);
	public static final Color PURPLE_BRICK_THREE = new Color(155,48,255);
	public static final Color YELLOW_BRICK_ONE = new Color(255,215,0);
	public static final Color YELLOW_BRICK_TWO = new Color(255,255,0);
	public static final Color YELLOW_BRICK_THREE = new Color(255,246,143);
	public static final Color PINK_BRICK_ONE = new Color(205,0,205);
	public static final Color PINK_BRICK_TWO = new Color(238,130,238);
	public static final Color PINK_BRICK_THREE = new Color(255,225,255);
	public static final Color GRAY_BRICK_ONE = new Color(77,77,77);
	public static final Color GRAY_BRICK_TWO = new Color(125,125,125);
	public static final Color GRAY_BRICK_THREE = new Color(189,189,189);
	public static final Color GREEN_BRICK_ONE = new Color(0,139,0);
	public static final Color GREEN_BRICK_TWO = new Color(0,205,0);
	public static final Color GREEN_BRICK_THREE = new Color(0,255,0);

	//Items
	public static final int ITEM_WIDTH = 20;
	public static final int ITEM_HEIGHT = 10;
	public static final int ITEM_BIGGER = 1;
	public static final int ITEM_SMALLER = 2;
	public static final int ITEM_EMPTY = 3;
}