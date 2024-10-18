package br.com.professorclaytonandrade.sistemaservicosjavafx.service;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dao.ClienteDao;
import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.ClienteDto;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    private final ClienteDao clienteDao;

    public ClienteService() {
        this.clienteDao = new ClienteDao();
    }

    public boolean criar(ClienteDto clienteDto) {
        Cliente cliente = new Cliente(clienteDto);
        return clienteDao.inserir(cliente);
    }

    public boolean atualizar(ClienteDto clienteDto) {
        Cliente cliente = new Cliente(clienteDto);
        return clienteDao.atualizar(cliente);
    }

    public Cliente buscarPorId(Long id) {
        Cliente cliente = clienteDao.buscarPorId(id);
        return cliente;
    }

    public void remover(Long id) {
        clienteDao.excluir(id);
    }

    public List<ClienteDto> listarTodos() {
        List<Cliente> clientes = clienteDao.listar();  // Busca a lista de Cliente
        return clientes.stream()
                .map(cliente -> new ClienteDto(cliente))  // Mapeia cada Cliente para um ClienteDto
                .collect(Collectors.toList());  // Coleta o resultado em uma lista
    }

}
