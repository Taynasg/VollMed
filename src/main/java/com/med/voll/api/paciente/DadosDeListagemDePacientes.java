package com.med.voll.api.paciente;

public record DadosDeListagemDePacientes(Long id,String nome, String email, String cpf, String telefone) {

public DadosDeListagemDePacientes(Paciente paciente){
    this(paciente.getId(),paciente.getNome(), paciente.getEmail(), paciente.getCpf(),paciente.getTelefone());
}





}
