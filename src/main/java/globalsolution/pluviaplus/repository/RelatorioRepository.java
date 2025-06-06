package globalsolution.pluviaplus.repository;

import globalsolution.pluviaplus.infrastructure.DatabaseConfig;
import globalsolution.pluviaplus.models.Relatorio;
import globalsolution.pluviaplus.models.TipoRelatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RelatorioRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(RelatorioRepository.class);

    public void add(Relatorio relatorio) {
        String query = "INSERT INTO PP_RELATORIO (id_relatorio, tipo, data_geracao, id_usuario) VALUES (?, ?, ?, ?)";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, relatorio.getId_relatorio());
            statement.setString(2, relatorio.getTipo().name()); // corrigido
            statement.setDate(3, Date.valueOf(relatorio.getData_geracao()));
            statement.setInt(4, relatorio.getId_usuario());
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao adicionar relatorio", e);
        }
    }

    public void update(Relatorio relatorio) {
        String query = "UPDATE PP_RELATORIO SET tipo = ?, data_geracao = ?, id_usuario = ? WHERE id_relatorio = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setString(1, relatorio.getTipo().name()); // corrigido
            statement.setDate(2, Date.valueOf(relatorio.getData_geracao()));
            statement.setInt(3, relatorio.getId_usuario());
            statement.setInt(4, relatorio.getId_relatorio());
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar relatorio", e);
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM PP_RELATORIO WHERE id_relatorio = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao deletar relatorio por ID: {}", id, e);
        }
    }

    public List<Relatorio> get() {
        List<Relatorio> relatorios = new ArrayList<>();
        String query = "SELECT * FROM PP_RELATORIO";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Relatorio relatorio = new Relatorio(
                        resultSet.getInt("id_relatorio"),
                        TipoRelatorio.valueOf(resultSet.getString("tipo")), // corrigido
                        resultSet.getDate("data_geracao").toLocalDate(),
                        resultSet.getInt("id_usuario")
                );
                relatorios.add(relatorio);
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao listar relatorios", e);
        }

        return relatorios;
    }

    public Optional<Relatorio> getById(int id) {
        String query = "SELECT * FROM PP_RELATORIO WHERE id_relatorio = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Relatorio relatorio = new Relatorio(
                        resultSet.getInt("id_relatorio"),
                        TipoRelatorio.valueOf(resultSet.getString("tipo")), // corrigido
                        resultSet.getDate("data_geracao").toLocalDate(),
                        resultSet.getInt("id_usuario")
                );
                return Optional.of(relatorio);
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao buscar relatorio por ID: {}", id, e);
        }

        return Optional.empty();
    }
}
