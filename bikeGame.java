import hsa_ufa.Console;
import java.awt.*;
import java.util.Random;
import SimpleSound.SoundPlayer;

public class bikeGame {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Random rng = new Random();
		Console c = new Console();
		
		Image bike = Toolkit.getDefaultToolkit().getImage(c.getClass().getClassLoader().getResource("bike.png"));
		Image coin = Toolkit.getDefaultToolkit().getImage(c.getClass().getClassLoader().getResource("coin.png"));
		Image car = Toolkit.getDefaultToolkit().getImage(c.getClass().getClassLoader().getResource("car.png"));
		Image heart = Toolkit.getDefaultToolkit().getImage(c.getClass().getClassLoader().getResource("heart.png"));
		Image dad = Toolkit.getDefaultToolkit().getImage(c.getClass().getClassLoader().getResource("dad.jpg"));

		//music: bensound.com
		SoundPlayer background = new SoundPlayer("416778__mativve__happy-sandbox.wav");
		
		int x1 = 0;
		int y1 = 50;
		int w1 = 50;
		int h1 = 50;
		int random_numx1 = 100;
		int random_numy1 = 100;
		int w2 = 30;
		int h2 = 30;
		int w3 = 50;
		int h3 = 50;
		int x3 = c.getDrawWidth() - w3;
		int random_numy2 = 100;
		int x4 = c.getDrawWidth() - w3;
		int random_numy3 = 200;
		int counter = 0;
		int points = 0;
		int highscore = 0;
		int obstacle = 0;
		int seconds = 50;
		int counter2 = 0;
		boolean life1 = true;
		boolean life2 = true;
		boolean life3 = true;
		boolean game_on = true;
		boolean level_2 = false;
		
		background.play();
		c.setBackgroundColor(Color.BLUE);
		c.clear();
		c.setColor(Color.GREEN);
		c.setFont(new Font("SansSerif", Font.BOLD, 50));
		c.drawString("Bike to Fifty", c.getDrawWidth() / 3, c.getDrawHeight()/2);
		c.setFont(new Font("SansSerif", Font.ITALIC, 20));
		c.drawString("Press any key to begin", c.getDrawWidth() / 3, 380);
		c.getChar();
		
