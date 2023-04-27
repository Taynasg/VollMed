package com.med.voll.api.controller;

import com.med.voll.api.consultas.Consulta;
import com.med.voll.api.consultas.ConsultasRepository;
import com.med.voll.api.consultas.DadosDeAgendamentoDeConsultas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {
    @Autowired
    private ConsultasRepository repository;

    @PostMapping
    @Transactional
    public void agendar(@Valid @RequestBody DadosDeAgendamentoDeConsultas dados) {
        repository.save(new Consulta(dados));
    }

}
