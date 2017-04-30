package com.richard.brewer.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {
	
	private Page<T> page;
	private UriComponentsBuilder uriBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		this.uriBuilder = ServletUriComponentsBuilder.fromRequest(httpServletRequest);
	}
	
	public List<T> getContent() {
		return this.page.getContent();
	}
	
	public boolean isEmpty() {
		return this.page.getContent().isEmpty();
	}
	
	public int getCurrent() {
		return this.page.getNumber();
	}
	
	public boolean isFirst() {
		return this.page.isFirst();
	}
	
	public boolean isLast() {
		return this.page.isLast();
	}
	
	public int getTotal() {
		return page.getTotalPages();
	}
	
	public String urlForPage(int page) {
		return uriBuilder.replaceQueryParam("page", page).build(true).encode().toUriString();
	}

}
