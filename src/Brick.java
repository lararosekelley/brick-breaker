/*
*    Brick Breaker, Version 1.0
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

//This "Brick" class extends the "Structure" class. It is for the bricks used in the game.

//Imports
import java.awt.*;

//Class definition
public class Brick extends Structure implements Constants {
	//Variables
	private int lives, hits;
	private boolean destroyed;
	public Item item;
	private Color itemColor;
	private Color[] colors = {BLUE_BRICK_ONE, BLUE_BRICK_TWO, BLUE_BRICK_THREE, Color.BLACK, Color.BLACK};

	//Constructor
	public Brick(int x, int y, int width, int height, Color color, int lives, int itemType) {
		super(x, y, width, height, color);
		setLives(lives);
		setHits(0);
		setDestroyed(false);

		if (itemType == 1) {
			itemColor = Color.GREEN;
		}
		if (itemType == 2) {
			itemColor = Color.RED;
		}

		//Places an item of specified type inside the brick to fall when the brick is destroyed
		item = new Item(x + (width / 4), y + (height / 4), ITEM_WIDTH, ITEM_HEIGHT, itemColor, itemType);
	}

	//Draws a brick
	@Override
	public void draw(Graphics g) {
		if (!destroyed) {
			g.setColor(color);
			g.fillRect(x, y, width, height);
		}
	}

	//Add a hit to the brick, and destroy the brick when hits == lives
	public void addHit() {
		hits++;
		nextColor();
		if (hits == lives) {
			setDestroyed(true);
		}
	}

	//Change color to get more pale until the brick is destroyed
	public void nextColor() {
		color = colors[hits];
	}

	//Detect if the brick has been hit on its bottom, top, left, or right sides
	public boolean hitBottom(int ballX, int ballY) {
		if ((ballX >= x) && (ballX <= x + width + 1) && (ballY == y + height) && (destroyed == false)) {
			addHit();
			return true;
		}
		return false;
	}

	public boolean hitTop(int ballX, int ballY) {
		if ((ballX >= x) && (ballX <= x + width + 1) && (ballY == y) && (destroyed == false)) {
			addHit();
			return true;
		}
		return false;
	}

	public boolean hitLeft(int ballX, int ballY) {
		if ((ballY >= y) && (ballY <= y + height) && (ballX == x) && (destroyed == false)) {
			addHit();
			return true;
		}
		return false;
	}

	public boolean hitRight(int ballX, int ballY) {
		if ((ballY >= y) && (ballY <= y + height) && (ballX == x + width) && (destroyed == false)) {
			addHit();
			return true;
		}
		return false;
	}

	//Mutator methods
	public void setLives(int lives) {
		this.lives = lives;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	//Accessor methods
	public int getLives() {
		return lives;
	}

	public int getHits() {
		return hits;
	}

	public boolean isDestroyed() {
		return destroyed;
	}
}