		//Level 1
		while (true) {
			points = 0;
			counter = 0;
			x1 = 0;
			y1 = 50;
			life1 = true;
			life2 = true;
			life3 = true;
			c.clear();
			c.setBackgroundColor(Color.PINK);
			c.clear();
			c.setColor(Color.BLACK);
			c.setFont(new Font("SansSerif", Font.BOLD, 40));
			c.drawString("Level One", 200, 50);
			c.setFont(new Font("SansSerif", Font.ITALIC, 20));
			c.drawString("Welcome to the Bike to Fifty Game! Move your bike using the arrow", 0, 100);
			c.drawString("keys and try to collect the coins before they disappear and without", 0, 150);
			c.drawString("hitting the edges. You must collect 50 coins to pass the level!", 0, 200);
			c.drawString("If you let three coins disappear, you will lose.", 0, 250);
			c.setFont(new Font("SansSerif", Font.BOLD, 20));
			c.drawString("Press any key to begin.", 200, 300);
			c.getChar();
			c.clear();
			do {
				synchronized (c) {
					c.clear();
					c.setBackgroundColor(Color.WHITE);
					c.clear();
					c.drawImage(bike, x1, y1, w1, h1);
					c.drawImage(coin, random_numx1, random_numy1, w2, h2);
					counter += 1;
					c.setColor(Color.BLACK);
					c.setFont(new Font("Times New Roman", Font.BOLD, 30));
					c.drawString("Points: "+ points, 0, 30);
					if (life3)
						c.drawImage(heart, 150, 5, 30, 30);
					if (life2)
						c.drawImage(heart, 200, 5, 30, 30);
					if (life1)
						c.drawImage(heart, 250, 5, 30, 30);

				}
				Thread.sleep(30);
				//check music
				if (!background.isPlaying())
					background.play();
				//move bike
				if (c.getLastKeyCode() == (Console.VK_LEFT))
					x1 -= 5;
				else if (c.getLastKeyCode() == (Console.VK_RIGHT))
					x1 += 5;
				else if (c.getLastKeyCode() == (Console.VK_UP))
					y1 -= 5;
				else if (c.getLastKeyCode() == (Console.VK_DOWN))
					y1 += 5;
				// collision with walls
				if (x1 + w1 > c.getDrawWidth() || x1 < 0 || y1 < 0 || y1 + h1 > c.getDrawHeight()) {
					c.setBackgroundColor(Color.BLACK);
					c.clear();
					c.setColor(Color.WHITE);
					c.setFont(new Font("SansSerif", Font.BOLD, 40));
					c.drawString("YOU LOST!", 200, 50);
					c.setFont(new Font("SansSerif", Font.BOLD, 20));
					c.drawString("Press any key to continue.",  200, 100);
					c.getChar();
					game_on = false;
				}
				//check for collision with coin
				if (x1 < random_numx1 + w2 && random_numx1 < x1 + w1 && y1 < random_numy1 + h2 && random_numy1 < y1 + h1) {
					random_numx1 = rng.nextInt(c.getDrawWidth() - w2);
					random_numy1 = rng.nextInt(c.getDrawHeight() - h2);
					points++;
					counter = 0;
				}
				//coin disappears after 4 seconds
				if (counter == 133) {
					if (life1) {
						life1 = false;
						random_numx1 = rng.nextInt(c.getDrawWidth() - w2);
						random_numy1 = rng.nextInt(c.getDrawHeight() - h2);
						counter = 0;
					}
					else if (life2) {
						life2 = false;
						random_numx1 = rng.nextInt(c.getDrawWidth() - w2);
						random_numy1 = rng.nextInt(c.getDrawHeight() - h2);
						counter = 0;
					}
					else if (life3) {
						c.setBackgroundColor(Color.BLACK);
						c.clear();
						c.setColor(Color.WHITE);
						c.setFont(new Font("SansSerif", Font.BOLD, 40));
						c.drawString("YOU LOST!", 200, 50);
						c.setFont(new Font("SansSerif", Font.BOLD, 20));
						c.drawString("Press any key to continue.", 200, 100);
						c.getChar();
						game_on = false;
						counter = 0;
					}
				}
				//completed challenge (50 coins)
				if (points == 50) {
					c.setBackgroundColor(Color.MAGENTA);
					c.clear();
					c.setColor(Color.BLACK);
					c.setFont(new Font("SansSerif", Font.BOLD, 40));
					c.drawString("YOU WON!", 200, 50);
					c.setFont(new Font("SansSerif", Font.BOLD, 20));
					c.drawString("Press any key to continue.", 200, 100);
					c.getChar();
					game_on = false;
				}
				
			} while (game_on);
			c.setBackgroundColor(Color.CYAN);
			c.clear();
			
			if (highscore < points)
				highscore = points;
			if (points == 50) {
				c.setColor(Color.BLACK);
				c.setFont(new Font("SansSerif", Font.BOLD, 40));
				c.drawString("Level One", 200, 50);
				c.setFont(new Font("SansSerif", Font.BOLD, 40));
				c.drawString("Points: " + points, 200, 100);
				c.drawString("High Score: " + highscore, 200, 150);
				c.setFont(new Font("SansSerif", Font.ITALIC, 20));
				c.drawString("Press enter to play again", 200, 250);
				c.drawString("Press 2 to proceed to level 2", 200, 300);
				c.drawString("Press backspace to exit", 200, 350);
				c.getChar();
			}
			else {
				c.setColor(Color.BLACK);
				c.setFont(new Font("SansSerif", Font.BOLD, 40));
				c.drawString("Level One", 200, 50);
				c.setFont(new Font("SansSerif", Font.BOLD, 40));
				c.drawString("Points: " + points, 200, 100);
				c.drawString("High Score: " + highscore, 200, 150);
				c.setFont(new Font("SansSerif", Font.ITALIC, 20));
				c.drawString("Press enter to play again", 200, 250);
				c.drawString("Press backspace to exit", 200, 300);
				c.getChar();			
			}
			//menu
			if (c.getLastKeyCode() == Console.VK_ENTER) {
				game_on = true;
			}
			else if (c.getLastKeyCode() == ('2')) {
				level_2 = true;
				break;
			}
			else if (c.getLastKeyCode() == Console.VK_BACK_SPACE) {
				break;
			}
		}
			
