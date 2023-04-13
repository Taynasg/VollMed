package com.med.voll.api.medicos;

import com.med.voll.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor


// Vai gerar um código a aleatório e fazer
// a comparação apenas pelo Id
@EqualsAndHashCode(of = "id")

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    //Converte o Enum para o valor ordinal ou String
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    //Essa anotação determina que quando a tabela médico for gerada,
    //os dados contidos no 'objeto dadosEndereco"
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosDeCadastroDeMedicos dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizandoDados(DadosDeAtualizacaoDeMedicos dadosAtualizados) {
        if (dadosAtualizados.nome() != null) {
            this.nome = dadosAtualizados.nome();
        }
        if (dadosAtualizados.telefone() != null) {
            this.telefone = dadosAtualizados.telefone();
        }
        if (dadosAtualizados.dadosEndereco() != null) {
            this.endereco.atualizar(dadosAtualizados.dadosEndereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}

