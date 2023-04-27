package br.com.example.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class PessoaFilterDTO {
    @NonNull
    private String nome;
    private Integer id;
}
