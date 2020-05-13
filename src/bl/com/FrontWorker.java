package bl.com;

import java.io.IOException;
import java.net.Socket;
import java.nio.CharBuffer;

public class FrontWorker implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " ");		
	}

}
