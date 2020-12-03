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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author FPMananaA
 */
class VentanaPago {
static Label lbl_total;
static RadioButton rb_efectivo, rb_tarjeta;
static TextField txt_tarjeta, txt_cp;
static ToggleGroup tg_medio_pago;
static Button btn_factura;

    static void mostrarVentana(Stage ventana, int precio_total, ComunicacionPago pago) {
        try {
            File f=new File("recursos/pnt_pago.fxml");
            URL url_pantala_pago=f.toURI().toURL();
            Parent root=FXMLLoader.load(url_pantala_pago);
            Scene escena=new Scene(root);
            ventana.setScene(escena);
            /*INICIALIZO*/
            lbl_total=(Label)escena.lookup("#lbl_total");
            rb_efectivo=(RadioButton)escena.lookup("#rb_efectivo");
            rb_tarjeta=(RadioButton)escena.lookup("#rb_tarjeta");
            txt_tarjeta=(TextField)escena.lookup("#txt_tarjeta");
            txt_cp=(TextField)escena.lookup("#txt_cp");
            btn_factura=(Button)escena.lookup("#btn_sacar_factura");
            
            /***/
            lbl_total.setText("TOTAL: "+precio_total+"€ ");
            EventHandler<ActionEvent> oyente_rb=new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    RadioButton rb_clicado=(RadioButton)t.getSource();
                    String clicado=rb_clicado.getId();
                    if (clicado.equals("rb_tarjeta"))
                    {
                        txt_tarjeta.setVisible(true);
                    }else
                    {
                        txt_tarjeta.setVisible(false);
                    }
                    
                }
            };
            rb_tarjeta.setOnAction(oyente_rb);
            rb_efectivo.setOnAction(oyente_rb);
            btn_factura.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    String cp_cliente=txt_cp.getText();
                    String n_tarjeta="";
                    if(rb_tarjeta.isSelected())
                    {
                        n_tarjeta=txt_tarjeta.getText();
                    }
                    pago.realizarFactura(cp_cliente, n_tarjeta);
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(VentanaPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  interface ComunicacionPago
    {
        public void realizarFactura(String cp_cliente, String n_tarjeta);
    }
}
