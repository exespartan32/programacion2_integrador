/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Service.MatriculaServiceImplement;
import com.mycompany.integrador.Service.AlumnoServiceImplement;
import com.mycompany.integrador.Service.CursoServiceImplement;
import com.mycompany.integrador.Service.ProfesorServiceImplement;
import java.util.Scanner;
import com.mycompany.integrador.Models.Alumno;
import com.mycompany.integrador.Models.Profesor;
import com.mycompany.integrador.Models.Curso;
import com.mycompany.integrador.Models.Matricula;
import java.util.ArrayList;
import java.util.InputMismatchException;
import javax.crypto.AEADBadTagException;

/**
 *
 * @author exequiel
 */
public class Matricula_Controller {

    MatriculaServiceImplement matriculaServiceImplement = new MatriculaServiceImplement();
    AlumnoServiceImplement alumnoServiceImplement = new AlumnoServiceImplement();
    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();
    ProfesorServiceImplement profesorServiceImplement = new ProfesorServiceImplement();
    Scanner sc = new Scanner(System.in);

    public void matriculaPrueba() {
        matriculaServiceImplement.matricularAlumno("43270183", "maquillaje");
    }

    public void matricularAlumnoEnCurso() {
        if (alumnoServiceImplement.buscarAlumno().size() <= 0) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay alumnos que mostrar                   ");
            System.out.println("                 primero agregue alumnos en el sistema            ");
            System.out.println("------------------------------------------------------------------");
        } else {
            try {
                System.out.println("_________________________________________________________________________");
                System.out.println("                   seleccine el alumno a matricular                      ");
                System.out.println("_________________________________________________________________________");
                System.out.println("///////////////////////////////////////////////////////////////////");
                ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("___________________________________________________________________");
                    System.out.println("| nombre: " + listaAlumnos.get(i).getNombres());
                    System.out.println("| apellidos: " + listaAlumnos.get(i).getApellidoPaterno() + " " + listaAlumnos.get(i).getApellidoMaterno());
                    System.out.println("| DNI: " + listaAlumnos.get(i).getDNI());
                    System.out.println("___________________________________________________________________\n");
                }
                System.out.print("Seleccionar el Elemento Nº ");
                int i_alumno = sc.nextInt();

                Alumno alumno = listaAlumnos.get(i_alumno);
                String dniAlumno = alumno.getDNI();

                ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
                System.out.println("\n\n=============================================================================");
                System.out.println("=============================================================================");
                System.out.println("\n\n_______________________________________________________________________________________________________________________________________");
                System.out.println("\n seleccine el curso al que desea matriclar el alumno "
                        + alumno.getNombres()
                        + " " + alumno.getApellidoPaterno()
                        + " " + alumno.getApellidoMaterno()
                        + " con DNI " + alumno.getDNI()
                );
                System.out.println("\n\n_______________________________________________________________________________________________________________________________________");
                System.out.println("///////////////////////////////////////////////////////////////////");
                if (listaCursos.size() <= 0) {
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("             no hay cursos donde matricular el alumno             ");
                    System.out.println("                 primero agregue cursos al sistema                ");
                    System.out.println("------------------------------------------------------------------");
                } else {
                    for (int j = 0; j < listaCursos.size(); j++) {
                        System.out.println("///////////////////////////////////////////////////////////////////");
                        System.out.println("|                           Elemento " + j + ":                           |");
                        System.out.println("___________________________________________________________________");
                        System.out.println("| nombre del curso: " + listaCursos.get(j).getNombreCurso());
                        System.out.println("| meses de duracion: " + listaCursos.get(j).getMesesDuracion());
                        Profesor profesor = profesorServiceImplement.buscarProfesor(listaCursos.get(j).getDniProfesor());
                        System.out.println("| nombre del profesor a cargo: " + profesor.getNombres());
                        System.out.println("| apellidos del profesor a cargo: " + profesor.getApellidoPaterno() + " " + profesor.getApellidoMaterno());
                        System.out.println("| DNI del profesor a cargo: " + profesor.getDNI());
                        System.out.println("___________________________________________________________________\n");
                    }
                    System.out.print("Seleccionar el Elemento Nº ");
                    int i_curso = sc.nextInt();
                    String nombreCurso = listaCursos.get(i_curso).getNombreCurso();

                    // si el alumno ya esta matriculado en este curso no se puede volver a matricluar
                    if (matriculaServiceImplement.buscarMatriculaCurso(alumnoServiceImplement.buscarAlumno(dniAlumno), listaCursos.get(i_curso)) == null) {
                        System.out.println("el alumno con DNI " + dniAlumno + " ya esta matriculado en el curso de " + listaCursos.get(i_curso).getNombreCurso());
                    } else {
                        //System.out.println("se matricula el alumno con dni " + dniAlumno + " en el curso de " + nombreCurso);
                        matriculaServiceImplement.matricularAlumno(dniAlumno, nombreCurso);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("           ERROR!!: no existe el registro seleccionado             ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("           ERROR!!: debe ser un numero                            ");
                System.out.println("------------------------------------------------------------------");
            }

        }
    }

    public void buscarMatriculaDeAlumno() {
        if (alumnoServiceImplement.buscarAlumno().size() <= 0) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                  no hay alumnos que mostrar                      ");
            System.out.println("            primero agregue alumnos en el sistema                 ");
            System.out.println("------------------------------------------------------------------");
        } else {
            try {
                System.out.println("_____________________________________________________________________");
                System.out.println("       seleccione el alumno del que quiete buscar la matricula       ");
                System.out.println("_____________________________________________________________________");
                ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();

                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("___________________________________________________________________");
                    System.out.println("| nombre del alumno: " + listaAlumnos.get(i).getNombres());
                    System.out.println("| apellidos del alumno: " + listaAlumnos.get(i).getApellidoPaterno() + " " + listaAlumnos.get(i).getApellidoMaterno());
                    System.out.println("| DNI del alumno: " + listaAlumnos.get(i).getDNI());
                    System.out.println("___________________________________________________________________");
                }
                System.out.print("Seleccionar el Elemento Nº ");
                int i_alumno = sc.nextInt();
                Alumno alumno = alumnoServiceImplement.buscarAlumno().get(i_alumno);
                matriculaServiceImplement.buscarMatriculaCurso(alumno);
                ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(alumno);

                System.out.println(" _______________________________________________________________________________________________________________________________");
                System.out.println("|                                                      Datos Encontrados:                                                        |");
                System.out.println(" _______________________________________________________________________________________________________________________________");
                for (int j = 0; j < listaMatriculas.size(); j++) {
                    System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                    System.out.println("|                                                           Elemento " + j + ":                                                         |");
                    System.out.println("________________________________________________________________________________________________________________________________");
                    System.out.println("El alumno "
                            + alumno.getNombres()
                            + " " + alumno.getApellidoPaterno()
                            + " " + alumno.getApellidoMaterno()
                            + " con DNI " + alumno.getDNI()
                            + " esta matriculado en el curso de "
                            + listaMatriculas.get(j).getNombreCurso()
                    );
                    System.out.println("________________________________________________________________________________________________________________________________");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("           ERROR!!: no existe el registro seleccionado            ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("           ERROR!!: debe ser un numero                            ");
                System.out.println("------------------------------------------------------------------");
            }
        }
    }

    public void buscarMatriculaDeCurso() {
        try {
            if (cursoServiceImplement.buscarCurso().size() <= 0) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                    no hay cursos para mostrar                    ");
                System.out.println("                primero agregue cursos al sistema                 ");
                System.out.println("------------------------------------------------------------------");
            } else {
                System.out.println("_________________________________________________________________________________");
                System.out.println("            seleccione el curso del que quiete buscar la matricula               ");
                System.out.println("_________________________________________________________________________________");
                ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
                for (int i = 0; i < listaCursos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("___________________________________________________________________");
                    System.out.println("| nombre del curso: " + listaCursos.get(i).getNombreCurso());
                    System.out.println("| meses de duracion: " + listaCursos.get(i).getMesesDuracion());
                    Profesor profesor = profesorServiceImplement.buscarProfesor(listaCursos.get(i).getDniProfesor());
                    System.out.println("| nombre del profesor a cargo: " + profesor.getNombres());
                    System.out.println("| apellidos del profesor a cargo: " + profesor.getApellidoPaterno() + " " + profesor.getApellidoMaterno());
                    System.out.println("| DNI del profesor a cargo: " + profesor.getDNI());
                    System.out.println("___________________________________________________________________\n");
                }
                System.out.print("Seleccionar el Elemento Nº ");
                int i_alumno = sc.nextInt();
                Curso curso = cursoServiceImplement.buscarCurso().get(i_alumno);
                ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(curso.getNombreCurso());

                System.out.println(" _______________________________________________________________________________________________________________________________");
                System.out.println("|                                                      Datos Encontrados:                                                        |");
                System.out.println(" _______________________________________________________________________________________________________________________________");
                for (int j = 0; j < listaMatriculas.size(); j++) {
                    Alumno alumno = alumnoServiceImplement.buscarAlumno().get(j);
                    System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                    System.out.println("|                                                           Elemento " + j + ":                                                         |");
                    System.out.println("________________________________________________________________________________________________________________________________");
                    System.out.println("El alumno "
                            + alumno.getNombres()
                            + " " + alumno.getApellidoPaterno()
                            + " " + alumno.getApellidoMaterno()
                            + " con DNI " + alumno.getDNI()
                            + " esta matriculado en el curso de "
                            + listaMatriculas.get(j).getNombreCurso()
                    );
                    System.out.println("________________________________________________________________________________________________________________________________\n");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("             ERROR!!: no existe el registro seleccionado          ");
            System.out.println("------------------------------------------------------------------");
        } catch (InputMismatchException e) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                  ERROR!!: debe ser un numero                     ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarTodasLasMatriculas() {
        if (matriculaServiceImplement.buscarMatriculaCurso().size() > 0) {
            ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso();
            for (int j = 0; j < listaMatriculas.size(); j++) {
                Alumno alumno = alumnoServiceImplement.buscarAlumno().get(j);
                System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                System.out.println("|                                                           Elemento " + j + ":                                                         |");
                System.out.println("________________________________________________________________________________________________________________________________");
                System.out.println("El alumno "
                        + alumno.getNombres()
                        + " " + alumno.getApellidoPaterno()
                        + " " + alumno.getApellidoMaterno()
                        + " con DNI " + alumno.getDNI()
                        + " esta matriculado en el curso de "
                        + listaMatriculas.get(j).getNombreCurso()
                );
                System.out.println("________________________________________________________________________________________________________________________________\n");

            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                  no hay registros para mostrar                   ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void darDeBajaAlumno() {
        if (alumnoServiceImplement.buscarAlumno().size() <= 0) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                    no hay alumnos que mostrar                    ");
            System.out.println("              primero agregue alumnos en el sistema               ");
            System.out.println("------------------------------------------------------------------");
        } else {
            try {
                System.out.println("___________________________________________________________________");
                System.out.println("            seleccione el alumno que quiere dar de baja            ");
                System.out.println("___________________________________________________________________");
                ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("___________________________________________________________________");
                    System.out.println("| nombre del alumno: " + listaAlumnos.get(i).getNombres());
                    System.out.println("| apellidos del alumno: " + listaAlumnos.get(i).getApellidoPaterno() + " " + listaAlumnos.get(i).getApellidoMaterno());
                    System.out.println("| DNI del alumno: " + listaAlumnos.get(i).getDNI());
                    System.out.println("___________________________________________________________________");
                }
                System.out.print("Seleccionar el Elemento Nº ");
                int i_alumno = sc.nextInt();
                Alumno alumno = alumnoServiceImplement.buscarAlumno().get(i_alumno);
                ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(alumno);

                System.out.println("\n\n ====================================================================================");
                if (listaMatriculas.size() > 0) {
                    System.out.println("______________________________________________________________________");
                    System.out.println("      seleccione el curso del que quiere der de baja el alumno:       ");
                    System.out.println("______________________________________________________________________");
                    for (int j = 0; j < listaMatriculas.size(); j++) {
                        Curso curso = cursoServiceImplement.buscarCurso(listaMatriculas.get(j).getNombreCurso());
                        System.out.println("///////////////////////////////////////////////////////////////////");
                        System.out.println("|                           Elemento " + j + ":                           |");
                        System.out.println("___________________________________________________________________");
                        System.out.println("| nombre del curso: " + curso.getNombreCurso());
                        System.out.println("| meses de duracion: " + curso.getMesesDuracion());
                        Profesor profesor = profesorServiceImplement.buscarProfesor(curso.getDniProfesor());
                        System.out.println("| nombre del profesor a cargo: " + profesor.getNombres());
                        System.out.println("| apellidos del profesor a cargo: " + profesor.getApellidoPaterno() + " " + profesor.getApellidoMaterno());
                        System.out.println("| DNI del profesor a cargo: " + profesor.getDNI());
                        System.out.println("___________________________________________________________________");
                    }
                    System.out.print("Seleccionar el Elemento Nº ");
                    int i_curso = sc.nextInt();
                    Curso curso = cursoServiceImplement.buscarCurso().get(i_curso);
                    sc.nextLine();
                    System.out.print("¿Está seguro que desea dar de baja el alumno "
                            + alumno.getNombres() + " "
                            + alumno.getApellidoPaterno() + " "
                            + alumno.getApellidoMaterno() + " con dni "
                            + alumno.getDNI() + " del curso de "
                            + curso.getNombreCurso() + "? (si/no): ");

                    String confirmacion = sc.nextLine();
                    if (confirmacion.equalsIgnoreCase("si") || confirmacion.equalsIgnoreCase("no")) {
                        if (confirmacion.equalsIgnoreCase("si")) {
                            matriculaServiceImplement.desvincularAlumno(alumno.getDNI(), curso.getNombreCurso());
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                    } else {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("          ERROR!!: opcion incorrecta. debe colocar si o no        ");
                        System.out.println("------------------------------------------------------------------");
                    }
                } else {
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("ERROR!!: el alumno seleccionado no esta matriculado en ningun curso");
                    System.out.println("--------------------------------------------------------------------");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("             ERROR!!: no existe el registro seleccionado          ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                ERROR!!: debe ser un numero                       ");
                System.out.println("------------------------------------------------------------------");
            }
        }
    }
}
