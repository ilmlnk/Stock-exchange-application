package sample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/*
*           ИНСТРУКЦИЯ
*   Класс предназначен для установки подключения
*
*
* */

public class databasehandlerseller extends configs {

    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbConnection;
    }

    /*    Добавление нового продавца      */
    public void AddNewSeller(String nameFirm, String surname, String name, String patronymic, String telephone, String lot){
        String insertSeller = "INSERT INTO " + constant.DATABASE_SELLER_TABLE + "(" + constant.SELLER_NAMEFIRM + ", " +
                constant.SELLER_SURNAME + ", " + constant.SELLER_NAME + ", " + constant.SELLER_PATRONYMIC
                + ", " + constant.SELLER_TELEPHONE + ", " + constant.SELLER_LOT + ") " + "VALUES (?, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement prStC = getDbConnection().prepareStatement(insertSeller);

            prStC.setString(1, nameFirm);
            prStC.setString(2, surname);
            prStC.setString(3, name);
            prStC.setString(4, patronymic);
            prStC.setString(5, telephone);
            prStC.setString(6, lot);
            prStC.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
