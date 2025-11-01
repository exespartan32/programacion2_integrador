/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Interface.ValorCursoService;
import com.mycompany.integrador.Models.Curso;
import com.mycompany.integrador.Models.ValorCurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ValorCursoServiceImplement implements ValorCursoService {

    ConexionDB conn = new ConexionDB();

    @Override
    public void asignarPrecio(ValorCurso valorCurso) {
        if (buscarValorCursos(valorCurso.getNombreCurso()) == null) {
            String sql = "INSERT INTO ValorCurso (nombreCurso, precioCurso, fechaCreacion)  VALUES (?,?,?)";
            Connection connectC = conn.conectarDB();
            try {
                PreparedStatement ps = connectC.prepareStatement(sql);
                ps.setString(1, valorCurso.getNombreCurso());
                ps.setInt(2, valorCurso.getPrecioCurso());

                DateTimeFormatter formatterEs = DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.SHORT)
                        .withLocale(new Locale("es", "ES"));
                String fechaString = LocalDate.now().format(formatterEs);
                ps.setString(3, fechaString);
                ps.execute();

            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("este curso ya tiene un precio asignado");
        }
    }

    @Override
    public void modificarPrecio(int id, ValorCurso valorCurso) {
        if (buscarValorCursos(id) != null) {
            String sql = "UPDATE ValorCurso\n"
                    + "   SET precioCurso = ?,\n"
                    + "       fechaModificacion = ?\n"
                    + "WHERE idValorCurso = ?";
            Connection connectC = conn.conectarDB();

            try {
                PreparedStatement ps = connectC.prepareStatement(sql);

                DateTimeFormatter formatterEs = DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.SHORT)
                        .withLocale(new Locale("es", "ES"));
                String fechaString = valorCurso.getFechaModificacion().format(formatterEs);

                ps.setInt(1, valorCurso.getPrecioCurso());
                ps.setString(2, fechaString);
                ps.setInt(3, id);

                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("precio actualizado correctamente");
                } else {
                    System.out.println("No se encontro un precio del curso de " + valorCurso.getNombreCurso());
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
    public void eliminarPrecio(int id) {
        if (buscarValorCursos(id) != null) {
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
    public ArrayList<ValorCurso> buscarValorCursos() {
        ArrayList<ValorCurso> listaPrecios = new ArrayList<>();
        String sql = "SELECT * FROM valorCurso";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //connect.commit();;
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
                ValorCurso valorCurso = new ValorCurso(
                        rs.getInt("idValorCurso"),
                        rs.getString("nombreCurso"),
                        rs.getInt("precioCurso"),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion);
                listaPrecios.add(valorCurso);
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return listaPrecios;
    }

    @Override
    public ValorCurso buscarValorCursos(String nombreCurso) {
        String sql = "SELECT * FROM valorCurso WHERE nombreCurso = ?";
        ValorCurso valorCurso = new ValorCurso();
        try ( Connection connect = conn.conectarDB()) {
            habilitarClavesForaneas(connect);
            connect.setAutoCommit(false);
            try {
                PreparedStatement ps = connect.prepareStatement(sql);
                ps.setString(1, nombreCurso);
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
                    valorCurso = new ValorCurso(
                            rs.getInt("idValorCurso"),
                            nombreCurso,
                            rs.getInt("precioCurso"),
                            fechaCreacion,
                            fechaModificacion,
                            fechaEliminacion);
                }
                connect.commit();
            } catch (SQLException e) {
                System.out.println("error al ejecutar la consulta" + e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ValorCursoServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valorCurso;
    }

    @Override
    public ValorCurso buscarValorCursos(int id) {
        ValorCurso valorCurso = new ValorCurso();
        String sql = "SELECT * FROM valorCurso WHERE idValorCurso = ?";
        try ( Connection connect = conn.conectarDB()) {
            habilitarClavesForaneas(connect);
            connect.setAutoCommit(false);
            try {
                PreparedStatement ps = connect.prepareStatement(sql);
                ps.setInt(1, id);
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
                    valorCurso = new ValorCurso(
                            id,
                            rs.getString("nombreCurso"),
                            rs.getInt("precioCurso"),
                            fechaCreacion,
                            fechaModificacion,
                            fechaEliminacion);
                    connect.commit();
                }
            } catch (SQLException e) {
                System.out.println("error al ejecutar la consulta" + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValorCursoServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valorCurso;
    }

    private static void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }

}
