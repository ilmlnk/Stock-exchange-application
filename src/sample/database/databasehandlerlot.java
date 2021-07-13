package sample.database;

import java.sql.*;

/*              ИНСТРУКЦИЯ
*
* Класс предназначен для установки подключения с БД и таблицей lot для выполнения SQL-запросов с помощью команд
*
* */


public class databasehandlerlot extends configs {

    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbConnection;
    }

    public void AddNewLot(String type, String name, double startprice, int year) {
        String insertLot = "INSERT INTO " + constant.DATABASE_LOT_TABLE + "(" + constant.LOT_TYPE + ", " +
                constant.LOT_NAME + ", " + constant.LOT_STARTPRICE + ", " + constant.LOT_YEAR + ")"
                + " VALUES (?, ?, ?, ?);";

        // INSERT INTO routes (type, name, startprice, creationyear)
        try {
            PreparedStatement prStR = getDbConnection().prepareStatement(insertLot);
            prStR.setString(1, type);
            prStR.setString(2, name);
            prStR.setDouble(3, startprice);
            prStR.setInt(4, year);

            prStR.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
