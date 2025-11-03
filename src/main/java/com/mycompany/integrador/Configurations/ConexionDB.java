/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Configurations;

import java.io.File;
import java.sql.*;

/**
 *
 * @author exequiel
 */
public class ConexionDB {

    Connection conn = null;
    String db = "DB_ProyectoIntegrador.db";
    String conector = "jdbc:sqlite:" + db;

    public Connection conectarDB() {
        try {
            File archivoDB = new File(db);
            boolean existe = archivoDB.exists();

            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection(conector);

            if (this.conn != null) {
                if (!existe) {
                    System.out.println("No se encontró la base de datos. Creando nueva...");
                    this.inicializarTablas();
                } else {
                    System.out.println("Base de datos existente detectada.");
                }
            } else {
                System.out.println("ERROR! no se pudo conectar a base de datos");
            }
        } catch (SQLException a) {
            System.out.println("SQLException: " + a.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return this.conn;
    }

    private void inicializarTablas() {
        try ( Statement stmt = this.conn.createStatement()) {
            System.out.println("Verificando e inicializando tablas...");
            stmt.execute("PRAGMA foreign_keys = ON;");

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS Persona (
                DNI TEXT PRIMARY KEY UNIQUE NOT NULL,
                nombres TEXT NOT NULL,
                apellidoMaterno TEXT NOT NULL,
                edad INTEGER NOT NULL,
                apellidoPaterno TEXT NOT NULL,
                fechaCreacion TEXT NOT NULL,
                fechaModificacion TEXT DEFAULT NULL,
                fechaEliminacion TEXT DEFAULT NULL,
                tipoPersona TEXT NOT NULL
            );
        """);

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS Instituto (
                idInstituto INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
                razonSocial TEXT NOT NULL,
                cursos TEXT,
                alumnos TEXT,
                materiales TEXT,
                profesores TEXT
            );
        """);

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS Usuario (
                idUsuario INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
                email TEXT NOT NULL,
                nombreUsuario TEXT NOT NULL UNIQUE,
                contrasenia TEXT NOT NULL
            );
        """);

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS Profesor (
                DNIProfesor TEXT PRIMARY KEY UNIQUE NOT NULL,
                sueldo REAL NOT NULL,
                presentismo INTEGER NOT NULL,
                FOREIGN KEY (DNIProfesor)
                    REFERENCES Persona(DNI)
                    ON DELETE CASCADE ON UPDATE CASCADE
            );
        """);

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS Curso (
                nombreCurso TEXT PRIMARY KEY UNIQUE NOT NULL,
                mesesDuracion INTEGER NOT NULL,
                fechaCreacion TEXT NOT NULL,
                DNIProfesor TEXT NOT NULL UNIQUE,
                FOREIGN KEY (DNIProfesor)
                    REFERENCES Profesor(DNIProfesor)
                    ON DELETE CASCADE ON UPDATE CASCADE
            );
        """);

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS ValorCurso (
                idValorCurso INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
                nombreCurso TEXT NOT NULL UNIQUE,
                precioCurso INTEGER NOT NULL,
                fechaCreacion TEXT NOT NULL,
                fechaModificacion TEXT DEFAULT NULL,
                fechaEliminacion TEXT DEFAULT NULL,
                FOREIGN KEY (nombreCurso)
                    REFERENCES Curso(nombreCurso)
                    ON DELETE CASCADE ON UPDATE CASCADE
            );
        """);

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS Alumno (
                DNIAlumno TEXT PRIMARY KEY UNIQUE NOT NULL,
                anioIngreso INTEGER NOT NULL,
                mesIngreso INTEGER NOT NULL,
                FOREIGN KEY (DNIAlumno)
                    REFERENCES Persona(DNI)
                    ON DELETE CASCADE ON UPDATE CASCADE
            );
        """);

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS Matricula (
                idMatricula INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
                fechaCreacion TEXT NOT NULL,
                DNIAlumno TEXT NOT NULL,
                nombreCurso TEXT NOT NULL,
                FOREIGN KEY (DNIAlumno)
                    REFERENCES Alumno(DNIAlumno)
                    ON DELETE CASCADE ON UPDATE CASCADE,
                FOREIGN KEY (nombreCurso)
                    REFERENCES Curso(nombreCurso)
                    ON DELETE CASCADE ON UPDATE CASCADE
            );
        """);

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS HistorialDeCuentas (
                idHistorialCuenta INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
                DNIAlumno TEXT NOT NULL,
                nombreCurso TEXT NOT NULL,
                idValorCurso INTEGER NOT NULL,
                pagoActual REAL NOT NULL,
                saldoAlumno REAL NOT NULL,
                descripcion TEXT,
                fechaCreacion TEXT NOT NULL,
                fechaModificacion TEXT DEFAULT NULL,
                fechaEliminacion TEXT DEFAULT NULL,
                FOREIGN KEY (DNIAlumno)
                    REFERENCES Alumno(DNIAlumno)
                    ON DELETE CASCADE ON UPDATE CASCADE,
                FOREIGN KEY (nombreCurso)
                    REFERENCES Curso(nombreCurso)
                    ON DELETE CASCADE ON UPDATE CASCADE,
                FOREIGN KEY (idValorCurso)
                    REFERENCES ValorCurso(idValorCurso)
                    ON DELETE CASCADE ON UPDATE CASCADE
            );
        """);

            System.out.println("Tablas verificadas y listas para usar.");

        } catch (SQLException e) {
            System.out.println("ERROR AL INICIALIZAR TABLAS: " + e.getMessage());
        }
    }

    public void desconetarDB() {
        try {
            if (this.conn != null && !this.conn.isClosed()) {
                this.conn.close();
                System.out.println("La conexion se cerro correctamaente");
            }
        } catch (SQLException e) {
            System.out.println("ERROR al cerrar la DB: " + e.toString());
        }
    }
}
