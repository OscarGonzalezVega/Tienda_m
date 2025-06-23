package com.tienda.service;

import com.tienda.domain.Producto;
import com.tienda.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {
    
    //Se define un único objeto para todos los usuarios
    //y se crea automáticamente
    @Autowired
    private ProductoRepository productoRepository;
    
    @Transactional(readOnly=true)
    public List<Producto> getProductos(boolean activo) {
        var lista = productoRepository.findAll();
        
        //Se hace el filtro si sólo se desean las categorías activas...
        if (activo){
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }   
    
    @Transactional(readOnly=true)
    public Producto getProductos(Producto producto) {
        return productoRepository.findById(producto.getIdProducto()).orElse(null);
    }   
    @Transactional
    public void save(Producto producto){
        productoRepository.save(producto);     
    }
    
    @Transactional
    public boolean delete(Producto producto){
        try {
           productoRepository.delete(producto);  
           productoRepository.flush();
           return true;
        }catch (Exception e){
            return false;
        }    
    }
    
    
}
