/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Interface.UsuarioService;
import com.mycompany.integrador.Models.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author exequiel
 */
public class UsuarioServiceImplement implements UsuarioService {

    ConexionDB conn = new ConexionDB();

    @Override
    public void guardarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (email, nombreUsuario, contrasenia) Values (?,?,?)";

        if (buscarUsuario(usuario.getNombreUsuario()).getNombreUsuario() == null) {
            try ( Connection connectC = conn.conectarDB()) {
                if (buscarUsuario(usuario.getNombreUsuario()) != null) {
                    try ( PreparedStatement psUsuario = connectC.prepareStatement(sql)) {
                        psUsuario.setString(1, usuario.getEmail());
                        psUsuario.setString(2, usuario.getNombreUsuario());
                        psUsuario.setString(3, usuario.getContrasenia());
                        psUsuario.executeUpdate();
                        System.out.println("usuario creado correctamente");
                    } catch (SQLException e) {
                        System.out.println("error al ejecutar consulta sql " + e.toString());
                    }
                } else {
                    System.out.println("ya existe un usuario con el mismo nombreUsuario");
                }

            } catch (SQLException ex) {
                System.out.println("Error al crear el usuario: " + ex.getMessage());
                Logger.getLogger(UsuarioServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("ya existe una cuenta con ese nombre de usuario");
        }

    }

    @Override
    public void modificarUsuario(int id, Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarUsuario(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Usuario> buscarTodasLosUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario buscarUsuario(int id) {
        String sql = "SELECT * FROM Usuario WHERE idUsuario = ? ";
        Connection connectC = conn.conectarDB();
        PreparedStatement ps;
        Usuario usuario = null;
        try {
            ps = connectC.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            ps.setInt(1, id);
            usuario = new Usuario(rs.getString("email"), rs.getString("nombreUsuario"), rs.getString("contrasenia"));
            return usuario;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    @Override
    public Usuario buscarUsuario(String nombreUsuario) {
        String sql = "SELECT * FROM Usuario WHERE nombreUsuario = ? ";
        Usuario usuario = null;

        try ( Connection connectC = conn.conectarDB()) {
            try ( PreparedStatement psUsuario = connectC.prepareStatement(sql)) {
                psUsuario.setString(1, nombreUsuario);
                ResultSet rs = psUsuario.executeQuery();
                usuario = new Usuario(rs.getString("email"), rs.getString("nombreUsuario"), rs.getString("contrasenia"));
            } catch (SQLException e) {
                System.out.println("error al ejecutar consulta sql " + e.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

}
