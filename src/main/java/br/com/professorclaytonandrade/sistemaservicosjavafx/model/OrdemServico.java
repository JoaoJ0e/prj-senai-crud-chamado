package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.OrdemServicoDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdemServico{

    private Long id;
    private Long tecnicoId;
    private Long clienteId;
    private Double valor;
    private LocalDate dataCriacao;

    public OrdemServico(){
    }

    public OrdemServico(Long id, Long tecnicoId, Long clienteId, Double valor) {
        this.id = id;
        this.tecnicoId = tecnicoId;
        this.clienteId = clienteId;
        this.valor = valor;
        this.dataCriacao = LocalDate.now();
    }

    public OrdemServico(OrdemServicoDto dto) {
        this.id = dto.getId();
        this.tecnicoId = dto.getTecnicoId();
        this.clienteId = dto.getClienteId();
        this.valor = dto.getValor();
        this.dataCriacao = dto.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(Long tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}