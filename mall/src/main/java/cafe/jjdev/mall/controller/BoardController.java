package cafe.jjdev.mall.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.commons.PathConstant;
import cafe.jjdev.mall.service.BoardService;
import cafe.jjdev.mall.vo.Board;
import cafe.jjdev.mall.vo.BoardRequest;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/addBoard")
	public String addBoard() {
		return "board/addBoard";
	}
	@PostMapping("/board/addBoard")
	public String addBoard(BoardRequest boardRequest) {
		System.out.println("BoardController.addBoard boardRequest : "+boardRequest);
		// Servlet을 이용하기 때문에 Controller에서 매개변수로 보낸다
		// String path = ServletUriComponentsBuilder.fromCurrentContextPath().path("upload").toUriString(); // c:/temp/ <- 스프링에서는 이렇게도 가능
		boardService.addBoard(boardRequest);
		return "redirect:"+"/board/getBoardList";
	}
	@GetMapping("/board/removeBoard")
	public String removeBoard(Model model, @RequestParam(value="boardNo", required=true) int boardNo) {
		model.addAttribute("boardNo", boardNo);
		return "board/removeBoard";
	}
	@PostMapping("/board/removeBoard")
	public String removeBoard(Board board) {
		System.out.println("BoardController.removeBoard board : "+board);
		boardService.removeBoard(board);
		return "redirect:"+"/board/getBoardList";
	}
	@GetMapping("/board/modifyBoard")
	public String modifyBoard(Model model, @RequestParam(value="boardNo", required=true) int boardNo) {
		Map<String, Object> map = boardService.getBoard(boardNo);
		model.addAttribute("board", map.get("board"));
		model.addAttribute("boardFile", map.get("boardFile"));
		return "board/modifyBoard";
	}
	@PostMapping("/board/modifyBoard")
	public String modifyBoard(Board board) {
		System.out.println(board);
		boardService.modifyBoard(board);
		return "redirect:"+"/board/getBoard?boardNo="+board.getBoardNo();
	}
	@GetMapping("/board/getBoard")
	public String getBoard(Model model, @RequestParam(value="boardNo", required=true) int boardNo) {
		System.out.println("BoardController.getBoard GET boardNo : "+boardNo);
		Map<String, Object> map = boardService.getBoardAndCommentListAndFile(boardNo);
		System.out.println(map);
		model.addAttribute("board", map.get("board"));
		model.addAttribute("boardCommentList", map.get("boardCommentList"));
		model.addAttribute("boardFile", map.get("boardFile"));
		return "board/getBoard";
	}
	@GetMapping("/board/getBoardList")
	public String getBoardList(Model model, @RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
		Map<String, Object> returnmap = boardService.getBoardList(currentPage);
		model.addAttribute("list", returnmap.get("list"));
		model.addAttribute("lastPage", returnmap.get("lastPage"));
		model.addAttribute("boardCount", returnmap.get("boardCount"));
		model.addAttribute("currentPage", currentPage);
		return "board/getBoardList";
	}
}
