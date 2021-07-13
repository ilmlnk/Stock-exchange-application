package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.database.constant;
import sample.database.databasehandlerlot;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LotList implements Initializable {

    @FXML
    private ListView<String> listViewLotList;

    @FXML
    private TextField txtFieldFindLot;

    @FXML
    private Button buttonFindLot;

    @FXML
    private Label labelLotList;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonDeleteAll;

    @FXML
    private Button buttonDeleteSelected;

    @FXML
    private Button buttonRegisterExpert;

    @FXML
    private TextField txtFieldCost;

    @FXML
    private Button buttonShowCost;

    @FXML
    void buttonDeleteAllAction(ActionEvent event) { // удалить всю информацию из списка + из БД
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Press OK to delete.");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null || option.get() == ButtonType.OK) {
            listViewLotList.getItems().clear();
            try {
                databasehandlerlot dbHandlerL = new databasehandlerlot();
                String query = "DELETE FROM lot;";
                PreparedStatement prestate = dbHandlerL.getDbConnection().prepareStatement(query);

                prestate.execute();

                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);

                alertSuccess.setTitle("Information");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("All lots were deleted successfully!");

                alertSuccess.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (option.get() == ButtonType.CANCEL) {
                event.consume();
            }
        }
    }

    @FXML
    void buttonExitAction(ActionEvent event) { // выход из окна
        Stage stage = (Stage) buttonExit.getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Press OK to exit.");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null  || option.get() == ButtonType.OK) {
            stage.close();
        } else if (option.get() == ButtonType.CANCEL) {
            event.consume();
        }
    }

    public void initialize(URL location, ResourceBundle resources){ // инициализация списка с данными из базы данных
        try {
            databasehandlerlot dbHandlerL = new databasehandlerlot();
            String query = "SELECT * FROM " + constant.DATABASE_LOT_TABLE;
            PreparedStatement prestate = dbHandlerL.getDbConnection().prepareStatement(query);
            ResultSet result = prestate.executeQuery();

            while (result.next()) {
                String type = result.getString("type");
                String name = result.getString("name");
                String startprice = result.getString("startprice");
                String year = result.getString("year");

                String resultString = type + ", " + name + ", " + startprice + ", " + year;

                ObservableList<String> list = FXCollections.observableArrayList(resultString);
                listViewLotList.getItems().addAll(list);
            }

            prestate.close();
            result.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    void buttonFindLotAction(ActionEvent event) { // нахождение лотов определенного типа
        try{
            databasehandlerlot dbHandlerL = new databasehandlerlot();
            String find = txtFieldFindLot.getText();
            listViewLotList.getItems().clear();
            String query = "SELECT * FROM lot WHERE type = ?;";
            PreparedStatement prestate = dbHandlerL.getDbConnection().prepareStatement(query);
            prestate.setString(1, find);
            ResultSet result = prestate.executeQuery();

            while (result.next()){
                String type = result.getString("type");
                String name = result.getString("name");
                String startprice = result.getString("startprice");
                String year = result.getString("year");

                String resultString = type + ", " + name + ", " + startprice + ", " + year;

                ObservableList<String> list = FXCollections.observableArrayList(resultString);
                listViewLotList.getItems().addAll(list);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Operation was completed successfully!");

            alert.showAndWait();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void buttonResetAction(ActionEvent event) { // кнопка для восстановления всех данных после поиска лотов
        try {
            databasehandlerlot dbHandlerD = new databasehandlerlot();
            listViewLotList.getItems().clear();
            String query = "SELECT * FROM " + constant.DATABASE_LOT_TABLE;
            PreparedStatement prestate = dbHandlerD.getDbConnection().prepareStatement(query);
            ResultSet result = prestate.executeQuery();

            while (result.next()) {
                String type = result.getString("type");
                String name = result.getString("name");
                String startprice = result.getString("startprice");
                String year = result.getString("year");

                String resultString = type + ", " + name + ", " + startprice + ", " + year;

                ObservableList<String> list = FXCollections.observableArrayList(resultString);
                listViewLotList.getItems().addAll(list);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Operation was completed successfully!");

            alert.showAndWait();
            prestate.close();
            result.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    void buttonShowCostAction(ActionEvent event) { // найти лоты выше введенной суммы
        double cost = Double.parseDouble(txtFieldCost.getText());
        try {
            databasehandlerlot dbHandlerL = new databasehandlerlot();
            listViewLotList.getItems().clear();
            String query = "SELECT * FROM " + constant.DATABASE_LOT_TABLE + " WHERE startprice > " + cost + ";";
            PreparedStatement prestate = dbHandlerL.getDbConnection().prepareStatement(query);
            ResultSet result = prestate.executeQuery();

            while (result.next()) {
                String type = result.getString("type");
                String name = result.getString("name");
                String startprice = result.getString("startprice");
                String year = result.getString("year");

                String resultString = type + ", " + name + ", " + startprice + ", " + year;

                ObservableList<String> list = FXCollections.observableArrayList(resultString);
                listViewLotList.getItems().addAll(list);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Operation was completed successfully!");

            alert.showAndWait();
            prestate.close();
            result.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
