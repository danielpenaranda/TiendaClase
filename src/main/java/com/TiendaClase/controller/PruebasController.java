package com.TiendaClase.controller;

import com.TiendaClase.service.CategoriaService;
import com.TiendaClase.service.ProductoService;
import com.tiendaClase.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HUAWEI PC
 */
@Controller
@RequestMapping("/pruebas")
public class PruebasController {
        @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias);
        return "/pruebas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String listado(Model model, Categoria categoria) {
        var productos = categoriaService.getCategoria(categoria)/*.getProducto()*/;
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias);
        return "/pruebas/listado";
    }
    //Los métodos siguientes son para la prueba de consultas ampliadas
    @GetMapping("/listado2")
    public String listado2(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        return "/pruebas/listado2";
    }

    @PostMapping("/consulta1")
    public String consultaQuery1(@RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup, Model modelo) {
        var productos = productoService.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
        modelo.addAttribute("productos", productos);
        modelo.addAttribute("precioInf", precioInf);
        modelo.addAttribute("precioSup", precioSup);
        return "/consulta/listado2";
    }
    @PostMapping("/consulta2")
    public String consultaQuery2(@RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup, Model modelo) {
        var productos = productoService.metodoJPQL(precioInf, precioSup);
        modelo.addAttribute("productos", productos);        
        modelo.addAttribute("totalProductos", productos.size());
        modelo.addAttribute("precioInf", precioInf);
        modelo.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";
    }
     @PostMapping("/consulta3")
    public String consultaQuery3(@RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup, Model model) {
        var productos = productoService.metodoJPQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";
    }

}
