/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.integrador;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Controllers.Persona_Controller;

/**
 *
 * @author exequiel
 */
public class Integrador {

    public static void main(String[] args) {
        System.out.println("Hello World!");
//        ConexionDB conn = new ConexionDB();
//        conn.conectarBB();
//        conn.desconetarDB();

        Persona_Controller persona_Controller = new Persona_Controller();
        //persona_Controller.crearNuevaPersona();
        persona_Controller.buscarPersona();
    }
}
