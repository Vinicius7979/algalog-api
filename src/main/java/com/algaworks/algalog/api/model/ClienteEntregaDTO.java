package com.algaworks.algalog.api.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteEntregaDTO {

	private Long clienteId;

	private EntregaResumoDTO entrega;

	private List<OcorrenciaResumoDTO> ocorrencias;

}
