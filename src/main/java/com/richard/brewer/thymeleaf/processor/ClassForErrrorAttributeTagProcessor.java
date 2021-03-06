package com.richard.brewer.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.util.FieldUtils;
import org.thymeleaf.templatemode.TemplateMode;

public class ClassForErrrorAttributeTagProcessor extends AbstractAttributeTagProcessor {
	
	private static final String NAME_ATTRIBUTE = "classforerror";
	private static final int PRECEDENCE = 1000;

	public ClassForErrrorAttributeTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, NAME_ATTRIBUTE, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue,
			IElementTagStructureHandler structureHandler) {
		
		if (FieldUtils.hasErrors(context, attributeValue)) {
			String classExist = tag.getAttributeValue("class");
			structureHandler.setAttribute("class", classExist + " has-error");
		}
	}
}
