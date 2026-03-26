package com.facens.biblioteca_api.service;

import com.facens.biblioteca_api.repository.LivroRepository;

public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }


}
