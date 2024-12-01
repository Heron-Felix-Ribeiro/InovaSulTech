package br.com.senac.inovasultech.useCases.tipoAgente;

import br.com.senac.inovasultech.entitys.TipoAgente;
import br.com.senac.inovasultech.useCases.tipoAgente.domains.TipoAgenteRequestDom;
import br.com.senac.inovasultech.useCases.tipoAgente.domains.TipoAgenteResponseDom;
import br.com.senac.inovasultech.useCases.tipoAgente.implement.mapper.TipoAgenteMapper;
import br.com.senac.inovasultech.useCases.tipoAgente.implement.repository.TipoAgenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoAgenteService {

    @Autowired
    private TipoAgenteRepository tipoAgenteRepository;

    public TipoAgenteResponseDom criarTipoAgente (TipoAgenteRequestDom tipoAgente) {

        TipoAgente tipoAgentePersist = TipoAgenteMapper.tipoAgenteRequestToTipoAgente(tipoAgente);

        tipoAgenteRepository.save(tipoAgentePersist);

        TipoAgenteResponseDom response = TipoAgenteMapper.tipoAgenteToTipoAgenteResponse(tipoAgentePersist);

        return response;
    }

    public List<TipoAgenteResponseDom> listarTipoAgente () throws Exception{

        List<TipoAgente> tipoAgenteResult = tipoAgenteRepository.findAll();

        if (!tipoAgenteResult.isEmpty()){

            List<TipoAgenteResponseDom> tipoAgenteResponse =
                    tipoAgenteResult
                            .stream()
                            .map(row -> new TipoAgenteResponseDom(
                                    row.getId(),
                                    row.getNmTipoAgente()))
                            .toList();

            return tipoAgenteResponse;

        }

        throw new Exception("Não foi possível encontrar Tipos de Agente");
    }

    public TipoAgenteResponseDom atualizarTipoAgente (Long id, TipoAgenteRequestDom tipoAgente) throws Exception {

        Optional<TipoAgente> optionalTipoAgente = tipoAgenteRepository.findById(id);

        if (!optionalTipoAgente.isEmpty()) {

            TipoAgente tipoAgenteExist = TipoAgenteMapper.tipoAgenteRequestToTipoAgente(tipoAgente);
            tipoAgenteExist.setId(optionalTipoAgente.get().getId());

            tipoAgenteRepository.save(tipoAgenteExist);

            TipoAgenteResponseDom response = TipoAgenteMapper.tipoAgenteToTipoAgenteResponse(tipoAgenteExist);

            return response;
        }

        throw new Exception("Tipo Agente não existe");
    }

    public void deletarTipoAgente (Long tipoAgenteId) throws Exception {

        Optional<TipoAgente> optionalTipoAgente = tipoAgenteRepository.findById(tipoAgenteId);

        if (!optionalTipoAgente.isEmpty()) {

            TipoAgente tipoAgente = optionalTipoAgente.get();

            tipoAgenteRepository.delete(tipoAgente);

            return;
        }

        throw new Exception("Tipo Agente não existe");
    }
}
