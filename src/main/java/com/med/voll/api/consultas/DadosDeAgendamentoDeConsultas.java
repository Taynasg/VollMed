package com.med.voll.api.consultas;

import com.med.voll.api.medicos.Medico;
import com.med.voll.api.paciente.Paciente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosDeAgendamentoDeConsultas(


        @NotNull @Valid
        Paciente paciente,

        @NotNull @Valid
        Medico medico,

        @NotNull @Valid
        Date dataDaConsulta,

       @NotNull @Valid
        Date horaDaConsulta


) {
}
