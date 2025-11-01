/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Service.UsuarioServiceImplement;
import com.mycompany.integrador.Models.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class Usuario_Controller {
    
    UsuarioServiceImplement usuarioServiceImplement = new UsuarioServiceImplement();
    Scanner sc = new Scanner(System.in);
    
    public void nuevoUsuario() {
        Usuario usuario = new Usuario("exemay777@gmail.com", "exeSpartan32666", "contraseña", LocalDate.now());
        usuarioServiceImplement.guardarUsuario(usuario);
    }
    
    public void modificarDatosUsuario() {
        System.out.println("seleccione el usuario que desea modificar:");
        System.out.println("///////////////////////////////////////////////////////////////////");
        ArrayList<Usuario> listaUsuario = usuarioServiceImplement.buscarUsuario();
        for (int i = 0; i < listaUsuario.size(); i++) {
            int elemento = i + 1;
            System.out.println("Elemento " + elemento + ": " + listaUsuario.get(i));
        }
        
        int i_curso = sc.nextInt();
        Usuario usuario = new Usuario(
                listaUsuario.get(i_curso).getIdUsuario(),
                "nuevo email",
                "nuevo nombre de usuario",
                "nuevo contraseña",
                LocalDate.MIN,
                LocalDate.now(),
                LocalDate.MIN);
        usuarioServiceImplement.modificarUsuario(i_curso, usuario);
    }
    
    public void eliminarUsuario() {
        
    }
    
    public void verTodosLosUsuario() {
        
    }
    
    public void buscarUsuarioPorId() {
        
    }
    
    public void buscarUsuarioPorNombreUsuario() {
        
    }
    
}
