package com.med.voll.api.controller;

import com.med.voll.api.paciente.DadosDePaciente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @PostMapping
    public  void cadatrar(@RequestBody DadosDePaciente dados){
        System.out.println(dados);


    }


}
