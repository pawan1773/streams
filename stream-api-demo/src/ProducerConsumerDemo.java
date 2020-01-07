import java.util.ArrayList;
import java.util.List;

class MessageQueue {
	private List<String> messages;
	private int limit;

	public MessageQueue(int limit) {
		this.messages = new ArrayList<>();
		this.limit = limit;
	}

	private boolean isEmpty() {
		return this.messages.isEmpty();
	}

	private boolean isFull() {
		return this.messages.size() == this.limit;
	}

	public synchronized void enqueue(String message) {
		while (this.isFull()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.messages.add(message);
		this.notify();
	}

	public synchronized String dequeue() {
		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String message = this.messages.remove(0);
		this.notify();
		return message;
	}

}

class Producer extends Thread {
	private MessageQueue messageQueue;

	public Producer(MessageQueue messageQueue) {
		super();
		this.messageQueue = messageQueue;
	}

	public void run() {
		for (int i = 0; i <= 10; i++) {
			System.out.println("Added Text_" + i);
			messageQueue.enqueue("Text_" + i);
		}
	}
}

class Consumer extends Thread {
	private MessageQueue messageQueue;

	public Consumer(MessageQueue messageQueue) {
		super();
		this.messageQueue = messageQueue;
	}

	public void run() {
		for (int i = 0; i <= 10; i++) {
			System.out.println("Removed: " + messageQueue.dequeue());
		}
	}
}

public class ProducerConsumerDemo {
	public static void main(String[] args) {
		MessageQueue messageQueue = new MessageQueue(3);
		new Producer(messageQueue).start();
		new Consumer(messageQueue).start();
	}
}
