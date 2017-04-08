package com.richard.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;

import com.richard.brewer.model.Style;

public class StyleConverter implements Converter<String, Style> {

	@Override
	public Style convert(String code) {
		Style style = new Style();
		style.setCode(Long.valueOf(code));
		return style;
	}

}
