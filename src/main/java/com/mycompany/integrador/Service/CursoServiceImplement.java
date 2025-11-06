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
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class CursoServiceImplement implements CursoService {

    ConexionDB conn = new ConexionDB();
    ProfesorServiceImplement profesorServiceImplement = new ProfesorServiceImplement();
    Scanner sc = new Scanner(System.in);

    @Override
    public void guardarCurso(Curso curso) {
        ArrayList<Profesor> listaProfesores = profesorServiceImplement.buscarProfesor();

        if (listaProfesores.size() > 0) {
            if (existeCurso(curso.getNombreCurso())) {
                System.out.println("ERROR: El curso de " + curso.getNombreCurso() + " ya esta registrado. No se permite la insercion duplicada.");
            } else {
                String sql = "INSERT INTO Curso (nombreCurso, mesesDuracion, fechaCreacion, DNIProfesor)  VALUES (?,?,?,?)";
                Connection connectC = conn.conectarDB();
                try {
                    PreparedStatement ps = connectC.prepareStatement(sql);
                    DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
                    String fechaString = curso.getFechaCreacion().format(formatterEs);

                    ps.setString(1, curso.getNombreCurso());
                    ps.setInt(2, curso.getMesesDuracion());
                    ps.setString(3, fechaString);

                    System.out.println("seleccione el profesor que desea asignara a este curso");
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    for (int j = 0; j < listaProfesores.size(); j++) {
                        System.out.println("///////////////////////////////////////////////////////////////////");
                        System.out.println("|                         Elemento " + j + ":                            |");
                        System.out.println("-------------------------------------------------------------------");
                        System.out.println("| nombre: " + listaProfesores.get(j).getNombres());
                        System.out.println("| apellidos: " + listaProfesores.get(j).getApellidoPaterno() + " " + listaProfesores.get(j).getApellidoMaterno());
                        System.out.println("| DNI: " + listaProfesores.get(j).getDNI());
                        System.out.println("------------------------------------------------------------------");
                    }
                    System.out.print("elemento Nº ");
                    try {
                        int i_profesor = sc.nextInt();
                        String dniProfesor = listaProfesores.get(i_profesor).getDNI();
                        ps.setString(4, dniProfesor);
                        ps.execute();
                        System.out.println("curso guardado correctamente");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("ERROR!!: no existe el registro seleccionado");
                        System.out.println("------------------------------------------------------------------");
                    } catch (InputMismatchException e) {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("ERROR!!: debe ser un numero.");
                        System.out.println("------------------------------------------------------------------");
                    }

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
            DateTimeFormatter formatterEs = DateTimeFormatter.ofPattern("dd/MM/yy");
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
//            connect.commit();

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
                        fechaEliminacion,
                        rs.getString("DNIProfesor")
                );
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
        String sql = "SELECT * FROM Curso WHERE nombreCurso = ?";
        Curso curso = new Curso();
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
                        fechaEliminacion,
                        rs.getString("DNIProfesor")
                );
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

    @Override
    public Curso buscarCurso(String dniProfesor, int estado) {
        Curso curso = new Curso();
        String sql = "SELECT * FROM Curso WHERE DNIProfesor = ?";
        Connection connectC = conn.conectarDB();
        try ( PreparedStatement ps = connectC.prepareStatement(sql)) {
            ps.setString(1, dniProfesor);
            ResultSet rs = ps.executeQuery();

            System.out.println("consulta: " + ps);

            LocalDate fechaModificacion = null;
            if (rs.getString("fechaModificacion") != null) {
                fechaModificacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
            }
            LocalDate fechaEliminacion = null;
            if (rs.getString("fechaEliminacion") != null) {
                fechaEliminacion = LocalDate.parse(rs.getString("fechaModificacion"), DateTimeFormatter.ofPattern("dd/MM/yy"));
            }
            curso = new Curso(
                    rs.getString("nombreCurso"),
                    rs.getInt("mesesDuracion"),
                    LocalDate.parse(rs.getString("fechaCreacion"), DateTimeFormatter.ofPattern("dd/MM/yy")),
                    fechaModificacion,
                    fechaEliminacion,
                    rs.getString("DNIProfesor")
            );
        } catch (SQLException e) {
            System.out.println("error al ejecutar la consulta" + e.getMessage());
            e.printStackTrace();
        }
        return curso;
    }

    private static void habilitarClavesForaneas(Connection conn) throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            stmt.execute();
        }
    }
}
