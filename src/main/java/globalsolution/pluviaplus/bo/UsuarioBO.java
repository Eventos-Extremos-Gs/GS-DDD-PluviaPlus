package globalsolution.pluviaplus.bo;

import globalsolution.pluviaplus.dto.UsuarioDto;
import globalsolution.pluviaplus.models.Usuario;
import globalsolution.pluviaplus.repository.UsuarioRepository;
import globalsolution.pluviaplus.exception.RequisicaoInvalidaException;
import globalsolution.pluviaplus.exception.RecursoNaoEncontradoException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioBO {

    private final UsuarioRepository repository = new UsuarioRepository();

    public void create(UsuarioDto dto) {
        validate(dto);
        Usuario usuario = toEntity(dto);
        repository.add(usuario);
    }

    public List<UsuarioDto> getAll() {
        return repository.get().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UsuarioDto getById(int id) {
        Optional<Usuario> usuario = repository.getById(id);
        return usuario.map(this::toDto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario com ID " + id + " não encontrado."));
    }

    public void update(int idUsuario, UsuarioDto dto) {
        validate(dto);
        Usuario usuario = toEntity(dto);
        usuario.setId_usuario(idUsuario);
        repository.update(usuario);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    private void validate(UsuarioDto dto) {
        if (dto.nm_Usuario() == null || dto.nm_Usuario().isBlank()
                || dto.email() == null || dto.email().isBlank()
                || dto.tp_Usuario() == null) {
            throw new RequisicaoInvalidaException("Nome, email e tipo de usuario são obrigatorios!");
        }
    }

    private Usuario toEntity(UsuarioDto dto) {
        return new Usuario(
                dto.id_Usuario(),
                dto.nm_Usuario(),
                dto.email(),
                dto.tp_Usuario()
        );
    }

    private UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getId_usuario(),
                usuario.getNm_usuario(),
                usuario.getEmail(),
                usuario.getTp_usuario()
        );
    }

    public Map<String, Long> contagemPorTipo() {
        return repository.contagemPorTipo();
    }

    public long contagemTotal() {
        return repository.contagemTotal();
    }


}
