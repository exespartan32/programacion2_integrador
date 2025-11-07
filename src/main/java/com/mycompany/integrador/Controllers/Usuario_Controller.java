/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Service.UsuarioServiceImplement;
import com.mycompany.integrador.Models.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class Usuario_Controller {

    UsuarioServiceImplement usuarioServiceImplement = new UsuarioServiceImplement();
    Scanner sc = new Scanner(System.in);

    public void nuevoUsuario() {
        System.out.println("--- Creando Nuevo Usuario ---");
        System.out.print("Ingrese el Email: ");
        String email = sc.nextLine();
        System.out.print("Ingrese el Nombre de Usuario: ");
        String nombreUsuario = sc.nextLine();
        System.out.print("Ingrese la Contraseña: ");
        String contrasenia = sc.nextLine();
        Usuario usuario = new Usuario(email, nombreUsuario, contrasenia, LocalDate.now(), null, null);
        usuarioServiceImplement.guardarUsuario(usuario);
        System.out.println("¡Usuario " + nombreUsuario + " guardado con éxito!");
    }

    public void modificarDatosUsuario() {
        ArrayList<Usuario> listaUsuarios = usuarioServiceImplement.buscarUsuario();
        if (listaUsuarios.size() > 0) {
            try {
                System.out.println("________________________________________________________________________________");
                System.out.println("                    seleccione el usuario que desea modificar:                  ");
                System.out.println("________________________________________________________________________________");
                ArrayList<Usuario> listaUsuario = usuarioServiceImplement.buscarUsuario();
                for (int i = 0; i < listaUsuario.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| email " + listaUsuario.get(i).getEmail());
                    System.out.println("| nombre de usuario " + listaUsuario.get(i).getNombreUsuario());
                    System.out.println("| contraseña " + listaUsuario.get(i).getContrasenia());
                    System.out.println("------------------------------------------------------------------");
                }
                System.out.print("Seleccionar el elemento Nº ");
                int i_usuario = Integer.parseInt(sc.nextLine());
                Usuario usuario = listaUsuario.get(i_usuario);
                int id_usuario = usuario.getIdUsuario();
                System.out.print("Nuevo Email: ");
                usuario.setEmail(sc.nextLine());
                System.out.print("Nuevo Nombre de Usuario: ");
                usuario.setNombreUsuario(sc.nextLine());
                System.out.print("Nueva Contraseña: ");
                usuario.setContrasenia(sc.nextLine());
                usuario.setFechaModificacion(LocalDate.now());
                usuarioServiceImplement.modificarUsuario(id_usuario, usuario);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("               ERROR!!: no existe el registro seleccionado        ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                ERROR!!: debe ser un numero.                      ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                 no hay usuarios guardados en el sistema          ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void eliminarUsuario() {
        ArrayList<Usuario> listaUsuarios = usuarioServiceImplement.buscarUsuario();
        if (listaUsuarios.size() > 0) {
            System.out.println("________________________________________________________________________________");
            System.out.println("                      seleccione el usuario que desea eliminar:                 ");
            System.out.println("________________________________________________________________________________");
            try {
                ArrayList<Usuario> listaUsuario = usuarioServiceImplement.buscarUsuario();
                for (int i = 0; i < listaUsuario.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| email " + listaUsuario.get(i).getEmail());
                    System.out.println("| nombre de usuario " + listaUsuario.get(i).getNombreUsuario());
                    System.out.println("| contraseña " + listaUsuario.get(i).getContrasenia());
                    System.out.println("------------------------------------------------------------------");
                }
                System.out.print("Seleccionbar el elemento Nº ");
                int i_usuario = sc.nextInt();
                Usuario usuario = listaUsuario.get(i_usuario);
                int id_usuario = usuario.getIdUsuario();
                sc.nextLine();
                System.out.print("¿Está seguro que desea eliminar al usuario " + usuario.getNombreUsuario() + "? (si/no): ");
                String confirmacion = sc.nextLine();
                if (confirmacion.equalsIgnoreCase("si") || confirmacion.equalsIgnoreCase("no")) {
                    if (confirmacion.equalsIgnoreCase("si")) {
                        usuarioServiceImplement.eliminarUsuario(id_usuario);
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                } else {
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("ERROR!!: opcion incorrecta. debe colocar si o no");
                    System.out.println("------------------------------------------------------------------");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("               ERROR!!: no existe el registro seleccionado        ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                ERROR!!: debe ser un numero.                      ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("               no hay usuarios guardados en el sistema            ");
            System.out.println("                 primero agregue usuarios al sistema              ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void verTodosLosUsuario() {
        ArrayList<Usuario> listaUsuarios = usuarioServiceImplement.buscarUsuario();
        if (listaUsuarios.size() > 0) {
            System.out.println("///////////////////////////////////////////////////////////////////");
            System.out.println("|                         datos encontrados                       |");
            System.out.println("------------------------------------------------------------------");
            for (int j = 0; j < listaUsuarios.size(); j++) {
                System.out.println("///////////////////////////////////////////////////////////////////");
                System.out.println("|                           Elemento " + j + ":                           |");
                System.out.println("------------------------------------------------------------------");
                System.out.println("| email " + listaUsuarios.get(j).getEmail());
                System.out.println("| nombre de usuario " + listaUsuarios.get(j).getNombreUsuario());
                System.out.println("| contraseña " + listaUsuarios.get(j).getContrasenia());
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("               no hay usuarios guardados en el sistema            ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarUsuarioPorId() {
        try {
            System.out.print("ingrese el id del usuario que desea buscar");
            int id_usuario = sc.nextInt();
            Usuario usuario = usuarioServiceImplement.buscarUsuario(id_usuario);
            System.out.println("///////////////////////////////////////////////////////////////////");
            System.out.println("|                         datos encontrados                       |");
            System.out.println("------------------------------------------------------------------");
            System.out.println("| email " + usuario.getEmail());
            System.out.println("| nombre de usuario " + usuario.getNombreUsuario());
            System.out.println("| contraseña " + usuario.getContrasenia());
            System.out.println("------------------------------------------------------------------");
        } catch (NullPointerException e) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                 no existe ningun usuario con ese id              ");
            System.out.println("------------------------------------------------------------------");
        } catch (InputMismatchException e) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                   ERROR!!: debe ser un numero.                   ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarUsuarioPorNombreUsuario() {
        ArrayList<Usuario> listaUsuarios = usuarioServiceImplement.buscarUsuario();
        if (listaUsuarios.size() > 0) {
            System.out.print("ingrese el nombre del usuario que desea buscar: ");
            String nombreUsuario = sc.nextLine();
            try {
                Usuario usuario = usuarioServiceImplement.buscarUsuario(nombreUsuario);
                if (usuario.getEmail() != null) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                         datos encontrados                       |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| email " + usuario.getEmail());
                    System.out.println("| nombre de usuario " + usuario.getNombreUsuario());
                    System.out.println("| contraseña " + usuario.getContrasenia());
                    System.out.println("------------------------------------------------------------------");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("              ERROR!!: no existe el registro seleccionado         ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                    ERROR!!: debe ser un numero.                  ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("               no hay usuarios guardados en el sistema            ");
            System.out.println("------------------------------------------------------------------");
        }

    }

}
