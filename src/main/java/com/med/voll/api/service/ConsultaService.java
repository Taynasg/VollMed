package com.med.voll.api.service;
import com.med.voll.api.consultas.Consulta;
import com.med.voll.api.consultas.ConsultasRepository;
import com.med.voll.api.consultas.DadosDeAgendamentoDeConsultas;
import com.med.voll.api.consultas.DadosDeCancelamentoDeConsultas;
import com.med.voll.api.exceptions.*;
import com.med.voll.api.medicos.Medico;
import com.med.voll.api.medicos.MedicoRepository;
import com.med.voll.api.paciente.Paciente;
import com.med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConsultaService {
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final ConsultasRepository consultasRepository;

    @Autowired
    public ConsultaService(MedicoRepository medicoRepository, PacienteRepository pacienteRepository, ConsultasRepository consultasRepository) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.consultasRepository = consultasRepository;
    }



    public void cancelarConsulta(DadosDeCancelamentoDeConsultas dados) throws ConsultaNaoPodeSerCanceladaException, ConsultaNaoEncontradaException {
        Optional<Consulta> consultaOptional = consultasRepository.findById(dados.idConsulta());
        if (consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime consultaDateTime = LocalDateTime.from(consulta.getHoraInicio());
            LocalDateTime limiteCancelamento = now.plusHours(24);
            if (limiteCancelamento.isBefore(consultaDateTime)) {
              consulta.desativar();
                System.out.println("Consulta cancelada com sucesso!");
            } else {
                throw new ConsultaNaoPodeSerCanceladaException("A consulta não pode ser desmarcada sem 24 horas de antecedência");
            }
        } else {
            throw new ConsultaNaoEncontradaException("Consulta não encontrada");
        }
    }
    public void agendarConsulta(DadosDeAgendamentoDeConsultas dados) throws PacienteOuMedicoInativoNoSistemaException,
            MedicoOuPacienteNotFoundException, MedicoEscolhidoNaoDisponivelExcption, PacienteNaoPodeMarcarException,
            ConsultaComDuracaoInvalidaException, HoraDaConsultaInvalidaExption, ConsultaComAntecedenciaInsuficienteException,
            ConsultaComDataInvalidaException {

        Optional<Medico> medicoOptional = medicoRepository.findByIdAndAtivoTrue(dados.idMedico());
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(dados.idPaciente());

        if (medicoOptional.isPresent()) {
            var medico = medicoOptional.get();

            if (!medico.getAtivo()) {
                throw new PacienteOuMedicoInativoNoSistemaException("Não é permitido agendar consultas com médicos inativos no sistema");
            } else {
                Optional<Consulta> consultaMedicoOptional = consultasRepository.findByIdMedicoAndDataDaConsulta(medico.getId(), dados.dataDaConsulta());
                if (consultaMedicoOptional.isPresent()) {
                    throw new MedicoEscolhidoNaoDisponivelExcption("O(a) médico(a) escolhido não está disponível para consultas no momento");
                }
            }
        } else {
            throw new MedicoOuPacienteNotFoundException("O médico informado não está cadastrado");
        }

        if (pacienteOptional.isPresent()) {
            var paciente = pacienteOptional.get();

            if (!paciente.getAtivo()) {
                throw new PacienteOuMedicoInativoNoSistemaException("Não é permitido agendar consultas com pacientes inativos no sistema");
            } else {
                Optional<Consulta> consultaPacienteOptional = consultasRepository.findByIdPacienteAndDataDaConsulta(paciente.getId(), dados.dataDaConsulta());
                if (consultaPacienteOptional.isPresent()) {
                    throw new PacienteNaoPodeMarcarException("Esse paciente não pode marcar uma consulta no momento pois ele já marcou uma consulta " +
                            "no dia de hoje, espere 24 horas e tente novamente");
                }
            }

        } else {
            throw new MedicoOuPacienteNotFoundException("O paciente informado não está cadastrado");
        }

        Duration duration = Duration.between(dados.horaInicio(), dados.horaFim());
        if (duration.getSeconds() != 3600) {
            throw new ConsultaComDuracaoInvalidaException("A duração da consulta deve ser de exatamente 1 hora");
        }

        if (dados.horaInicio().getHour() < 7 || dados.horaFim().getHour() > 19) {
            throw new HoraDaConsultaInvalidaExption("Só é permitido marcar consultas no horário comercial de 7:00 às 19:00 horas");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime consultaDateTime = LocalDateTime.of(dados.dataDaConsulta(), dados.horaInicio());

        if (consultaDateTime.isBefore(now.plusDays(1))) {
            throw new ConsultaComDataInvalidaException("Não é permitido marcar consultas com datas inferiores a " + LocalDate.now());
        }

        if (consultaDateTime.isBefore(now.plusMinutes(30))) {
            throw new ConsultaComAntecedenciaInsuficienteException("A consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }

        consultasRepository.save(new Consulta(dados));
    }

}
