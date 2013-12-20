/*
*    Brick Breaker, Version 1.2
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

README

Author: Ty-Lucas Kelley
Music: Dan (YouTube Channel: 8BitDanooct1)
Current Version: 1.2
Date of Last Update: 12/19/2013

What's New:
	-Fixed Music bug on Linux (need to use Oracle Java, not open-jdk)
	-Changed look and feel to emulate the OS it's running on
	-Small bug fixes

Description: 
	This game is a clone of "Breakout", a game in which the player controls a paddle that is used to bounce a ball that breaks blocks. Items are occasionally dropped from the blocks. The game tracks the player's name, lives, level, and score as well. If the player scores high enough, they are placed on the high score board, which is displayed at the end of every game. There are an unlimited amount of levels, and the user can also select to have background music played throughout the game. Catch the green items to make your paddle larger, and avoid the red ones!

Controls:
	Spacebar: starts game, pauses/resumes during game
	Left arrow: moves paddle left
	Right arrow: moves paddle right
		**It is recommended to click and release the keys each time you wish to move, rather than hold them down, due to the delay built into most operating systems. You will be far more precise if you click and release for each movement.**

How to Download Latest Version:
	Go to this URL: https://github.com/tylucaskelley/BrickBreaker
	Click the "Download ZIP" button near the bottom-right side
	Unzip the folder once it downloads.

How to Run:
	Double-click the "BrickBreaker.jar" file.
		-On Ubuntu Linux, you may have to run from terminal: "java -jar BrickBreaker.jar"
	Enter your name.
	Select an option for audio playback.
	Enjoy!

**To-Do: For myself and any who wish to contribute to the game's improvement**
	-Refine relationship between ball and brick: sometimes there is odd behavior when ball hits corner of a brick (technically hits 2 sides at same time).
	-Potential sounds: when ball hits something, new level, life loss, game over, etc.
	-Fix error where two clicks of spacebar are needed occasionally. May be a concurrency problem.
	-Compose original soundtrack for game using FamiTracker

Thank you for playing! For more projects and information about me, check out www.tylucaskelley.com and my GitHub profile: https://github.com/tylucaskelley/
-Ty
