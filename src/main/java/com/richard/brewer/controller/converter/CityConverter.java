package com.richard.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.richard.brewer.model.City;

public class CityConverter implements Converter<String, City> {

	@Override
	public City convert(String code) {
		
		if (!StringUtils.isEmpty(code)) {
			City city = new City();
			city.setCode(Long.valueOf(code));
			return city;
		}
		return null;
	}

}
