package Thread;

public class SyncObject {
	int counter;

	public SyncObject() {
		counter = 0;
	}

	public SyncObject(int i) {
		counter = i;
	}
	
	public void setZero( ) {
		counter = 0;
	}

	public synchronized void add1() {
		counter++;
		notifyAll();
	}

	public synchronized void add10() {
		counter += 10;
		notifyAll();
	}

	public synchronized int getCount() {
		return counter;
	}
}
