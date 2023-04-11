package com.med.voll.api.controller;

import com.med.voll.api.medicos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosDeCadastroDeMedicos dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosDeListagemDeMedicos> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDeListagemDeMedicos::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosDeAtualizacaoDeMedicos dadosAtualizados) {
        var medico = repository.getReferenceById(dadosAtualizados.id());
        medico.atualizandoDados(dadosAtualizados);

    }

}