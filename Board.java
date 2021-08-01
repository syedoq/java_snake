import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Font;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.util.Random;

class Board extends JPanel{
	Random rand; 
	final int WIDTH = 600;
	final int HEIGHT = 600;
	int score = 0;
	int highScore = 0;
	int foodX = 0;
	int foodY = 0;
	boolean notGameOver = true;
	Snake snake;
	Font small;
	String msgScore = "Score : ";
	String msgHighScore = "High Score : ";
	int lastDirection = 0;
	
	Board(){
		rand = new Random();
		setPreferredSize(new Dimension(WIDTH,HEIGHT+50));
		small = new Font("Helvetica", Font.BOLD, 14);
		snake = new Snake(HEIGHT/2,WIDTH/2);
		snake.WIDTH = WIDTH;
		snake.HEIGHT = HEIGHT;
		generateFood();
	}
	void generateFood(){
		foodY = rand.nextInt((WIDTH-1)/10);
		foodX = rand.nextInt((HEIGHT-1)/10);
		foodX *= 10;
		foodY *= 10;
		if(foodX == 0 || foodX == WIDTH-10){
			foodX = 10;
		}if(foodY == 0 || foodY == HEIGHT-10){
			foodY = 10;
		}
		Body current;
		current = snake.head;
		for(int i=0;i<snake.size;i++){   // < snake size
			if(foodX == current.x || foodX == current.y || foodY == current.x || foodY == current.y){
				generateFood();
				break;
			}
			current = current.prev;
		}
	}
	public void paint(Graphics g){
		if(!notGameOver){
			g.setColor(Color.BLACK);
			g.fillRect(0,0,WIDTH,HEIGHT); //x,y,width,height	
			g.setColor(Color.WHITE);
			g.setFont(small);
			g.drawString("GAME OVER", (WIDTH/2)-40, HEIGHT/2);
			return;
		}
		
		g.clearRect(0,0,WIDTH,HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,WIDTH,HEIGHT+200); //x,y,width,height
		
		/*
		g.setColor(Color.RED);
		
		for(int i=0;i<WIDTH/10;i++){
			g.drawLine(i*10,0,i*10,HEIGHT);
		}for(int i=0;i<HEIGHT/10;i++){
			g.drawLine(0,i*10,HEIGHT,i*10);
		}
		*/
		//create a line for score
		g.setColor(Color.WHITE);
		g.drawLine(0,(WIDTH/10)*10,HEIGHT+200,(WIDTH/10)*10);
	
		//food
		g.setColor(Color.RED);
		g.fillRect(foodX, foodY, 10, 10);	
				
	
		//score and high score
		if(score>highScore){
			highScore = score;
		}
		g.setFont(small);
		//g.drawString(msgScore+String.valueOf(score), 20, HEIGHT+30);
		g.drawString(msgScore+String.valueOf(score), 20, HEIGHT+30);
		g.drawString("\uD83D\uDC0D", (WIDTH/2), HEIGHT+30);
		g.drawString(msgHighScore+String.valueOf(highScore), WIDTH-150, HEIGHT+30);
		
		//snake
		Body current;
		current = snake.head;
		if(current.x == foodX && current.y == foodY){
			try{
			generateFood();
			snake.move(lastDirection,false);
			}catch(Exception e){
				System.out.print("TEST");
			}
			score += 1;
		} 
		for(int i=0;i<snake.size;i++){   // < snake size
			if(i==0){
				g.setColor(Color.GREEN);
				g.fillRect(current.x, current.y, 10, 10);	
				g.setColor(Color.ORANGE);
			}
			else{
				if(current != null){
					g.fillRect(current.x, current.y, 10, 10);
				}
			}
			if(current.prev != null){
				current = current.prev;				
			}
		}
	}
		
};