package com.med.voll.api.controller;
import com.med.voll.api.medicos.DadosDeMedicos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @PostMapping
    public void cadastrar(@RequestBody DadosDeMedicos dados) {
        System.out.println(dados);
    }

}