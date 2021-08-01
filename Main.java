import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*; 
import java.util.Scanner; 

class Main{
	
	public static void main(String[] args){
		gameLoop();
	}

	static void gameLoop(){
		
		Board board = new Board();
		try {
			File myObj = new File("score.txt");
			Scanner myReader = new Scanner(myObj);
			board.highScore = myReader.nextInt();
			myReader.close();
		}catch(FileNotFoundException e){}
		
		JFrame jframe = new JFrame("SNAKE");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		jframe.add(board);
		jframe.pack();
		jframe.setResizable(false);
		jframe.setVisible(true);
		jframe.setLocationRelativeTo(null);
		
	
		jframe.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				super.keyPressed(e);
				int key = e.getKeyCode();
				if(board.notGameOver){						
					if(key == KeyEvent.VK_LEFT){
						board.notGameOver = board.snake.move(1,true);
						board.lastDirection = 1;
					}else if(key == KeyEvent.VK_RIGHT){
						board.notGameOver = board.snake.move(2,true);
						board.lastDirection = 2;
					}else if(key == KeyEvent.VK_UP){
						board.notGameOver = board.snake.move(3,true);
						board.lastDirection = 3;
					}else if(key == KeyEvent.VK_DOWN){
						board.notGameOver = board.snake.move(4,true);
						board.lastDirection = 4;
					}
					board.repaint();
				} 
			}
		});
	
		while(board.notGameOver){
			if(board.lastDirection!=0){
				board.notGameOver = board.snake.move(board.lastDirection,true);				
			}
			try{
				TimeUnit.MILLISECONDS.sleep(150);
			}catch(InterruptedException e){
				
			}
			board.repaint();		
		}
		board.repaint();
		try{
			FileWriter myWriter = new FileWriter("score.txt");
			myWriter.write(String.valueOf(board.highScore));
			myWriter.close();
		}catch(IOException e){}
		
	}

};