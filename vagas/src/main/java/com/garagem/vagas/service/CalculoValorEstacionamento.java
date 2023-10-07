package com.garagem.vagas.service;

import com.garagem.vagas.dto.DadosRemoverForm;
import com.garagem.vagas.model.Veiculo;
import com.garagem.vagas.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class CalculoValorEstacionamento {

    @Autowired
    private VeiculoRepository repository;

    public int calcular(DadosRemoverForm dados){

        Veiculo veiculo = repository.findByPlaca(dados.placa())
                .orElseThrow(() -> new RuntimeException("Veiculo não encontrado!"));

        var horaEntrada = veiculo.getHora_entrada();
        var horaSaida = LocalDateTime.now();
        var diferencaEmMinutos = horaEntrada.until(horaSaida, ChronoUnit.MINUTES);


        if(diferencaEmMinutos < 5){
            return 5;
        }

        if (diferencaEmMinutos < 10){
            return 10;
        }

        if (diferencaEmMinutos < 20){
            return 20;
        }

        if (diferencaEmMinutos < 30){
            return 30;
        }

        return 50;
    }

}
