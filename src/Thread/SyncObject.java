package Thread;

public class SyncObject {
    private int counter;
    private int finishCount;
    private int maxThread;

    @SuppressWarnings("unused")
    private SyncObject() {

    }

    public SyncObject(int maxThread) {
        counter = 0;
        finishCount = 0;
        this.maxThread = maxThread;
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

    public synchronized void finish() {
        this.finishCount++;
    }

    public synchronized boolean isFinish() {
        return this.finishCount == this.maxThread;
    }
}
