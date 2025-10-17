/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Interface.ProfesorService;
import com.mycompany.integrador.Models.Profesor;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
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
                        DateTimeFormatter formatterEs = DateTimeFormatter
                                .ofLocalizedDate(FormatStyle.SHORT)
                                .withLocale(new Locale("es", "ES"));
                        String fechaString = profesor.getFechaCreacion().format(formatterEs);

                        psPersona.setString(1, profesor.getDNI());
                        psPersona.setString(2, profesor.getNombres());
                        psPersona.setString(3, profesor.getApellidoMaterno());
                        psPersona.setString(4, profesor.getApellidoPaterno());
                        psPersona.setInt(5, profesor.getEdad());
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
    public void modificarProfesor(Long id, Profesor profesor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarProfesor(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Profesor> buscarTodasLosProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Profesor> buscarProfesor(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }

}
