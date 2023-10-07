package com.garagem.vagas.repository;

import com.garagem.vagas.model.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Page<Veiculo> findAll(Pageable pageable);

    Optional<Veiculo> findByPlaca(String placa);

    @Query("""
    select vagas from Veiculo
    order by vagas asc
    """)
    List<Long> findByVagas();
}
