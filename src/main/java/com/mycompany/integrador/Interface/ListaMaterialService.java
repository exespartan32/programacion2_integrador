/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;

import com.mycompany.integrador.Models.ListaMateriales;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author exequiel
 */
public interface ListaMaterialService {

    public void guardarElementoLista(ListaMateriales listaMateriales);

    public void modificarElementoLista(Long id, ListaMateriales listaMateriales);

    public void eliminarElementoLista(Long idLista);

    public void prestarMaterial(Long id);

    public void devolverMaterial(Long id);

    public ArrayList<ListaMateriales> buscarTodaLaLista();

    public Optional<ListaMateriales> buscarElementoLista(Long id);
}
