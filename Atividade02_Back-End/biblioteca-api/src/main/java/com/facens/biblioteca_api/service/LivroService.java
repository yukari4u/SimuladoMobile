package com.facens.biblioteca_api.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.facens.biblioteca_api.model.Livro;
import com.facens.biblioteca_api.repository.LivroRepository;

public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpsStatus.NOT_FOUND, 
        "Livro não encontrado"
        ));
    }

    public Livro criar(Livro livro) {
        livro.setId(null);
        if (livro.getEmprestado() == null) {
            livro.setEmprestado(false);
        }
        if (Boolean.FALSE.equals(livro.getEmprestado())) {
            livro.setDataEmprestimo(null);
        }
        return livroRepository.save(livro);
    }

    public Livro atualizar(Long id, Livro livroAtualizado) {
        Livro livroExistente = buscarPorId(id);
            livroExistente.setTitulo(livroAtualizado.getTitulo());
            livroExistente.setAutor(livroAtualizado.getAutor());
        

    }

}

