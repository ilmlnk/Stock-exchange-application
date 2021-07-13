package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.databasehandlerlot;
import sample.database.databasehandlerseller;

import java.util.Optional;

public class newSeller {

    @FXML
    private TextField txtFieldNameFirm;

    @FXML
    private TextField txtFieldSurname;

    @FXML
    private TextField txtFieldNameSeller;

    @FXML
    private TextField txtFieldPatronymic;

    @FXML
    private TextField txtFieldTelephone;

    @FXML
    private TextField txtFieldType;

    @FXML
    private TextField txtFieldStartPrice;

    @FXML
    private TextField txtFieldName;

    @FXML
    private TextField txtFieldYear;

    @FXML
    private Button buttonCreate;

    @FXML
    private Button buttonExit;

    @FXML
    void buttonCreateAction(ActionEvent event) { // создание нового продавца + новый лот
        try {
            databasehandlerseller dbHandlerS = new databasehandlerseller();
            databasehandlerlot dbHandlerL = new databasehandlerlot();

            String namefirm = txtFieldNameFirm.getText();
            String surname = txtFieldSurname.getText();
            String nameSeller = txtFieldNameSeller.getText();
            String patronymic = txtFieldPatronymic.getText();
            String telephone = txtFieldTelephone.getText();
            String type = txtFieldType.getText();
            String name = txtFieldName.getText();
            double startprice = Double.parseDouble(txtFieldStartPrice.getText());
            int year = Integer.parseInt(txtFieldYear.getText());

            dbHandlerS.AddNewSeller(namefirm, surname, nameSeller, patronymic, telephone, name);
            dbHandlerL.AddNewLot(type, name, startprice, year);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("New seller and lot were added successfully!");

            alert.showAndWait();
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Input all fields!");

            alert.showAndWait();

            e.printStackTrace();
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
}

