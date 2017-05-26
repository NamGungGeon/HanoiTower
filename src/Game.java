import java.util.Scanner;

public class Game {
	public static void main(String args[]){
		Scanner scan=new Scanner(System.in);
		HanoiTop hanoi;
		
		while(true){
			System.out.print("Number of disk >> ");
			//Number of disk
			int scanedNum=scan.nextInt();
			scan.nextLine();
			if(scanedNum<=1){
				//Exception Handling
				System.out.println("Number must be bigger than 1");
				continue;
			}else{
				hanoi=new HanoiTop(scanedNum);
				break;
			}
		}
		
		hanoi.printHanoiTopStatus();
		hanoi.start();
		
	}
}
