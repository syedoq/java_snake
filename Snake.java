
class Body{
	
	Body next;
	Body prev;
	int x;
	int y;
	Body(int a,int b){
		x = a;
		y = b;
		next = null;
		prev = null;
	}
};
class Snake{
	int WIDTH;
	int HEIGHT;
		
	Body head = null;
	Body tail = null;
	int size = 0;
	Snake(int x,int y){
		createHead(x,y);
	}
	void createHead(int x,int y){
		head = new Body(x,y);
		size++;
		tail = head;
	}
	void grow(int x,int y){			// <t>- - - b<H>
		Body b = new Body(x,y);		//add to the last of linked list and make that head
		b.prev = head;
		head.next = b;
		head = b;
		size++;
	}
	boolean move(int pos,boolean removeTail){  
		Body current = head;
		if(pos == 1){  //move left
			if(head.x == 0){
				return false;
			}
			grow(head.x-10,head.y);
		}else if(pos == 2){//move right
			if(head.x == WIDTH-10){
				return false;
			}
			grow(head.x+10,head.y);
		}else if(pos == 3){//move up
			if(head.y == 0){
				return false;
			}
			grow(head.x,head.y-10);
		}
		else if(pos == 4){//move down
			if(head.y == HEIGHT-10){
				return false;
			}
			grow(head.x,head.y+10);			
		}if(removeTail){				
			size--;
			tail.next.prev = null;
			tail = tail.next;
		}
		return true;
	}

};