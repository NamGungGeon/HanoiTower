import java.util.Stack;

public class HanoiTop {
	private Block block[];
	private int numberOfBlock;
	private Stack<Block> top[];
	private final int numberOfTop=3;
	
	private int numberOfMove=0;
	
	private Stack<Block> endTopStatus;
	
	public HanoiTop(int numberOfBlock){
		initHanoiTop(numberOfBlock);
	}
	//==================================================
	//Must called in Constructor
	//Do not change call order
	private void initHanoiTop(int numberOfBlock){
		createBlock(numberOfBlock);
		createTop();
	}
	private void createBlock(int numberOfBlock){
		this.numberOfBlock=numberOfBlock;
		block=new Block[numberOfBlock];
		for(int i=0; i<numberOfBlock; i++){
			block[i]=new Block(i+1);
		}
	}
	private void createTop(){
		top=new Stack[numberOfTop];
		endTopStatus=new Stack<Block>();
		for(int i=0; i<numberOfTop; i++){
			top[i]=new Stack<Block>();
		}
		for(int i=numberOfBlock-1; i>=0; i--){
			top[0].push(block[i]);
			endTopStatus.push(block[i]);
		}
	}
	//==================================================
	
	public void printHanoiTopStatus(){
		System.out.println("==============================");
		for(int i=0; i<numberOfTop; i++){
			System.out.print("Stack "+(i+1)+" : ");
			for(int j=0; j<top[i].size(); j++){
				System.out.print(top[i].get(j).getSize()+" ");
			}
			System.out.println("");
		}
		System.out.println("Number of Movememt: "+numberOfMove);
		System.out.println("==============================");
	}
	
	private void recursive(int n, Stack<Block> one, Stack<Block> two, Stack<Block> three){
		if(n==1){
			moveBlock(one, three);
		}else{
			recursive(n-1, one, three, two);
			moveBlock(one, three);
			recursive(n-1, two, one, three);
		}
	}
	
	private void non_recursive(){
		int flag=numberOfBlock;
		Stack<Object> order=new Stack<Object>();
		Stack<Block> from=top[0];
		Stack<Block> by=top[1];
		Stack<Block> to=top[2];
		
		while(true){
			while(flag> 1){
				//Save MoveOrder to Stack
				//to->from, to->by, to-> from....( ~flag==2)
				order.push(to);
				order.push(by);
				order.push(from);
				//Save number to Stack
				//numberOfBlock, numberOfBlock-1, ... 2, 1
				order.push(new Integer(flag));
				flag--;
				
				Stack<Block> temp=to;
				to=by;
				by=temp;
			}
			//if numberOfBlock is odd number, to is top[2] (3rd top)
			//else if numberOfBlock is even number, to is top[1]  (2rd top)
			moveBlock(from, to);
			
			if(order.size()!=0){
				flag=Integer.parseInt(order.pop().toString());
				from=(Stack<Block>)order.pop();
				by=(Stack<Block>)order.pop();
				to=(Stack<Block>)order.pop();
				
				moveBlock(from, to);
				flag--;
				
				Stack<Block> temp=from;
				from=by;
				by=temp;
			}else{
				break;
			}
		}
		
	}
	
	public void start(){
		recursive(numberOfBlock, top[0], top[1], top[2]);
		//non_recursive();
	}
	
	public boolean moveBlock(Stack<Block> beforeTop, Stack<Block> afterTop){
		if(beforeTop.size()==0){
			return false;
		}
		
		if(beforeTop.size()!=0 && afterTop.size()==0){
			afterTop.push(beforeTop.pop());
			numberOfMove++;
			printHanoiTopStatus();
			return true;
		}
		
		//compare blocksize of beforeTop to blocksize of afterTop
		if(beforeTop.get(beforeTop.size()-1).getSize()< afterTop.get(afterTop.size()-1).getSize()){
			afterTop.push(beforeTop.pop());
			numberOfMove++;
			printHanoiTopStatus();
			return true;
		}
		
		return false;
	}
	
}
