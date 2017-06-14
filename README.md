# Brick Breaker

Version 1.2 (Last Updated: 9/6/2014)

Music used with permission of [owner](https://www.youtube.com/user/8BitDanooct1).

---

## What's New

* Cleaned up unneeded folders and files
* Removed all console logging
* Fixed music bug on Linux
* Changed look and feel to emulate the OS it's running on
* Various bug fixes

## Description

This game is a clone of "Breakout", a game in which the player controls a paddle that is used to bounce a ball
that breaks blocks. Items are occasionally dropped from the blocks. The game tracks the player's name, lives,
level, and score as well. If the player scores high enough, they are placed on the high score board, which is
displayed at the end of every game. There are an unlimited amount of levels, and the user can also select to
have background music played throughout the game. Catch the green items to make your paddle larger, and avoid
the red ones!

## Screenshot

![Game](dist/img/screenshot.png "Game")

## Controls

* Spacebar: starts game, pauses/resumes during game
* Left arrow: moves paddle left
* Right arrow: moves paddle right

It is recommended to click and release the keys each time you wish to move, rather than hold them down, due to
the delay built into most operating systems. You will be far more precise if you click and release for each movement.

## How to Download

* Go to this URL: https://github.com/tylucaskelley/BrickBreaker
* Click the "Download ZIP" button near the bottom-right side
* Unzip the folder once it downloads.

## How to Run

* Double-click the `BrickBreaker.jar` file, located in `dist`.
* On Linux, you may have to run from terminal:

```bash
$ java -jar dist/BrickBreaker.jar
```

* Enter your name.
* Select an option for audio playback.
* Enjoy!

## Contributing

1. Refine relationship between ball and brick: sometimes there is odd behavior when ball hits corner of a
   brick (technically hits 2 sides at same time).
2. Potential sounds: when ball hits something, new level, life loss, game over, etc.
3. Fix error where two clicks of spacebar are needed occasionally. May be a concurrency problem.
4. Compose original soundtrack for game
