package br.com.example.controller;

import br.com.example.model.dao.PessoaDAO;
import br.com.example.model.dto.PessoaFilterDTO;
import br.com.example.model.dto.PageableRequestDTO;
import br.com.example.repository.PessoaRepository;
import br.com.example.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor


public class PessoaController {

    @Autowired
    PessoaRepository repository;


    @Autowired
    private PessoaService service;


    @GetMapping("/pessoa")
    public Page<PessoaDAO> getAllPessoas(@RequestParam (value = "nome")String nome) {
        PageableRequestDTO pageableRequestDTO = new PageableRequestDTO();
        pageableRequestDTO.setLimit(20);
        pageableRequestDTO.setSort("");
        pageableRequestDTO.setOffset(0);
        PessoaFilterDTO pessoaFilterDTO = new PessoaFilterDTO();
        pessoaFilterDTO.setNome(nome);

        return repository.getAllPessoas(pageableRequestDTO, pessoaFilterDTO);
    }


    @GetMapping("/pessoa/{id}")
    public PessoaDAO getPessoaById(@PathVariable Long id) {
        return repository.findById(id).get();
    }


    @PostMapping("/pessoa")
    public PessoaDAO savePessoa(@RequestBody PessoaDAO pessoa) {
        return repository.save(pessoa);

    }

    @DeleteMapping("/pessoa/{id}")
    public void deletePessoa(@PathVariable Long id) {
        repository.deleteById(id);
    }


    @PutMapping("/pessoa/{id}")
    public PessoaDAO atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDAO pessoa) throws Exception {
        //  var atualizar = getPessoaById(id);

        var atualizar = repository.findById(id);

        if (atualizar.isPresent()) {
            // var salvarPessoa = new Pessoa();
            var salvarPessoa = atualizar.get();
            salvarPessoa.setId(id);
            salvarPessoa.setIdade(pessoa.getIdade());
            salvarPessoa.setNome(pessoa.getNome());

            repository.save(salvarPessoa);
            return salvarPessoa;


        } else {
            throw new Exception("Pessoa não encontrada");
        }



    }


}
