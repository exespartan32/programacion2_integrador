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

    public void nuevoPago() {
        HistorialDeCuentas historialDeCuentas = new HistorialDeCuentas(
                "43270183",
                "peluqueria",
                LocalDate.now(),
                true,
                12000,
                2000,
                0,
                "pago total"
        );
        historialCuentaServiceImplement.pagarCurso(historialDeCuentas);
    }

    public void pagarCurso() {
        System.out.println("seleccione el curso que quiere pagar:");
        System.out.println("///////////////////////////////////////////////////////////////////");
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        for (int i = 0; i < listaCursos.size(); i++) {
            int elemento = i + 1;
            System.out.println("Elemento " + elemento + ": " + listaCursos.get(i).getNombreCurso());
        }
        int i_curso = sc.nextInt();
        Curso curso = listaCursos.get(i_curso - 1);

        int saldo = 0;

        ArrayList<Matricula> matricula = matriculaServiceImplement.buscarMatriculaCurso(listaCursos.get(i_curso).getNombreCurso());
        if (matricula.size() > 0) {
            System.out.println("seleccione el alumno que quiere pagara:");
            System.out.println("///////////////////////////////////////////////////////////////////");

            for (int i = 0; i < matricula.size(); i++) {
                int elemento = i + 1;
                System.out.println("Elemento " + elemento + ": " + matricula.get(i).getDNIAlumno());
            }
            int i_matricula = sc.nextInt();

            System.out.println("alumno: " + matricula.get(i_matricula - 1).getDNIAlumno());

            Alumno alumno = alumnoServiceImplement.buscarAlumno(matricula.get(i_matricula - 1).getDNIAlumno());

            //System.out.println("alumno: " + alumno.toString());
//            System.out.println("esta pagado ? : " + historialCuentaServiceImplement.cursoPagado(curso.getNombreCurso(), alumno.getDNI()));
            if (!historialCuentaServiceImplement.cursoPagado(curso.getNombreCurso(), alumno.getDNI())) {
                saldo = historialCuentaServiceImplement.verSaldo(alumno.getDNI());
                System.out.println("el saldo actual del alumno es de $ " + saldo);

                System.out.println("ingrese el pago realizado");
                int pago = sc.nextInt();

                ValorCurso valorCurso = valorCursoServiceImplement.buscarValorCursos(curso.getNombreCurso());
                int precioCurso = valorCurso.getPrecioCurso();

                int saldoAlumno = 0;
                if (saldo > 0) {
                    saldoAlumno = pago + saldo - precioCurso;
                } else {
                    saldoAlumno = saldo + pago;
                }

                sc.nextLine();
                System.out.println("ingresa una descripcion para el pago");
                String descripcionPago = sc.nextLine();

                boolean pagado = pago > precioCurso;

//                HistorialDeCuentas historialDeCuentas = new HistorialDeCuentas(
//                        alumno.getDNI(),
//                        curso.getNombreCurso(),
//                        LocalDate.now(),
//                        pagado,
//                        precioCurso,
//                        pago,
//                        saldoAlumno,
//                        descripcionPago
//                );

                //System.out.println("datos del objeto" + historialDeCuentas.toString());

                //historialCuentaServiceImplement.pagarCurso(historialDeCuentas);
            } else {
                System.out.println("este alumno ya pago el curso");
            }

        } else {
            System.out.println("no hay alumnos matriculados en este curso");
        }
    }
}
