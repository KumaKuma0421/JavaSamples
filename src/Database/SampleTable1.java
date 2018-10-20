/**
 * テーブルをSELECTするプログラム
 */

package Database;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import Database.Bean.SampleTable1Bean;
import Exception.ApplicationException;

/**
 * データベースに接続し、データを取得するクラス
 */
public class SampleTable1 {
    private final String sqlRead = "select number, login_id, family_name, first_name, create_datetime, update_datetime from sample_table_1 where number <= 100;";
    private final String sqlWrite = "update sample_table_1 set update_datetime='2018-03-13' where number < 10";

    public List<SampleTable1Bean> readAllTable() throws Exception {
        var myLogger = LogManager.getLogger(this.getClass());
        var myDAO = new DataAccessObject(myLogger);
        var response = new ArrayList<SampleTable1Bean>();

        myLogger.trace("Start readAllTable()");

        try {
            /*
             * DAOの初期化
             */
            myDAO.initInstance();

            /*
             * SQL(SELECT文)の発行
             */
            var myResultSet = myDAO.sqlQuery(sqlRead);

            /*
             * 結果の出力
             */
            while (myResultSet.next()) {
                var record = new SampleTable1Bean();
                record.setNumber(myResultSet.getInt("number"));
                record.setLoginID(myResultSet.getString("login_id"));
                record.setFamilyName(myResultSet.getString("family_name"));
                record.setFirstName(myResultSet.getString("first_name"));
                record.setCreateDateTime(myResultSet.getDate("create_datetime"));
                record.setUpdateDateTime(myResultSet.getDate("update_datetime"));

                response.add(record);
            }

            /*
             * DAOの後処理
             */
            myDAO.exitInstance();

        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        myLogger.trace("Finish readAllTable()");
        return response;
    }

    public void updateSomeData() throws Exception {
        var myLogger = LogManager.getLogger(this.getClass());
        var myDAO = new DataAccessObject(myLogger);

        myLogger.trace("Start updateSomeData()");

        try {
            /*
             * DAOの初期化
             */
            myDAO.initInstance();

            /*
             * データの更新
             */
            var result = myDAO.sqlExecute(sqlWrite);
            myLogger.debug("update count=" + result);

            /*
             * DAOの後処理
             */
            myDAO.exitInstance();

        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        myLogger.trace("Finish updateSomeData()");
    }
}