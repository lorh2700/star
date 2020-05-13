package bl.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class LoadBalancer {

	public static void main(String[] args) throws InterruptedException {
		
		
		for(int i = 0; i < 5; i ++) {
			Thread thread = new Thread(new FrontWorker());
			
			thread.start();
			thread.join();
			thread.sleep(10000);
		}

	}

}
