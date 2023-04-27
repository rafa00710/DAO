package br.com.example.specification.impl;

import br.com.example.model.dao.PessoaDAO;
import br.com.example.model.dto.PessoaFilterDTO;
import br.com.example.model.dto.PageableRequestDTO;
import br.com.example.model.mapper.PessoaMapper;
import br.com.example.specification.PessoaSpecification;
import br.com.example.util.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

public class PessoaSpecificationImpl implements PessoaSpecification {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private QueryUtils queryUtils;

    @Override
    @ReadOnlyProperty
    public Page<PessoaDAO> getAllPessoas(PageableRequestDTO pageable, PessoaFilterDTO filter) {
        TypedQuery<Tuple> query = entityManager.createNamedQuery("getAllPessoas", Tuple.class);
        List<Tuple> result = query
                .setParameter("nome", queryUtils.parameterValueContains(filter.getNome()))
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getLimit())
                .getResultList();

        return new PageImpl<>(PessoaMapper.toPessoa(result), PageRequest.of(pageable.getOffset() / pageable.getLimit(),
                pageable.getLimit()), countAllPessoas(filter));
    }

    @Override
    public Integer countAllPessoas(PessoaFilterDTO filter) {
        Query query = entityManager.createNamedQuery("countAllPessoas");
        query.setParameter("nome", queryUtils.parameterValueContains(filter.getNome()));
        return ((BigInteger) query.getSingleResult()).intValue();
    }
}
