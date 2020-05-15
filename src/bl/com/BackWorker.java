package bl.com;

import java.util.LinkedList;
import java.util.Queue;

public class BackWorker {
	
	private Queue<Integer> myQueue;
	
	private String name;
	
	private int limit = 3;
	
	private Thread th;
	
	private boolean threadFlag;
	
	public int getQueueSize() {
		return myQueue.size();
	}
	
	public boolean getThreadFlag() {
		return threadFlag;
	}
	
	public BackWorker(String name) throws InterruptedException {
		this.name = name;
		System.out.println(name + " 생성됨 ");
		threadFlag = true;
		
		myQueue = new LinkedList<Integer>();
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				do{
					if(!myQueue.isEmpty()) {
						System.out.println(name + " delete queue," + " 현재 사이즈 =" + myQueue.size());
						
						int sleepTime = myQueue.poll();
						
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(myQueue.isEmpty()) {
							threadFlag = false;
						}
					}
				}
				while(threadFlag);
				
				System.out.println(name + " out ");
			}
		};
		th = new Thread(run);
		th.start();
	}
	
	public void enQueue(int value) {
		myQueue.add(value);
		System.out.println(name +" en queue, 현재 사이즈 = "+myQueue.size());
	}
	
	public boolean isFull() {
		if(myQueue.size() > limit) {
			return true;
		}else {
			return false;
		}
		 
	}
	
	public void terminateThread() {
		threadFlag = false;
	}
	
	public Queue<Integer> getMyQueue() {
		return myQueue;
	}

	public void setMyQueue(Queue<Integer> myQueue) {
		this.myQueue = myQueue;
	}

}
