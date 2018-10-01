/**
 * Runnableインターフェースを使ってスレッドを作成
 */
package Thread;

import java.util.function.BiConsumer;

/**
 * Runnableインターフェースを実装してのスレッドの作成<br/>
 * 多重継承ができないJavaはメインの継承に対してRunnable<br/>
 * インターフェースを使用してスレッド機能を実装します。<br/>
 */
public class ThreadRunner implements Runnable {
    private SyncObject sync = null;
    private String msg = null;
    private BiConsumer<SyncObject, String> consumer = null;

    public ThreadRunner(SyncObject sync, String msg, BiConsumer<SyncObject, String> consumer) {
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
