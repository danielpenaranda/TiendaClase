package com.TiendaClase.service.impl;

import com.TiendaClase.dao.ProductoDao;
import com.TiendaClase.domain.Producto;
import com.TiendaClase.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HUAWEI PC
 */
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    private ProductoDao productoDao;
    
    @Override
    @Transactional (readOnly=true)
    public List<Producto> getProducto(boolean activos) {
      var lista = productoDao.findAll();
        if(activos){
            lista.removeIf(e -> !e.isActivo() );
        }
        return lista;        
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> getCategoria(Producto producto) {
        return (List<Producto>) productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }

    
}
