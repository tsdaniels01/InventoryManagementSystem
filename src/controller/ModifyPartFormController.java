package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**This class is the controller for the ModifyPartForm. It controls all parts of the ModifyPartForm.FXML document
 ***********************************************************************************************************
 * FUTURE ENHANCEMENT
 * As it is, I think the GUI could use some improvements, perhaps adding some more contrast and color. In addition,
 * I think a clear button to clear all the text fields would be beneficial to the user.
 */
public class ModifyPartFormController {

    Stage stage;  // Holds the stage
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
    }
    /**This method is to acknowledge rather the Outsourced RadioButton is selected as well as provides a
     * * random ID number.
     * @param  event is a RadioButton
     * */
    @FXML
    void onActionOutsourced(ActionEvent event) {
        if (outSourcedRBtn.isSelected()) // Is it selected
            machineIdLbl.setText("Company Name"); // Sets text in company name
            machineIdTxt.setVisible(false); // Hides the machineId text field
            companyNameTxt.setVisible(true); // Ensures that the company name text field is visible
    }
    /**This method returns the application to the mainForm. It is a cancel button
     * @param event is a button.
     * */
    @FXML
    void onActionMainForm(ActionEvent event) throws IOException {
        // Alert dialog box to inform that cancelling will clear all entries
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values, Do you" +
                " want to continue?");

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
    /**
     * This method separates the part by InHouse or Outsourced and saves the newly created part. It provides
     * Alert dialog boxes to ensure that the proper information is received.
     * @param event is a button
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
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The Maximum Inventory must be greater than" +
                            " the Minimum Inventory!");
                    Optional<ButtonType> result = alert.showAndWait();
                    // Clears text fields for new entry
                    if (result.isPresent() && result.get() == ButtonType.OK)
                        partMaxTxt.clear();
                    partMinTxt.clear();
                    // Ensures that the stock inventory is between the maximum and minimum inventory level
                } else if (stock < min || stock > max) {
                    // Alert dialog box if true
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The Inventory level must be greater than " +
                            "or equal to the Minimum Inventory and less than or equal to the Maximum Inventory!");
                    Optional<ButtonType> result = alert.showAndWait();
                    // Clears text field for new entry
                    if (result.isPresent() && result.get() == ButtonType.OK)
                        partInventoryTxt.clear();
                    // Saves data as a new InHouse part
                } else {
                    // Does the part already exist
                    if (Inventory.selectPart(id) == true)
                        // If so, update the part
                        Inventory.updatePart(id, new InHouse(id, name, price, stock, min, max, machineID));
                    // Else, add to allPart list
                    else Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineID));
                    // Dialog box confirming the save
                    JOptionPane.showMessageDialog(null, "The modified product has been saved!");
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
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The Maximum Inventory must be greater than the" +
                            " Minimum Inventory!");
                    Optional<ButtonType> result = alert.showAndWait();
                    // Clears text fields for new entry
                    if (result.isPresent() && result.get() == ButtonType.OK)
                        partMaxTxt.clear();
                    partMinTxt.clear();
                }
                // Ensures that the stock inventory is between the maximum and minimum inventory level
                else if (stock < min || stock > max) {
                    // Alert dialog box if true
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The Inventory level must be greater than or" +
                            " equal to the Minimum Inventory and less than or equal to the Maximum Inventory!");
                    Optional<ButtonType> result = alert.showAndWait();
                    // Clears text field for new entry
                    if (result.isPresent() && result.get() == ButtonType.OK)
                        partInventoryTxt.clear();
                }
                // Saves data as a new Outsourced part
                else {
                    // Does the part already exist
                    if (Inventory.selectPart(id) == true)
                        // If so, update the part
                        Inventory.updatePart(id, new Outsourced(id, name, price, stock, min, max, companyName));
                        // Else, adds part to allPart list
                    else Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
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
    /**This method retrieves the InHouse part data sent by the mainForm.
     * @param part the part sent by the mainForm
     *******************************************************************************************************
     * LOGICAL ERROR
     * I could not get the part to send from the mainForm. I realized that I needed the machineID for the InHouse
     * and the companyName for the Outsourced. The only solution I could come up with was to send them separately
     * from the mainForm. I generated different random number categories for each one, so that I could
     * differentiate between the two before sending them over. It worked.
     *******************************************************************************************************
     * */
    public void sendPart(InHouse part)  {
        // Parses the partID and assigns to the partIDTxt
        partIdTxt.setText(String.valueOf(part.getPartID()));
        // Parses the partName and assigns to the partNameTxt
        partNameTxt.setText(part.getPartName());
        // Parses the partInStock and assigns to the partInventoryTxt
        partInventoryTxt.setText(String.valueOf(part.getPartInStock()));
        // Parses the partPrice and assigns to the partPriceTxt
        partPriceTxt.setText(String.valueOf(part.getPartPrice()));
        // Parses the partMin and assigns to the partMinTxt
        partMinTxt.setText(String.valueOf(part.getMin()));
        // Parses the partMax and assigns to the partMaxTxt
        partMaxTxt.setText(String.valueOf(part.getMax()));
        // Parses the machineID and assigns to the machineIDTxt
        machineIdTxt.setText(String.valueOf(part.getMachineID()));

    }
    /**This method retrieves the Outsourced part data sent by the mainForm.
     * @param part the part sent by the mainForm
     * */
    public void sendPart(Outsourced part) {

        machineIdLbl.setText("Company Name"); // If sent, sets the text in the machine label to Company name
        machineIdTxt.setVisible(false); // Hides the machineIdTxt
        companyNameTxt.setVisible(true); // Sets visible on companyNameTxt
        outSourcedRBtn.setSelected(true); // Selects the outSourcedRBtn

        // Parses the partID and assigns to the partIDTxt
        partIdTxt.setText(String.valueOf(part.getPartID()));
        // Parses the partName and assigns to the partNameTxt
        partNameTxt.setText(part.getPartName());
        // Parses the partInStock and assigns to the partInventoryTxt
        partInventoryTxt.setText(String.valueOf(part.getPartInStock()));
        // Parses the partPrice and assigns to the partPriceTxt
        partPriceTxt.setText(String.valueOf(part.getPartPrice()));
        // Parses the partMin and assigns to the partMinTxt
        partMinTxt.setText(String.valueOf(part.getMin()));
        // Parses the partMax and assigns to the partMaxTxt
        partMaxTxt.setText(String.valueOf(part.getMax()));
        // Parses the companyName and assigns it to the companyNameTxt
        companyNameTxt.setText(String.valueOf(part.getCompanyName()));
    }
}






