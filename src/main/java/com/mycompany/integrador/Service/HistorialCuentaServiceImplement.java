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
        String sql = "insert into HistorialDeCuentas (DNIAlumno, nombreCurso, pagoAlumno, saldoAlumno, descripcionPago, fechaCreacion, pagado) values (?, ?, ?, ?, ?, ?, ?)";
        try ( Connection connectC = conn.conectarDB()) {
            habilitarClavesForaneas(connectC);
            connectC.setAutoCommit(false);
            try {
                DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
                String fechaString = hdc.getFechaCreacion().format(formatterEs);

                PreparedStatement ps = connectC.prepareStatement(sql);

                ps.setString(1, hdc.getDniAlumno());
                ps.setString(2, hdc.getNombreCurso());
                ps.setInt(3, hdc.getPago());
                ps.setInt(4, hdc.getSaldo());
                ps.setString(5, hdc.getDescripcionPago());
                ps.setString(6, fechaString);
                int pagado = (hdc.isPagado()) ? 1 : 0;
                ps.setInt(7, pagado);

                ps.execute();
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
        String sql = "SELECT * FROM HistorialDeCuentas";
        ArrayList<HistorialDeCuentas> listaPagos = new ArrayList<>();
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
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
                boolean pagado = rs.getInt("pagado") == 1;

                ValorCurso valorCurso = valorCursoServiceImplement.buscarValorCursos(rs.getString("nombreCurso"));
                int precioCurso = valorCurso.getPrecioCurso();

                HistorialDeCuentas historialDeCuentas = new HistorialDeCuentas(
                        rs.getInt("idHistorialCuenta"),
                        rs.getString("DNIAlumno"),
                        rs.getString("nombreCurso"),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion,
                        pagado,
                        precioCurso,
                        rs.getInt("pagoAlumno"),
                        rs.getInt("saldoAlumno"),
                        rs.getString("descripcionPago"));

                listaPagos.add(historialDeCuentas);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return listaPagos;
    }

    @Override
    public HistorialDeCuentas buscarPago(int id) {
        HistorialDeCuentas historialDeCuentas = new HistorialDeCuentas();
        String sql = "SELECT * FROM HistorialDeCuentas WHERE idHistorialCuenta = ? ORDER BY idHistorialCuenta DESC LIMIT 1";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
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
                boolean pagado = rs.getInt("pagado") == 1;

                ValorCurso valorCurso = valorCursoServiceImplement.buscarValorCursos(rs.getString("nombreCurso"));
                int precioCurso = valorCurso.getPrecioCurso();

                historialDeCuentas = new HistorialDeCuentas(
                        id,
                        rs.getString("DNIAlumno"),
                        rs.getString("nombreCurso"),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion,
                        pagado,
                        precioCurso,
                        rs.getInt("pagoAlumno"),
                        rs.getInt("saldoAlumno"),
                        rs.getString("descripcionPago"));
            } else {
                System.out.println("no se encontro ningun pago con este id");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return historialDeCuentas;
    }

    @Override
    public ArrayList<HistorialDeCuentas> buscarPago(String DNIAlumno) {
        ArrayList<HistorialDeCuentas> listaPagos = new ArrayList<>();
        String sql = "SELECT * FROM HistorialDeCuentas WHERE DNIAlumno = ? ORDER BY idHistorialCuenta";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, DNIAlumno);
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
                boolean pagado = rs.getInt("pagado") == 1;

                ValorCurso valorCurso = valorCursoServiceImplement.buscarValorCursos(rs.getString("nombreCurso"));
                int precioCurso = valorCurso.getPrecioCurso();

                HistorialDeCuentas historialDeCuentas = new HistorialDeCuentas(
                        rs.getInt("idHistorialCuenta"),
                        DNIAlumno,
                        rs.getString("nombreCurso"),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion,
                        pagado,
                        precioCurso,
                        rs.getInt("pagoAlumno"),
                        rs.getInt("saldoAlumno"),
                        rs.getString("descripcionPago"));
                listaPagos.add(historialDeCuentas);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return listaPagos;
    }

    @Override
    public int verSaldo(String DNIAlumno) {
        int saldo = 0;
        String sql = "SELECT * FROM HistorialDeCuentas WHERE DNIAlumno = ? ORDER BY idHistorialCuenta DESC LIMIT 1";
        // Usa try-with-resources para asegurar el cierre de los recursos
        try ( Connection connect = conn.conectarDB()) {
            PreparedStatement ps = connect.prepareStatement(sql);
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
    public ArrayList<HistorialDeCuentas> buscarPago(String nombreCurso, boolean pago) {
        ArrayList<HistorialDeCuentas> listaPagos = new ArrayList<>();
        String sql = "SELECT * FROM HistorialDeCuentas WHERE nombreCurso = ? ORDER BY idHistorialCuenta DESC LIMIT 1";
        Connection connect = conn.conectarDB();
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
                boolean pagado = rs.getInt("pagado") == 1;

                ValorCurso valorCurso = valorCursoServiceImplement.buscarValorCursos(nombreCurso);
                int precioCurso = valorCurso.getPrecioCurso();

                HistorialDeCuentas historialDeCuentas = new HistorialDeCuentas(
                        rs.getInt("idHistorialCuenta"),
                        rs.getString("dniAlumno"),
                        nombreCurso,
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion,
                        pagado,
                        precioCurso,
                        rs.getInt("pagoAlumno"),
                        rs.getInt("saldoAlumno"),
                        rs.getString("descripcionPago"));
                listaPagos.add(historialDeCuentas);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return listaPagos;
    }

    public boolean cursoPagado(String nombreCurso, String dniAlumno) {
        boolean pagado = false;
        String sql = "SELECT * FROM HistorialDeCuentas WHERE nombreCurso = ? AND DNIAlumno = ? ORDER BY idHistorialCuenta DESC LIMIT 1";
        try ( Connection connect = conn.conectarDB();  PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, nombreCurso);
            ps.setString(2, dniAlumno);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pagado = (rs.getInt("pagado")) == 1;
                }
            }
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
