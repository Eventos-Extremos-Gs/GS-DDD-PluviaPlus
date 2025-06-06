package globalsolution.pluviaplus.bo;

import globalsolution.pluviaplus.dto.DispositivoDto;
import globalsolution.pluviaplus.exception.RecursoNaoEncontradoException;
import globalsolution.pluviaplus.exception.RequisicaoInvalidaException;
import globalsolution.pluviaplus.models.Dispositivo;
import globalsolution.pluviaplus.repository.DispositivoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DispositivoBO {

    private final DispositivoRepository repository = new DispositivoRepository();

    public void registrar(DispositivoDto dto) {
        validate(dto);
        Dispositivo dispositivo = toEntity(dto);
        repository.add(dispositivo);
    }

    public List<DispositivoDto> getAll() {
        return repository.get().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public DispositivoDto getById(int id) {
        Optional<Dispositivo> dispositivo = repository.getById(id);
        return dispositivo.map(this::toDto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Dispositivo com ID " + id + " não encontrado."));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public Map<String, Object> gerarResumoGeral() {
        Map<String, Object> resumo = new HashMap<>();
        resumo.put("Modelo", repository.gerarResumo());
        resumo.put("Quantidade de modelos em cada localizacao", repository.gerarResumoPorLocalizacao());
        resumo.put("Modelos por cidade", repository.mapearModelosPorCidades());
        return resumo;
    }

    private void validate(DispositivoDto dto) {
        if (dto.nome() == null || dto.nome().isBlank()
                || dto.modelo() == null || dto.modelo().isBlank()) {
            throw new RequisicaoInvalidaException("Nome e modelo do dispositivo são obrigatórios.");
        }

        if (!dto.nome().equals("C.A.P.T.A")) {
            throw new RequisicaoInvalidaException("Apenas dispositivos do modelo 'C.A.P.T.A' são permitidos.");
        }
    }


    private Dispositivo toEntity(DispositivoDto dto) {
        return new Dispositivo(
                dto.idDispositivo(),
                dto.nome(),
                dto.modelo(),
                dto.idLocalizacao()
        );
    }

    private DispositivoDto toDto(Dispositivo dispositivo) {
        return new DispositivoDto(
                dispositivo.getId_dispositivo(),
                dispositivo.getNome(),
                dispositivo.getModelo(),
                dispositivo.getId_localizacao()
        );
    }
}
