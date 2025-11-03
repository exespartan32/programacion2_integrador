/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.integrador;

import com.mycompany.integrador.Configurations.ConexionDB;
import com.mycompany.integrador.Controllers.Alumno_Controller;
import com.mycompany.integrador.Controllers.Profesor_Controller;
import com.mycompany.integrador.Controllers.Usuario_Controller;
import com.mycompany.integrador.Controllers.Curso_Controller;
import com.mycompany.integrador.Controllers.Matricula_Controller;
import com.mycompany.integrador.Controllers.ValorCurso_Controller;
import com.mycompany.integrador.Controllers.HistorialCuenta_Controller;

/**
 *
 * @author exequiel
 */
public class Integrador {

    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        ConexionDB conn = new ConexionDB();
//        conn.conectarBB();
//        conn.desconetarDB();

        Alumno_Controller alumno_Controller = new Alumno_Controller();
        Profesor_Controller profesor_Controller = new Profesor_Controller();
        Usuario_Controller usuario_Controller = new Usuario_Controller();
        Curso_Controller curso_Controller = new Curso_Controller();
        Matricula_Controller matricula_Controller = new Matricula_Controller();
        ValorCurso_Controller valorCurso_Controller = new ValorCurso_Controller();
        HistorialCuenta_Controller historialCuenta_Controller = new HistorialCuenta_Controller();
//        System.out.println("ingrese una opcion");
//        System.out.println("1 - Alumnos");
//        System.out.println("1 - Profesorres");
//        System.out.println("1 - Uusarios");
//        System.out.println("1 - Cursos");
//        System.out.println("1 - Matricula");
//        System.out.println("1 - Pagos");
        //--------------------------------------------------------------------------------

        //valorCurso_Controller.buscarPrecioDeCurso();
        //valorCurso_Controller.nuevoValorCurso();
        //valorCurso_Controller.buscarPrecioPorId();
        //valorCurso_Controller.buscarPrecioPorCurso();
        //valorCurso_Controller.modificarPrecio();
        //--------------------------------------------------------------------------------
        //matricula_Controller.matricularAlumnoEnCurso();
        //matricula_Controller.buscarMatriculaDeAlumno();
        //matricula_Controller.buscarMatriculaDeCurso();
        //matricula_Controller.buscarTodasLasMatriculas();
        //matricula_Controller.darDeBajaAlumno();
        //--------------------------------------------------------------------------------
        //curso_Controller.nuevoCurso();
        //curso_Controller.buscarTodosLosCursos();
        //curso_Controller.buscarCurso();
        //curso_Controller.modificarCurso();
        //curso_Controller.eliminarCurso();
        //--------------------------------------------------------------------------------
        //alumno_Controller.nuevoAlumno();
        //alumno_Controller.buscarAlumno();
        //alumno_Controller.modificarAlumno();
        //alumno_Controller.buscarTodosAlumnos();
        //alumno_Controller.eliminarAlumno();
        //alumno_Controller.nuevoAlumno();
        //--------------------------------------------------------------------------------
        //profesor_Controller.nuevoProfesor();
        //profesor_Controller.buscarProfesor();
        //profesor_Controller.buscarTodosProfesores();
        //profesor_Controller.nuevoProfesor();
        //--------------------------------------------------------------------------------
        //usuario_Controller.nuevoUsuario();
        //usuario_Controller.buscarUsuarioPorId();
        //usuario_Controller.buscarUsuarioPorNombreUsuario();
        //usuario_Controller.eliminarUsuario();
        //usuario_Controller.modificarDatosUsuario();
        //usuario_Controller.verTodosLosUsuario();
        //--------------------------------------------------------------------------------
        //historialCuenta_Controller.buscarPagoPorId();
        //historialCuenta_Controller.pagarCurso();;
        //historialCuenta_Controller.verTodosLosPagos();
    }
}
