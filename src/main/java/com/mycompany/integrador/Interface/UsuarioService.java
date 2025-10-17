/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;

import java.util.ArrayList;
import java.util.Optional;
import com.mycompany.integrador.Models.Usuario;

/**
 *
 * @author exequiel
 */
public interface UsuarioService {

    public void guardarUsuario(Usuario usuario);

    public void modificarUsuario(int id, Usuario usuario);

    public void eliminarUsuario(int id);

    public ArrayList<Usuario> buscarTodasLosUsuarios();

    public Usuario buscarUsuario(int id);

    public Usuario buscarUsuario(String nombreUsuario);
}
