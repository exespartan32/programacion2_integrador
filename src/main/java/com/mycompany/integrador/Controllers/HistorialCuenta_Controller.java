/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Models.Curso;
import com.mycompany.integrador.Service.HistorialCuentaServiceImplement;
import com.mycompany.integrador.Service.CursoServiceImplement;
import com.mycompany.integrador.Service.ValorCursoServiceImplement;
import com.mycompany.integrador.Service.MatriculaServiceImplement;
import com.mycompany.integrador.Service.AlumnoServiceImplement;
import com.mycompany.integrador.Models.Matricula;
import com.mycompany.integrador.Models.Alumno;
import com.mycompany.integrador.Models.HistorialDeCuentas;
import com.mycompany.integrador.Models.ValorCurso;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class HistorialCuenta_Controller {

    HistorialCuentaServiceImplement historialCuentaServiceImplement = new HistorialCuentaServiceImplement();
    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();
    ValorCursoServiceImplement valorCursoServiceImplement = new ValorCursoServiceImplement();
    MatriculaServiceImplement matriculaServiceImplement = new MatriculaServiceImplement();
    AlumnoServiceImplement alumnoServiceImplement = new AlumnoServiceImplement();
    Scanner sc = new Scanner(System.in);

    public void pagarCurso() {
        System.out.println("pagar curso");
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            System.out.println("_____________________________________________________________________________________");
            System.out.println("                       seleccione el curso que quiere pagar:                         ");
            System.out.println("_____________________________________________________________________________________");
            try {
                if (listaCursos.size() > 0) {
                    for (int i = 0; i < listaCursos.size(); i++) {
                        System.out.println("///////////////////////////////////////////////////////////////////");
                        System.out.println("|                           Elemento " + i + ":                           |");
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("| nombre del curso: " + listaCursos.get(i).getNombreCurso());
                        System.out.println("------------------------------------------------------------------");
                    }
                    try {
                        System.out.print("Seleecionar el elemento Nº ");
                        int i_curso = sc.nextInt();
                        Curso curso = listaCursos.get(i_curso);
                        String nombreCurso = listaCursos.get(i_curso).getNombreCurso();
                        int saldo = 0;
                        ArrayList<Matricula> matricula = matriculaServiceImplement.buscarMatriculaCurso(nombreCurso);
                        if (matricula.size() > 0) {
                            System.out.println("seleccione el alumno que quiere pagara:");
                            System.out.println("///////////////////////////////////////////////////////////////////");
                            for (int i = 0; i < matricula.size(); i++) {
                                System.out.println("///////////////////////////////////////////////////////////////////");
                                System.out.println("|                           Elemento " + i + ":                           |");
                                System.out.println("------------------------------------------------------------------");
                                Alumno alumno = alumnoServiceImplement.buscarAlumno(matricula.get(i).getDNIAlumno());
                                System.out.println("| nombre: " + alumno.getNombres());
                                System.out.println("| apellidos: " + alumno.getApellidoPaterno() + " " + alumno.getApellidoMaterno());
                                System.out.println("| DNI: " + alumno.getDNI());
                                System.out.println("------------------------------------------------------------------");
                            }
                            System.out.print("Seleecionar el elemento Nº ");
                            int i_matricula = sc.nextInt();
                            Alumno alumno = alumnoServiceImplement.buscarAlumno(matricula.get(i_matricula).getDNIAlumno());

                            if (!historialCuentaServiceImplement.cursoPagado(curso.getNombreCurso(), alumno.getDNI())) {
                                saldo = historialCuentaServiceImplement.verSaldo(alumno.getDNI());
                                System.out.println("ingrese el pago realizado");
                                int pago = sc.nextInt();
                                ValorCurso valorCurso = valorCursoServiceImplement.buscarValorCursos(curso.getNombreCurso());
                                int precioCurso = valorCurso.getPrecioCurso();
                                boolean pagado = false;
                                if (saldo > 0) {
                                    // tiene saldo a favor
                                    saldo = pago + saldo - precioCurso;
                                    if (pago > precioCurso) {
                                        pagado = true;
                                    }
                                } else {
                                    // tiene una deuda
                                    saldo = saldo + pago;
                                    if (saldo >= 0) {
                                        pagado = true;
                                    }
                                }
                                sc.nextLine();
                                System.out.println("escribe la descripcion");
                                String descripcion = sc.nextLine();
                                HistorialDeCuentas historialDeCuentas = new HistorialDeCuentas(
                                        alumno.getDNI(),
                                        curso.getNombreCurso(),
                                        LocalDate.now(),
                                        pagado,
                                        precioCurso,
                                        pago,
                                        saldo,
                                        descripcion
                                );
                                historialCuentaServiceImplement.pagarCurso(historialDeCuentas);
                            } else {
                                System.out.println("----------------------------------------------------------------------------------");
                                System.out.println("          el alumno " + alumno.getNombres() + " con dni " + alumno.getDNI() + " ya pago el curso de " + curso.getNombreCurso());
                                System.out.println("----------------------------------------------------------------------------------");
                            }
                        } else {
                            System.out.println("-------------------------------------------------------------------");
                            System.out.println("               no hay alumnos matriculados en este curso           ");
                            System.out.println("                    primero debe matricular alumnos                ");
                            System.out.println("-------------------------------------------------------------------");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("                    ERROR!!: debe ser un numero.                  ");
                        System.out.println("------------------------------------------------------------------");
                    }
                } else {
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("               no hay cursos guardados en base de datos            ");
                    System.out.println("                 primero agregue cursos en el sistema              ");
                    System.out.println("-------------------------------------------------------------------");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("          ERROR!!: no existe el registro seleccionado             ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                  ERROR!!: debe ser un numero.                    ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                    no hay cursos que mostrar                     ");
            System.out.println("                primero agregue cursos al sistema                 ");
            System.out.println("------------------------------------------------------------------");
        }

    }

    public void verTodosLosPagos() {
        ArrayList<HistorialDeCuentas> listaPagos = historialCuentaServiceImplement.buscarPago();
        for (int i = 0; i < listaPagos.size(); i++) {
            System.out.println("///////////////////////////////////////////////////////////////////");
            System.out.println("|                           Elemento " + i + ":                           |");
            System.out.println("------------------------------------------------------------------");
            System.out.println("| DNI del alumno:" + listaPagos.get(i).getDniAlumno());
            System.out.println("| nombre del curso:" + listaPagos.get(i).getNombreCurso());
            System.out.println("| pago del alumno:" + listaPagos.get(i).getPago());
            System.out.println("| saldo del alumno:" + listaPagos.get(i).getSaldo());
            System.out.println("| descripcion del pago:" + listaPagos.get(i).getDescripcionPago());
            System.out.println("| esta pagado? :" + listaPagos.get(i).isPagado());
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void verPagoAlumno() {
        if (alumnoServiceImplement.buscarAlumno().size() <= 0) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay alumnos que mostrar                   ");
            System.out.println("               primero agregue alumnos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        } else {
            System.out.println("______________________________________________________________");
            System.out.println("                       seleccine el alumno                    ");
            System.out.println("______________________________________________________________");
            try {
                ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| nombre: " + listaAlumnos.get(i).getNombres());
                    System.out.println("| apellidos: " + listaAlumnos.get(i).getApellidoPaterno() + " " + listaAlumnos.get(i).getApellidoMaterno());
                    System.out.println("| DNI: " + listaAlumnos.get(i).getDNI());
                    System.out.println("------------------------------------------------------------------");
                }
                System.out.print("Seleecionar el elemento Nº ");
                int i_alumno = sc.nextInt();
                ArrayList<HistorialDeCuentas> listaPagos = historialCuentaServiceImplement.buscarPago(alumnoServiceImplement.buscarAlumno().get(i_alumno).getDNI());
                if (listaPagos.size() > 0) {
                    for (int i = 0; i < listaPagos.size(); i++) {
                        System.out.println("///////////////////////////////////////////////////////////////////");
                        System.out.println("|                           Elemento " + i + ":                           |");
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("| DNI del alumno:" + listaPagos.get(i).getDniAlumno());
                        System.out.println("| nombre del curso:" + listaPagos.get(i).getNombreCurso());
                        System.out.println("| pago del alumno:" + listaPagos.get(i).getPago());
                        System.out.println("| saldo del alumno:" + listaPagos.get(i).getSaldo());
                        System.out.println("| descripcion del pago:" + listaPagos.get(i).getDescripcionPago());
                        System.out.println("| esta pagado? :" + listaPagos.get(i).isPagado());
                        System.out.println("------------------------------------------------------------------");
                    }
                } else {
                    System.out.println("---------------------------------------------------------------------------------");
                    System.out.println("             no se encontro ningun pagos realizados por el alumno con dni " + listaAlumnos.get(i_alumno).getDNI());
                    System.out.println("---------------------------------------------------------------------------------");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("          ERROR!!: no existe el registro seleccionado             ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                  ERROR!!: debe ser un numero.                    ");
                System.out.println("------------------------------------------------------------------");
            }
        }
    }

    public void verPagoCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            try {
                System.out.println("__________________________________________________________________");
                System.out.println("           seleccione el curso del que quiete ver el pago         ");
                System.out.println("__________________________________________________________________");
                System.out.println("///////////////////////////////////////////////////////////////////");
                for (int i = 0; i < listaCursos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| curso: " + listaCursos.get(i).getNombreCurso());
                    System.out.println("| meses de duracion: " + listaCursos.get(i).getMesesDuracion());
                    System.out.println("| DNI del profesor a cargo: " + listaCursos.get(i).getDniProfesor());
                    System.out.println("------------------------------------------------------------------");
                }
                System.out.print("Seleecionar el elemento Nº ");
                int i_curso = sc.nextInt();
                ArrayList<HistorialDeCuentas> listaPagos = historialCuentaServiceImplement.buscarPago(listaCursos.get(i_curso).getNombreCurso(), true);
                if (listaPagos.size() > 0) {
                    for (int i = 0; i < listaPagos.size(); i++) {
                        System.out.println("///////////////////////////////////////////////////////////////////");
                        System.out.println("|                           Elemento " + i + ":                           |");
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("| DNI del alumno:" + listaPagos.get(i).getDniAlumno());
                        System.out.println("| nombre del curso:" + listaPagos.get(i).getNombreCurso());
                        System.out.println("| pago del alumno:" + listaPagos.get(i).getPago());
                        System.out.println("| saldo del alumno:" + listaPagos.get(i).getSaldo());
                        System.out.println("| descripcion del pago:" + listaPagos.get(i).getDescripcionPago());
                        System.out.println("| esta pagado? :" + listaPagos.get(i).isPagado());
                        System.out.println("------------------------------------------------------------------");
                    }
                } else {
                    System.out.println("------------------------------------------------------------------------------------");
                    System.out.println("            no se encontro ningun pagos del curso de " + listaCursos.get(i_curso).getNombreCurso());
                    System.out.println("------------------------------------------------------------------------------------");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("          ERROR!!: no existe el registro seleccionado             ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                  ERROR!!: debe ser un numero.                    ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay cursos que mostrar                    ");
            System.out.println("                 primero agregue cursos al sistema                ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarPagoPorId() {
        ArrayList<HistorialDeCuentas> listaPagos = historialCuentaServiceImplement.buscarPago();
        if (listaPagos.size() > 0) {
            System.out.println("ingrese el id del pago que desea buscar");
            int id_pago = sc.nextInt();
            HistorialDeCuentas historialDeCuentas = historialCuentaServiceImplement.buscarPago(id_pago);

            if (historialDeCuentas.getDniAlumno() != null) {
                System.out.println(historialDeCuentas.toString());
            } else {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                   no se encontro ningun pago con id " + id_pago);
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                 no hay pagos guardados en el sistema             ");
            System.out.println("------------------------------------------------------------------");
        }
    }

}
