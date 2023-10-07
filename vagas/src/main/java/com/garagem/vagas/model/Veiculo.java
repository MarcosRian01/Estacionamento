package com.garagem.vagas.model;

import com.garagem.vagas.dto.DadosCadastrarForm;
import com.garagem.vagas.dto.DadosRemoverForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "veiculos")
@Entity(name = "Veiculo")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca_modelo;
    private String placa;
    private LocalDateTime hora_entrada;
    private LocalDateTime hora_saida;
    private Long vagas;
    private Integer valor_garagem;

    public Veiculo(DadosCadastrarForm dados) {
        this.marca_modelo = dados.marcaModelo();
        this.placa = dados.placa();
        this.hora_entrada = LocalDateTime.now();
    }

    public void sairVeiculo(DadosRemoverForm dados) {
        if (dados.marcaModelo() != null) {
            this.marca_modelo = dados.marcaModelo();
        }
        if (dados.placa() != null) {
            this.placa = dados.placa();
        }
        this.hora_saida = LocalDateTime.now();
    }

    public void setValor_garagem(Integer valorGaragem){
        this.valor_garagem = valorGaragem;
    }

    public void setVaga_estacionamento(Long vagaEstacionamento) {
        this.vagas = vagaEstacionamento;
    }
}
