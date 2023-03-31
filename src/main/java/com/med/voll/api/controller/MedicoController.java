package com.med.voll.api.controller;

import com.med.voll.api.medicos.DadosDeCadastroMedicos;
import com.med.voll.api.medicos.Medico;
import com.med.voll.api.medicos.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosDeCadastroMedicos dados) {
        repository.save(new Medico(dados));
    }
}