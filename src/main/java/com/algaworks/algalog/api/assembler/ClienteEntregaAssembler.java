package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.ClienteEntregaDTO;
import com.algaworks.algalog.domain.model.Entrega;

@Component
public class ClienteEntregaAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteEntregaDTO toModel(Entrega entrega) {
		return modelMapper.map(entrega, ClienteEntregaDTO.class);
	}
	
	public List<ClienteEntregaDTO> toCollectionModel(List<Entrega> entregas){
		return entregas.stream().map(this::toModel).collect(Collectors.toList());
	}

}
