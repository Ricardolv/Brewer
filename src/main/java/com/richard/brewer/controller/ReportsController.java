package com.richard.brewer.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.richard.brewer.dto.ReportingPeriod;

@Controller
@RequestMapping("/reports")
public class ReportsController {
	
	@GetMapping("/salesIssued")
	public ModelAndView relatorioVendasEmitidas() {
		ModelAndView mv = new ModelAndView("report/report_sales_issued");
		mv.addObject(new ReportingPeriod());
		return mv;
	}
	
	@PostMapping("/salesIssued")
	public ModelAndView gerarRelatorioVendasEmitidas(ReportingPeriod reportingPeriod) {
		Map<String, Object> parameters = new HashMap<>();
		
		Date startDate = Date.from(LocalDateTime.of(reportingPeriod.getStartDate(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		Date endDate = Date.from(LocalDateTime.of(reportingPeriod.getEndDate(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		parameters.put("format", "pdf");
		parameters.put("start_date", startDate);
		parameters.put("end_date", endDate);
		
		return new ModelAndView("report_sales_issued", parameters);
	}

}
