package com.garagem.vagas.controller;

import com.garagem.vagas.dto.DadosCadastrarForm;
import com.garagem.vagas.dto.DadosDetalhamentoView;
import com.garagem.vagas.dto.DadosRemoverForm;
import com.garagem.vagas.dto.DadosRemoverView;
import com.garagem.vagas.repository.VeiculoRepository;
import com.garagem.vagas.service.ServiceVagas;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estacionamento")
public class VeiculoController {

    @Autowired
    private ServiceVagas vagaestacionamento;

    @Autowired
    private VeiculoRepository repository;
    private Pageable pageable;

    @PostMapping("/entrar")
    @Transactional
    public ResponseEntity<DadosDetalhamentoView> cadastrar(@RequestBody @Valid DadosCadastrarForm dados){
        var dto = vagaestacionamento.entrar(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoView>> listar(@PageableDefault(sort = {"vagas"}) Pageable pageable){
        var page = repository.findAll(pageable).map(DadosDetalhamentoView::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/sair")
    @Transactional
    public ResponseEntity<DadosRemoverView> sair(@RequestBody @Valid DadosRemoverForm dados) {
        var dto = vagaestacionamento.sair(dados);
        return ResponseEntity.ok(dto);
    }
}
