package cafe.jjdev.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cafe.jjdev.mall.service.BoardFileService;

@Controller
public class BoardFileController {
	@Autowired
	private BoardFileService boardFileService;
	
	@PostMapping("/board/addBoardFile")
	public String addBoardFile(MultipartFile boardFile, @RequestParam(value="boardNo", required = true) int boardNo){
		System.out.println("BoardFileController.addBoardFile boardFile : "+boardFile);
		System.out.println("BoardFileController.addBoardFile boardFile : "+boardNo);
		boardFileService.addBoardFile(boardFile, boardNo);
		return "redirect:"+"/board/modifyBoard?boardNo="+boardNo;
	}
	@GetMapping("/board/removeBoardFile")
	public String removeBoardFile(Model model, @RequestParam(value="boardFileNo", required = true) int boardFileNo) {
		model.addAttribute("boardFileNo", boardFileNo);
		return "board/removeBoardFile";
	}
	@PostMapping("/board/removeBoardFile")
	public String removeBoardFile(@RequestParam(value="boardFileNo", required = true) int boardFileNo, @RequestParam(value="boardNo", required = true) int boardNo) {
		System.out.println(boardNo);
		boardFileService.removeBoardFile(boardFileNo);
		return "redirect:"+"/board/modifyBoard?boardNo="+boardNo;
	}
	
	
}
