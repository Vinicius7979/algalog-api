package com.algaworks.algalog.api.model;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaResumoDTO {

	private String descricao;

	private OffsetDateTime dataRegistro;

}
