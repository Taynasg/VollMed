package com.med.voll.api.medicos;

public record DadosDeListagemDeMedicos(String nome, String email, String crm, Especialidade especialidade) {
    public DadosDeListagemDeMedicos(Medico medico) {
    this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
