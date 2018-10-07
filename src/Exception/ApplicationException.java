/**
 * アプリケーションレベルの例外を定義します。
 */
package Exception;

/**
 * アプリケーションレベルの例外を定義します。
 */
public class ApplicationException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    @SuppressWarnings("unused")
    private ApplicationException() {
    }

    /**
     * コンストラクタ
     * 
     * @param message
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * 
     * @param cause
     */
    public ApplicationException(Throwable cause) {
        super(cause);
    }

    /**
     * コンストラクタ
     * 
     * @param message
     * @param cause
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     * 
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ApplicationException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
