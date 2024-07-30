package com.algaworks.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.algaworks.algalog.domain.repository.EntregaRepository;

@Service
public class ExcluiEntregaService {
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	public void excluiEntrega(Long id) {
		entregaRepository.deleteById(id);
	}

}
