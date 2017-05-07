package com.richard.brewer.thymeleaf.processor;

import javax.servlet.http.HttpServletRequest;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

public class MenuAttrbuteTagProcessor extends AbstractAttributeTagProcessor {
	
	private static final String NAME_ATTRIBUTE = "menu";
	private static final int PRECEDENCE = 1000;

	public MenuAttrbuteTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, NAME_ATTRIBUTE, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue,
			IElementTagStructureHandler structureHandler) {
		
		IEngineConfiguration configuration = context.getConfiguration();
		IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		IStandardExpression expression = parser.parseExpression(context, attributeValue);
		String menu = (String) expression.execute(context);
		
		HttpServletRequest request = ((IWebContext) context).getRequest();
		String uri = request.getRequestURI();
		
		if (uri.startsWith(menu)) {
			String classExist = tag.getAttributeValue("class");
			structureHandler.setAttribute("class", classExist + " is-active");
		}
		
	}

}
