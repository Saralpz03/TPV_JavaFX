/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpv;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author FPMananaA
 */
class PantallaLogin {
static String nombre, password;
static Label lbl_error;
static Button btn_comprobar;
 static TextField txt_nombre, txt_pwd;
    static void cargarPantallaLogin(Stage window, ComunicacionLogin llamante) {
       
       File f=new File("recursos/pantalla.fxml");
        try {
            URL url_pantalla_login=f.toURI().toURL();
            Parent root=FXMLLoader.load(url_pantalla_login);
            Scene escena=new Scene(root);
            /*INICIALIZO OBJETOS*/
            txt_nombre=(TextField)escena.lookup("#txt_nombre");
            txt_pwd=(TextField)escena.lookup("#txt_pwd");
            btn_comprobar=(Button)escena.lookup("#btn_comprobar");
            lbl_error=(Label)escena.lookup("#lbl_error");
            EventHandler<ActionEvent> oyente_btn=new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                   nombre=txt_nombre.getText();
                   password=txt_pwd.getText();
                   //JavaFX_1.comprobarUsuario2(nombre, password);
                   llamante.comprobarUsuario(nombre, password);
                }
            };
            btn_comprobar.setOnAction(oyente_btn);
            window.setScene(escena);
            window.show();
        } catch (Exception ex) {
            Logger.getLogger(PantallaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 public static void indicarError()
    {
        lbl_error.setText("Ha habido un error. Inténtelo de nuevo");
        txt_nombre.setText("");
        txt_pwd.setText("");
        
    }
  public interface ComunicacionLogin
  {
      public void comprobarUsuario(String nombre, String password);
  }
    
}
