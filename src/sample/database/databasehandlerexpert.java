package sample.database;

import sample.Expert;

import java.sql.*;

/*          ИНСТРУКЦИЯ
*
* Класс предназначен для подключения к БД и выполнения SQL-запросов с помощью команд для таблицы expert
*
* */

public class databasehandlerexpert extends configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbConnection;
    }
    public void AddNewExpert(String firmname, String surname, String name, String patronymic,
                           String telephone, String address, String specialization, String login, String password){

        String insertDispatcher = "INSERT INTO " + constant.DATABASE_EXPERT_TABLE + "(" + constant.EXPERT_NAMEFIRM + ", " +
                constant.EXPERT_SURNAME + ", " + constant.EXPERT_NAME + ", " +
                constant.EXPERT_PATRONYMIC + ", " + constant.EXPERT_TELEPHONE + "," + constant.EXPERT_ADDRESS +
                ", " + constant.EXPERT_SPECIALIZATION + ", " + constant.EXPERT_LOGIN + ", " + constant.EXPERT_PASSWORD + ")" + "VALUES (?, " +
                "?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement prStD = getDbConnection().prepareStatement(insertDispatcher);
            prStD.setString(1, firmname);
            prStD.setString(2, surname);
            prStD.setString(3, name);
            prStD.setString(4, patronymic);
            prStD.setString(5, telephone);
            prStD.setString(6, address);
            prStD.setString(7, specialization);
            prStD.setString(8, login);
            prStD.setString(9, password);
            prStD.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getExpert(Expert expert) {
        ResultSet resSet = null;
        String select = "SELECT * FROM expert" + " WHERE " + constant.EXPERT_LOGIN +
                " =? AND " + constant.EXPERT_PASSWORD + " =?;";
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, expert.getLogin());
            prSt.setString(2, expert.getPassword());
            resSet = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
    }
