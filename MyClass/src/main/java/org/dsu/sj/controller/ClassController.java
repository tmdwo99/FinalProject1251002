package org.dsu.sj.controller;

import org.dsu.sj.domain.ClassVO;
import org.dsu.sj.domain.Criteria;
import org.dsu.sj.domain.PageDTO;
import org.dsu.sj.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/*")
public class ClassController {
	
	@Autowired
	private ClassService service;
	
	@GetMapping("list")
	public void list(Criteria cri, Model model) throws Exception {
		log.info("[Class] List Criteria : {}", cri);;
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, 123));
	}
	
	@GetMapping("register")
	public void register() {
		
	}
	
	@PostMapping("register")
	public String register(ClassVO cla, RedirectAttributes rttr) {
		log.info("[Class] register: {}", cla);
		service.register(cla);
		rttr.addFlashAttribute("result", cla.getBno());
		return "redirect:/list";
	}
	
	@GetMapping("modify")
	public String modify(@RequestParam("bno") Long bno,@RequestParam("pcnum") Long pcnum, Model model, RedirectAttributes rttr) {
		log.info("[Board] modify... bno={}", bno);
		log.info("[Board] modify... {}", service.get(bno));
		if(service.get(bno).getEndDate() == null) {
			service.modify(service.get(bno));
			rttr.addFlashAttribute("resultRead", pcnum);
		}else {
			rttr.addFlashAttribute("resultRead", 0);
		}
		return "redirect:/list";
	}
}
