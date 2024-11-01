package br.com.professorclaytonandrade.sistemaservicosjavafx.service;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dao.ChamadoDao;
import br.com.professorclaytonandrade.sistemaservicosjavafx.dto.ChamadoDto;
import br.com.professorclaytonandrade.sistemaservicosjavafx.enums.StatusChamadoEnum;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Chamado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ChamadoService {
    private static final Logger logger = LoggerFactory.getLogger(ChamadoService.class);
    private final ChamadoDao chamadoDao;

    public ChamadoService() {
        this.chamadoDao = new ChamadoDao();
    }

    public boolean criar(ChamadoDto chamadoDto) {
        chamadoDto.setStatus(StatusChamadoEnum.ABERTO); // Garante que novo chamado sempre começa aberto
        Chamado chamado = new Chamado(chamadoDto);
        return chamadoDao.inserir(chamado);
    }

    public List<ChamadoDto> listarTodos() {
        List<Chamado> chamados = chamadoDao.listar();
        return chamados.stream()
                .map(ChamadoDto::new)
                .collect(Collectors.toList());
    }

    public Chamado buscarPorId(Long id) {
        return chamadoDao.buscarPorId(id);
    }

    public boolean encerrarChamado(Long id) {
        Chamado chamado = buscarPorId(id);
        if (chamado == null) {
            logger.warn("Tentativa de encerrar chamado inexistente: {}", id);
            return false;
        }

        if (chamado.getStatus() == StatusChamadoEnum.ENCERRADO) {
            logger.warn("Tentativa de encerrar chamado já encerrado: {}", id);
            return false;
        }

        return chamadoDao.encerrarChamado(id);
    }
}