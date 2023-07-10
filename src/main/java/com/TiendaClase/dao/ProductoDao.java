/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.TiendaClase.dao;

import com.TiendaClase.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author HUAWEI PC
 */
public interface ProductoDao extends JpaRepository <Producto,Long>{
    
}
