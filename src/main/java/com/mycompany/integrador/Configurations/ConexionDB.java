/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Configurations;

import java.sql.*;

/**
 *
 * @author exequiel
 */
public class ConexionDB {

    Connection conn = null;
    String db = "DB_ProyectoIntegrador.db";
    String conector = "jdbc:sqlite:" + db;

    public Connection conectarDB() {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(conector);

            if (conn != null) {
                //System.out.println("se pudo conectar a base de datos");
            } else {
                System.out.println("ERROR! no se pudo conectar a base de datos");
            }

        } catch (SQLException a) {
            System.out.println(a);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return conn;
    }

    public void desconetarDB() {
        try {
            if (conn != null) {
                conn.close();
            } else {
                System.out.println("La conexion se cerro correctamaente");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.toString());
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.toString());
        }
    }

}
