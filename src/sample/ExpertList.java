package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.database.constant;
import sample.database.databasehandlerexpert;
import sample.database.databasehandlerlot;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ExpertList implements Initializable {

    @FXML
    private ListView<String> listViewExpertList;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonDeleteAll;

    @FXML
    private Button buttonDeleteSelected;

    @FXML
    private Label labelExpertList;

    @FXML
    private Button buttonRegisterExpert;

    @FXML
    void buttonDeleteAllAction(ActionEvent event) { // удаление всех данных из listview + базы данных
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Press OK to delete.");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null || option.get() == ButtonType.OK) {
            listViewExpertList.getItems().clear();
            try {
                databasehandlerlot dbHandlerL = new databasehandlerlot();
                String query = "DELETE FROM expert;";
                PreparedStatement prestate = dbHandlerL.getDbConnection().prepareStatement(query);

                prestate.execute();

                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);

                alertSuccess.setTitle("Information");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("All experts were deleted successfully!");

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
    void buttonExitAction(ActionEvent event) { // закрыть окно
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

    public void initialize(URL location, ResourceBundle resources) { // инициализация данных для listview
        try {
            databasehandlerexpert dbHandlerE = new databasehandlerexpert();
            String query = "SELECT * FROM " + constant.DATABASE_EXPERT_TABLE;
            PreparedStatement prestate = dbHandlerE.getDbConnection().prepareStatement(query);
            ResultSet result = prestate.executeQuery();

            while (result.next()) {
                String namefirm = result.getString("namefirm");
                String surname = result.getString("surname");
                String name = result.getString("name");
                String patronymic = result.getString("patronymic");
                String address = result.getString("address");
                String telephone = result.getString("telephone");
                String specialization = result.getString("specialization");

                String resultString = namefirm + ", " + surname + ", " + name + ", " + patronymic + ", " +
                        address + ", " + telephone + ", " + specialization;

                ObservableList<String> list = FXCollections.observableArrayList(resultString);
                listViewExpertList.getItems().addAll(list);
            }

            prestate.close();
            result.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void buttonRegisterExpertAction(ActionEvent event) { // переход к окну регистрации эксперта
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Registration form");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initOwner(buttonRegisterExpert.getScene().getWindow());

            stage.showAndWait();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


}

