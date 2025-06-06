package globalsolution.pluviaplus.dto;

import java.time.LocalDate;

public record ProducaoAguaDto(
        int idProducao,
        int idDispositivo,
        LocalDate dataProducao,
        double litrosGerados
) {
}

