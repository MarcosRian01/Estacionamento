package com.garagem.vagas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastrarForm(
        @NotNull
        String marcaModelo,
        @NotNull
        String placa,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime horaEntrada,
        Long vagaEstacionamento
) {

}
