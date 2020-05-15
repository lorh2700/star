package bl.com;

import java.util.Random;

public class FrontWorker extends Thread {
	
	public FrontWorker() {
		Random rd = new Random();
		
		for(int i = 0; i < 30; i++) {
			LoadBalancer.fwQueue.add(rd.nextInt(10000));
		}
		System.out.println("enqueue done = "+ LoadBalancer.fwQueue);
	}
	
	private static BackWorker getFreeWorker() {
		for (int i=0; i<LoadBalancer.wokers.length; i++){
            if (LoadBalancer.wokers[i].isFull() == false)
                return LoadBalancer.wokers[i];
        }
        return null;
	}
	
	@Override
	public void run() {
		while (true){
			BackWorker worker = getFreeWorker();
			int aliveCnt = 0;

			if(worker != null && !LoadBalancer.fwQueue.isEmpty()) {
				worker.enQueue(LoadBalancer.fwQueue.poll());
			}
			
			for(BackWorker bw : LoadBalancer.wokers) {
				if(bw.getThreadFlag()) {
					aliveCnt++;
				}
			}
			
			if(aliveCnt == 0) {
				System.out.println("all thread is done");
				terminateThread();
				break;
			}
        }
	}
	
	private static void terminateThread() {
		for(BackWorker bw : LoadBalancer.wokers) {
			bw.terminateThread();
		}
	}
}
