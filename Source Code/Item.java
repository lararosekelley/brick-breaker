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

//This "Item" class extends the "Structure" class. It is used for the items that fall from some bricks.

//Imports
import java.awt.*;

//Class definition
public class Item extends Structure implements Constants {
	//Variables
	private int type;

	//Constructor
	public Item(int x, int y, int width, int height, Color color, int type) {
		super(x, y, width, height, color);
		setType(type);
	}

	//Draw an item
	public void draw(Graphics g) {
		if(type == 3) {
			return;
		}
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	//Drop the item down towards the paddle at slow pace
	public void drop() {
		y += 1;
	}

	//Resize the paddle, depending on which item is caught. Changes in increments of 15 until min/max width is reached.
	public void resizePaddle(Paddle p) {
		if (getType() == 1 && p.getWidth() < PADDLE_MAX) {
			p.setWidth(p.getWidth() + 15);
		}
		else if (getType() == 2 && p.getWidth() > PADDLE_MIN) {
			p.setWidth(p.getWidth() - 15);
		}
	}

	//Set the item's type
	public void setType(int type) {
		this.type = type;
	}

	//Get the item's type
	public int getType() {
		return type;
	}
}