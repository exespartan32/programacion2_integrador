/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Interface.HistorialCuentaService;
import com.mycompany.integrador.Models.HistorialDeCuentas;
import com.mycompany.integrador.Models.ValorCurso;
import com.mycompany.integrador.Service.ValorCursoServiceImplement;
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
public class HistorialCuentaServiceImplement implements HistorialCuentaService {

    ConexionDB conn = new ConexionDB();
    ValorCursoServiceImplement valorCursoServiceImplement = new ValorCursoServiceImplement();

    @Override
    public void pagarCurso(HistorialDeCuentas hdc) {
        String sql = "insert into HistorialDeCuentas (DNIAlumno, nombreCurso, pagoAlumno, saldoAlumno, descripcion, fechaCreacion, pagado) values (?, ?, ?, ?, ?, ?, ?)";
        //Connection connectC = conn.conectarDB();

        try ( Connection connectC = conn.conectarDB()) {
            habilitarClavesForaneas(connectC);
            connectC.setAutoCommit(false);
            try {

                DateTimeFormatter formatterEs = DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.SHORT)
                        .withLocale(new Locale("es", "ES"));
                String fechaString = hdc.getFechaCreacion().format(formatterEs);

                PreparedStatement ps = connectC.prepareStatement(sql);

                System.out.println("dni alumno: " + hdc.getNombreCurso());
                System.out.println("nombre curso: " + hdc.getNombreCurso());
                System.out.println("pago: " + hdc.getPago());
                System.out.println("saldo: " + hdc.getSaldo());
                System.out.println("descripcion: " + hdc.getDescripcionPago());
                System.out.println("fecha creacion: " + fechaString);

                ps.setString(1, hdc.getDniAlumno());
                ps.setString(2, hdc.getNombreCurso());
                ps.setInt(3, hdc.getPago());
                ps.setInt(4, hdc.getSaldo());
                ps.setString(5, hdc.getDescripcionPago());
                ps.setString(6, fechaString);
                int pagado = (hdc.isPagado()) ? 1 : 0;
                ps.setInt(7, pagado);

                //ps.execute();

                connectC.commit();
                System.out.println("historial creado correcamente");

            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistorialCuentaServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<HistorialDeCuentas> buscarPago() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HistorialDeCuentas buscarPago(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HistorialDeCuentas buscarPago(String DNIAlumno) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int verSaldo(String DNIAlumno) {
        int saldo = 0;
        String sql = "SELECT * FROM HistorialDeCuentas WHERE DNIAlumno = ? ORDER BY idHistorialCuenta DESC LIMIT 1";
        // Usa try-with-resources para asegurar el cierre de los recursos
        try ( Connection connect = conn.conectarDB();  PreparedStatement ps = connect.prepareStatement(sql);) {

            ps.setString(1, DNIAlumno);

            try ( ResultSet rs = ps.executeQuery()) { // Usa try-with-resources para el ResultSet también
                if (rs.next()) { // Siempre verifica si hay resultados
                    saldo = rs.getInt("saldoAlumno");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return saldo;
    }

    @Override
    public HistorialDeCuentas buscarPago(String nombreCurso, boolean pago) {
        HistorialDeCuentas historialDeCuentas = null;
        String sql = "SELECT * FROM HistorialDeCuentas WHERE nombreCurso = ? ORDER BY idHistorialCuenta DESC LIMIT 1";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, nombreCurso);
            ResultSet rs = ps.executeQuery();

            LocalDate fechaCreacion = LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
            LocalDate fechaModificacion = null;
            if (rs.getString("fechaModificacion") != null) {
                fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
            }
            LocalDate fechaEliminacion = null;
            if (rs.getString("fechaEliminacion") != null) {
                fechaEliminacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
            }
            boolean pagado = rs.getInt("pagado") == 1;

            ValorCurso valorCurso = valorCursoServiceImplement.buscarValorCursos(nombreCurso);
            int precioCurso = valorCurso.getPrecioCurso();

            historialDeCuentas = new HistorialDeCuentas(
                    rs.getInt("idHistorialCuenta"),
                    rs.getString("dniAlumno"),
                    nombreCurso,
                    fechaCreacion,
                    fechaModificacion,
                    fechaEliminacion,
                    pagado,
                    precioCurso,
                    rs.getInt("pago"),
                    rs.getInt("saldoAlumno"),
                    rs.getString("descripcionPago"));

        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return historialDeCuentas;
    }

    public boolean cursoPagado(String nombreCurso, String dniAlumno) {
        boolean pagado = false;
        String sql = "SELECT * FROM HistorialDeCuentas WHERE nombreCurso = ? AND DNIAlumno = ? ORDER BY idHistorialCuenta DESC LIMIT 1";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, nombreCurso);
            ps.setString(2, dniAlumno);
            ResultSet rs = ps.executeQuery();
            //connect.commit();
            pagado = (rs.getInt("pagado")) == 1;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return pagado;
    }

    private static void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }
}
