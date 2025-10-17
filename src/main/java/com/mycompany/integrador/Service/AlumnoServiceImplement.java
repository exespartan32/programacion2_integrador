/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Interface.AlumnoService;
import com.mycompany.integrador.Models.Alumno;
import java.util.ArrayList;
import java.util.Optional;
import com.mycompany.integrador.Configurations.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try {
            if (existeDni(alumno.getDNI())) {
                System.out.println("ERROR: El DNI " + alumno.getDNI() + " ya esta registrado. No se permite la insercion duplicada.");
            } else {

                String sqlPersona = "insert into Persona (DNI, nombres, apellidoMaterno, apellidoPaterno, edad, fechaCreacion, tipoPersona) VALUES (?,?,?,?,?,?,?)";
                String sqlAlumno = "insert into Alumno (DNIAlumno, anioIngreso, mesIngreso) VALUES (?,?,?)";

                //Connection connect = conn.conectarDB();
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
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean existeDni(String dni) throws SQLException {
        String sql = "SELECT DNI FROM Persona WHERE DNI = ?";
        Connection connectC = conn.conectarDB();
        PreparedStatement psAlumno = connectC.prepareStatement(sql);
        psAlumno.setString(1, dni);
        ResultSet rs = psAlumno.executeQuery();
        return rs.getString("DNI") != null;
    }

    @Override
    public void modificarALumno(Long id, Alumno alumno) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrarAlumno(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Alumno> buscarTodosLosAlumnos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Alumno> buscarAlumno(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }

}
