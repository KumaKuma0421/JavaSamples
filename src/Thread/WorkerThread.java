/**
 * Threadクラスを継承してスレッドを作成
 */
package Thread;

import java.util.function.BiConsumer;

/**
 * Threadクラスを継承してワーカースレッドクラスを作成します
 */
public class WorkerThread extends Thread {
    private SyncObject sync = null;
    private String msg = null;
    private BiConsumer<SyncObject, String> consumer = null;

    public WorkerThread(SyncObject sync, String msg, BiConsumer<SyncObject, String> consumer) {
        this.sync = sync;
        this.msg = msg;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            consumer.accept(this.sync, this.msg);
        }
    }
}
