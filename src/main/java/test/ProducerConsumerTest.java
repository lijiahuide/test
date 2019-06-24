package test;

class Info {
	private String title;
	private String content;

	private boolean flag = true;

	public Info() {

	}

	public synchronized void set(String title, String content) {
		if (this.flag == false) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.title = title;
		this.content = content;
		this.flag = false;
		super.notify();
	}

	public synchronized void get() {
		if (this.flag == true) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(this.title + "---->" + this.content);
		this.flag = true;
		notify();
	}
}

class Producer implements Runnable {

	private Info info;

	public Producer(Info info) {
		this.info = info;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			if (i % 2 == 0) {
				this.info.set("小动物", "16岁");
			} else {
				this.info.set("李四", "20岁");
			}
		}
	}
}

class Consumer implements Runnable {

	private Info info;

	public Consumer(Info info) {
		this.info = info;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.info.get();
		}
	}
}

public class ProducerConsumerTest {
	// 生产消费者模型
	public static void main(String[] args) {
		Info info = new Info();
		new Thread(new Producer(info)).start();
		new Thread(new Consumer(info)).start();
	}
}
