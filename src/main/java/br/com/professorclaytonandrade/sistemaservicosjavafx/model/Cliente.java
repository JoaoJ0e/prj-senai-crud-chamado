package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.ClienteDto;

public class Cliente extends Pessoa{

    public Cliente(){
        super();
    }

    public Cliente(Long id, String nome, String email, String senha, String cpf) {
        super(id, nome, email, senha, cpf);
    }

    public Cliente(ClienteDto clienteDto) {
        super(
            (clienteDto.getId() != null) ? clienteDto.getId() : null,
            clienteDto.getNome(),
            clienteDto.getEmail(),
            clienteDto.getSenha(),
            clienteDto.getCpf()
        );
        this.dataCriacao = clienteDto.getDataCriacao();
    }

}