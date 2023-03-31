package com.med.voll.api.endereco;


import com.med.voll.api.paciente.DadosDePaciente;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Essa anotação indica os dados de qual classe
// farão parte de outra tabela
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.bairro = dados.bairro();
        this.complemento = dados.complemento();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.cep = dados.cep();

    }
}

