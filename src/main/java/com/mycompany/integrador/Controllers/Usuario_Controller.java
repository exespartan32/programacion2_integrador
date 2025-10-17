/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Service.UsuarioServiceImplement;
import com.mycompany.integrador.Models.Usuario;

/**
 *
 * @author exequiel
 */
public class Usuario_Controller {

    UsuarioServiceImplement usuarioServiceImplement = new UsuarioServiceImplement();

    public void nuevoUsuario() {
        Usuario usuario = new Usuario("exemay777@gmail.com", "exeSpartan32666", "contraseña");
        usuarioServiceImplement.guardarUsuario(usuario);
    }

}
