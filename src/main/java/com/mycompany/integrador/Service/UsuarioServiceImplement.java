/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Interface.UsuarioService;
import com.mycompany.integrador.Models.Usuario;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author exequiel
 */
public class UsuarioServiceImplement implements UsuarioService {

    ConexionDB conn = new ConexionDB();

    @Override
    public void guardarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (email, nombreUsuario, contrasenia, fechaCreacion) Values (?,?,?,?)";
        if (buscarUsuario(usuario.getNombreUsuario()).getNombreUsuario() == null) {
            try ( Connection connectC = conn.conectarDB()) {
                try ( PreparedStatement psUsuario = connectC.prepareStatement(sql)) {
                    psUsuario.setString(1, usuario.getEmail());
                    psUsuario.setString(2, usuario.getNombreUsuario());
                    psUsuario.setString(3, usuario.getContrasenia());
                    DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
                    String fechaString = usuario.getFechaCreacion().format(formatterEs);
                    psUsuario.setString(4, fechaString);
                    psUsuario.executeUpdate();
                    System.out.println("usuario creado correctamente");
                } catch (SQLException e) {
                    System.out.println("error al ejecutar consulta sql " + e.toString());
                }

            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("ya existe una cuenta con ese nombre de usuario");
        }
    }

    @Override
    public void modificarUsuario(int id, Usuario usuario) {
        if (buscarUsuario(id) != null) {
            String sql = "UPDATE Usuario\n"
                    + "   SET email = ?,\n"
                    + "       nombreUsuario = ?,\n"
                    + "       fechaModificacion = ?\n"
                    + "WHERE contrasenia = ?";
            Connection connectC = conn.conectarDB();
            try {
                PreparedStatement ps = connectC.prepareStatement(sql);
                DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
                String fechaString = usuario.getFechaCreacion().format(formatterEs);
                ps.setString(1, usuario.getEmail());
                ps.setString(2, usuario.getNombreUsuario());
                ps.setString(3, fechaString);
                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("precio actualizado correctamente");
                } else {
                    System.out.println("No se encontro el registro");
                }
            } catch (SQLException e) {
                System.out.println("error al ejecutar la consulta" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("no se encontro el registro seleccionado");
        }
    }

    @Override
    public void eliminarUsuario(int id) {
        if (buscarUsuario(id) != null) {
            String sql = "DELETE FROM Curso WHERE nombreCurso = ?";
            Connection connect = conn.conectarDB();
            try {
                PreparedStatement ps = connect.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("precio eliminado correctamente");
                } else {
                    System.out.println("No se encontro un el registro con id " + id);
                }
            } catch (SQLException e) {
                System.out.println("error al ejecutar la consulta" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("no se encontro el registro seleccionado");
        }
    }

    @Override
    public ArrayList<Usuario> buscarUsuario() {
        String sql = "SELECT * FROM Usuario";
        Connection connectC = conn.conectarDB();
        PreparedStatement ps;
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            ps = connectC.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                Usuario usuario = new Usuario(
                        rs.getString("email"),
                        rs.getString("nombreUsuario"),
                        rs.getString("contrasenia"),
                        fechaCreacion
                );
                listaUsuarios.add(usuario);
            } else {
                System.out.println("no se encontro ningun usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return listaUsuarios;
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
            LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
            if (rs.next()) {
                ps.setInt(1, id);
                usuario = new Usuario(rs.getString(
                        "email"),
                        rs.getString("nombreUsuario"),
                        rs.getString("contrasenia"),
                        fechaCreacion
                );
            } else {
                System.out.println("no se ecnotro el usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Usuario buscarUsuario(String nombreUsuario) {
        String sql = "SELECT * FROM Usuario WHERE nombreUsuario = ? ";
        Usuario usuario = new Usuario();

        try ( Connection connectC = conn.conectarDB()) {
            try ( PreparedStatement psUsuario = connectC.prepareStatement(sql)) {
                psUsuario.setString(1, nombreUsuario);
                ResultSet rs = psUsuario.executeQuery();
                if (rs.next()) {
                    LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    usuario = new Usuario(
                            rs.getString("email"),
                            rs.getString("nombreUsuario"),
                            rs.getString("contrasenia"),
                            fechaCreacion
                    );
                } else {
                    System.out.println("no se encontro el usuario");
                }
            } catch (SQLException e) {
                System.out.println("error al ejecutar consulta sql " + e.toString());
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }

}
