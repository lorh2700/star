package bl.com;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


public class LoadBalancer {
	
	public static Queue<Integer> fwQueue;
	public static BackWorker[] wokers;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		fwQueue = new LinkedList<>();
		
		wokers = new BackWorker[5];
		initializeWorkers();
		
		Thread th = new Thread(new FrontWorker());
		th.start();
		th.join();
	
		System.out.println(" 종료  ");
		
	}
	
	public static void initializeWorkers() throws InterruptedException{
        for(int i=0; i<wokers.length; i++){
        	wokers[i] = new BackWorker("thread - " + (i + 1));        
        }
    }
}
