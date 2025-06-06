package globalsolution.pluviaplus.repository;

import globalsolution.pluviaplus.infrastructure.DatabaseConfig;
import globalsolution.pluviaplus.models.Impacto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class ImpactoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImpactoRepository.class);

    public void add(Impacto impacto) {
        String query = "INSERT INTO PP_IMPACTO (id_impacto, id_producao, co2_economizado_kg, pessoas_beneficiadas) VALUES (?, ?, ?, ?)";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, impacto.getId_impacto());
            statement.setInt(2, impacto.getId_producao());
            statement.setDouble(3, impacto.getCo2_economizado_kg());
            statement.setInt(4, impacto.getPessoas_beneficiadas());
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao adicionar impacto", e);
        }
    }

    public void update(Impacto impacto) {
        String query = "UPDATE PP_IMPACTO SET id_producao = ?, co2_economizado_kg = ?, pessoas_beneficiadas = ? WHERE id_impacto = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, impacto.getId_producao());
            statement.setDouble(2, impacto.getCo2_economizado_kg());
            statement.setInt(3, impacto.getPessoas_beneficiadas());
            statement.setInt(4, impacto.getId_impacto());
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar impacto", e);
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM PP_IMPACTO WHERE id_impacto = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao deletar impacto", e);
        }
    }

    public List<Impacto> get() {
        List<Impacto> impactos = new ArrayList<>();
        String query = "SELECT * FROM PP_IMPACTO";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Impacto impacto = new Impacto(
                        resultSet.getInt("id_impacto"),
                        resultSet.getInt("id_producao"),
                        resultSet.getDouble("co2_economizado_kg"),
                        resultSet.getInt("pessoas_beneficiadas")
                );
                impactos.add(impacto);
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao listar impactos", e);
        }

        return impactos;
    }

    public Optional<Impacto> getById(int id) {
        String query = "SELECT * FROM PP_IMPACTO WHERE id_impacto = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Impacto impacto = new Impacto(
                        resultSet.getInt("id_impacto"),
                        resultSet.getInt("id_producao"),
                        resultSet.getDouble("co2_economizado_kg"),
                        resultSet.getInt("pessoas_beneficiadas")
                );
                return Optional.of(impacto);
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao buscar impacto por ID", e);
        }

        return Optional.empty();
    }

    public Map<String, Object> getEstatisticasTotais() {
        Map<String, Object> totais = new HashMap<>();

        String sql = """
        SELECT
            SUM(i.co2_economizado_kg) AS total_co2,
            SUM(i.pessoas_beneficiadas) AS total_pessoas
        FROM PP_IMPACTO i
    """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                totais.put("totalCO2", rs.getDouble("total_co2"));
                totais.put("totalPessoas", rs.getInt("total_pessoas"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular estatísticas totais de impacto", e);
        }

        return totais;
    }

}

