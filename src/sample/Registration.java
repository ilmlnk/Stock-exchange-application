package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.database.databasehandlerexpert;

import java.util.Optional;

public class Registration {

        @FXML
        private Button buttonExit;

        @FXML
        private TextField txtFieldNameFirm;

        @FXML
        private TextField txtFieldSurname;

        @FXML
        private Label labelNameFirm;

        @FXML
        private Label labelSurname;

        @FXML
        private TextField txtFieldName;

        @FXML
        private TextField txtFieldPatronymic;

        @FXML
        private TextField txtFieldTelephone;

        @FXML
        private Label labelName;

        @FXML
        private Label labelPatronymic;

        @FXML
        private Label labelTelephone;

        @FXML
        private TextField txtFieldAddress;

        @FXML
        private TextField txtFieldSpecialization;

        @FXML
        private TextField txtFieldLogin;

        @FXML
        private Button buttonAddNewExpert;

        @FXML
        private Label labelAddress;

        @FXML
        private Label labelSpecialization;

        @FXML
        private Label labelLogin;

        @FXML
        private Label labelPassword;

        @FXML
        private PasswordField txtFieldPassword;

        @FXML
        void buttonAddNewExpertAction(ActionEvent event) {
            try {
                String nameFirm = txtFieldNameFirm.getText(); // название фирмы
                String surname = txtFieldSurname.getText(); // фамилия диспетчера
                String name = txtFieldName.getText(); // имя диспетчера
                String patronymic = txtFieldPatronymic.getText(); // отчество диспетчера
                String telephone = txtFieldTelephone.getText(); // телефон диспетчера
                String address = txtFieldAddress.getText(); // адрес диспетчера
                String specialization = txtFieldSpecialization.getText();
                String login = txtFieldLogin.getText(); // логин диспетчера
                String password = txtFieldPassword.getText(); // пароль диспетчера

                if (!nameFirm.equals("") && !surname.equals("") && !name.equals("") && !patronymic.equals("") &&
                        !telephone.equals("") && !address.equals("") && !specialization.equals("") && !login.equals("") && !password.equals("")) {

                    databasehandlerexpert dbHandlerE = new databasehandlerexpert();
                    dbHandlerE.AddNewExpert(nameFirm, surname, name,
                            patronymic, telephone, address, specialization,
                            login, password);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Notification");
                    alert.setHeaderText(null);
                    alert.setContentText("New expert was added successfully!");

                    alert.showAndWait();

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Input all fields!");

                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        void buttonExitAction(ActionEvent event) {
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
