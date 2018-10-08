package Thread;

import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public class ThreadSample {

    private void timeWait() {
        IntStream.range(0, 10).forEach((i) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void runThread2() {
        var syncObj = new SyncObject();
        syncObj.setZero();

        BiConsumer<SyncObject, String> consumer = (sync, msg) -> {
            try {
                for (int i = 0; i < 100; i++) {
                    sync.add1();
                    System.out.println(msg + " count " + i + " now running " + sync.getCount());
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        var worker1 = new WorkerThread(syncObj, "worker1", consumer);

        var runner1 = new ThreadRunner(syncObj, "runner1", consumer);
        var runThread1 = new Thread(runner1);

        worker1.start();
        runThread1.start();

        this.timeWait();
    }

    public void runThread4() {
        var syncObj = new SyncObject();
        syncObj.setZero();

        BiConsumer<SyncObject, String> consumer = (sync, msg) -> {
            try {
                for (int i = 0; i < 100; i++) {
                    sync.add1();
                    System.out.println(msg + " count " + i + " now running " + sync.getCount());
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        var worker1 = new WorkerThread(syncObj, "worker1", consumer);
        var worker2 = new WorkerThread(syncObj, "worker2", consumer);

        var runner1 = new ThreadRunner(syncObj, "runner1", consumer);
        var runner2 = new ThreadRunner(syncObj, "runner2", consumer);

        var runThread1 = new Thread(runner1);
        var runThread2 = new Thread(runner2);

        worker1.start();
        worker2.start();
        runThread1.start();
        runThread2.start();

        this.timeWait();
    }
}