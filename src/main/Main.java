package main;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Project: C482
 * Written by Timothy Daniels
 * Student ID:001164972
 * This program creates an Inventory Management System and utilizes the MVC framework
 * Javadoc path: C482_Project\Javadoc
 * */

/**This is the main class. It initiates the start command for Javafx and loads the MainForm.
 *
 * */


public class Main extends Application {

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        primaryStage.setTitle("C482 Performance Assessment");
        primaryStage.setScene(new Scene(root, 1000, 425));
        primaryStage.show();
    }

    /**This is the main method; it is used to provide test data for the application.
     ****************************************************************************************************
     * LOGICAL ERROR
     * I wasn't sure how the associated parts test data could be included. I wasn't sure if I had to provide test data
     * for the whole overloaded constructor, or if there was a way to append it to the original constructor. Instructor
     * support helped me to realize this method while assisting me with creating the overloaded constructor.
     ****************************************************************************************************
     * FUTURE ENHANCEMENT
     * Create more test data to push the program.
     * */
    public static void main(String[] args) {

    // sample test data for partTableView/parts
        InHouse part1 = new InHouse(16,"Tire", 8.59, 42, 10, 70, 17);
        InHouse part2 = new InHouse(17,"Rim", 16.59, 24, 20, 40, 18);
        InHouse part3 = new InHouse(24,"Brake", 12.59, 53, 10, 65, 19);
        Outsourced part4 = new Outsourced(35,"Handle Bars", 34.59, 12, 10, 45, "Stewart Inc.");
        Outsourced part5 = new Outsourced(40,"Seat", 22.59, 62, 30, 80, "Lowell Industries");

    // add parts to allParts Observable arraylist
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);

    // sample test data for productTableView/products
        Product product1 = new Product(56,"Cruiser", 199.99, 6, 2, 20);
        Product product2 = new Product(57,"BMX", 499.99, 8, 2, 20);
        Product product3 = new Product(68,"Mountain", 799.99, 4, 2, 20);
        Product product4 = new Product(61,"Ten Speed", 259.99, 9, 2, 20);

    // adds associated parts to overloaded Product constructor
        product1.addAssocPart(part1);
        product2.addAssocPart(part1);
        product2.addAssocPart(part2);
        product3.addAssocPart(part1);
        product3.addAssocPart(part3);
        product3.addAssocPart(part5);
        product4.addAssocPart(part2);
        product4.addAssocPart(part4);

    // add products to allProducts Observable arraylist
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

        launch(args);
    }

}
