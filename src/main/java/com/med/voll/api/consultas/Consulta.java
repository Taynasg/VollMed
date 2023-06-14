package com.med.voll.api.consultas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Consulta")
@Table(name = "CONSULTA")
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Boolean ativo;

    @JoinColumn(name = "ID_MEDICO")
    private Long idMedico;

    @JoinColumn(name = "ID_PACIENTE")
    private Long idPaciente;

    @Column(name = "DATA")
    private LocalDate dataDaConsulta;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    public Consulta(DadosDeAgendamentoDeConsultas consultas) {
        this.ativo = true;
        this.idMedico = consultas.idMedico();
        this.idPaciente = consultas.idPaciente();
        this.dataDaConsulta = consultas.dataDaConsulta();
        this.horaInicio = consultas.horaInicio();
        this.horaFim = consultas.horaFim();


    }

    public void desativar() {
        this.ativo = false;
    }
}