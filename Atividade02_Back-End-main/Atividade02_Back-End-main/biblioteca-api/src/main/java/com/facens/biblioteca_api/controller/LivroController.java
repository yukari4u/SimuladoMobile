package com.facens.biblioteca_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.facens.biblioteca_api.model.Livro;
import com.facens.biblioteca_api.service.LivroService;

@RestController

@RequestMapping

public class LivroController {
    
    private final LivroService livroService;

    public LivroController (LivroService livroService){
        this.livroService = livroService;
    }

    @GetMapping
    public List<Livro> listarLivros(){
        return livroService.listarTodos();
    }

    @GetMapping("/teste")
    public String testeAPI() {
        return "API funcionando corretamente";
    }

    @GetMapping("/{id}")
    public Livro buscaPorId(PathVariable Long id){
        return livroService.buscaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro criarLivro(@RequestBody Livro livro) {
        return livroService.criarLivro(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        return livroService.atualizarLivro(id, livro);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerLivro(@PathVariable Long id) {
        return livroService.deletar(id);
    }

    @PutMapping("/{id}/emprestar")
    public Livro emprestarLivro(@PathVariable Long id) {    
        return livroService.emprestar(id);
    }

    @PutMapping("/{id}/devolver")
    public Livro devolverLivro(@PathVariable Long id) {
        return livroService.devolver(id);
    }
 
}
