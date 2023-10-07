package com.garagem.vagas.service;

import com.garagem.vagas.dto.DadosCadastrarForm;
import com.garagem.vagas.dto.DadosDetalhamentoView;
import com.garagem.vagas.dto.DadosRemoverForm;
import com.garagem.vagas.dto.DadosRemoverView;
import com.garagem.vagas.model.Veiculo;
import com.garagem.vagas.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.*;

@Service
public class ServiceVagas {

    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private CalculoValorEstacionamento calculoValorEstacionamento;

    /*Cadastro de veiculo no banco*/
    public DadosDetalhamentoView entrar(DadosCadastrarForm dados) {
        var veiculo = new Veiculo(dados);

        List<Long> vagaDisponivel = repository.findByVagas();

        if (vagaDisponivel.size() == 40){
            throw new RuntimeException("Número de vagas disponveis acabaram!");
        }

        /* Verificar se o id é existente */
        if (!vagaDisponivel.isEmpty() && vagaDisponivel.get(0) != null) {
            for (int x = 0; x < vagaDisponivel.size(); x++) {
                if (vagaDisponivel.get(x) == (x + 1)){
                    Long vagaNova = vagaDisponivel.get(x) + 1;
                    veiculo.setVaga_estacionamento(vagaNova);
                } else {
                    Long vagaNova = (long) x + 1;
                    veiculo.setVaga_estacionamento(vagaNova);
                    break;
                }
            }
        } else if (vagaDisponivel.isEmpty() || vagaDisponivel.get(0) == null) {
            Long vagaNova = 1L;
            veiculo.setVaga_estacionamento(vagaNova);
        }

        repository.save(veiculo);

        return new DadosDetalhamentoView(veiculo);
    }

    /*Remoção de veiculo no banco com calculo do valor de estacionamento*/
    public DadosRemoverView sair(DadosRemoverForm dados) {
        Optional<Veiculo> placaVeiculo = repository.findByPlaca(dados.placa());

        if (placaVeiculo.isPresent()) {
            Veiculo veiculo = placaVeiculo.get();

            Integer valorGaragem = calculoValorEstacionamento.calcular(dados);
            veiculo.setValor_garagem(valorGaragem);
            veiculo.sairVeiculo(dados);

            repository.delete(veiculo);

            return new DadosRemoverView(veiculo);

        } else {
            throw new RuntimeException("Veículo não encontrado");
        }
    }
}
