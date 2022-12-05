package com.example.supplychainmanegment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class productPage {
    ListView<HBox> products;
    ListView<HBox> showProductsbyName(String search)throws SQLException {

        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from product");
        products = new ListView<>();
        Label Name = new Label();
        Label Price = new Label();
        Label id = new Label();
        HBox details = new HBox();

        Name.setMinWidth(60);
        id.setMinWidth(60);
        Price.setMinWidth(60);
        Name.setText("Name");
        Price.setText("price");
        id.setText("productID");

        details.getChildren().addAll(id,Name,Price);
        productList.add(details);

        while(res.next()){
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())) {

                Label productName = new Label();
                Label productPrice = new Label();
                Label productID = new Label();
                Button buy = new Button();
                HBox productDetails = new HBox();

                productName.setMinWidth(60);
                productID.setMinWidth(60);
                productPrice.setMinWidth(60);
                buy.setText("Buy");
                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailId.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                            dialog.setContentText("Login First Before Placing A Order");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        } else {

                            try {
                                Order place = new Order();
                                place.placeOrder(productID.getText());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            System.out.println("clicked buy button");
                        }
                    }
                });
                productName.setText(res.getString("productName"));
                productPrice.setText(res.getString("price"));
                productID.setText("" + res.getInt("productID"));

                productDetails.getChildren().addAll(productID, productName, productPrice, buy);
                productList.add(productDetails);
            }
        }

        if(productList.size()==1)
        {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Search Result");
            ButtonType type = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("OOPS NO Product Available For this Search");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        products.setItems(productList);
        return  products;
    }


    ListView<HBox> showProducts()throws SQLException {




        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from product");
        products = new ListView<>();
        Label Name = new Label();
        Label Price = new Label();
        Label id = new Label();
        HBox details = new HBox();

        Name.setMinWidth(60);
        id.setMinWidth(60);
        Price.setMinWidth(60);
        Name.setText("Name");
       Price.setText("price");
        id.setText("productID");

        details.getChildren().addAll(id,Name,Price);
        productList.add(details);

        while(res.next()){
            Label productName = new Label();
            Label productPrice = new Label();
            Label productID = new Label();
            Button buy = new Button();
            HBox productDetails = new HBox();

            productName.setMinWidth(60);
            productID.setMinWidth(60);
            productPrice.setMinWidth(60);
            buy.setText("Buy");
            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent)  {
                    if(HelloApplication.emailId.equals("")){
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("Login First Before Placing A Order");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }
                    else {

                        try {
                            Order place = new Order();
                            place.placeOrder(productID.getText());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("clicked buy button");
                    }
                }
            });
            productName.setText(res.getString("productName"));
            productPrice.setText(res.getString("price"));
            productID.setText("" + res.getInt("productID"));

            productDetails.getChildren().addAll(productID,productName,productPrice,buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return  products;
    }
}
