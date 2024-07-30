package com.algaworks.algalog.domain.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;

	public BuscaEntregaService(EntregaRepository entregaRepository) {
		super();
		this.entregaRepository = entregaRepository;
	}

	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));

	}

	public boolean verificaEntrega(Long clienteId) {
		boolean existe = entregaRepository.findByClienteId(clienteId).stream().anyMatch(entregaExistente -> entregaExistente.getCliente().getId() == clienteId);

		if(existe) {
			return true;
		}else {
			return false;
		}
	}
	
	public List<Entrega> buscaPorCliente(Long clienteId) {
		return entregaRepository.findByClienteId(clienteId);
	}

}