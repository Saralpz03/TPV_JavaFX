/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpv;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
/**
 *
 * @author FPMananaA
 */
public class JavaFX_1 extends Application  implements PantallaLogin.ComunicacionLogin,
        PantallaProductos.ComunicacionPantallaProductos, VentanaPago.ComunicacionPago{

static Stage ventana;
static ArrayList<String> lista_productos=new ArrayList();
static int id_dependiente;


   public static void main(String[] args) {
 
       launch();
    }

    @Override
    public void start(Stage window) throws Exception {
        ventana=window;
        PantallaLogin.cargarPantallaLogin(window, this);
    }

    @Override
    public void comprobarUsuario(String nombre, String password) {
       Dependiente d=AccesoBD.comprobarDependiente(nombre, password);
       if(d==null)
       {
           PantallaLogin.indicarError();
       }
       else
       {//Si etá bien logueado
           id_dependiente=d.getId_dependiente();
           PantallaProductos.mostrarPantalla(ventana, d.getNombre(), this);
       }
    }
   
    //A este metodo se le llama desde PantallaProductos
    @Override
    public void aniadirProducto(String id_producto)
    {
        //Añadir id_producto a ArrayList
        lista_productos.add(id_producto);
    }
    @Override
    public void pagar()
    {
       //Para sacar el precio_total, recorro el AL y voy sacando el precio
       int precio_total=AccesoBD.calcularPrecioTotal(lista_productos);
       VentanaPago.mostrarVentana(ventana, precio_total, this);
        
    }

    @Override
    public void realizarFactura(String cp_cliente, String n_tarjeta) {
       AccesoBD.actualizarDatos(cp_cliente, n_tarjeta,lista_productos, id_dependiente);
    }

}
