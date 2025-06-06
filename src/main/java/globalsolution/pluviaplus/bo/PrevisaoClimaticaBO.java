package globalsolution.pluviaplus.bo;

import globalsolution.pluviaplus.dto.PrevisaoClimaticaDto;
import globalsolution.pluviaplus.infrastructure.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrevisaoClimaticaBO {

    public List<PrevisaoClimaticaDto> getSimulada() {
        List<PrevisaoClimaticaDto> previsoes = new ArrayList<>();

        String sql = """
            SELECT p.id_previsao, p.id_localizacao, p.data_previsao, p.umidade_relativa, p.temperatura_celsius
            FROM PP_PREVISAO_CLIMATICA p
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PrevisaoClimaticaDto dto = new PrevisaoClimaticaDto(
                        rs.getInt("id_previsao"),
                        rs.getInt("id_localizacao"),
                        rs.getDate("data_previsao").toLocalDate(),
                        rs.getDouble("umidade_relativa"),
                        rs.getDouble("temperatura_celsius")
                );
                previsoes.add(dto);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar previsões climáticas simuladas", e);
        }

        return previsoes;
    }
}
