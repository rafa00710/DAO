package br.com.example.specification;

import br.com.example.model.dao.PessoaDAO;
import br.com.example.model.dto.PessoaFilterDTO;
import br.com.example.model.dto.PageableRequestDTO;
import org.springframework.data.domain.Page;

public interface PessoaSpecification {
    public Page<PessoaDAO> getAllPessoas(PageableRequestDTO pageable, PessoaFilterDTO filter);
    public Integer countAllPessoas(PessoaFilterDTO filter);
}
