package globalsolution.pluviaplus.dto;

import globalsolution.pluviaplus.models.TipoUsuario;

public record UsuarioDto(
        int id_Usuario,
        String nm_Usuario,
        String email,
        TipoUsuario tp_Usuario
) {
}
