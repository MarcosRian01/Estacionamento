package com.garagem.vagas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.garagem.vagas.model.Veiculo;

import java.time.LocalDateTime;

public record DadosDetalhamentoView(
        Long id,
        String marcaModelo,
        String placa,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime horaEntrada,
        Long vagas) {

    public DadosDetalhamentoView(Veiculo veiculo){
        this(veiculo.getId(), veiculo.getMarca_modelo(), veiculo.getPlaca(), veiculo.getHora_entrada(), veiculo.getVagas());
    }

}
