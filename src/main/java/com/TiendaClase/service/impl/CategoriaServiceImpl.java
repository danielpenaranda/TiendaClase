package com.tiendaClase.service.impl;

import com.TiendaClase.dao.CategoriaDao;
import com.TiendaClase.service.CategoriaService;
import com.tiendaClase.domain.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;
    
    @Override
    @Transactional (readOnly=true)
    public List<Categoria> getCategorias(boolean activos) {
        var lista = categoriaDao.findAll();
        if(activos){
            lista.removeIf(categoriaEletemento -> !categoriaEletemento.isActivo() );
        }
        return lista;        
    }
    
}
