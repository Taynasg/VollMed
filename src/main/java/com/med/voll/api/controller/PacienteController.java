package com.med.voll.api.controller;

import com.med.voll.api.paciente.DadosDeCadastroDePacientes;
import com.med.voll.api.paciente.DadosDeListagemDePacientes;
import com.med.voll.api.paciente.Paciente;
import com.med.voll.api.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Transactional
    @PostMapping
    public void cadatrar(@RequestBody @Valid DadosDeCadastroDePacientes dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosDeListagemDePacientes> listar(@PageableDefault(page = 0, size = 2, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDeListagemDePacientes::new);

    }
}

