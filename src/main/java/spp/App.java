/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 *
 * @author gomes
 */
public class App extends Application{
    @Override
    public void start(Stage stage) {
        Label label = new Label("Sistema listo 🚀");

        Scene scene = new Scene(label, 400, 200);

        stage.setScene(scene);
        stage.setTitle("Prueba inicial");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
