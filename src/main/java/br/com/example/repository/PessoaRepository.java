package br.com.example.repository;

import br.com.example.model.dao.PessoaDAO;
import br.com.example.specification.PessoaSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaDAO, Long>, PessoaSpecification {

}
