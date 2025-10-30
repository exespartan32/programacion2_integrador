/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.integrador;

import com.mycompany.integrador.Controllers.Persona_Controller;
import com.mycompany.integrador.Controllers.Alumno_Controller;
import com.mycompany.integrador.Controllers.Profesor_Controller;
import com.mycompany.integrador.Controllers.Usuario_Controller;
import com.mycompany.integrador.Controllers.Curso_Controller;
import com.mycompany.integrador.Controllers.Matricula_Controller;

/**
 *
 * @author exequiel
 */
public class Integrador {

    public static void main(String[] args) {
        System.out.println("Hello World!");
//        ConexionDB conn = new ConexionDB();
//        conn.conectarBB();
//        conn.desconetarDB();

        Persona_Controller persona_Controller = new Persona_Controller();
        Alumno_Controller alumno_Controller = new Alumno_Controller();
        Profesor_Controller profesor_Controller = new Profesor_Controller();
        Usuario_Controller usuario_Controller = new Usuario_Controller();
        Curso_Controller curso_Controller = new Curso_Controller();
        Matricula_Controller matricula_Controller = new Matricula_Controller();

        //matricula_Controller.matricularAlumnoEnCurso();
        //matricula_Controller.buscarMatriculaDeAlumno();
        //matricula_Controller.buscarMatriculaDeCurso();
        //matricula_Controller.buscarTodasLasMatriculas();
        //matricula_Controller.darDeBajaAlumno();
        
        
        
        //curso_Controller.nuevoCurso();
        //curso_Controller.buscarTodosLosCursos();
        //curso_Controller.buscarCurso();
        //curso_Controller.modificarCurso();
        //curso_Controller.eliminarCurso();

        //alumno_Controller.nuevoAlumno();
        //alumno_Controller.buscarAlumno();
        //alumno_Controller.modificarAlumno();
        //alumno_Controller.buscarTodosAlumnos();
        //alumno_Controller.eliminarAlumno();
        //profesor_Controller.nuevoProfesor();
        //profesor_Controller.buscarProfesor();
        //profesor_Controller.buscarTodosProfesores();
        //profesor_Controller.nuevoProfesor();
        //usuario_Controller.nuevoUsuario();
        //alumno_Controller.nuevoAlumno();
        //persona_Controller.crearNuevaPersona();
        //persona_Controller.buscarPersona();
    }
}
