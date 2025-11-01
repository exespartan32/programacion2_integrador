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
        String sql = "UPDATE Usuario\n"
                + "   SET email = ?,\n"
                + "       nombreUsuario = ?,\n"
                + "       fechaModificacion = ?\n"
                + "WHERE idUsuario = ?";

        try ( Connection connectC = conn.conectarDB()) {
            habilitarClavesForaneas(connectC);
            configurarBusyTimeout(connectC, 5000);
            connectC.setAutoCommit(false);
            try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
                DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
                String fechaString = usuario.getFechaModificacion() != null ? usuario.getFechaModificacion().format(formatterEs) : LocalDate.now().format(formatterEs);

                ps.setString(1, usuario.getEmail());
                ps.setString(2, usuario.getNombreUsuario());
                ps.setString(3, fechaString);
                ps.setInt(4, id);

                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Usuario actualizado correctamente");
                } else {
                    System.out.println("No se encontró el registro");
                }
                connectC.commit();

            } catch (SQLException e) {
                connectC.rollback(); // IMPORTANTE: hacer rollback en caso de error
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE idUsuario = ?";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("usuario eliminado correctamente");
            } else {
                System.out.println("No se encontro un el registro con id " + id);
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Usuario> buscarUsuario() {
        String sql = "SELECT * FROM Usuario";
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try ( Connection connectC = conn.conectarDB()) {
            try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    LocalDate fechaModificacion = null;
                    if (rs.getString("fechaModificacion") != null) {
                        fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    }
                    LocalDate fechaEliminacion = null;
                    if (rs.getString("fechaEliminacion") != null) {
                        fechaEliminacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    }
                    Usuario usuario = new Usuario(
                            rs.getInt("idUsuario"),
                            rs.getString("email"),
                            rs.getString("nombreUsuario"),
                            rs.getString("contrasenia"),
                            fechaCreacion,
                            fechaModificacion,
                            fechaEliminacion
                    );
                    listaUsuarios.add(usuario);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
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
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps.setInt(1, id);
                LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                LocalDate fechaModificacion = null;
                if (rs.getString("fechaModificacion") != null) {
                    fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                LocalDate fechaEliminacion = null;
                if (rs.getString("fechaEliminacion") != null) {
                    fechaEliminacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                usuario = new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("email"),
                        rs.getString("nombreUsuario"),
                        rs.getString("contrasenia"),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion
                );
            } else {
                System.out.println("no se encontro el usuario");
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
            try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
                ps.setString(1, nombreUsuario);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    LocalDate fechaModificacion = null;
                    if (rs.getString("fechaModificacion") != null) {
                        fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    }
                    LocalDate fechaEliminacion = null;
                    if (rs.getString("fechaEliminacion") != null) {
                        fechaEliminacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    }
                    usuario = new Usuario(
                            rs.getInt("idUsuario"),
                            rs.getString("email"),
                            rs.getString("nombreUsuario"),
                            rs.getString("contrasenia"),
                            fechaCreacion,
                            fechaModificacion,
                            fechaEliminacion
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

    private static void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }

    private static void configurarBusyTimeout(Connection conn, int timeoutMs) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA busy_timeout = " + timeoutMs + ";")) {
            stmt.execute();
        }
    }
}
