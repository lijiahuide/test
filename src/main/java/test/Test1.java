package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 通过继承实现
 *
 */
class Th1 extends Thread {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}

}

/**
 * 通过接口
 */
class Th2 implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}

class Th3 implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println("callable");
		return "callable";
	}
}

public class Test1 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Th1 th1 = new Th1();
		th1.start();
		new Thread(new Th2()).start();
		Th3 th3 = new Th3();
		FutureTask<String> taks = new FutureTask<>(th3);
		new Thread(taks).start();
		System.out.println(taks.get());
	}

}
