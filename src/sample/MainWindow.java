package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.database.constant;
import sample.database.databasehandlerlot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class MainWindow {

    @FXML
    private Button buttonAddNewSellerAndLot;

    @FXML
    private Button buttonSellerList;

    @FXML
    private Button buttonExpertList;

    @FXML
    private Button buttonLotList;

    @FXML
    private Button buttonAverageLotCost;

    @FXML
    private Button buttonTheOldestLot;

    @FXML
    private Button buttonExit;

    @FXML
    void buttonAverageLotCostAction(ActionEvent event) {
        double averageLotCost = 0;
        try {
            databasehandlerlot dbHandlerL = new databasehandlerlot();
            String query = "SELECT AVG(startprice) as average FROM " + constant.DATABASE_LOT_TABLE;
            PreparedStatement prestate = dbHandlerL.getDbConnection().prepareStatement(query);
            ResultSet result = prestate.executeQuery();
            while(result.next()) {
                averageLotCost = result.getDouble("average");
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            String averageCostFormat = String.format("%.2f", averageLotCost);

            alert.setTitle("Information");
            alert.setHeaderText("Average cost");
            alert.setContentText("The average cost is: " + averageCostFormat);

            alert.showAndWait();

            prestate.close();
            result.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void buttonExpertListAction(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("expertList.fxml"));
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Expert List");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initOwner(buttonExpertList.getScene().getWindow());

            stage.showAndWait();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    void buttonLotListAction(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lotList.fxml"));
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Lot List");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initOwner(buttonLotList.getScene().getWindow());

            stage.showAndWait();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    void buttonSellerListAction(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sellerList.fxml"));
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Seller List");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initOwner(buttonSellerList.getScene().getWindow());

            stage.showAndWait();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    void buttonTheOldestLotAction(ActionEvent event) {
        try {
            databasehandlerlot dbHandlerL = new databasehandlerlot();
            String query = "SELECT MIN(year) as year FROM lot;";
            PreparedStatement prestate = dbHandlerL.getDbConnection().prepareStatement(query);
            ResultSet result = prestate.executeQuery();

            while(result.next()){

                String required = result.getString("year");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information");
                alert.setHeaderText("The oldest lot");
                alert.setContentText("The oldest lot is: " + required);

                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void buttonAddNewSellerAndLotAction(ActionEvent event) { // перейти к окну регистрации продавца
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sellerAndLot.fxml"));
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("New seller and lot");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initOwner(buttonAddNewSellerAndLot.getScene().getWindow());

            stage.showAndWait();
        } catch (Exception exc) {
            exc.printStackTrace();
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

