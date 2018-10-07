/**
 * 汎用DataAccessObjectクラス
 */
package Database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

import Exception.ApplicationException;

/**
 * 汎用DataAccessObjectクラス
 */
public class DataAccessObject {
    private Properties dbProperties = null;
    private Optional<Logger> myLogger = null;
    private Connection myConnection = null;
    private Statement myStatement = null;
    private ResultSet myResultSet = null;

    /**
     * コンストラクタ
     */
    public DataAccessObject() {
        this.dbProperties = new Properties();
    }

    /**
     * コンストラクタ
     * 
     * @param myLogger
     */
    public DataAccessObject(Logger myLogger) {
        this.dbProperties = new Properties();
        this.myLogger = Optional.ofNullable(myLogger);
    }

    /**
     * クラスの初期化処理
     * 
     * @throws ApplicationException
     */
    public void initInstance() throws ApplicationException {
        this.logTrace("start initInstance()");

        try {
            /*
             * Propertiesからデータベース情報を取得する
             */
            var is = getClass().getResourceAsStream("/db.properties");
            this.dbProperties.load(is);
            var driverName = this.dbProperties.getProperty("driverName");
            var connectionString = this.dbProperties.getProperty("connectionString");
            var login = this.dbProperties.getProperty("login");
            var password = this.dbProperties.getProperty("password");

            /*
             * ドライバーのロード
             */
            Class.forName(driverName);

            /*
             * データベースに接続
             */
            this.myConnection = DriverManager.getConnection(connectionString, login, password);
            this.myStatement = this.myConnection.createStatement();

        } catch (ClassNotFoundException | SQLException | IOException e) {
            this.logError(e.getMessage());
            throw new ApplicationException(e);
        }

        this.logTrace("finish initInstance()");
    }

    /**
     * SELECTクエリの実行
     * 
     * @param query SELECT文
     * @return ResultSet
     * @throws ApplicationException
     */
    public ResultSet sqlQuery(String query) throws ApplicationException {
        this.logTrace("start sqlQuery()");

        /*
         * SQLの発行
         */
        try {
            this.logInfo("sql=" + query);
            this.myResultSet = this.myStatement.executeQuery(query);
        } catch (SQLException e) {
            this.logError(e.getMessage());
            throw new ApplicationException(e);
        }

        this.logTrace("finish sqlQuery()");
        return this.myResultSet;
    }

    /**
     * INSERT,UPDATE,DELETEクエリの実行
     * 
     * @param query SELECT以外のSQL
     * @return データの処理件数
     * @throws ApplicationException
     */
    public int sqlExecute(String query) throws ApplicationException {
        this.logTrace("start sqlExecute()");

        var response = 0;

        /*
         * SQLの発行
         */
        try {
            this.logInfo("sql=" + query);

            response = this.myStatement.executeUpdate(query);
        } catch (SQLException e) {
            this.logError(e.getMessage());
            throw new ApplicationException(e);
        }

        this.logTrace("finish sqlExecute()");
        return response;
    }

    /**
     * インスタンス解放処理
     * 
     * @throws ApplicationException
     */
    public void exitInstance() throws ApplicationException {
        this.logTrace("start exitInstance()");

        try {
            /*
             * 接続を切断する
             */
            if (this.myResultSet != null && !this.myResultSet.isClosed()) {
                this.myResultSet.close();
            }
            if (!this.myStatement.isClosed()) {
                this.myStatement.close();
            }
            if (!this.myConnection.isClosed()) {
                this.myConnection.close();
            }
        } catch (SQLException e) {
            this.logError(e.getMessage());
            throw new ApplicationException(e);
        }

        this.myResultSet = null;
        this.myStatement = null;
        this.myConnection = null;

        this.logTrace("finish exitInstance()");
    }

    private void logTrace(String message) {
        this.myLogger.ifPresent((logger) -> {
            logger.trace(message);
        });
    }

    private void logInfo(String message) {
        this.myLogger.ifPresent((logger) -> {
            logger.info(message);
        });
    }

    private void logError(String message) {
        this.myLogger.ifPresent((logger) -> {
            logger.error(message);
        });
    }
}
