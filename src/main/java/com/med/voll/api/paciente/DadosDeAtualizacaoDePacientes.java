package com.med.voll.api.paciente;

import com.med.voll.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosDeAtualizacaoDePacientes(

        @NotNull
        Long id,

        String nome,

        String telefone,

        DadosEndereco dadosEndereco) {




}
