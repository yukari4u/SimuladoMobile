package com.facens.biblioteca_api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service; // Importação necessária
import org.springframework.web.server.ResponseStatusException;

import com.facens.biblioteca_api.model.Livro;
import com.facens.biblioteca_api.repository.LivroRepository;

@Service // Essencial para o Spring injetar o Service no Controller
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
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
        
        if (livroAtualizado.getEmprestado() != null) {
            livroExistente.setEmprestado(livroAtualizado.getEmprestado());

            if (Boolean.TRUE.equals(livroAtualizado.getEmprestado())) {
                if (livroAtualizado.getDataEmprestimo() == null) {
                    livroExistente.setDataEmprestimo(LocalDate.now());
                } else {
                    livroExistente.setDataEmprestimo(livroAtualizado.getDataEmprestimo());
                }
            } else {
                livroExistente.setDataEmprestimo(null);
            }
        }
        return livroRepository.save(livroExistente);
    }

    public void deletar(Long id) {
        Livro livro = buscarPorId(id); 
        livroRepository.delete(livro);
    }

    public Livro emprestar(Long id) {
        Livro livro = buscarPorId(id);
        if (Boolean.TRUE.equals(livro.getEmprestado())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livro já está emprestado");
        }
        livro.setEmprestado(true);
        livro.setDataEmprestimo(LocalDate.now());
        return livroRepository.save(livro);
    }

    public Livro devolver(Long id) {
        Livro livro = buscarPorId(id);
        livro.setEmprestado(false);
        livro.setDataEmprestimo(null);
        return livroRepository.save(livro);
    }
}