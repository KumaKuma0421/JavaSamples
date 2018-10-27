/**
 * デザインパターン：シングルトン
 */
package Pattern.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * シングルトンクラス これ自体はインスタンスクラスですが、 <br>
 * 提供するオブジェクトはstaticなSingletonです。
 */
public class Singleton {
    private static Singleton instance = null;
    private final Logger myLogger = LogManager.getLogger("JavaSample");

    /**
     * 引数なしコンストラクタはprivate宣言で<br>
     * 使用できないようにします。
     */
    private Singleton() {
    }

    /**
     * staticなSingletonオブジェクトを提供します。
     *
     * @return Singleton
     */
    public static Singleton Instance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }

    public Logger get() {
        return this.myLogger;
    }
}
