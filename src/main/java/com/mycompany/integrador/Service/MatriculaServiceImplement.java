/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Interface.MatriculaInterface;
import com.mycompany.integrador.Models.Alumno;
import com.mycompany.integrador.Models.Curso;
import com.mycompany.integrador.Models.Matricula;
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
import com.mycompany.integrador.Service.AlumnoServiceImplement;

/**
 *
 * @author exequiel
 */
public class MatriculaServiceImplement implements MatriculaInterface {

    ConexionDB conn = new ConexionDB();
    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();
    AlumnoServiceImplement alumnoServiceImplement = new AlumnoServiceImplement();

    @Override
    public void matricularAlumno(String DNIAlumno) {
        if (cursoServiceImplement.buscarCurso().size() > 0) {
            String sql = "INSERT INTO Matricula (DNIAlumno, fechaCreacion, nombreCurso)  VALUES (?,?,?)";
            Connection connectC = conn.conectarDB();
            try {
                PreparedStatement ps = connectC.prepareStatement(sql);
                ps.setString(1, DNIAlumno);
                DateTimeFormatter formatterEs = DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.SHORT)
                        .withLocale(new Locale("es", "ES"));
                String fechaString = LocalDate.now().format(formatterEs);
                ps.setString(2, fechaString);

                System.out.println("\n seleccine el curso al que desea matriclar el alumno con DNI: " + DNIAlumno);
                System.out.println("///////////////////////////////////////////////////////////////////");
                for (int i = 0; i < cursoServiceImplement.buscarCurso().size(); i++) {
                    System.out.println("Elemento " + i + ": " + cursoServiceImplement.buscarCurso().get(i).toString());
                }
                Scanner sc = new Scanner(System.in);
                int i = sc.nextInt();
                ps.setString(3, cursoServiceImplement.buscarCurso().get(i).getNombreCurso());

                // si el alumno ya esta matriculado en este curso no se puede volver a matricluar
                if (buscarMatriculaCurso(alumnoServiceImplement.buscarAlumno(DNIAlumno), cursoServiceImplement.buscarCurso().get(i)) != null) {
                    System.out.println("el alumno con DNI " + DNIAlumno + " ya esta matriculado en el curso de " + cursoServiceImplement.buscarCurso().get(i).getNombreCurso());
                } else {
                    ps.execute();
                    System.out.println("matricula guardada correctamente");
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            if (cursoServiceImplement.buscarCurso().size() > 0) {
                System.out.println("no hay cursos para asignar");
            }
        }
    }

    @Override
    public void desvincularAlumno(String DNIAlumno, String nombreCurso) {
        String sql = "DELETE FROM Matricula WHERE DNIAlumno = ? AND nombreCurso = ?";
        Connection connect = conn.conectarDB();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, DNIAlumno);
            ps.setString(2, nombreCurso);
            habilitarClavesForaneas(connect);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("alumno desvinculado correctamente");
            } else {
                System.out.println("No se encontro el registro asociado");
            }

        } catch (SQLException e) {
            System.out.println("error al desvincular el alumno");
            System.out.println("ERROR: " + e.toString());
        }
    }

    @Override
    public ArrayList<Matricula> buscarMatriculaCurso() {
        ArrayList<Matricula> listaMatriculas = new ArrayList<>();

        Connection connect = conn.conectarDB();
        String sql = "SELECT * FROM Matricula";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            connect.commit();
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
                Matricula matricula = new Matricula(
                        rs.getInt("idMatricula"),
                        rs.getString("DNIAlumno"),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion,
                        rs.getString("nombreCurso"));
                listaMatriculas.add(matricula);
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return listaMatriculas;
    }

    @Override
    public ArrayList<Matricula> buscarMatriculaCurso(String nombreCurso) {
        ArrayList<Matricula> listaMatriculas = new ArrayList<>();
        Connection connect = conn.conectarDB();
        String sql = "SELECT * FROM Matricula WHERE TRIM(nombreCurso) = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, nombreCurso);
            ResultSet rs = ps.executeQuery();
//            connect.commit();;
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
                Matricula matricula = new Matricula(
                        rs.getInt("idMatricula"),
                        rs.getString("DNIAlumno"),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion,
                        rs.getString("nombreCurso"));
                listaMatriculas.add(matricula);
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return listaMatriculas;
    }

    @Override
    public ArrayList<Matricula> buscarMatriculaCurso(Alumno alumno) {
        ArrayList<Matricula> listaMatriculas = new ArrayList<>();

        Connection connect = conn.conectarDB();
        String sql = "SELECT * FROM Matricula WHERE TRIM(DNIAlumno) = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, alumno.getDNI());
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
                Matricula matricula = new Matricula(
                        rs.getInt("idMatricula"),
                        alumno.getDNI(),
                        fechaCreacion,
                        fechaModificacion,
                        fechaEliminacion,
                        rs.getString("nombreCurso"));
                listaMatriculas.add(matricula);
            }
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return listaMatriculas;
    }

    @Override
    public Matricula buscarMatriculaCurso(Alumno alumno, Curso curso) {
        Connection connect = conn.conectarDB();
        String sql = "SELECT * FROM Matricula WHERE TRIM(DNIAlumno) = ? AND TRIM(nombreCurso) = ?";

        Matricula matricula = null;

        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, alumno.getDNI());
            ps.setString(2, curso.getNombreCurso());

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
            matricula = new Matricula(
                    alumno.getDNI(),
                    fechaCreacion,
                    fechaModificacion,
                    fechaEliminacion,
                    curso.getNombreCurso());

        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return matricula;
    }

    private static void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }
}
