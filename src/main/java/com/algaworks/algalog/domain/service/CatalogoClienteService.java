package com.algaworks.algalog.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.algaworks.algalog.api.assembler.ClienteEntregaAssembler;
import com.algaworks.algalog.api.model.ClienteEntregaDTO;
import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CatalogoClienteService {

	@Autowired
	private ClienteEntregaAssembler clienteEntregaAssembler;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BuscaEntregaService buscaEntregaService;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		if (this.validar(cliente)) {
			boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream()
					.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

			if (emailEmUso) {
				throw new NegocioException("Ja existe um cliente cadastrado com este e-mail.");
			}

			return clienteRepository.save(cliente);
		} else {
			return null;
		}
	}

	@Transactional
	public void excluir(Long clienteId) {
		if (buscaEntregaService.verificaEntrega(clienteId)) {
			throw new NegocioException("Erro! existe uma entrega para esse cliente");
		}

		try {
			clienteRepository.deleteById(clienteId);
			log.info("Cliente excluído: " + clienteId);
		} catch (Exception e) {
			log.error("Falha ao excluir cliente com id: " + clienteId);
		}
	}

	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	public Cliente pesquisar(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(() -> new NegocioException("Cliente não encontrado"));
	}

	public boolean validar(@Valid Cliente cliente) {
		if (cliente.getNome().length() < 10) {
			log.warn("Cliente com nome menor que o obrigatório: " + cliente);
			return false;
		}
		return true;
	}

	public List<Cliente> buscaPorNome(String nome) {
		return clienteRepository.findByNomeContaining(nome);
	}

	public List<ClienteEntregaDTO> listaEntregas(long clienteId) {
		return clienteEntregaAssembler.toCollectionModel(buscaEntregaService.buscaPorCliente(clienteId));
	}

}
