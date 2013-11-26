/*
*    Brick Breaker, Version 1.0
*    By Ty-Lucas Kelley
*	
*	 **LICESNSE**
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

README: Brick Breaker, Version 1.0

Author: Ty-Lucas Kelley
Class: CSC 171 The Science of Programming
Lab Session: MW 1230-145
TA: Ciaran Downey
Assignment: Project 02

Description: 
	This game is a clone of "BREAKOUT", a game in which the player controls a paddle that is used to bounce a ball that breaks blocks. Items are occasionally dropped from the blocks. The game tracks the player's name, lives, level, and score as well. If the player scores high enough, they are placed on the high score board, which is displayed at the end of every game. There are an unlimited amount of levels, and the user can also select to have background music played throughout the game.

Controls:
	Spacebar: starts game, pauses during game
	Left arrow: moves paddle left
	Right arrow: moves paddle right
		**It is recommended to click and release the keys each time you wish to move, rather than hold them down, due to the delay built into most operating systems. You will be far more precise if you click and release for each movement.**

How to download (from GitHub):
	Go to this URL: https://github.com/tylucaskelley/BrickBreaker
	Find the "Download ZIP" button near the bottom-right side
	Click it!
	Unzip the folder once it downloads.

How to Run:
	Double-click the "BrickBreaker.jar" file.
	Enter your name.
	Select an option for audio.
	Enjoy the game!

**To-Do**
	Fix error where two clicks of space bar are sometimes needed. May be a concurrency/syncing issue. 
	Change color of bricks for each level to break up monotony.
	Refine relationship between ball and brick: sometimes there is odd behavior when ball hits corner of a brick (technically 2 sides at same time).
	Different songs for each level.
