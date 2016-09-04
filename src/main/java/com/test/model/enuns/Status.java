package com.test.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
	ATIVO("Ativo")
	,DESATIVO("Destivo")
	,DELETAR("Deletar");

	private String descricao;
}