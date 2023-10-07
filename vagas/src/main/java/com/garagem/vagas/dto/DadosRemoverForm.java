package com.garagem.vagas.dto;

import java.time.LocalDateTime;

public record DadosRemoverForm(
        String marcaModelo,
        String placa,
        LocalDateTime horaSaida,
        int valorGaragem
) {
}
