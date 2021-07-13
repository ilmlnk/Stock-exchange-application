package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.animations.Shake;
import sample.database.databasehandlerexpert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp {

    @FXML
    private TextField txtFieldLogin;

    @FXML
    private PasswordField txtFieldPassword;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Button buttonSignUp;

    @FXML
    private Label labelAuction;

    @FXML
    private Label labelLogin;

    @FXML
    private Label labelPassword;

    @FXML
    void buttonSignInAction(ActionEvent event) { // войти в аккаунт (при неудачной попытке вывести уведомление)
        String loginText = txtFieldLogin.getText().trim();
        String passwordText = txtFieldPassword.getText().trim();

        if (!loginText.equals("") && !passwordText.equals("")) {
            loginUser(loginText, passwordText);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Warning!");
            alert.setHeaderText(null);
            alert.setContentText("Input login and password!");

            alert.showAndWait();
        }
    }

    private void loginUser(String loginText, String passwordText) { // валидация данных в БД
        Parent root;

        databasehandlerexpert dbHandlerE = new databasehandlerexpert();
        Expert expert = new Expert();
        expert.setLogin(loginText);
        expert.setPassword(passwordText);
        ResultSet result = dbHandlerE.getExpert(expert);

        int counter = 0;

        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (counter >= 1) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
                root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Main window");

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initOwner(buttonSignIn.getScene().getWindow());

                stage.showAndWait();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        } else {

            Shake userLoginAnim = new Shake(txtFieldLogin);
            Shake userPassAnim = new Shake(txtFieldPassword);
            userLoginAnim.playAnimShake();
            userPassAnim.playAnimShake();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect password or login!");

            alert.showAndWait();
        }
    }

    @FXML
    void buttonSignUpAction(ActionEvent event) { // перейти в окно регистрации
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Registration form");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initOwner(buttonSignUp.getScene().getWindow());

            stage.showAndWait();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}

