package com.med.voll.api.controller;

import com.med.voll.api.paciente.DadosDeCadastroDePacientes;
import com.med.voll.api.paciente.Paciente;
import com.med.voll.api.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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


}
