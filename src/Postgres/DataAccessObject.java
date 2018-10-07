package Postgres;

/**
 * PostgreSQL用のDAOクラス
 */
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author user01
 *
 */
public class DataAccessObject {
    Properties dbProperties = null;
    Logger logger = null;
    Connection myConnection = null;
    Statement myStatement = null;
    ResultSet myResultSet = null;

    /**
     * コンストラクタ
     */
    public DataAccessObject() {
        dbProperties = new Properties();
        logger = LogManager.getLogger(this.getClass());
    }

    /**
     * コンストラクタ
     * 
     * @param myLogger
     */
    public DataAccessObject(Logger myLogger) {
        dbProperties = new Properties();
        logger = myLogger;
    }

    /**
     * クラスの初期化処理
     */
    public void initInstance() {
        logger.trace("start");

        try {
            /*
             * Propertiesからデータベース情報を取得する
             */
            InputStream is = getClass().getResourceAsStream("/db.properties");
            dbProperties.load(is);
            String driverName = dbProperties.getProperty("driverName");
            String connectionString = dbProperties.getProperty("connectionString");
            String login = dbProperties.getProperty("login");
            String password = dbProperties.getProperty("password");

            /*
             * ドライバーのロード
             */
            Class.forName(driverName);

            /*
             * データベースに接続
             */
            myConnection = DriverManager.getConnection(connectionString, login, password);
            myStatement = myConnection.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // logger.trace("finish");
    }

    /**
     * SELECTクエリの実行
     * 
     * @param query SELECT文
     * @return ResultSet
     */
    public ResultSet executeQuery(String query) {
        logger.trace("start");

        /*
         * SQLの発行
         */
        try {
            logger.info("sql=" + query);

            myResultSet = myStatement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        logger.trace("finish");
        return myResultSet;
    }

    /**
     * INSERT,UPDATE,DELETEクエリの実行
     * 
     * @param query SELECT以外のSQL
     * @return データの処理件数
     */
    public int executeUpdate(String query) {
        logger.trace("start");

        int response = 0;

        /*
         * SQLの発行
         */
        try {
            logger.info("sql=" + query);

            response = myStatement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        logger.trace("finish");
        return response;
    }

    /**
     * インスタンス解放処理
     */
    public void exitInstance() {
        logger.trace("start");

        try {
            /*
             * 接続を切断する
             */
            if (!myResultSet.isClosed()) {
                myResultSet.close();
            }
            if (!myStatement.isClosed()) {
                myStatement.close();
            }
            if (!myConnection.isClosed()) {
                myConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        myConnection = null;
        myStatement = null;
        myResultSet = null;

        logger.trace("finish");
    }
}
