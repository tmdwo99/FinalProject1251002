package org.dsu.dc.controller;

import org.dsu.dc.domain.BoardVO;
import org.dsu.dc.domain.Criteria;
import org.dsu.dc.domain.PageDTO;
import org.dsu.dc.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("list")
	public void listAll(Criteria cri, Model model) throws Exception{
		log.info("[Board] List Criteria : {} ", cri);
		model.addAttribute("list",service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri,123));
		
		int total = service.getTotal(cri);
		log.info("[Board] Total Count : {} ", total);
		model.addAttribute("pageMaker", new PageDTO(cri,total));
	}
	
	@PostMapping("register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("[Board] register : {}", board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping("register")
	public void register() {
		
	}
	
	@GetMapping("read")
	public void read(@RequestParam("bno") Long bno,@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("[Board] Read.... bno={}", bno);
		model.addAttribute("board", service.get(bno));
		model.addAttribute("cri",cri);
	}
	
	@PostMapping("modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("[Board] modify : {}",board);
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("type", cri.getType());
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@GetMapping("modify")
	public void modify(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("[Board] modify.... bno = {}", bno);
		model.addAttribute("board",service.get(bno));
	}
	
	@GetMapping("remove")
	public void remove() {
		
	}
	
	@PostMapping("remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("[Board] remove..... bno = {}", bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("type", cri.getType());
		 
		return "redirect:/board/list" + cri.getListLink();
	}
	
}
