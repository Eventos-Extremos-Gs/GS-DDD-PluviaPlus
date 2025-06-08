package globalsolution.pluviaplus.repository;

import globalsolution.pluviaplus.infrastructure.DatabaseConfig;
import globalsolution.pluviaplus.models.Dispositivo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class DispositivoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispositivoRepository.class);

    public void add(Dispositivo dispositivo) {
        String query = "INSERT INTO PP_DISPOSITIVO (id_dispositivo, nome, modelo, id_localizacao) VALUES (?, ?, ?, ?)";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, dispositivo.getId_dispositivo());
            statement.setString(2, dispositivo.getNome());
            statement.setString(3, dispositivo.getModelo());
            statement.setInt(4, dispositivo.getId_localizacao());
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao adicionar dispositivo", e);
        }
    }

    public void update(Dispositivo dispositivo) {
        String query = "UPDATE PP_DISPOSITIVO SET nome = ?, modelo = ?, id_localizacao = ? WHERE id_dispositivo = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setString(1, dispositivo.getNome());
            statement.setString(2, dispositivo.getModelo());
            statement.setInt(3, dispositivo.getId_localizacao());
            statement.setInt(4, dispositivo.getId_dispositivo());
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar dispositivo", e);
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM PP_DISPOSITIVO WHERE id_dispositivo = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("Erro ao deletar dispositivo por ID: {}", id, e);
        }
    }

    public List<Dispositivo> get() {
        List<Dispositivo> dispositivos = new ArrayList<>();
        String query = "SELECT * FROM PP_DISPOSITIVO";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Dispositivo dispositivo = new Dispositivo(
                        resultSet.getInt("id_dispositivo"),
                        resultSet.getString("nome"),
                        resultSet.getString("modelo"),
                        resultSet.getInt("id_localizacao")
                );
                dispositivos.add(dispositivo);
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao listar dispositivos", e);
        }

        return dispositivos;
    }

    public Optional<Dispositivo> getById(int id) {
        String query = "SELECT * FROM PP_DISPOSITIVO WHERE id_dispositivo = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Dispositivo dispositivo = new Dispositivo(
                        resultSet.getInt("id_dispositivo"),
                        resultSet.getString("nome"),
                        resultSet.getString("modelo"),
                        resultSet.getInt("id_localizacao")
                );
                return Optional.of(dispositivo);
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao buscar dispositivo por ID: {}", id, e);
        }


        return Optional.empty();
    }

    public Map<String, Object> gerarResumo() {
        String query = "SELECT modelo, COUNT(*) as total FROM PP_DISPOSITIVO GROUP BY modelo";
        Map<String, Object> resumo = new HashMap<>();

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String modelo = resultSet.getString("modelo");
                int total = resultSet.getInt("total");
                resumo.put(modelo, total);
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao gerar resumo de dispositivos", e);
        }

        return resumo;
    }

}
