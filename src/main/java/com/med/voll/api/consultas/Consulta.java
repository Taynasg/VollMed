package com.med.voll.api.consultas;

import com.med.voll.api.medicos.Medico;
import com.med.voll.api.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity(name = "Consulta")
@Table(name = "CONSULTA")
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "ID_MEDICO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;


    @JoinColumn(name = "ID_PACIENTE")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;


    private Date data;

    private  Date hora;

    public Consulta(DadosDeAgendamentoDeConsultas consultas) {
        this.medico = consultas.medico();
        this.paciente = consultas.paciente();
        this.data = consultas.dataDaConsulta();
        this.hora = consultas.horaDaConsulta();
    }

}
