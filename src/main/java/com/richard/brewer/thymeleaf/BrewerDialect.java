package com.richard.brewer.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.richard.brewer.thymeleaf.processor.ClassForErrrorAttributeTagProcessor;

public class BrewerDialect extends AbstractProcessorDialect {
	
	private static final String NAME = "Project Brewer";
	private static final String PREFIX = "brewer";

	public BrewerDialect() {
		super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		
		final Set<IProcessor> processors = new HashSet<>();
		processors.add(new ClassForErrrorAttributeTagProcessor(dialectPrefix));
		
		return processors;
	}

}
