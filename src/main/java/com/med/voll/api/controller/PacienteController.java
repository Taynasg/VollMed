package com.med.voll.api.controller;

import com.med.voll.api.paciente.*;
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
    public Page<DadosDeListagemDePacientes> listar(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDeListagemDePacientes::new);

    }

    @PutMapping
    @Transactional
    public void atualizarDados(@RequestBody @Valid DadosDeAtualizacaoDePacientes dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarDados(dados);
    }

    //Exclusão do Banco de Dados
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id) {
//        repository.deleteById(id);
//    }


// Exclusão Lógica

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();

    }
}

