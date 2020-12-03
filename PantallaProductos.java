/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpv;

import java.io.File;
import java.io.IOException;
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
class PantallaProductos {
static Label lbl_dependiente;
static TextField txt_producto;
static Button btn_aniadir, btn_factura;

    static void mostrarPantalla(Stage ventana, String nombre, ComunicacionPantallaProductos llamante) {
        try {
            File f=new File("recursos/pnt_introproductos.fxml");
            URL url_pantala_intro_productos=f.toURI().toURL();
            Parent root=FXMLLoader.load(url_pantala_intro_productos);
            Scene escena=new Scene(root);
            lbl_dependiente=(Label)escena.lookup("#lbl_dependiente");
            txt_producto=(TextField)escena.lookup("#txt_producto");
            btn_aniadir=(Button)escena.lookup("#btn_aniadir");
            btn_factura=(Button)escena.lookup("#btn_factura");
            
            lbl_dependiente.setText("dependiente: "+nombre);
            EventHandler<ActionEvent> oyente_btn=new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                   Object objeto_clicado=t.getSource();
                   Button btn_clicado=(Button)objeto_clicado;
                   String id_clicado=btn_clicado.getId();
                   if (id_clicado.equals("btn_factura"))
                   {
                       //Llamo a metod para que saque la factura
                       llamante.pagar();
                   }
                   else
                   {
                       //Llamo a metodo para que meta objketo en ArrayList
                       String txt_id=txt_producto.getText();
                       llamante.aniadirProducto(txt_id);
                       txt_producto.setText("");
                   }
                    
                }
            };
            btn_aniadir.setOnAction(oyente_btn);
            btn_factura.setOnAction(oyente_btn);
            
            ventana.setScene(escena);
        } catch (Exception ex) {
            Logger.getLogger(PantallaProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    public interface ComunicacionPantallaProductos
    {
        public void aniadirProducto(String id_producto);
         public void pagar();
    }
}
