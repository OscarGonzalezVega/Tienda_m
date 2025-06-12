package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {
    
    //Se define un único objeto para todos los usuarios
    //y se crea automáticamente
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias(boolean activo) {
        var lista = categoriaRepository.findAll();
        
        //Se hace el filtro si sólo se desean las categorías activas...
        if (activo){
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }   
    
    @Transactional(readOnly=true)
    public Categoria getCategorias(Categoria categoria) {
        return categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
    }   
    @Transactional
    public void save(Categoria categoria){
        categoriaRepository.save(categoria);     
    }
    
    @Transactional
    public void delete(Categoria categoria){
        categoriaRepository.delete(categoria);     
    }
    
    
}
