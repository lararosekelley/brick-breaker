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

//This "Board" class handles all game logic and displays items on the screen.

//Imports
import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.lang.Thread;
import javax.sound.sampled.*;
import java.io.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.TreeMap;

//Class definition
public class Board extends JPanel implements Runnable, Constants {
	//Items on-screen
	private Paddle paddle;
	private Ball ball;
	private Brick[][] brick = new Brick[10][5];

	//Initial Values for some important variables
	private int score = 0, lives = MAX_LIVES, bricksLeft = MAX_BRICKS, waitTime = 3, xSpeed, withSound, level = 5;

	//Player's name
	private String playerName;

	//The game
	private Thread game;

	//Songs for background music
	private String songOne = "Source Code/Music/One.wav";
	private String songTwo = "Source Code/Music/Two.wav";
	private String songThree = "Source Code/Music/Three.wav";
	private String songFour = "Source Code/Music/Four.wav";
	private String songFive = "Source Code/Music/Five.wav";
	private String songSix = "Source Code/Music/Six.wav";
	private String songSeven = "Source Code/Music/Seven.wav";
	private String songEight = "Source Code/Music/Eight.wav";
	private String songNine = "Source Code/Music/Nine.wav";
	private String songTen = "Source Code/Music/Ten.wav";
	private String[] trackList = {songOne, songTwo, songThree, songFour, songFive, songSix, songSeven, songEight, songNine, songTen};
	private AudioInputStream audio;
	private Clip clip;

	//Data structures to handle high scores
	private ArrayList<Item> items = new ArrayList<Item>();
	private AtomicBoolean isPaused = new AtomicBoolean(true);

	//Colors for the bricks
	private Color[] blueColors = {BLUE_BRICK_ONE, BLUE_BRICK_TWO, BLUE_BRICK_THREE, Color.BLACK};
	private Color[] redColors = {RED_BRICK_ONE, RED_BRICK_TWO, RED_BRICK_THREE, Color.BLACK};
	private Color[] purpleColors = {PURPLE_BRICK_ONE, PURPLE_BRICK_TWO, PURPLE_BRICK_THREE, Color.BLACK};
	private Color[] yellowColors = {YELLOW_BRICK_ONE, YELLOW_BRICK_TWO, YELLOW_BRICK_THREE, Color.BLACK};
	private Color[] pinkColors = {PINK_BRICK_ONE, PINK_BRICK_TWO, PINK_BRICK_THREE, Color.BLACK};
	private Color[] grayColors = {GRAY_BRICK_ONE, GRAY_BRICK_TWO, GRAY_BRICK_THREE, Color.BLACK};
	private Color[] greenColors = {GREEN_BRICK_ONE, GREEN_BRICK_TWO, GREEN_BRICK_THREE, Color.BLACK};
	private Color[][] colors = {blueColors, redColors, purpleColors, yellowColors, pinkColors, grayColors, greenColors};

