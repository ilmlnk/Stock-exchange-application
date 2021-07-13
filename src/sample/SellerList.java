package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.database.constant;
import sample.database.databasehandlerlot;
import sample.database.databasehandlerseller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class SellerList implements Initializable {

    @FXML
    private ListView<String> listViewSellerList;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonDeleteAll;

    @FXML
    void buttonDeleteAllAction(ActionEvent event) { // удалить все данные из списка + из БД
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Press OK to delete.");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null || option.get() == ButtonType.OK) {
            listViewSellerList.getItems().clear();
            try {
                databasehandlerlot dbHandlerL = new databasehandlerlot();
                String query = "DELETE FROM seller;";
                PreparedStatement prestate = dbHandlerL.getDbConnection().prepareStatement(query);

                prestate.execute();

                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);

                alertSuccess.setTitle("Information");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("All sellers were deleted successfully!");

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
    void buttonExitAction(ActionEvent event) { // выйти из окна
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

    public void initialize(URL location, ResourceBundle resources) { // инициализировать данные для списка
        try {
            databasehandlerseller dbHandlerS = new databasehandlerseller();
            String query = "SELECT * FROM " + constant.DATABASE_SELLER_TABLE;
            PreparedStatement prestate = dbHandlerS.getDbConnection().prepareStatement(query);
            ResultSet result = prestate.executeQuery();

            while (result.next()) {
                String namefirm = result.getString("namefirm");
                String surname = result.getString("surname");
                String name = result.getString("name");
                String patronymic = result.getString("patronymic");
                String telephone = result.getString("telephone");
                String lot = result.getString("lot");

                String resultString = namefirm + ", " + surname + ", " + name + ", " + patronymic + ", " + telephone + ", " + lot;

                ObservableList<String> list = FXCollections.observableArrayList(resultString);
                listViewSellerList.getItems().addAll(list);
            }

            prestate.close();
            result.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
