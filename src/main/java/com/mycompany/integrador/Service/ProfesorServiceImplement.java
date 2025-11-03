/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Enums.TipoPersona;
import com.mycompany.integrador.Interface.ProfesorService;
import com.mycompany.integrador.Models.Profesor;
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
public class ProfesorServiceImplement implements ProfesorService {

    ConexionDB conn = new ConexionDB();

    @Override
    public void guardarProfesor(Profesor profesor) {
        try {
            if (existeDni(profesor.getDNI())) {
                System.out.println("ERROR: El DNI " + profesor.getDNI() + " ya esta registrado. No se permite la insercion duplicada.");
            } else {
                String sqlPersona = "insert into Persona (DNI, nombres, apellidoMaterno, apellidoPaterno, edad, fechaCreacion, tipoPersona) VALUES (?,?,?,?,?,?,?)";
                String sqlProfesor = "insert into Profesor (DNIProfesor, sueldo, presentismo) VALUES (?,?,?)";

                try ( Connection connectC = conn.conectarDB()) {
                    habilitarClavesForaneas(connectC);
                    connectC.setAutoCommit(false);
                    int idGenerado = -1;

                    try ( PreparedStatement psPersona = connectC.prepareStatement(sqlPersona, PreparedStatement.RETURN_GENERATED_KEYS)) {
                        DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
                        String fechaString = profesor.getFechaCreacion().format(formatterEs);

                        psPersona.setString(1, profesor.getDNI());
                        psPersona.setString(2, profesor.getNombres());
                        psPersona.setString(3, profesor.getApellidoMaterno());
                        psPersona.setString(4, profesor.getApellidoPaterno());
                        psPersona.setInt(5, profesor.getEdad());
                        psPersona.setString(6, fechaString);
                        psPersona.setString(7, "PROFESOR");

                        psPersona.executeUpdate();

                        // Obtener el id generado automáticamente
                        try ( var rs = psPersona.getGeneratedKeys()) {
                            if (rs.next()) {
                                idGenerado = rs.getInt(1);
                            }
                        }
                    }
                    if (idGenerado == -1) {
                        throw new SQLException("No se pudo obtener el ID de la persona insertado.");
                    }

                    // insertar el profesor asociado
                    try ( PreparedStatement psProfesor = connectC.prepareStatement(sqlProfesor)) {
                        psProfesor.setString(1, profesor.getDNI());
                        psProfesor.setInt(2, profesor.getSueldo());
                        psProfesor.setInt(3, (profesor.isPresentismo()) ? 0 : 1);
                        psProfesor.executeUpdate();
                    }

                    connectC.commit();
                    System.out.println("Profesor creado correctamente con ID: " + idGenerado);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Error al crear el profesor: " + ex.getMessage());
                    Logger.getLogger(AlumnoServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfesorServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean existeDni(String dni) throws SQLException {
        String sql = "SELECT DNI FROM Persona WHERE DNI = ?";
        Connection connectC = conn.conectarDB();
        PreparedStatement ps = connectC.prepareStatement(sql);
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        return rs.getString("DNI") != null;
    }

    @Override
    public void modificarProfesor(String dni, Profesor profesorModificado) {
        Profesor profesor1 = buscarProfesor(dni);
        if (profesor1 == profesorModificado) {
            System.out.println("no se modificaran datos");
        } else {
            try ( Connection connectC = conn.conectarDB()) {
                String sqlPersona = "UPDATE Persona\n"
                        + "   SET nombres = ?,\n"
                        + "       apellidoMaterno = ?,\n"
                        + "       edad = ?,\n"
                        + "       apellidoPaterno = ?,\n"
                        + "       fechaModificacion = ?\n"
                        + "WHERE DNI = ?";

                try ( PreparedStatement psPersona = connectC.prepareStatement(sqlPersona)) {
                    psPersona.setString(1, profesorModificado.getNombres());
                    psPersona.setString(2, profesorModificado.getApellidoMaterno());
                    psPersona.setInt(3, profesorModificado.getEdad());
                    psPersona.setString(4, profesorModificado.getApellidoPaterno());
                    DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
                    String fechaString = profesorModificado.getFechaModificacion().format(formatterEs);
                    psPersona.setString(5, fechaString);
                    psPersona.setString(6, dni);
                    psPersona.execute();

                } catch (SQLException e) {
                    System.out.println("no se pudo modificar la tabla personas");
                    System.out.println("" + e.toString());
                }

                String sqlProfesor = "UPDATE Profesor\n"
                        + "    SET sueldo  = ?,\n"
                        + "        presentismo  = ?\n"
                        + "WHERE DNIProfesor = ?";

                try ( PreparedStatement psProfesor = connectC.prepareStatement(sqlProfesor)) {
                    psProfesor.setInt(1, profesorModificado.getSueldo());
                    psProfesor.setString(2, String.valueOf(profesorModificado.isPresentismo()));
                    psProfesor.setString(3, dni);
                    psProfesor.execute();
                } catch (SQLException e) {
                    System.out.println("no se pudo modificar la tabla profesor");
                    System.out.println(e.toString());
                }
            } catch (SQLException ex) {
                System.out.println("Error al ejecutar la consulta: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void eliminarProfesor(String dni) {
        String sql = "DELETE FROM Profesor WHERE DNIProfesor = ?";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            habilitarClavesForaneas(connect);
            ps.setString(1, dni);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("profesor eliminado correctamente");
            } else {
                System.out.println("No se encontro un profesor con el DNI " + dni);
            }

        } catch (Exception e) {
            System.out.println("error al eliminar el profesor");
            System.out.println("ERROR: " + e.toString());
        }
    }

    @Override
    public ArrayList<Profesor> buscarProfesor() {
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
        Connection connectC = conn.conectarDB();

        String sql = "SELECT p.DNI, \n"
                + "	p.nombres, \n"
                + "	p.apellidoMaterno, \n"
                + "	p.edad, \n"
                + "	p.apellidoPaterno, \n"
                + "	p.fechaCreacion, \n"
                + "	p.fechaModificacion, \n"
                + "	p.fechaEliminacion, \n"
                + "	p.tipoPersona, \n"
                + "	pr.sueldo,\n"
                + "	pr.presentismo\n"
                + "FROM Persona p\n"
                + "INNER JOIN Profesor pr ON p.DNI = pr.DNIProfesor \n";

        try ( PreparedStatement psProfesor = connectC.prepareStatement(sql)) {
            ResultSet rs = psProfesor.executeQuery();
            while (rs.next()) {
                LocalDate fechaModificacion = null;
                if (rs.getString("fechaModificacion") != null) {
                    fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                LocalDate fechaEliminacion = null;
                if (rs.getString("fechaEliminacion") != null) {
                    fechaEliminacion = LocalDate.parse(rs.getString("fechaEliminacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                Profesor profesor = new Profesor(
                        rs.getInt("sueldo"),
                        Boolean.parseBoolean(rs.getString("presentismo")),
                        rs.getString("DNI"),
                        rs.getString("nombres"),
                        rs.getString("apellidoMaterno"),
                        rs.getString("apellidoPaterno"),
                        rs.getInt("edad"),
                        LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy")),
                        fechaModificacion,
                        fechaEliminacion,
                        TipoPersona.PROFESOR);
                listaProfesores.add(profesor);
            }

        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return listaProfesores;
    }

    @Override
    public Profesor buscarProfesor(String dni) {
        String sql = "SELECT p.DNI, \n"
                + "	p.nombres, \n"
                + "	p.apellidoMaterno, \n"
                + "	p.edad, \n"
                + "	p.apellidoPaterno, \n"
                + "	p.fechaCreacion, \n"
                + "	p.fechaModificacion, \n"
                + "	p.fechaEliminacion, \n"
                + "	p.tipoPersona, \n"
                + "	pr.sueldo,\n"
                + "	pr.presentismo\n"
                + "FROM Persona p\n"
                + "INNER JOIN Profesor pr ON p.DNI = pr.DNIProfesor \n"
                + "WHERE p.DNI = ? ";
        Profesor profesor = new Profesor();

        try ( Connection connectC = conn.conectarDB()) {
            try ( PreparedStatement psProfesor = connectC.prepareStatement(sql)) {
                psProfesor.setString(1, dni);
                ResultSet rs = psProfesor.executeQuery();
                while (rs.next()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                    LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), formatter);
                    profesor = new Profesor(
                            rs.getInt("sueldo"),
                            Boolean.parseBoolean(rs.getString("presentismo")),
                            rs.getString("DNI"),
                            rs.getString("nombres"),
                            rs.getString("apellidoMaterno"),
                            rs.getString("apellidoPaterno"),
                            rs.getInt("edad"),
                            fechaCreacion,
                            TipoPersona.PROFESOR);
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta: " + ex.getMessage());
            ex.printStackTrace();
        }
        return profesor;
    }

    private static void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }

}
