package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**This class is the controller for the AddPartForm. It controls all parts of the AddPartForm.FXML document
 ***********************************************************************************************************
 * FUTURE ENHANCEMENT
 * I think that reversing the location of the minimum and maximum labels and text fields in the GUI makes more sense.
 * As it is now, the greater number shows on the left, which is uncommon in my opinion.
 */

public class AddPartFormController implements Initializable {

        Stage stage; // Holds the stage
        Parent scene; // Holds the scene


        @FXML
        private RadioButton inHouseRBtn; // Assigns inHouseRBtn as a RadioButton

        @FXML
        private RadioButton outSourcedRBtn; // Assigns outSourcedRBtn as a RadioButton

        @FXML
        private Label machineIdLbl; // Assigns machineIDLbl as a Label

        @FXML
        private TextField partIdTxt; // Assigns partIdTxt as a TextField

        @FXML
        private TextField partNameTxt; // Assigns partNameTxt as a TextField

        @FXML
        private TextField partInventoryTxt; // Assigns partInventoryTxt as a TextField

        @FXML
        private TextField partPriceTxt; // Assigns partPriceTxt as a TextField

        @FXML
        private TextField partMaxTxt; // Assigns partMaxTxt as a TextField

        @FXML
        private TextField machineIdTxt; // Assigns machineIdTxt as a TextField

        @FXML
        private TextField companyNameTxt; // Assigns companyNameTxt as a TextField

        @FXML
        private TextField partMinTxt; // Assigns partMinTxt as a TextField

