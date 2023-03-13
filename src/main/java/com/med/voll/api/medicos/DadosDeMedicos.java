package com.med.voll.api.medicos;

import com.med.voll.api.endereco.Endereco;

public record DadosDeMedicos(String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {
}
