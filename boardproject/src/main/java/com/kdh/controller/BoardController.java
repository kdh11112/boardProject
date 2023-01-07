package com.kdh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kdh.domain.BoardVO;
import com.kdh.domain.Criteria;
import com.kdh.domain.PageDTO;
import com.kdh.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	//@Autowired
	private BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) { //addAttribute 메소드를 이용해 Model 객체에 담아서 전달
//		log.info("[CONTROLLER] get list...");
//		model.addAttribute("list",service.getList()); //Model에 BoardVO의 목록을 담아서 전달
//	}
	
	@GetMapping("/list")  //페이징 처리
	public void list(Criteria cri , Model model) { //addAttribute 메소드를 이용해 Model 객체에 담아서 전달
		log.info("[CONTROLLER] get list..."+cri);
		model.addAttribute("list",service.getList(cri)); //Model에 BoardVO의 목록을 담아서 전달
		model.addAttribute("pageMaker",new PageDTO(cri,service.getTotal(cri)));
		//model.addAttribute("pageMaker",new PageDTO(cri,123));
	    // pageDTO객체를 구성하기 위해서는 전체 데이터 수가 필요한데, 
	    //아직 그 처리가 이루어지지 않았으므로 임의의 값으로 123을 지정 
	}
	
	//게시물 등록 화면 추가된부분
	@GetMapping("/register")
	public void registerGET() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board,RedirectAttributes rttr) {
		log.info("[CONTROLLER] register..."+board);
		service.register(board);
		rttr.addFlashAttribute("result",board.getBno());
		return "redirect:/board/list";
	}
	
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String registPOST(BoardVO board, Model model) throws Exception{
//        
//		service.register(board);
//        model.addAttribute("result","success");
//        log.info("되는거 맞아??");
//        return "redirect:/board/listAll";
//    }
	
//	@GetMapping("/get")
//	public void get(@RequestParam("bno") Long bno, Model model) {
//	    log.info("[ CONTROLLER ] get ……..");
//	    model.addAttribute("board", service.get(bno));
//	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		 log.info("[ CONTROLLER ] get ……..+ modify!!");
		model.addAttribute("board",service.get(bno));
	}
	
	// 실제로 게시물을 수정하는 경우
		@PostMapping("/modify")
		public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
			log.info("[ CONTROLLER ] modify:" + board);
			if (service.modify(board)) {
				rttr.addFlashAttribute("result", "success");
			}
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			rttr.addAttribute("type", cri.getType());		//추가
			rttr.addAttribute("keyword", cri.getKeyword());		//추가
			return "redirect:/board/list";
		}

		//삭제
		@PostMapping("/remove")
		public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
			log.info("[ CONTROLLER ] remove..." + bno);
			if (service.remove(bno)) {
				rttr.addFlashAttribute("result", "success");
			}
			log.info(cri.getPageNum());
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			rttr.addAttribute("type", cri.getType());		//추가
			rttr.addAttribute("keyword", cri.getKeyword());		//추가
			return "redirect:/board/list";
		}
	
	
}
