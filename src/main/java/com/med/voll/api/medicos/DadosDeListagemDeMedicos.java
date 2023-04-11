package com.med.voll.api.medicos;

public record DadosDeListagemDeMedicos(Long id,String nome, String email, String crm, Especialidade especialidade) {
    public DadosDeListagemDeMedicos(Medico medico) {
    this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
