/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.TiendaClase.service;
import com.tiendaClase.domain.Categoria;
import java.util.List;
/**
 *
 * @author HUAWEI PC
 */
public interface CategoriaService {
    //SE OBTIENE UN LISTADO DE CATEGORIAS DE UN LIST
    public List<Categoria> getCategorias(boolean activo);
}
