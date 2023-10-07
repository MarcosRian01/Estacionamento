package com.garagem.vagas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.garagem.vagas.model.Veiculo;
import com.garagem.vagas.service.CalculoValorEstacionamento;

import java.time.LocalDateTime;

public record DadosRemoverView(
        Long id,
        String marcaModelo,
        String placa,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime horaSaida,
        int valorGaragem) {

    public DadosRemoverView(Veiculo veiculo) {
        this(veiculo.getId(), veiculo.getMarca_modelo(), veiculo.getPlaca(), veiculo.getHora_saida(), veiculo.getValor_garagem());
    }

}
