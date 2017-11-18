package br.com.mobiew.siseve.controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.mobiew.siseve.model.enuns.SexoEnum;
import br.com.mobiew.siseve.util.scopes.Scopes;

import com.google.common.collect.Lists;

@Component
@Scope(Scopes.PROTOTYPE)
public class CachedResources {

	private List<SexoEnum> sexos;

	public List<SexoEnum> getSexos() {
		if (sexos == null) {
			sexos = Lists.newArrayList(SexoEnum.values());
		}

		return sexos;
	}
}
