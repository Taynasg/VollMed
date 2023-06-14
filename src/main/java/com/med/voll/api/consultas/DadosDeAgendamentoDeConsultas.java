package com.med.voll.api.consultas;
import java.lang.Long;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;


public record DadosDeAgendamentoDeConsultas(
        @NotNull
        Long idPaciente,

        @NotNull
        Long idMedico,

        @NotNull @Valid
        LocalDate dataDaConsulta,

        @NotNull @Valid
        LocalTime horaInicio,


        @NotNull @Valid
        LocalTime horaFim

) {

}
