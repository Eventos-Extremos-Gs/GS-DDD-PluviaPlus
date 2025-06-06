package globalsolution.pluviaplus.repository;

import globalsolution.pluviaplus.infrastructure.DatabaseConfig;
import globalsolution.pluviaplus.models.TipoUsuario;
import globalsolution.pluviaplus.models.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.util.*;

public class UsuarioRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioRepository.class);

    public void add(Usuario usuario) {
        String query = "INSERT INTO PP_USUARIO (id_usuario, nm_usuario, email, tp_usuario) VALUES (?, ?, ?, ?)";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, usuario.getId_usuario());
            statement.setString(2, usuario.getNm_usuario());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getTp_usuario().name());

            statement.executeUpdate();

        } catch (Exception exception) {
            LOGGER.error("Erro ao adicionar usuario", exception);
        }
    }

    public void update(Usuario usuario) {
        String query = "UPDATE PP_USUARIO SET nm_usuario = ?, email = ?, tp_usuario = ? WHERE id_usuario = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setString(1, usuario.getNm_usuario());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getTp_usuario().name());
            statement.setInt(4, usuario.getId_usuario());

            statement.executeUpdate();

        } catch (Exception exception) {
            LOGGER.error("Erro ao atualizar usuario: {}", usuario.getId_usuario(), exception);
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM PP_USUARIO WHERE id_usuario = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception exception) {
            LOGGER.error("Erro ao deletar usuario por ID: {}", id, exception);
        }
    }

    public List<Usuario> get() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM PP_USUARIO";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getInt("ID_USUARIO"),
                        resultSet.getString("NM_USUARIO"),
                        resultSet.getString("EMAIL"),
                        TipoUsuario.valueOf(resultSet.getString("TP_USUARIO"))
                );
                usuarios.add(usuario);
            }

        } catch (Exception exception) {
            LOGGER.error("Erro ao listar usuarios", exception);
        }

        return usuarios;
    }

    public Optional<Usuario> getById(int id) {
        String query = "SELECT * FROM PP_USUARIO WHERE id_usuario = ?";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getInt("ID_USUARIO"),
                        resultSet.getString("NM_USUARIO"),
                        resultSet.getString("EMAIL"),
                        TipoUsuario.valueOf(resultSet.getString("TP_USUARIO"))
                );
                return Optional.of(usuario);
            }

        } catch (Exception exception) {
            LOGGER.error("Erro ao buscar usuario por ID: {}", id, exception);
        }

        return Optional.empty();
    }

    public Map<String, Long> contagemPorTipo() {
        Map<String, Long> contagem = new HashMap<>();
        String query = "SELECT tp_usuario, COUNT(*) AS total FROM PP_USUARIO GROUP BY tp_usuario";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tipo = resultSet.getString("tp_usuario");
                long total = resultSet.getLong("total");
                contagem.put(tipo, total);
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao contar usuarios por tipo", e);
        }

        return contagem;
    }

    public Long contagemTotal() {
        String query = "SELECT COUNT(*) FROM PP_USUARIO";

        try (var connection = DatabaseConfig.getConnection();
             var statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getLong(1);
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao contar total de usuários", e);
        }

        return 0L;
    }


}
