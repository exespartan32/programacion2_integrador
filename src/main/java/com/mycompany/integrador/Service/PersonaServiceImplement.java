/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Enums.TipoPersona;
import com.mycompany.integrador.Interface.PersonaService;
import com.mycompany.integrador.Models.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author exequiel
 */
public class PersonaServiceImplement implements PersonaService {

    ConexionDB conn = new ConexionDB();

    @Override
    public void guardarPersona(Persona persona) {
        String sql = "INSERT INTO Persona (DNI, nombres, apellidoMaterno, edad, apellidoPaterno, fechaCreacion, tipoPersona) values (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.conectarDB().prepareStatement(sql);
            ps.setString(1, persona.getDNI());
            ps.setString(2, persona.getNombres());
            ps.setString(3, persona.getApellidoMaterno());
            ps.setString(4, String.valueOf(persona.getEdad()));
            ps.setString(5, persona.getApellidoPaterno());
            ps.setString(6, persona.getFechaCreacion().toString());
            ps.setString(7, persona.getTipoPersona().toString());

            ps.execute();
            System.out.println("datos guardados correctamente");

        } catch (SQLException ex) {
            System.out.println("ERROR! " + ex.toString());
        } catch (Exception e) {
            System.out.println("erro al guardar en base de datos");
        }
    }

    @Override
    public void modificarPersona(Long id, Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPersona(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Persona> buscarTodasLasPersonas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Persona buscarPersona(String dni) {
        String sql = "SELECT * FROM Persona WHERE DNI = (?)";

        try {
            PreparedStatement ps = conn.conectarDB().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Persona persona = new Persona(rs.getString("DNI"), rs.getString("nombres"), rs.getString("apellidoMaterno"), rs.getString("apellidoPaterno"), rs.getInt("edad"), rs.getDate("fechaCreacion").toLocalDate(), TipoPersona.valueOf(rs.getString("tipoPersona")));
                return persona;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("EEOR " + e.toString());
            return null;
        }
    }
}
