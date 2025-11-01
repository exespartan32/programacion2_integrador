/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Interface.CursoService;
import com.mycompany.integrador.Models.Curso;
import com.mycompany.integrador.Models.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class CursoServiceImplement implements CursoService {

    ConexionDB conn = new ConexionDB();
    ProfesorServiceImplement profesorServiceImplement = new ProfesorServiceImplement();

    @Override
    public void guardarCurso(Curso curso) {
        ArrayList<Profesor> litaProfesores = profesorServiceImplement.buscarProfesor();

        if (litaProfesores.size() > 0) {
            if (existeCurso(curso.getNombreCurso())) {
                System.out.println("ERROR: El curso de " + curso.getNombreCurso() + " ya esta registrado. No se permite la insercion duplicada.");
            } else {
                String sql = "INSERT INTO Curso (nombreCurso, mesesDuracion, fechaCreacion, DNIProfesor)  VALUES (?,?,?,?)";
                Connection connectC = conn.conectarDB();
                try {
                    PreparedStatement ps = connectC.prepareStatement(sql);
                    DateTimeFormatter formatterEs = DateTimeFormatter
                            .ofLocalizedDate(FormatStyle.SHORT)
                            .withLocale(new Locale("es", "ES"));
                    String fechaString = curso.getFechaCreacion().format(formatterEs);

                    ps.setString(1, curso.getNombreCurso());
                    ps.setInt(2, curso.getMesesDuracion());
                    ps.setString(3, fechaString);

                    System.out.println("seleccine el profesor que asignara a este curso");
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    for (int i = 0; i < litaProfesores.size(); i++) {
                        System.out.println("Elemento " + i + ": " + litaProfesores.get(i).toString());
                    }
                    Scanner sc = new Scanner(System.in);
                    int i = sc.nextInt();
                    ps.setString(4, litaProfesores.get(i).getDNI());

                    ps.execute();

                    System.out.println("curso guardado correctamente");

                } catch (SQLException e) {
                    System.out.println("Error al ejecutar la consulta: " + e.getMessage());
                    e.printStackTrace();
                }
            }

        } else {
            System.out.println("no hay ningun profesor disponible para dar este curso");
        }

    }

    @Override
    public void modificarCurso(String nombreCurso, Curso curso) {
        String sql = "UPDATE Curso\n"
                + "   SET nombreCurso = ?,\n"
                + "       mesesDuracion = ?,\n"
                + "       fechaModificacion = ?\n"
                + "WHERE nombreCurso = ?";
        Connection connectC = conn.conectarDB();

        try {
            PreparedStatement ps = connectC.prepareStatement(sql);
            ps.setString(1, curso.getNombreCurso());
            ps.setInt(2, curso.getMesesDuracion());
            DateTimeFormatter formatterEs = DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.SHORT)
                    .withLocale(new Locale("es", "ES"));
            String fechaString = curso.getFechaModificacion().format(formatterEs);
            ps.setString(3, fechaString);
            ps.setString(4, nombreCurso);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("curso actualizado correctamente");
            } else {
                System.out.println("No se encontro un curso con el nombre " + nombreCurso);
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCurso(String nombreCurso) {
        String sql = "DELETE FROM Curso WHERE nombreCurso = ?";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, nombreCurso);

            int filasAfectadas = ps.executeUpdate();
            connect.commit();
            
            if (filasAfectadas > 0) {
                System.out.println("curso eliminado correctamente");
            } else {
                System.out.println("No se encontro un curso con el nombre " + nombreCurso);
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Curso> buscarCurso() {
        ArrayList<Curso> listaCursos = new ArrayList<>();
        Connection connect = conn.conectarDB();
        String sql = "SELECT * FROM Curso";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
//            connect.commit();;
            while (rs.next()) {
                LocalDate fechaModificacion = null;
                if (rs.getString("fechaModificacion") != null) {
                    fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                LocalDate fechaEliminacion = null;
                if (rs.getString("fechaEliminacion") != null) {
                    fechaEliminacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                Curso curso = new Curso(
                        rs.getString("nombreCurso"),
                        rs.getInt("mesesDuracion"),
                        LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy")),
                        fechaModificacion,
                        fechaEliminacion);
                listaCursos.add(curso);
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return listaCursos;
    }

    @Override
    public Curso buscarCurso(String nombreCurso) {
        Connection connect = conn.conectarDB();
        String sql = "SELECT * FROM Curso WHERE = ?";
        Curso curso = null;
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, nombreCurso);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate fechaModificacion = null;
                if (rs.getString("fechaModificacion") != null) {
                    fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                LocalDate fechaEliminacion = null;
                if (rs.getString("fechaEliminacion") != null) {
                    fechaEliminacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
                }
                curso = new Curso(
                        nombreCurso,
                        rs.getInt("mesesDuracion"),
                        LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy")),
                        fechaModificacion,
                        fechaEliminacion);
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return curso;
    }

    @Override
    public boolean existeCurso(String nombreCurso) {
        String sql = "SELECT nombreCurso FROM Curso WHERE nombreCurso = ?";
        boolean existe = false;
        Connection connectC = conn.conectarDB();
        try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
            ps.setString(1, nombreCurso);
            ResultSet rs = ps.executeQuery();
            existe = rs.getString("nombreCurso") != null;
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return existe;
    }

    private static void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }
}
