package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    private String descripcion;
    private String rutaImagen;
    private boolean activo;
   
    @OneToMany
    @JoinColumn(name="id_categoria")
    private List<Producto> productos;
}
