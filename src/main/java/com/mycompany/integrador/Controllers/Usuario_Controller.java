/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Service.UsuarioServiceImplement;
import com.mycompany.integrador.Models.Usuario;
import java.time.LocalDate;
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
        try {
            ArrayList<Usuario> listaUsuario = usuarioServiceImplement.buscarUsuario();
            for (int i = 0; i < listaUsuario.size(); i++) {
                int elemento = i + 1;
                System.out.println("Elemento " + elemento + ": " + listaUsuario.get(i));
            }
            int i_usuario = sc.nextInt();
            int elemento = i_usuario - 1;
            Usuario usuario = listaUsuario.get(elemento);
            int id_usuario = usuario.getIdUsuario();

            usuario.setContrasenia("nuevo contraseña --- ");
            usuario.setEmail("nuevo email ----");
            usuario.setNombreUsuario("nuevo nombre de usuario ---- ");
            usuario.setFechaModificacion(LocalDate.now());
            usuarioServiceImplement.modificarUsuario(id_usuario, usuario);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("no existe el registro seleccionado");
        }
    }

    public void eliminarUsuario() {
        System.out.println("seleccione el usuario que desea eliminar:");
        System.out.println("///////////////////////////////////////////////////////////////////");
        try {
            ArrayList<Usuario> listaUsuario = usuarioServiceImplement.buscarUsuario();
            for (int i = 0; i < listaUsuario.size(); i++) {
                int elemento = i + 1;
                System.out.println("Elemento " + elemento + ": " + listaUsuario.get(i));
            }
            int i_usuario = sc.nextInt();
            int elemento = i_usuario - 1;
            Usuario usuario = listaUsuario.get(elemento);
            int id_usuario = usuario.getIdUsuario();
            usuarioServiceImplement.eliminarUsuario(id_usuario);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("no existe el registro seleccionado");
        }

    }

    public void verTodosLosUsuario() {
        ArrayList<Usuario> listaUsuarios = usuarioServiceImplement.buscarUsuario();
        System.out.println("datos encontrados:");
        for (int j = 0; j < listaUsuarios.size(); j++) {
            System.out.println("=> " + listaUsuarios.get(j));
        }
    }

    public void buscarUsuarioPorId() {
        System.out.println("ingrese el id del usuario que desea buscar");
        int id_usuario = sc.nextInt();
        try {
            Usuario usuario = usuarioServiceImplement.buscarUsuario(id_usuario);
            System.out.println("datos encontrados: " + usuario.toString());
        } catch (NullPointerException e) {
            System.out.println("no existe un registro con ese id");
        }
    }

    public void buscarUsuarioPorNombreUsuario() {
        System.out.println("ingrese el nombre del usuario que desea buscar");
        String nombreUsuario = sc.nextLine();

        try {
            Usuario usuario = usuarioServiceImplement.buscarUsuario(nombreUsuario);
            if (usuario.getEmail() != null) {
                System.out.println("datos encontrados: " + usuario.toString());
            }
        } catch (NullPointerException e) {
            System.out.println("no existe un registro con ese id");
        }

    }

}