	//Constructor
	public Board(int width, int height) {
		super.setSize(width, height);
		addKeyListener(new BoardListener());
		setFocusable(true);

		makeBricks();
		paddle = new Paddle(PADDLE_X_START, PADDLE_Y_START, PADDLE_WIDTH, PADDLE_HEIGHT, Color.BLACK);
		ball = new Ball(BALL_X_START, BALL_Y_START, BALL_WIDTH, BALL_HEIGHT, Color.BLACK);

		//Get the player's name
		playerName = JOptionPane.showInputDialog(null, "Please enter your name:", "Brick Breaker, Version 1.1", JOptionPane.QUESTION_MESSAGE);
		if (playerName == null) {
			System.exit(0);
		}

		//Start Screen that displays information and asks if the user wants music or not, stores that choice
		String[] options = {"Yes", "No"};
		withSound = JOptionPane.showOptionDialog(null, "Brick Breaker, Version 1.1\nTy-Lucas Kelley\nVisit www.tylucaskelley.com for more projects.\n\nControls\n    Spacebar: Start game, Pause/Resume while in game.\n    Left/Right arrow keys: Move paddle\nItems\n    Green Item: Expand paddle\n    Red Item: Shrink paddle\nScoring\n    Block: 50 points\n    Level-up: 100 points\n    Life Loss: -100 points\n\n\n     Do you want background music?", "About the Game", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		playMusic(trackList, withSound, level);

		game = new Thread(this);
		game.start();
		stop();
		isPaused.set(true);
	}

	//fills the array of bricks
	public void makeBricks() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 5; j++) {
				Random rand = new Random();
				int itemType = rand.nextInt(3) + 1;
				int numLives = 3;
				Color color = colors[rand.nextInt(7)][0];
				brick[i][j] = new Brick((i * BRICK_WIDTH), ((j * BRICK_HEIGHT) + (BRICK_HEIGHT / 2)), BRICK_WIDTH - 5, BRICK_HEIGHT - 5, color, numLives, itemType);
			}
		}
	}

	//starts the thread
	public void start() {
		game.resume();
		isPaused.set(false);
	}

	//stops the thread
	public void stop() {
		game.suspend();
	}

	//ends the thread
	public void destroy() {
		game.resume();
		isPaused.set(false);
		game.stop();
		isPaused.set(true);
	}

	//runs the game
	public void run() {
		xSpeed = 1;
		while(true) {
			int x1 = ball.getX();
			int y1 = ball.getY();

			//Makes sure speed doesnt get too fast/slow
			if (Math.abs(xSpeed) > 1) {
				if (xSpeed > 1) {
					xSpeed--;
				}
				if (xSpeed < 1) {
					xSpeed++;
				}
			}

			checkPaddle(x1, y1);
			checkWall(x1, y1);
			checkBricks(x1, y1);
			checkLives();
			checkIfOut(y1);
			ball.move();
			dropItems();
			checkItemList();
			repaint();

			try {
				game.sleep(waitTime);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	public void addItem(Item i) {
		items.add(i);
	}

	public void dropItems() {
		for (int i = 0; i < items.size(); i++) {
			Item tempItem = items.get(i);
			tempItem.drop();
			items.set(i, tempItem);
		}
	}

	public void checkItemList() {
		for (int i = 0; i < items.size(); i++) {
			Item tempItem = items.get(i);
			if (paddle.caughtItem(tempItem)) {
				items.remove(i);
			}
			else if (tempItem.getY() > WINDOW_HEIGHT) {
				items.remove(i);
			}
		}
	}

	public void checkLives() {
		if (bricksLeft == NO_BRICKS) {
			clip.stop();
			clip.close();
			try {
				audio.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ball.reset();
			bricksLeft = MAX_BRICKS;
			makeBricks();
			lives++;
			level++;
			score += 100;
			playMusic(trackList, withSound, level);
			repaint();
			stop();
			isPaused.set(true);
		}
		if (lives == MIN_LIVES) {
			repaint();
			stop();
			isPaused.set(true);
		}
	}

	public void checkPaddle(int x1, int y1) {
		if (paddle.hitPaddle(x1, y1) && ball.getXDir() < 0) {
			ball.setYDir(-1);
			xSpeed = -1;
			ball.setXDir(xSpeed);
		}
		if (paddle.hitPaddle(x1, y1) && ball.getXDir() > 0) {
			ball.setYDir(-1);
			xSpeed = 1;
			ball.setXDir(xSpeed);
		}

		if (paddle.getX() <= 0) {
			paddle.setX(0);
		}
		if (paddle.getX() + paddle.getWidth() >= getWidth()) {
			paddle.setX(getWidth() - paddle.getWidth());
		}
	}

	public void checkWall(int x1, int y1) {
		if (x1 >= getWidth() - ball.getWidth()) {
			xSpeed = -Math.abs(xSpeed);
			ball.setXDir(xSpeed);
		}
		if (x1 <= 0) {
			xSpeed = Math.abs(xSpeed);
			ball.setXDir(xSpeed);
		}
		if (y1 <= 0) {
			ball.setYDir(1);
		}
		if (y1 >= getHeight()) {
			ball.setYDir(-1);
		}
	}

	public void checkBricks(int x1, int y1) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				if (brick[i][j].hitBottom(x1, y1)) {
					ball.setYDir(1);
					if (brick[i][j].isDestroyed()) {
						bricksLeft--;
						score += 50;
						addItem(brick[i][j].item);
					}
				}
				if (brick[i][j].hitLeft(x1, y1)) {
					xSpeed = -xSpeed;
					ball.setXDir(xSpeed);
					if (brick[i][j].isDestroyed()) {
						bricksLeft--;
						score += 50;
						addItem(brick[i][j].item);
					}
				}
				if (brick[i][j].hitRight(x1, y1)) {
					xSpeed = -xSpeed;
					ball.setXDir(xSpeed);
					if (brick[i][j].isDestroyed()) {
						bricksLeft--;
						score += 50;
						addItem(brick[i][j].item);
					}
				}
				if (brick[i][j].hitTop(x1, y1)) {
					ball.setYDir(-1);
					if (brick[i][j].isDestroyed()) {
						bricksLeft--;
						score += 50;
						addItem(brick[i][j].item);
					}
				}
			}
		}
	}

	public void checkIfOut(int y1) {
		if (y1 > PADDLE_Y_START + 10) {
			lives--;
			score -= 100;
			ball.reset();
			repaint();
			stop();
			isPaused.set(true);
		}
	}

	//plays different music throughout game if user wants to
	public void playMusic(String[] songs, int yesNo, int level) {
		if (yesNo == 1) {
			return;
		}
		else if (yesNo == -1) {
			System.exit(0);
		}
		if (level == 10) {
			level = 1;
		}
		try {
			audio = AudioSystem.getAudioInputStream(new File(songs[level-1]).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audio);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			System.out.println("Current song: " + audio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//fills the board
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paddle.draw(g);
		ball.draw(g);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				brick[i][j].draw(g);
			}
		}
		g.setColor(Color.BLACK);
		g.drawString("Lives: " + lives, 10, getHeight() - (getHeight()/10));
		g.drawString("Score: " + score, 10, getHeight() - (2*(getHeight()/10)) + 25);
		g.drawString("Level: " + level, 10, getHeight() - (3*(getHeight()/10)) + 50);
		g.drawString("Player: " + playerName, 10, getHeight() - (4*(getHeight()/10)) + 75);

		for (Item i: items) {
			i.draw(g);
		}

		if (lives == MIN_LIVES) {
			g.setColor(Color.BLACK);
			g.fillRect(0,0,getWidth(),getHeight());
			g.setColor(Color.WHITE);
			g.drawString("Name: " + playerName + ", Score: " + score + ", Level: " + level, getWidth()/5, 20);
			g.drawString("Game Over! Did you make it onto the high score table?", getWidth()/5, 50);
			try {
				printScores(g);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			g.drawString("Press the Spacebar twice to play again.", getWidth()/5, getHeight()-20);
		}
	}

	//Makes sure the HighScores.txt file exists
	public void makeTable() throws IOException {
		String filename = "HighScores";
		File f = new File(filename + ".txt");
		if (f.createNewFile()) {
			try {
				writeFakeScores();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		else {
			//do nothing
		}
	}

	//if there was no previous high score table, this one inputs 10 fake players and scores to fill it
	public void writeFakeScores() throws IOException {
		Random rand = new Random();

		int numLines = 10;
		File f = new File("HighScores.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()));
		for (int i = 1; i <= numLines; i++) {
			int score = rand.nextInt(2000);
			if (numLines - i >= 1) {
				bw.write("Name: " + "Player" + i + ", " + "Score: " + score + "\n");
			}
			else {
				bw.write("Name: " + "Player" + i + ", " + "Score: " + score);
			}
		}
		bw.close();
		try {
			sortTable();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	//Returns the player's name and score formatted correctly
	public String playerInfo() {
		return "Name: " + playerName + ", Score: " + score;
	}

	//returns the number of lines in the high score file
	public int linesInFile(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f.getAbsoluteFile()));
		int lines = 0;
		while (br.readLine() != null) {
			lines++;
		}
		br.close();
		return lines;
	}

	//Add game to high score file by appending it and getting line number from previous method
	public void saveGame() throws IOException {
		File f = new File("HighScores.txt");
		FileWriter fw = new FileWriter(f.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append("\n" + playerInfo());
		bw.close();
	}

	//sorts the high score table high to low using maps and other fun things
	public void sortTable() throws IOException {
		File f = new File("HighScores.txt");
		File temp = new File("temp.txt");
		TreeMap<Integer, ArrayList<String>> topTen = new TreeMap<Integer, ArrayList<String>>();
		BufferedReader br = new BufferedReader(new FileReader(f.getAbsoluteFile()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(temp.getAbsoluteFile()));


		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				continue;
			}
				String[] scores = line.split("Score: ");
				Integer score = Integer.valueOf(scores[1]);
				ArrayList<String> players = null;
				
				//make sure two players with same score are dealt with
				if ((players = topTen.get(score)) == null) {
					players = new ArrayList<String>(1);
					players.add(scores[0]);
					topTen.put(Integer.valueOf(scores[1]), players);
				}
				else {
					players.add(scores[0]);
				}

		}

		for (Integer score : topTen.descendingKeySet()) {
			for (String player : topTen.get(score)) {
				try {
					bw.append(player + "Score: " + score + "\n");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		br.close();
		bw.close();
		try {
			makeNewScoreTable();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	//save the sorted table to the high score file
	public void makeNewScoreTable() throws IOException {
		File f = new File("HighScores.txt");
		File g = new File("temp.txt");
		f.delete();
		g.renameTo(f);
	}
	
	//Print the top 10 scores, but first excecutes all other file-related methods
	public void printScores(Graphics g) throws IOException {
		try {
			makeTable();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		try {
			saveGame();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		try {
			sortTable();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		int h = 100;
		File fileToRead = new File("HighScores.txt");
		LineNumberReader lnr = new LineNumberReader(new FileReader(fileToRead));
		String line = lnr.readLine();
		while (line != null && lnr.getLineNumber() <= 10) {
			int rank = lnr.getLineNumber();
			g.drawString(rank + ". " + line, getWidth()/5, h);
			h += 15;
			line = lnr.readLine();
		}
		lnr.close();
	}

	//Private class that handles gameplay and controls
	private class BoardListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent ke) {
			int key = ke.getKeyCode();
			if (key == KeyEvent.VK_SPACE) {
				if (lives > MIN_LIVES) {
					if (isPaused.get() == false) {
						stop();
						isPaused.set(true);
					}
					else {
						start();
					}
				}
				else {
					paddle.setWidth(getWidth()/7);
					lives = MAX_LIVES;
					score = 0;
					bricksLeft = MAX_BRICKS;
					level = 1;
					makeBricks();
					isPaused.set(true);
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 5; j++) {
							brick[i][j].setDestroyed(false);
						}
					}
				}
			}
			if (key == KeyEvent.VK_LEFT) {
				paddle.setX(paddle.getX() - 50);
			}
			if (key == KeyEvent.VK_RIGHT) {
				paddle.setX(paddle.getX() + 50);
			}
		}
		@Override
		public void keyReleased(KeyEvent ke) {
			int key = ke.getKeyCode();
			if (key == KeyEvent.VK_LEFT) {
				paddle.setX(paddle.getX());
			}
			if (key == KeyEvent.VK_RIGHT) {
				paddle.setX(paddle.getX());
			}
		}
	}
}