package com.med.voll.api.paciente;

public record DadosDeListagemDePacientes(String nome, String email, String cpf) {

public DadosDeListagemDePacientes(Paciente paciente){
    this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
}





}
