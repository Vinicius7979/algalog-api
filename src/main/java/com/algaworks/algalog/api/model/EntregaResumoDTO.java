package com.algaworks.algalog.api.model;

import java.time.OffsetDateTime;

import com.algaworks.algalog.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaResumoDTO {

	private Long entregaId;

	private StatusEntrega status;

	private OffsetDateTime dataPedido;

}