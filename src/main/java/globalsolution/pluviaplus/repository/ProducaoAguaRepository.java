package globalsolution.pluviaplus.repository;


import globalsolution.pluviaplus.infrastructure.DatabaseConfig;
import globalsolution.pluviaplus.models.ProducaoAgua;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ProducaoAguaRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducaoAguaRepository.class);

    public void add(ProducaoAgua producao) {
        String query = "INSERT INTO PP_PRODUCAO_AGUA (id_producao, id_dispositivo, data_producao, litros_gerados) VALUES (?, ?, ?, ?)";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, producao.getId_producao());
            statement.setInt(2, producao.getId_dispositivo());
            statement.setDate(3, Date.valueOf(producao.getData_producao()));
            statement.setDouble(4, producao.getLitros_gerados());
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao adicionar producao de agua", e);
        }
    }

    public void update(ProducaoAgua producao) {
        String query = "UPDATE PP_PRODUCAO_AGUA SET id_dispositivo = ?, data_producao = ?, litros_gerados = ? WHERE id_producao = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, producao.getId_dispositivo());
            statement.setDate(2, Date.valueOf(producao.getData_producao()));
            statement.setDouble(3, producao.getLitros_gerados());
            statement.setInt(4, producao.getId_producao());
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar producao de agua", e);
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM PP_PRODUCAO_AGUA WHERE id_producao = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao deletar producao de agua por ID: {}", id, e);
        }
    }

    public List<ProducaoAgua> get() {
        List<ProducaoAgua> producoes = new ArrayList<>();
        String query = "SELECT * FROM PP_PRODUCAO_AGUA";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ProducaoAgua producao = new ProducaoAgua(
                        resultSet.getInt("id_producao"),
                        resultSet.getInt("id_dispositivo"),
                        resultSet.getDate("data_producao").toLocalDate(),
                        resultSet.getDouble("litros_gerados")
                );
                producoes.add(producao);
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao listar producoes de agua", e);
        }

        return producoes;
    }

    public Optional<ProducaoAgua> getById(int id) {
        String query = "SELECT * FROM PP_PRODUCAO_AGUA WHERE id_producao = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ProducaoAgua producao = new ProducaoAgua(
                        resultSet.getInt("id_producao"),
                        resultSet.getInt("id_dispositivo"),
                        resultSet.getDate("data_producao").toLocalDate(),
                        resultSet.getDouble("litros_gerados")
                );
                return Optional.of(producao);
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao buscar producao de agua por ID: {}", id, e);
        }

        return Optional.empty();
    }

    public Map<Integer, Double> getVolumeGroupedByDevice() {
        String query = "SELECT ID_DISPOSITIVO, SUM(LITROS_GERADOS) AS TOTAL_VOLUME FROM PP_PRODUCAO_AGUA GROUP BY ID_DISPOSITIVO";
        Map<Integer, Double> result = new HashMap<>();

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query);
             var rs = statement.executeQuery()) {

            while (rs.next()) {
                result.put(rs.getInt("ID_DISPOSITIVO"), rs.getDouble("TOTAL_VOLUME"));
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao agrupar volume por dispositivo", e);
        }

        return result;
    }
}

