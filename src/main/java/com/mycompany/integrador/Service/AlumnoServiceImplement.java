/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Interface.AlumnoService;
import com.mycompany.integrador.Models.Alumno;
import java.util.ArrayList;
import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Enums.TipoPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author exequiel
 */
public class AlumnoServiceImplement implements AlumnoService {

    ConexionDB conn = new ConexionDB();

    @Override
    public void guardarAlumno(Alumno alumno) {
        if (existeDni(alumno.getDNI())) {
            System.out.println("ERROR: El DNI " + alumno.getDNI() + " ya esta registrado. No se permite la insercion duplicada.");
        } else {
            String sqlPersona = "insert into Persona (DNI, nombres, apellidoMaterno, apellidoPaterno, edad, fechaCreacion, tipoPersona) VALUES (?,?,?,?,?,?,?)";
            String sqlAlumno = "insert into Alumno (DNIAlumno, anioIngreso, mesIngreso) VALUES (?,?,?)";
            try ( Connection connectC = conn.conectarDB()) {
                habilitarClavesForaneas(connectC);
                connectC.setAutoCommit(false);
                int idGenerado = -1;

                // Insertar la persona y obtener el id generado
                try ( PreparedStatement psPersona = connectC.prepareStatement(sqlPersona, PreparedStatement.RETURN_GENERATED_KEYS)) {

                    DateTimeFormatter formatterEs = DateTimeFormatter
                            .ofLocalizedDate(FormatStyle.SHORT)
                            .withLocale(new Locale("es", "ES"));
                    String fechaString = alumno.getFechaCreacion().format(formatterEs);

                    psPersona.setString(1, alumno.getDNI());
                    psPersona.setString(2, alumno.getNombres());
                    psPersona.setString(3, alumno.getApellidoMaterno());
                    psPersona.setString(4, alumno.getApellidoPaterno());
                    psPersona.setInt(5, alumno.getEdad());
                    psPersona.setString(6, fechaString);
                    psPersona.setString(7, "ALUMNO");

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

                // insertar el alumno asociado
                try ( PreparedStatement psAlumno = connectC.prepareStatement(sqlAlumno)) {
                    psAlumno.setString(1, alumno.getDNI());
                    psAlumno.setString(2, alumno.getAnioIngreso());
                    psAlumno.setString(3, alumno.getMesIngreso());
                    psAlumno.executeUpdate();
                }

                connectC.commit();
                System.out.println("Alumno creado correctamente con ID: " + idGenerado);

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Error al crear el alumno: " + ex.getMessage());
                Logger.getLogger(AlumnoServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean existeDni(String dni) {
        String sql = "SELECT DNI FROM Persona WHERE DNI = ?";
        Connection connectC = conn.conectarDB();
        boolean existe = false;
        try {
            PreparedStatement psAlumno = connectC.prepareStatement(sql);
            psAlumno.setString(1, dni);
            ResultSet rs = psAlumno.executeQuery();
            existe = rs.getString("DNI") != null;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return existe;
    }

    @Override
    public void modificarALumno(String dni, Alumno alumnoModificado) {
        Alumno alumnoDB = this.buscarAlumno(dni);
        if (alumnoDB == alumnoModificado) {
            System.out.println("no se modificaron datos");
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
                    psPersona.setString(1, alumnoModificado.getNombres());
                    psPersona.setString(2, alumnoModificado.getApellidoMaterno());
                    psPersona.setInt(3, alumnoModificado.getEdad());
                    psPersona.setString(4, alumnoModificado.getApellidoPaterno());
                    DateTimeFormatter formatterEs = DateTimeFormatter
                            .ofLocalizedDate(FormatStyle.SHORT)
                            .withLocale(new Locale("es", "ES"));
                    String fechaString = alumnoModificado.getFechaModificacion().format(formatterEs);
                    psPersona.setString(5, fechaString);
                    psPersona.setString(6, dni);
                    psPersona.execute();
                } catch (SQLException e) {
                    System.out.println("no se pudo modificar la tabla personas");
                    System.out.println("" + e.toString());
                }
                String sqlAlumno = "UPDATE Alumno\n"
                        + "    SET anioIngreso  = ?,\n"
                        + "        mesIngreso  = ?\n"
                        + "WHERE DNIAlumno = ?";
                try ( PreparedStatement psAlumno = connectC.prepareStatement(sqlAlumno)) {
                    psAlumno.setString(1, alumnoModificado.getAnioIngreso());
                    psAlumno.setString(2, alumnoModificado.getMesIngreso());
                    psAlumno.setString(3, dni);
                    psAlumno.execute();
                } catch (SQLException e) {
                    System.out.println("no se pudo modificar la tabla alumnos");
                    System.out.println("" + e.toString());
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnoServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void borrarAlumno(String dni) {
        String sql = "DELETE FROM Persona WHERE DNI = ?";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            habilitarClavesForaneas(connect);
            ps.setString(1, dni);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("alumno eliminado correctamente");
            } else {
                System.out.println("No se encontro un alumno con el DNI " + dni);
            }
        } catch (SQLException e) {
            System.out.println("error al eliminar el contacto");
            System.out.println("ERROR: " + e.toString());
        }
    }

    @Override
    public ArrayList<Alumno> buscarAlumno() {
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        try ( Connection connectC = conn.conectarDB()) {
            String sqlAlumno = "SELECT p.DNI, \n"
                    + "	p.nombres, \n"
                    + "	p.apellidoMaterno, \n"
                    + "	p.edad, \n"
                    + "	p.apellidoPaterno, \n"
                    + "	p.fechaCreacion, \n"
                    + "	p.fechaModificacion, \n"
                    + "	p.fechaEliminacion, \n"
                    + "	p.tipoPersona, \n"
                    + "	a.anioIngreso,\n"
                    + "	a.mesIngreso\n"
                    + "FROM Persona p\n"
                    + "INNER JOIN Alumno a ON p.DNI = a.DNIAlumno \n";

            try ( PreparedStatement psAlumno = connectC.prepareStatement(sqlAlumno)) {
                ResultSet rs = psAlumno.executeQuery();
                connectC.commit();
                while (rs.next()) {
                    LocalDate fechaModificacion = null;
                    if (rs.getString("fechaModificacion") != null) {
                        fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    }
                    LocalDate fechaEliminacion = null;
                    if (rs.getString("fechaEliminacion") != null) {
                        fechaEliminacion = LocalDate.parse(rs.getString("fechaEliminacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    }
                    Alumno alumno = new Alumno(
                            rs.getString("anioIngreso"),
                            rs.getString("mesIngreso"),
                            rs.getString("DNI"),
                            rs.getString("nombres"),
                            rs.getString("apellidoMaterno"),
                            rs.getString("apellidoPaterno"),
                            rs.getInt("edad"),
                            LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy")),
                            fechaModificacion,
                            fechaEliminacion,
                            TipoPersona.ALUMNO);
                    listaAlumnos.add(alumno);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnoServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAlumnos;
    }

    @Override
    public Alumno buscarAlumno(String dni) {
        String sqlAlumno = "SELECT p.DNI, \n"
                + "	p.nombres, \n"
                + "	p.apellidoMaterno, \n"
                + "	p.edad, \n"
                + "	p.apellidoPaterno, \n"
                + "	p.fechaCreacion, \n"
                + "	p.fechaModificacion, \n"
                + "	p.fechaEliminacion, \n"
                + "	p.tipoPersona, \n"
                + "	a.anioIngreso,\n"
                + "	a.mesIngreso\n"
                + "FROM Persona p\n"
                + "INNER JOIN Alumno a ON p.DNI = a.DNIAlumno \n"
                + "WHERE p.DNI = ? ";

        Alumno alumno = new Alumno();
        try ( Connection connectC = conn.conectarDB()) {
            try ( PreparedStatement psAlumno = connectC.prepareStatement(sqlAlumno)) {
                psAlumno.setString(1, dni);
                ResultSet rs = psAlumno.executeQuery();
                while (rs.next()) {
                    LocalDate fechaModificacion = null;
                    if (rs.getString("fechaModificacion") != null) {
                        fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    }
                    LocalDate fechaEliminacion = null;
                    if (rs.getString("fechaEliminacion") != null) {
                        fechaEliminacion = LocalDate.parse(rs.getString("fechaEliminacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                    }
                    alumno = new Alumno(
                            rs.getString("anioIngreso"),
                            rs.getString("mesIngreso"),
                            rs.getString("DNI"),
                            rs.getString("nombres"),
                            rs.getString("apellidoMaterno"),
                            rs.getString("apellidoPaterno"),
                            rs.getInt("edad"),
                            LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy")),
                            fechaModificacion,
                            fechaEliminacion,
                            TipoPersona.ALUMNO);
                }
            } catch (Exception e) {
                System.out.println("ERROR " + e.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumno;
    }

    private static void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }

}
