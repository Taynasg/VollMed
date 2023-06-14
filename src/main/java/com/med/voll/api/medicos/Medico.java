package com.med.voll.api.medicos;

import com.med.voll.api.consultas.Consulta;
import com.med.voll.api.endereco.Endereco;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Table(name = "MEDICO")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    @ManyToMany(mappedBy = "idMedico", cascade = CascadeType.ALL)
    private List<Consulta> consultas;

    public Medico(DadosDeCadastroDeMedicos dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarDados(DadosDeAtualizacaoDeMedicos dadosAtualizados) {
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
