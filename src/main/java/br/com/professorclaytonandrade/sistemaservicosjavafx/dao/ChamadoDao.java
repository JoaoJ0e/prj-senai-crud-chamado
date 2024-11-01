package br.com.professorclaytonandrade.sistemaservicosjavafx.dao;

import br.com.professorclaytonandrade.sistemaservicosjavafx.config.conexao.FabricaDeConexao;
import br.com.professorclaytonandrade.sistemaservicosjavafx.enums.PrioridadeEnum;
import br.com.professorclaytonandrade.sistemaservicosjavafx.enums.StatusChamadoEnum;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Chamado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChamadoDao {
    private static final Logger logger = LoggerFactory.getLogger(ChamadoDao.class);

    // Método para inserir um chamado no banco de dados
    public boolean inserir(Chamado chamado) {
        boolean isInserido = false;
        chamado.setId(null);

        String sql = "INSERT INTO chamado (titulo, observacoes, prioridade, status, tecnico_id, cliente_id, " +
                "data_abertura, data_fechamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = FabricaDeConexao.obterConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, chamado.getTitulo());
            statement.setString(2, chamado.getObservacoes());
            statement.setInt(3, chamado.getPrioridade().getCodigo());
            statement.setInt(4, chamado.getStatus().getCodigo());
            statement.setLong(5, chamado.getTecnicoId());
            statement.setLong(6, chamado.getClienteId());
            statement.setDate(7, Date.valueOf(chamado.getDataAbertura()));
            statement.setDate(8, chamado.getDataFechamento() != null ?
                    Date.valueOf(chamado.getDataFechamento()) : null);

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                isInserido = true;
                logger.info("Chamado inserido com sucesso: {}", chamado.getTitulo());
            }
        } catch (SQLException e) {
            logger.error("Erro ao inserir chamado: {}", e.getMessage());
        }
        return isInserido;
    }

    // Método para listar todos os chamados
    public List<Chamado> listar() {
        List<Chamado> chamados = new ArrayList<>();
        String sql = "SELECT * FROM chamado";

        try (Connection conexao = FabricaDeConexao.obterConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                chamados.add(extrairChamadoDoResultSet(resultado));
            }
            logger.info("Lista de chamados recuperada com sucesso.");
        } catch (SQLException e) {
            logger.error("Erro ao listar chamados: {}", e.getMessage());
        }
        return chamados;
    }

    // Método para buscar um chamado pelo ID
    public Chamado buscarPorId(Long id) {
        Chamado chamado = null;
        String sql = "SELECT * FROM chamado WHERE id = ?";

        try (Connection conexao = FabricaDeConexao.obterConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                chamado = extrairChamadoDoResultSet(resultado);
                logger.info("Chamado encontrado: ID {}", chamado.getId());
            } else {
                logger.warn("Nenhum chamado encontrado com o ID: {}", id);
            }
        } catch (SQLException e) {
            logger.error("Erro ao buscar chamado por ID: {}", e.getMessage());
        }
        return chamado;
    }

    // Método para encerrar um chamado
    public boolean encerrarChamado(Long id) {
        boolean isEncerrado = false;
        String sql = "UPDATE chamado SET status = ?, data_fechamento = ? WHERE id = ? AND status != ?";

        try (Connection conexao = FabricaDeConexao.obterConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, StatusChamadoEnum.ENCERRADO.getCodigo());
            statement.setDate(2, Date.valueOf(LocalDate.now()));
            statement.setLong(3, id);
            statement.setInt(4, StatusChamadoEnum.ENCERRADO.getCodigo());

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                isEncerrado = true;
                logger.info("Chamado encerrado com sucesso: ID {}", id);
            }
        } catch (SQLException e) {
            logger.error("Erro ao encerrar chamado: {}", e.getMessage());
        }
        return isEncerrado;
    }

    // Método auxiliar para extrair um chamado do ResultSet
    private Chamado extrairChamadoDoResultSet(ResultSet resultado) throws SQLException {
        Chamado chamado = new Chamado();
        chamado.setId(resultado.getLong("id"));
        chamado.setTitulo(resultado.getString("titulo"));
        chamado.setObservacoes(resultado.getString("observacoes"));
        chamado.setPrioridade(PrioridadeEnum.toEnum(resultado.getInt("prioridade")));
        chamado.setStatus(StatusChamadoEnum.toEnum(resultado.getInt("status")));
        chamado.setTecnicoId(resultado.getLong("tecnico_id"));
        chamado.setClienteId(resultado.getLong("cliente_id"));
        chamado.setDataAbertura(resultado.getDate("data_abertura").toLocalDate());

        Date dataFechamento = resultado.getDate("data_fechamento");
        if (dataFechamento != null) {
            chamado.setDataFechamento(dataFechamento.toLocalDate());
        }

        return chamado;
    }
}