        /**This method returns the application to the mainForm. It is a cancel button
         * @param event is a button.
         * */
        @FXML
        void onActionMainForm(ActionEvent event) throws IOException {

            // Alert dialog box to inform that cancelling will clear all entries
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values, Do you " +
                    "want to continue?");
            Optional<ButtonType> result = alert.showAndWait();
            // When confirmed returns to MainForm
            if (result.isPresent() && result.get() == ButtonType.OK) {
            // Loads and sets the scene for the mainForm
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        }
        /**This method is to acknowledge rather the InHouse RadioButton is selected as well as provides a
         * random ID number.
         * @param  event is a RadioButton
         * */
        @FXML
        void onActionInHouse(ActionEvent event) {

            if (inHouseRBtn.isSelected()) // Is it selected
                machineIdLbl.setText("Machine ID"); // Sets text in machine label
                machineIdTxt.setVisible(true); // Ensures that the machineId text field is visible
                companyNameTxt.setVisible(false); //Hides the companyName text field
                partIdTxt.setText(String.valueOf(Inventory.inHouseID())); // Generates random number
        }
        /**This method is to acknowledge rather the Outsourced RadioButton is selected as well as provides a
         * * random ID number.
        * @param  event is a RadioButton
        * */
        @FXML
        void onActionOutsourced(ActionEvent event) {

            if (outSourcedRBtn.isSelected())// Is it selected
                machineIdLbl.setText("Company Name"); // Sets text in company name
                machineIdTxt.setVisible(false); // Hides the machineId text field
                companyNameTxt.setVisible(true); // Ensures that the company name text field is visible
                partIdTxt.setText(String.valueOf(Inventory.outsourcedID())); // Generates a random number
        }
        /**
         * This method separates the part by InHouse or Outsourced and saves the newly created part. It provides
         * Alert dialog boxes to ensure that the proper information is received.
         * @param event is a button
         *********************************************************************************************************
         * LOGICAL ERROR
         * I struggled to find a way to separate the InHouse and Outsourced parts for the save event. At first, I just
         * tried to branch it with just the machineID and companyName; however, I could not get it to save separately.
         * When running the program both parts would save under the InHouse and repeated parts would show in the
         * partsTableView. I tried this method, creating a new variable set for each part, and it worked after that.
         *********************************************************************************************************
         */
        @FXML
        void onActionSavePart(ActionEvent event) throws IOException {

            try { // Used in the event of an exception
                if (inHouseRBtn.isSelected()) { // Is it selected
                    // Creates a new variable and stores the partIdTxt in it
                    int id = Integer.parseInt(partIdTxt.getText());
                    // Creates a new variable and stores the partNameTxt in it
                    String name = partNameTxt.getText();
                    // Creates a new variable and stores the partInventoryTxt in it
                    int stock = Integer.parseInt(partInventoryTxt.getText());
                    // Creates a new variable and stores the partPriceTxt in it
                    double price = Double.parseDouble(partPriceTxt.getText());
                    // Creates a new variable and stores the partMaxTxt in it
                    int max = Integer.parseInt(partMaxTxt.getText());
                    // Creates a new variable and stores the partMinTxt in it
                    int min = Integer.parseInt(partMinTxt.getText());
                    // Creates a new variable and stores the machineIdTxt in it
                    int machineID = Integer.parseInt(machineIdTxt.getText());
                    // Ensures that the max Integer is greater than the minimum
                    if (max < min) {
                    // Alert dialog box when true
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The Maximum Inventory must be greater" +
                                 " than the Minimum Inventory!");
                        Optional<ButtonType> result = alert.showAndWait();
                        // Clears text fields for new entry
                        if (result.isPresent() && result.get() == ButtonType.OK)
                            partMaxTxt.clear();
                            partMinTxt.clear();
                    // Ensures that the stock inventory is between the maximum and minimum inventory level
                    } else if (stock < min || stock > max) {
                        // Alert dialog box if true
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The Inventory level must be greater than" +
                                " or equal to the Minimum Inventory and less than or equal to the Maximum Inventory!");
                        Optional<ButtonType> result = alert.showAndWait();
                        // Clears text field for new entry
                        if (result.isPresent() && result.get() == ButtonType.OK)
                            partInventoryTxt.clear();
                    // Saves data as a new InHouse part
                    } else {
                        // Adds the part to the allPart list
                        Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineID));
                        // Dialog box confirming the save
                        JOptionPane.showMessageDialog(null, "The modified product has been " +
                                "saved!");
                        // Loads and sets the scene for the mainForm
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
                else if (outSourcedRBtn.isSelected()) { // Is it selected
                    // Creates a new variable and stores the partIdTxt in it
                    int id = Integer.parseInt(partIdTxt.getText());
                    // Creates a new variable and stores the partNameTxt in it
                    String name = partNameTxt.getText();
                    // Creates a new variable and stores the partInventoryTxt in it
                    int stock = Integer.parseInt(partInventoryTxt.getText());
                    // Creates a new variable and stores the partPriceTxt in it
                    double price = Double.parseDouble(partPriceTxt.getText());
                    // Creates a new variable and stores the partMaxTxt in it
                    int max = Integer.parseInt(partMaxTxt.getText());
                    // Creates a new variable and stores the partMinTxt in it
                    int min = Integer.parseInt(partMinTxt.getText());
                    // Creates a new variable and stores the companyNameTxt in it
                    String companyName = companyNameTxt.getText();
                    // Ensures that the max Integer is greater than the minimum
                    if (max < min) {
                        // Alert dialog box if true
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The Maximum Inventory must be greater than" +
                                " the Minimum Inventory!");
                        Optional<ButtonType> result = alert.showAndWait();
                        // Clears text fields for new entry
                        if (result.isPresent() && result.get() == ButtonType.OK)
                            partMaxTxt.clear();
                            partMinTxt.clear();
                    }
                    // Ensures that the stock inventory is between the maximum and minimum inventory level
                    else if (stock < min || stock > max) {
                        // Alert dialog box if true
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The Inventory level must be greater than" +
                                " or equal to the Minimum Inventory and less than or equal to the Maximum Inventory!");
                        Optional<ButtonType> result = alert.showAndWait();
                        // Clears text field for new entry
                        if (result.isPresent() && result.get() == ButtonType.OK)
                            partInventoryTxt.clear();
                    }
                    // Saves data as a new Outsourced part
                    else {
                    // Adds part to allPart list
                    Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
                    // Confirms that the part has been saved
                    JOptionPane.showMessageDialog(null, "The modified product has been saved!");
                    // Loads and sets the scene for the mainForm
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
        }
            // Catches the exception
            catch (NumberFormatException e) {
                // Alert dialog box if text field values are incorrect
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Please enter a valid value for each text field");
                alert.showAndWait();
            }
        }

        public void initialize(URL url, ResourceBundle rb) {
            // Starts the form with the inHouse button selected
            inHouseRBtn.setSelected(true); // Starts the form with the inHouse button selected
            // Starts the form with a random number for the InHouseID
            partIdTxt.setText(String.valueOf(Inventory.inHouseID()));
        }

}





