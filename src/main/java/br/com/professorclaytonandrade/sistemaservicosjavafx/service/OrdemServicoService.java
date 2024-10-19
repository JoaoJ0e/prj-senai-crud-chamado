package br.com.professorclaytonandrade.sistemaservicosjavafx.service;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dao.OrdemServicoDao;
import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.OrdemServicoDto;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.OrdemServico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OrdemServicoService {

    private static final Logger logger = LoggerFactory.getLogger(OrdemServicoService.class);
    private final OrdemServicoDao ordemServicoDao;

    public OrdemServicoService() {
        this.ordemServicoDao = new OrdemServicoDao();
    }

    public boolean criar(OrdemServicoDto ordemServicoDto) {
        OrdemServico ordemServico = new OrdemServico(ordemServicoDto);
        return ordemServicoDao.inserir(ordemServico);
    }

    public boolean atualizar(OrdemServicoDto ordemServicoDto) {
        OrdemServico ordemServico = new OrdemServico(ordemServicoDto);
        return ordemServicoDao.atualizar(ordemServico);
    }

    public OrdemServico buscarPorId(Long id) {
        return ordemServicoDao.buscarPorId(id);
    }

    public void remover(Long id) {
        ordemServicoDao.excluir(id);
    }

    public List<OrdemServicoDto> listarTodos() {
        List<OrdemServico> ordensServico = ordemServicoDao.listar();  // Busca a lista de OrdemServico
        return ordensServico.stream()
                .map(OrdemServicoDto::new)  // Mapeia cada OrdemServico para um OrdemServicoDto
                .collect(Collectors.toList());  // Coleta o resultado em uma lista
    }
}
