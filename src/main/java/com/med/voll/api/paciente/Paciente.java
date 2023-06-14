package com.med.voll.api.paciente;

import com.med.voll.api.consultas.Consulta;
import com.med.voll.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.lang.Long;

import java.util.List;


@Entity(name = "Paciente")
@Table(name = "PACIENTE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    @ManyToMany(mappedBy = "idPaciente", cascade = CascadeType.ALL)
    private List<Consulta> medicos;

    public Paciente(DadosDeCadastroDePacientes dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.medicos = getMedicos();

    }

    public void atualizarDados(DadosDeAtualizacaoDePacientes dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.dadosEndereco() != null) {
            this.endereco = new Endereco(dados.dadosEndereco());
        }

    }

    public void excluir() {
        this.ativo = false;



        }
    }
