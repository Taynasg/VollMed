package com.med.voll.api.consultas;

import jakarta.validation.constraints.NotNull;

public record DadosDeCancelamentoDeConsultas (

    @NotNull
    Long idConsulta,

    @NotNull
    MotivoDoCancelamento motivoDoCancelamento

){

}
