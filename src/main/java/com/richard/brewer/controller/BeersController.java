package com.richard.brewer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.controller.page.PageWrapper;
import com.richard.brewer.dto.BeerDTO;
import com.richard.brewer.model.Beer;
import com.richard.brewer.model.Flavor;
import com.richard.brewer.model.Origin;
import com.richard.brewer.repository.filter.BeerFilter;
import com.richard.brewer.service.BeerService;
import com.richard.brewer.service.StyleService;

@Controller
@RequestMapping("/beers")
public class BeersController {
	
	@Autowired
	private BeerService beerService;
	
	@Autowired
	private StyleService styleService;
	
	@GetMapping("/new")
	public ModelAndView newBeer(Beer beer) {
		ModelAndView mv = new ModelAndView("beer/register-beers");
		mv.addObject("flavors", Flavor.values());
		mv.addObject("styles", styleService.findAll());
		mv.addObject("origins", Origin.values());
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView save(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newBeer(beer);
		}
		
		beerService.save(beer);
		attributes.addFlashAttribute("message", "Cerveja salva com sucesso!");
		return new ModelAndView("redirect:/beers/new");
	}
	
	@GetMapping
	public ModelAndView search(BeerFilter beerFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("beer/search-beers");
		mv.addObject("flavors", Flavor.values());
		mv.addObject("styles", styleService.findAll());
		mv.addObject("origins", Origin.values());
		
		PageWrapper<Beer> pageWrapper = new PageWrapper<>(beerService.filter(beerFilter, pageable), httpServletRequest);
		mv.addObject("page", pageWrapper);
		
		return mv;
	}
	
	@GetMapping("/filter")
	public @ResponseBody List<BeerDTO> search(String skuOrName) {
		return beerService.bySkuOrName(skuOrName);
	}

}

