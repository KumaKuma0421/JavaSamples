package Postgres;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * PostgreSQLのテーブルをSELECTするプログラム
 */

/**
 * PostgreSQLに接続し、データを取得するクラス
 */
public class ReadTable {

    public void testReadTable() throws Exception {
        Logger myLogger = LogManager.getLogger(ReadTable.class);
        DataAccessObject myDAO = new DataAccessObject(myLogger);
        ResultSet myResultSet = null;
        final String sqlRead = "select * from sample_table_1;";
        final String sqlWrite = "update sample_table_1 set update_datetime='2018-03-13' where number < 10";

        myLogger.trace("Start main()");

        try {
            /*
             * DAOの初期化
             */
            myDAO.initInstance();

            /*
             * SQL(SELECT文)の発行
             */
            myResultSet = myDAO.executeQuery(sqlRead);

            /*
             * フィールド一覧の取得
             */
            List<String> fields = new ArrayList<String>();
            ResultSetMetaData rsmd = myResultSet.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                fields.add(rsmd.getColumnName(i));
            }

            /*
             * 結果の出力
             */
            int rowCount = 0;
            while (myResultSet.next()) {
                rowCount++;
                StringBuilder builder = new StringBuilder();
                builder.append("row:" + rowCount + ":");
                for (String field : fields) {
                    builder.append(field + ":" + myResultSet.getString(field) + ",");
                }
                myLogger.debug(builder.toString());
            }

            /*
             * データの更新
             */
            int result = myDAO.executeUpdate(sqlWrite);
            myLogger.debug("update count=" + result);

            /*
             * DAOの後処理
             */
            myDAO.exitInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        myLogger.trace("Finish main()");
    }
}