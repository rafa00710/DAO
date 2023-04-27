package br.com.example.service.impl;

import br.com.example.repository.PessoaRepository;
import br.com.example.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Override
    public boolean existsByDocumentId(Long id) {
        return repository.existsById(id);
    }
}
