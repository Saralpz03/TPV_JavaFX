/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FPMananaA
 */
public class AccesoBD {
    
    public static Dependiente comprobarDependiente(String nombre, String password)
    {
        try {
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "");
            String sql="SELECT id_dependiente, nombre from dependientes where nombre='"+nombre+"' AND pwd='"+password+"'";
            Statement stmt=c.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next())
            {
                String nombre_bd=rs.getString("nombre");
                int id=rs.getInt("id_dependiente");
                Dependiente d=new Dependiente(nombre_bd, id);
                return d;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    static int calcularPrecioTotal(ArrayList<String> lista_productos) {
        int total=0;
        try {
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "");
            Statement stmt=c.createStatement();
            
            for(String txt_id: lista_productos)
            {
                int id_producto=Integer.valueOf(txt_id);
                String sql="SELECT precio from producto where id_producto="+id_producto+"";
                try {
                    ResultSet rs=stmt.executeQuery(sql);
                    if(rs.next())
                    {
                        int precio=rs.getInt("precio");
                        total+=precio;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            
            
            
          return total;
        
    }

    static void actualizarDatos(String cp_cliente, String n_tarjeta, ArrayList<String> lista_productos, int id_dependiente) {
        try {
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "");
            Statement stmt=c.createStatement();
           
            for(String id_prod: lista_productos )
            {
            String update_productos="UPDATE producto SET stock=stock-1 WHERE id_producto='"+id_prod+"'";
            stmt.executeUpdate(update_productos);
             }
            String query_insercion_factura="INSERT INTO factura (id_dependiente, codigo_postal_cliente, tarjeta) "
                    + "VALUES('"+id_dependiente+"', '"+cp_cliente+"', '"+n_tarjeta+"')";
            stmt.executeUpdate(query_insercion_factura,  Statement.RETURN_GENERATED_KEYS);
            ResultSet rs=stmt.getGeneratedKeys();
            int id=0;
            if(rs.next())
            {
             id=rs.getInt(1);
            }
            for(String id_prod: lista_productos )
            {
            String insert_fct_prod="INSERT INTO factura_producto VALUES("+id+", "+Integer.parseInt(id_prod)+")";
            stmt.executeUpdate(insert_fct_prod);
             }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
