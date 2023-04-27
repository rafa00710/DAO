package br.com.example.model.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(name = "getAllPessoas", query = "SELECT * FROM TB_PESSOA  WHERE nome LIKE :nome "),
        @NamedNativeQuery(name = "countAllPessoas", query = "SELECT COUNT(*) FROM tb_pessoa WHERE nome LIKE " +
                ":nome")
})




@Getter
@Setter
@Entity
@Table(name = "tb_pessoa")
public class PessoaDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long Id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "idade", nullable = false, length = 80)
    private Integer idade;


}
