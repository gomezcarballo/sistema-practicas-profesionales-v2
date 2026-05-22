/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author gomes
 */
public class App extends Application{
    
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {

        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/fxml/VistaMenuPrincipalCoordinador.fxml"));

        Parent raiz = cargadorFXML.load();

        Scene vistaPrincipal = new Scene(raiz);
        escenarioPrincipal.setScene(vistaPrincipal);
        escenarioPrincipal.setTitle("Sistema Principal");
        escenarioPrincipal.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