			//Level 2
			if (level_2) {
				highscore = 0;
				game_on = true;
				while (true) {
					points = 0;
					counter = 0;
					obstacle = 0;
					life1 = true;
					life2 = true;
					life3 = true;
					x1 = 0;
					y1 = 50;
					x3 = c.getDrawWidth() - w3;
					c.clear();
					c.setBackgroundColor(Color.ORANGE);
					c.clear();
					c.setFont(new Font("SansSerif", Font.BOLD, 40));
					c.drawString("Level Two", 200, 50);
					c.setFont(new Font("SansSerif", Font.ITALIC, 20));
					c.drawString("In Level Two, your goal is to avoid 50 cars without being hit.", 0, 100);
					c.drawString("Try to collect as many coins as you can and don't let the", 0, 150);
					c.drawString("coins disappear!", 0, 200);
					c.setFont(new Font("SansSerif", Font.BOLD, 20));
					c.drawString("Press any key to begin.", 200, 250);
					c.getChar();
					c.clear();
					do {
						synchronized (c) {
							c.clear();
							c.setBackgroundColor(Color.WHITE);
							c.clear();
							c.drawImage(bike, x1, y1, w1, h1);
							c.drawImage(coin, random_numx1, random_numy1, w2, h2);
							counter += 1;
							c.setFont(new Font("Times New Roman", Font.BOLD, 20));
							c.setColor(Color.BLACK);
							c.drawString("Points: " + points, 0, 30);
							c.drawString("Cars: "+obstacle, 100, 30);
							c.drawImage(car, x3, random_numy2, w3, h3);
							if (life3)
								c.drawImage(heart, 200, 5, 30, 30);
							if (life2)
								c.drawImage(heart, 250, 5, 30, 30);
							if (life1)
								c.drawImage(heart, 300, 5, 30, 30);
						}
						Thread.sleep(30);
						//check music
						if (!background.isPlaying())
							background.play();
						// move bike
						if (c.getLastKeyCode() == (Console.VK_LEFT))
							x1 -= 5;
						else if (c.getLastKeyCode() == (Console.VK_RIGHT))
							x1 += 5;
						else if (c.getLastKeyCode() == (Console.VK_UP))
							y1 -= 5;
						else if (c.getLastKeyCode() == (Console.VK_DOWN))
							y1 += 5;
						// collision with walls
						if (x1 + w1 > c.getDrawWidth() || x1 < 0 || y1 < 0 || y1 + h1 > c.getDrawHeight()) {
							c.setBackgroundColor(Color.BLACK);
							c.clear();
							c.setColor(Color.WHITE);
							c.setFont(new Font("SansSerif", Font.BOLD, 40));
							c.drawString("YOU LOST!", 200, 50);
							c.setFont(new Font("SansSerif", Font.BOLD, 20));
							c.drawString("Press any key to continue.", 200, 100);
							c.getChar();
							game_on = false;
						}
						// check for collision with coin
						if (x1 < random_numx1 + w2 && random_numx1 < x1 + w1 && y1 < random_numy1 + h2
								&& random_numy1 < y1 + h1) {
							random_numx1 = rng.nextInt(c.getDrawWidth() - w2);
							random_numy1 = rng.nextInt(c.getDrawHeight() - h2);
							points++;
							counter = 0;
						}
						// coin disappears after 4 seconds
						if (counter == 133) {
							if (life1) {
								life1 = false;
								random_numx1 = rng.nextInt(c.getDrawWidth() - w2);
								random_numy1 = rng.nextInt(c.getDrawHeight() - h2);
								counter = 0;
							}
							else if (life2) {
								life2 = false;
								random_numx1 = rng.nextInt(c.getDrawWidth() - w2);
								random_numy1 = rng.nextInt(c.getDrawHeight() - h2);
								counter = 0;
							}
							else if (life3) {
								c.setBackgroundColor(Color.BLACK);
								c.clear();
								c.setColor(Color.WHITE);
								c.setFont(new Font("SansSerif", Font.BOLD, 40));
								c.drawString("YOU LOST!", 200, 50);
								c.setFont(new Font("SansSerif", Font.BOLD, 20));
								c.drawString("Press any key to continue.", 200, 100);
								c.getChar();
								game_on = false;
								counter = 0;
							}
						}
						//move car
						x3 -= 10;
						if (x3 < 0) {
							obstacle++;
							random_numy2 = rng.nextInt(c.getDrawHeight() - h2);
							x3 = c.getDrawWidth() - w3;
						}
						//check for collision with car
						if (x1 < x3 + w3 && x3 < x1 + w1 && y1 < random_numy2 + h3 && random_numy2 < y1 + h1) {
							c.setBackgroundColor(Color.BLACK);
							c.clear();
							c.setColor(Color.WHITE);
							c.setFont(new Font("SansSerif", Font.BOLD, 40));
							c.drawString("YOU LOST!", 200, 50);
							c.setFont(new Font("SansSerif", Font.BOLD, 20));
							c.drawString("Press any key to continue.", 200, 100);
							c.getChar();
							game_on = false;
						}
						// completed challenge (50 obstacles)
						if (obstacle == 50) {
							c.setBackgroundColor(Color.MAGENTA);
							c.clear();
							c.setColor(Color.BLACK);
							c.setFont(new Font("SansSerif", Font.BOLD, 40));
							c.drawString("YOU WON!", 200, 50);
							c.setFont(new Font("SansSerif", Font.BOLD, 20));
							c.drawString("Press any key to continue.", 200, 100);
							c.getChar();
							game_on = false;
						}

					} while (game_on);
					c.setBackgroundColor(Color.CYAN);
					c.clear();

					if (highscore < points)
						highscore = points;
					if (obstacle == 50) {
						c.setColor(Color.BLACK);
						c.setFont(new Font("SansSerif", Font.BOLD, 40));
						c.drawString("Level Two", 200, 50);
						c.setFont(new Font("SansSerif", Font.BOLD, 40));
						c.drawString(obstacle + "/50 cars avoided!", 200, 100);
						c.drawString("Points: " + points, 200, 150);
						c.drawString("High Score: " + highscore, 200, 200);
						c.setFont(new Font("SansSerif", Font.ITALIC, 20));
						c.drawString("Press enter to play again", 200, 250);
						c.drawString("Press 3 to proceed to level 3", 200, 300);
						c.drawString("Press backspace to exit", 200, 350);
						c.getChar();
					} else {
						c.setColor(Color.BLACK);
						c.setFont(new Font("SansSerif", Font.BOLD, 40));
						c.drawString("Level Two", 200, 50);
						c.setFont(new Font("SansSerif", Font.BOLD, 40));
						c.drawString(obstacle + "/50 cars avoided!", 200, 100);
						c.drawString("Points: " + points, 200, 150);
						c.drawString("High Score: " + highscore, 200, 200);
						c.setFont(new Font("SansSerif", Font.ITALIC, 20));
						c.drawString("Press enter to play again", 200, 250);
						c.drawString("Press backspace to exit", 200, 300);
						c.getChar();
					}
					// menu
					if (c.getLastKeyCode() == Console.VK_ENTER) {
						game_on = true;
					} else if (c.getLastKeyCode() == Console.VK_BACK_SPACE || c.getLastKeyCode() == ('3')) {
						break;
				}
			}
				//Level 3
				if (c.getLastKeyCode() == '3') {
					highscore = 0;
					game_on = true;
					while (true) {
						points = 0;
						counter = 0;
						seconds = 50;
						counter2 = 0;
						x1 = 0;
						y1 = 50;
						x3 = c.getDrawWidth() - w3;
						x4 = c.getDrawWidth() - w3;
						obstacle = 0;
						life1 = true;
						life2 = true;
						life3 = true;
						c.clear();
						c.setBackgroundColor(Color.GREEN);
						c.clear();
						c.setFont(new Font("SansSerif", Font.BOLD, 40));
						c.drawString("Level Three", 200, 50);
						c.setFont(new Font("SansSerif", Font.ITALIC, 20));
						c.drawString("In Level Three, your goal is to avoid cars and stay alive for 50", 0, 100);
						c.drawString("seconds. Try to collect as many coins as you can and don't let", 0, 150);
						c.drawString("the coins disappear!", 0, 200);
						c.setFont(new Font("SansSerif", Font.BOLD, 20));
						c.drawString("Press any key to begin.", 200, 250);
						c.getChar();
						c.clear();
						do {
							synchronized (c) {
								c.clear();
								c.setBackgroundColor(Color.WHITE);
								c.clear();
								c.drawImage(bike, x1, y1, w1, h1);
								c.drawImage(coin, random_numx1, random_numy1, w2, h2);
								counter++;
								c.setFont(new Font("Times New Roman", Font.BOLD, 20));
								c.setColor(Color.BLACK);
								c.drawString("Points: " + points, 0, 30);
								c.drawString("Cars: "+obstacle, 100, 30);
								c.drawString("0: "+seconds, 200, 30);
								counter2++;
								c.drawImage(car, x3, random_numy2, w3, h3);
								c.drawImage(car, x4, random_numy3, w3, h3);
								if (life3)
									c.drawImage(heart, 250, 5, 30, 30);
								if (life2)
									c.drawImage(heart, 300, 5, 30, 30);
								if (life1)
									c.drawImage(heart, 350, 5, 30, 30);
							}
							Thread.sleep(30);
							//check music
							if (!background.isPlaying())
								background.play();
							// move bike
							if (c.getLastKeyCode() == (Console.VK_LEFT))
								x1 -= 5;
							else if (c.getLastKeyCode() == (Console.VK_RIGHT))
								x1 += 5;
							else if (c.getLastKeyCode() == (Console.VK_UP))
								y1 -= 5;
							else if (c.getLastKeyCode() == (Console.VK_DOWN))
								y1 += 5;
							//count down seconds
							if (counter2 == 11) {
								seconds--;
								counter2 = 0;
							}
							// collision with walls
							if (x1 + w1 > c.getDrawWidth() || x1 < 0 || y1 < 0 || y1 + h1 > c.getDrawHeight()) {
								c.setBackgroundColor(Color.BLACK);
								c.clear();
								c.setColor(Color.WHITE);
								c.setFont(new Font("SansSerif", Font.BOLD, 40));
								c.drawString("YOU LOST!", 200, 50);
								c.setFont(new Font("SansSerif", Font.BOLD, 20));
								c.drawString("Press any key to continue.", 200, 100);
								c.getChar();
								game_on = false;
							}
							// check for collision with coin
							if (x1 < random_numx1 + w2 && random_numx1 < x1 + w1 && y1 < random_numy1 + h2
									&& random_numy1 < y1 + h1) {
								random_numx1 = rng.nextInt(c.getDrawWidth() - w2);
								random_numy1 = rng.nextInt(c.getDrawHeight() - h2);
								points++;
								counter = 0;
							}
							// coin disappears after 4 seconds
							if (counter == 133) {
								if (life1) {
									life1 = false;
									random_numx1 = rng.nextInt(c.getDrawWidth() - w2);
									random_numy1 = rng.nextInt(c.getDrawHeight() - h2);
									counter = 0;
								}
								else if (life2) {
									life2 = false;
									random_numx1 = rng.nextInt(c.getDrawWidth() - w2);
									random_numy1 = rng.nextInt(c.getDrawHeight() - h2);
									counter = 0;
								}
								else if (life3) {
									c.setBackgroundColor(Color.BLACK);
									c.clear();
									c.setColor(Color.WHITE);
									c.setFont(new Font("SansSerif", Font.BOLD, 40));
									c.drawString("YOU LOST!", 200, 50);
									c.setFont(new Font("SansSerif", Font.BOLD, 20));
									c.drawString("Press any key to continue.", 200, 100);
									c.getChar();
									game_on = false;
									counter = 0;
								}
							}
							//move car
							x3 -= 10;
							if (x3 < 0) {
								obstacle++;
								random_numy2 = rng.nextInt(c.getDrawHeight() - h2);
								x3 = c.getDrawWidth() - w3;
							}
							x4 -= 7;
							if (x4 < 0) {
								obstacle++;
								random_numy3 = rng.nextInt(c.getDrawHeight() - h2);
								x4 = c.getDrawWidth() - w3;
							}
							//check for collision with car
							if (x1 < x3 + w3 && x3 < x1 + w1 && y1 < random_numy2 + h3 && random_numy2 < y1 + h1) {
								c.setBackgroundColor(Color.BLACK);
								c.clear();
								c.setColor(Color.WHITE);
								c.setFont(new Font("SansSerif", Font.BOLD, 40));
								c.drawString("YOU LOST!", 200, 50);
								c.setFont(new Font("SansSerif", Font.BOLD, 20));
								c.drawString("Press any key to continue.", 200, 100);
								c.getChar();
								game_on = false;
							}
							if (x1 < x4 + w3 && x4 < x1 + w1 && y1 < random_numy3 + h3 && random_numy3 < y1 + h1) {
								c.setBackgroundColor(Color.BLACK);
								c.clear();
								c.setColor(Color.WHITE);
								c.setFont(new Font("SansSerif", Font.BOLD, 40));
								c.drawString("YOU LOST!", 200, 50);
								c.setFont(new Font("SansSerif", Font.BOLD, 20));
								c.drawString("Press any key to continue.", 200, 100);
								c.getChar();
								game_on = false;
							}
							// completed challenge (50 seconds)
							if (seconds == 0) {
								c.setBackgroundColor(Color.MAGENTA);
								c.clear();
								c.setColor(Color.BLACK);
								c.setFont(new Font("SansSerif", Font.BOLD, 40));
								c.drawString("YOU WON!", 200, 50);
								c.setFont(new Font("SansSerif", Font.BOLD, 20));
								c.drawString("Press any key to continue.", 200, 100);
								c.getChar();
								game_on = false;
							}

						} while (game_on);
						c.setBackgroundColor(Color.CYAN);
						c.clear();

						if (highscore < points)
							highscore = points;
						if (seconds == 0) {
							c.drawImage(dad, 50, 0);
							c.setColor(Color.BLACK);
							c.setFont(new Font("SansSerif", Font.BOLD, 60));
							c.drawString("CONGRATULATIONS", 0, 150);
							c.drawString("YOU WON!", 200, 300);
							c.setFont(new Font("SansSerif", Font.BOLD, 20));
							c.drawString("Press backspace to exit", 200, 400);
							c.getChar();
						} else {
							c.setColor(Color.BLACK);
							c.setFont(new Font("SansSerif", Font.BOLD, 40));
							c.drawString("Level Three", 200, 50);
							c.setFont(new Font("SansSerif", Font.BOLD, 40));
							c.drawString(obstacle + "/50 cars avoided!", 200, 100);
							c.drawString("Points: " + points, 200, 150);
							c.drawString("High Score: " + highscore, 200, 200);
							c.drawString("Time 0:"+seconds, 200, 250);
							c.setFont(new Font("SansSerif", Font.ITALIC, 20));
							c.drawString("Press enter to play again", 200, 300);
							c.drawString("Press backspace to exit", 200, 350);
							c.getChar();
						}
						// menu
						if (c.getLastKeyCode() == Console.VK_ENTER) {
							game_on = true;
						} else if (c.getLastKeyCode() == Console.VK_BACK_SPACE) {
							break;
						}
					}
				}

			}
			//After exit
			c.setBackgroundColor(Color.WHITE);
			c.clear();
		}

	}
