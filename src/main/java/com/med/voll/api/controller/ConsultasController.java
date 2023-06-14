package com.med.voll.api.controller;

import com.med.voll.api.consultas.ConsultasRepository;
import com.med.voll.api.consultas.DadosDeAgendamentoDeConsultas;
import com.med.voll.api.consultas.DadosDeCancelamentoDeConsultas;
import com.med.voll.api.exceptions.*;
import com.med.voll.api.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {

    private final ConsultasRepository repository;
    private final ConsultaService consultaService;

    @Autowired
    public ConsultasController(ConsultasRepository repository, ConsultaService consultaService) {
        this.repository = repository;
        this.consultaService = consultaService;
    }

    @PostMapping("/{id}")
    @Transactional
    public void cancelarConsulta(@Valid @RequestBody DadosDeCancelamentoDeConsultas dados) throws ConsultaNaoPodeSerCanceladaException, ConsultaNaoEncontradaException {
        consultaService.cancelarConsulta(dados);
    }

    @PostMapping()
    @Transactional
    public void agendarConsulta(@Valid @RequestBody DadosDeAgendamentoDeConsultas dados) throws PacienteOuMedicoInativoNoSistemaException,
            MedicoOuPacienteNotFoundException, PacienteNaoPodeMarcarException,
            ConsultaComDuracaoInvalidaException, ConsultaComAntecedenciaInsuficienteException, ConsultaComDataInvalidaException,
            HoraDaConsultaInvalidaExption, MedicoEscolhidoNaoDisponivelExcption {
            consultaService.agendarConsulta(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void desativarConsulta(@PathVariable Long id) {
        var consulta = repository.getReferenceById(id);
        consulta.desativar();
    }
}
