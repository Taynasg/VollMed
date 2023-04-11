package com.med.voll.api.medicos;

import com.med.voll.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosDeAtualizacaoDeMedicos(

        @NotNull
        Long id,

        String nome,

        String telefone,

        DadosEndereco dadosEndereco) {
}
