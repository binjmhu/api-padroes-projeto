package com.example.demo.services;

import com.example.demo.entities.Livro;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Categoria;
import com.example.demo.infra.exceptions.livro.AutorNaoEncontrado;
import com.example.demo.infra.exceptions.livro.LivroNaoEncontrado;
import com.example.demo.infra.exceptions.usuario.EmailJaExistente;
import com.example.demo.infra.exceptions.usuario.NomeJaExistente;
import com.example.demo.infra.exceptions.usuario.UsuarioNaoEncontrado;
import com.example.demo.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> findAll(){
        return livroRepository.findAll();
    }

    public Livro findById(Long id){
        return livroRepository.findById(id).orElseThrow(LivroNaoEncontrado::new);
    }

    public List<Livro> findByAutor(String autor){
        if (livroRepository.existsByAutor(autor).size() == 0){
            throw  new AutorNaoEncontrado();
        }
        return livroRepository.findByAutor(autor);
    }

    public List<Livro> findByTitulo(String titulo){
        if (livroRepository.existsByTitulo(titulo).size() == 0){
            throw new LivroNaoEncontrado();
        }
        return livroRepository.findByTitulo(titulo);
    }

    public List<Livro> findByCategoria(Categoria categoria){
        return this.livroRepository.findByCategoria(categoria);
    }

}
