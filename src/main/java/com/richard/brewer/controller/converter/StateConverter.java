package com.richard.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.richard.brewer.model.State;

public class StateConverter implements Converter<String, State> {

	@Override
	public State convert(String code) {
		if (!StringUtils.isEmpty(code)) {
			State state = new State();
			state.setCode(Long.valueOf(code));
			return state;
		}
		return null;
	}

}
