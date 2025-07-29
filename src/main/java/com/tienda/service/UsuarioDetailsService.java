package com.tienda.service;

import com.tienda.domain.Rol;
import com.tienda.domain.Usuario;
import com.tienda.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UsuarioDetailsService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private HttpSession session;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Se busca el usuario en la tabla usuario... por medio del username
        Usuario usuario = usuarioRepository.findByUsername(username);
        
        if (usuario==null) { // No se encontró en la BD
            throw new UsernameNotFoundException(username);
        }
        
        //Si está acá... todo bien, usuario existe
        session.removeAttribute("imagenUsuario");
        session.setAttribute("imagenUsuario", usuario.getRutaImagen());
        
        //Ahora se crean los roles como un ArrayList de permisos
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoless()){
            roles.add(new SimpleGrantedAuthority("ROLE_"+rol.getNombre()));
        }
        
        //Retornamos el usuario ya con todo previsto
        
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}
