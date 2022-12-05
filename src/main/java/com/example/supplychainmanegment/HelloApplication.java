package com.example.supplychainmanegment;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;

    public static Group root;
    public static String  emailId;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        emailId = "";
         connection = new DatabaseConnection();
        root = new Group();
        Header header = new Header();
        productPage products = new productPage();
        ListView<HBox> productList = products.showProducts();

        AnchorPane productPane = new AnchorPane();
        productPane.setLayoutX(125);
        productPane.setLayoutY(100);

        productPane.getChildren().add(productList);
        root.getChildren().addAll(header.root,productPane);
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Supply Chain");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e ->{
            try {
                connection.con.close();
                System.out.println("our database connection is closed");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}