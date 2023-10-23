package com.example.demo.services;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario insert(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario findByEmail(String email) { return usuarioRepository.findByEmail(email); }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) usuarioRepository.findByEmail(username);
    }

    public Usuario updateUser(Long id, Usuario usuario) {
        return usuarioRepository.findById(id)
                .map(novo -> {
                    novo.setNome(usuario.getNome());
                    novo.setSenha(usuario.getSenha());
                    return usuarioRepository.save(novo);
                }).orElseThrow(RuntimeException::new);
    }

}
