package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.CidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import br.com.myfood.myfoodapi.domain.model.Cidade;
import br.com.myfood.myfoodapi.domain.model.Estado;
import br.com.myfood.myfoodapi.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCidadeService {

    public static final String MSG_CIDADE_NAO_ENCONTRADA = "Nenhuma cidade encontrada com id: %d";
    public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService estadoService;

    public List<Cidade> listar() {
        return this.cidadeRepository.findAll();
    }

    public Cidade buscarPorId(Long id) {
        return this.cidadeRepository
                .findById(id)
                .orElseThrow(() -> new CidadeNaoEncontradaException(
                        String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
    }

    public Cidade salvar(Cidade cozinhaInput) {

        Long idEstado = cozinhaInput.getEstado().getId();
        Estado estado = this.estadoService.buscaPorId(idEstado);
        cozinhaInput.setEstado(estado);

        return this.cidadeRepository.save(cozinhaInput);
    }

    public void remover(Long id) {

        try {
            Cidade cidadeRecuperada = this.buscarPorId(id);
            this.cidadeRepository.delete(cidadeRecuperada);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA,id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
        }
    }
}
