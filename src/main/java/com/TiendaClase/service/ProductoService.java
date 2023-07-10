package com.TiendaClase.service;

import com.TiendaClase.domain.Producto;
import com.tiendaClase.domain.Categoria;
import java.util.List;

/**
 *
 * @author HUAWEI PC
 */
public interface ProductoService {
       public List<Producto> getProducto(boolean activo);

    public List<Producto> getProducto(Producto producto);
    
    public void save(Producto producto);
    
    public void delete(Producto producto);
}
