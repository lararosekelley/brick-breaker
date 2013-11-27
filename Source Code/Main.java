/*
*    Brick Breaker, Version 1.1
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

//This "Main" class runs the game. 

//Imports
import javax.swing.*;
import java.awt.*;

//Class definition
public class Main extends JFrame implements Constants {
	//Variables
	private static JFrame frame;
	private static Board board;
	private static Container pane;
	private static Dimension dim;

	//Build and run the game
	public static void main(String[] args) {
		frame = new JFrame("Brick Breaker 1.1");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);

		pane = frame.getContentPane();
		pane.add(board);

		//Place frame in the middle of the screen
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

		frame.setVisible(true);
	}
}