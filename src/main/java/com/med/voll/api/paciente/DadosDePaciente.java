package com.med.voll.api.paciente;

import com.med.voll.api.endereco.EnderecoDePaciente;

public record DadosDePaciente(String nome, String email, String telefone, String cpf, EnderecoDePaciente endereco) {
}
