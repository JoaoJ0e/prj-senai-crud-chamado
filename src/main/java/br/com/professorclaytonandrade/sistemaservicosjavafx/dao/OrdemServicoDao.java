package br.com.professorclaytonandrade.sistemaservicosjavafx.dao;

import br.com.professorclaytonandrade.sistemaservicosjavafx.config.conexao.FabricaDeConexao;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.OrdemServico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoDao {

    private static final Logger logger = LoggerFactory.getLogger(OrdemServicoDao.class);

    // Método para inserir uma ordemServico no banco de dados
    public boolean inserir(OrdemServico ordemServico) {
        boolean isInserido = false; // Variável para armazenar o status da inserção

        ordemServico.setId(null);
        String sql = "INSERT INTO ordem_servico (tecnico_id, cliente_id, valor, data_criacao) VALUES (?, ?, ?, ?)";

        try (Connection conexao = FabricaDeConexao.obterConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setLong(1, ordemServico.getTecnicoId());
            statement.setLong(2, ordemServico.getClienteId());
            statement.setDouble(3, ordemServico.getValor());
            statement.setDate(4, Date.valueOf(ordemServico.getDataCriacao()));

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                isInserido = true; // A inserção foi bem-sucedida
                logger.info("OrdemServico inserida com sucesso: ID {}", ordemServico.getId());
            }

        } catch (SQLException e) {
            logger.error("Erro ao inserir ordemServico: {}", e.getMessage());
        }
        return isInserido; // Retorna o status da inserção
    }

    // Método para listar todas as ordens de serviço
    public List<OrdemServico> listar() {
        List<OrdemServico> ordemServicos = new ArrayList<>();
        String sql = "SELECT * FROM ordem_servico";

        try (Connection conexao = FabricaDeConexao.obterConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                OrdemServico ordemServico = new OrdemServico();
                ordemServico.setId(resultado.getLong("id"));
                ordemServico.setTecnicoId(resultado.getLong("tecnico_id"));
                ordemServico.setClienteId(resultado.getLong("cliente_id"));
                ordemServico.setValor(resultado.getDouble("valor"));
                ordemServico.setDataCriacao(resultado.getDate("data_criacao").toLocalDate());

                ordemServicos.add(ordemServico);
            }
            logger.info("Lista de ordens de serviço recuperada com sucesso.");

        } catch (SQLException e) {
            logger.error("Erro ao listar ordens de serviço: {}", e.getMessage());
        }
        return ordemServicos;
    }

    // Método para atualizar uma ordem de serviço no banco de dados
    public boolean atualizar(OrdemServico ordemServico) {
        boolean isAtualizado = false; // Variável para armazenar o status da atualização

        if (ordemServico.getId() == null) {
            logger.warn("ID da ordem de serviço não pode ser nulo. Atualização não realizada.");
            throw new IllegalArgumentException("ID da ordem de serviço não pode ser nulo.");
        }

        String sql = "UPDATE ordem_servico SET tecnico_id = ?, cliente_id = ?, valor = ?, data_criacao = ? WHERE id = ?";

        try (Connection conexao = FabricaDeConexao.obterConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setLong(1, ordemServico.getTecnicoId());
            statement.setLong(2, ordemServico.getClienteId());
            statement.setDouble(3, ordemServico.getValor());
            statement.setDate(4, Date.valueOf(ordemServico.getDataCriacao()));
            statement.setLong(5, ordemServico.getId());

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                isAtualizado = true; // A atualização foi bem-sucedida
                logger.info("OrdemServico atualizada com sucesso: ID {}", ordemServico.getId());
            }

        } catch (SQLException e) {
            logger.error("Erro ao atualizar ordem de serviço: {}", e.getMessage());
        }
        return isAtualizado;
    }

    // Método para excluir uma ordem de serviço do banco de dados
    public void excluir(Long id) {
        String sql = "DELETE FROM ordem_servico WHERE id = ?";

        try (Connection conexao = FabricaDeConexao.obterConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
            logger.info("OrdemServico excluída com sucesso: ID {}", id);

        } catch (SQLException e) {
            logger.error("Erro ao excluir ordem de serviço: {}", e.getMessage());
        }
    }

    // Método para buscar uma ordem de serviço pelo ID
    public OrdemServico buscarPorId(Long id) {
        OrdemServico ordemServico = null;
        String sql = "SELECT * FROM ordem_servico WHERE id = ?";

        try (Connection conexao = FabricaDeConexao.obterConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                ordemServico = new OrdemServico();
                ordemServico.setId(resultado.getLong("id"));
                ordemServico.setTecnicoId(resultado.getLong("tecnico_id"));
                ordemServico.setClienteId(resultado.getLong("cliente_id"));
                ordemServico.setValor(resultado.getDouble("valor"));
                ordemServico.setDataCriacao(resultado.getDate("data_criacao").toLocalDate());
                logger.info("OrdemServico encontrada: ID {}", ordemServico.getId());
            } else {
                logger.warn("Nenhuma ordem de serviço encontrada com o ID: {}", id);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar ordem de serviço por ID: {}", e.getMessage());
        }
        return ordemServico;
    }
}
