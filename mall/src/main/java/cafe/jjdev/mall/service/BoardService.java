package cafe.jjdev.mall.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cafe.jjdev.mall.commons.PathConstant;
import cafe.jjdev.mall.mapper.BoardCommentMapper;
import cafe.jjdev.mall.mapper.BoardFileMapper;
import cafe.jjdev.mall.mapper.BoardMapper;
import cafe.jjdev.mall.vo.Board;
import cafe.jjdev.mall.vo.BoardComment;
import cafe.jjdev.mall.vo.BoardFile;
import cafe.jjdev.mall.vo.BoardRequest;

@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private BoardCommentMapper boardCommentMapper;
	@Autowired
	private BoardFileMapper boardFileMapper;
	
	// 글 목록
	public Map<String, Object> getBoardList(int currentPage) {
		// 요청 가공
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage-1)*ROW_PER_PAGE;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rowPerPage", ROW_PER_PAGE);
		map.put("beginRow", beginRow);
		
		List<Board> list = boardMapper.selectBoardList(map);
		int boardCount = boardMapper.selectBoardCount();
		int lastPage = boardCount/ROW_PER_PAGE;
		if(boardCount % ROW_PER_PAGE != 0) {
			lastPage++;
		}
		// 모델값(리턴값) 가공
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("boardCount", boardCount);
		return returnMap;
	}
	// 페이징을 위한 글 갯수 카운트
	public int getBoardCount() {
		return boardMapper.selectBoardCount();
	}
	// 
	public Map<String, Object> getBoard(int boardNo) {
		
		Board board = boardMapper.selectBoard(boardNo);
		List<BoardFile> boardFile = boardFileMapper.selectBoardFileByFK(boardNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("boardFile", boardFile);
		return map;
	}
	// 글 상세정보
	public Map<String, Object> getBoardAndCommentListAndFile(int boardNo) {
		// 상세 조회
		Board board = boardMapper.selectBoard(boardNo);
		// 댓글 목록 조회
		List<BoardComment> boardCommentList = boardCommentMapper.selectBoardCommentListByBoardNo(boardNo);
		System.out.println("BoardService.getBoardAndCommentListAndFile boardCommentList : "+boardCommentList);
		// 첨부파일 조회
		List<BoardFile> boardFile = boardFileMapper.selectBoardFileByFK(boardNo);
		System.out.println("BoardService.getBoardAndCommentListAndFile boardFile : "+boardFile);
		// 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("boardCommentList", boardCommentList);
		map.put("boardFile", boardFile);
		return map;
	}
	// 글 수정
	public int modifyBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	// 글 삭제
	public int removeBoard(Board board) {
		// 비밀번호 조회
		String result = boardMapper.selectBoardPw(board.getBoardNo());
		System.out.println("BoardService.removeBoard result : "+result);
		// 파일 삭제를 위한 파일 정보 조회
		List<BoardFile> boardFileList = boardFileMapper.selectBoardFileByFK(board.getBoardNo());
		System.out.println("BoardService.removeBoard boardFileList : "+boardFileList);
		if(result.equals(board.getBoardPw())) {
			// 코멘트 삭제
			boardCommentMapper.deleteBoardCommentByBoardNo(board.getBoardNo());
			// 실제 파일 삭제
			if(boardFileList != null){
				for(BoardFile boardFile : boardFileList) {
					File file = new File(PathConstant.UPLOAD_PATH+"/"+boardFile.getBoardFileSaveName()+"."+boardFile.getBoardFileExt()); // 빈파일
					file.delete();
				}
			}
			// 파일 db 삭제
			boardFileMapper.deleteBoardFileByBoardNo(board.getBoardNo());
			// 글 삭제
			int deleteBoardResult = boardMapper.deleteBoard(board);
			System.out.println("BoardService.removeBoard deleteBoardResult : "+deleteBoardResult);
		}
		return 0;
	}
	// 글 & 파일 등록
	public void addBoard(BoardRequest boardRequest) {
		System.out.println("BoardService.addBoard boardRequest : "+boardRequest);
		Board board = new Board();
		board.setBoardTitle(boardRequest.getBoardTitle());
		board.setBoardContent(boardRequest.getBoardContent());
		board.setBoardPw(boardRequest.getBoardPw());
		board.setBoardUser(boardRequest.getBoardUser());
		boardMapper.insertBoard(board);
		
		List<MultipartFile> multipartFileList = boardRequest.getBoardFile();
		System.out.println("BoardService.addBoard multipartFileList : "+multipartFileList);
		if(multipartFileList.size() != 0) {
			for(MultipartFile multipartFile : multipartFileList) {
				String originalFileName = multipartFile.getOriginalFilename();
				int i = originalFileName.lastIndexOf(".");
				String originName = originalFileName.substring(0, i);
				String ext = originalFileName.substring(i+1).toLowerCase();
				UUID uuid = UUID.randomUUID();
				String saveName = uuid.toString().replace("-", "");
				
				BoardFile boardFile = new BoardFile();
				boardFile.setBoardNo(board.getBoardNo());
				boardFile.setBoardFileOriginName(originName);
				boardFile.setBoardFileSaveName(saveName);
				boardFile.setBoardFileExt(ext);
				boardFile.setBoardFileSize(multipartFile.getSize());
				boardFile.setBoardFileType(multipartFile.getContentType());
				System.out.println("BoardService.addBoard boardFile : "+boardFile);
				// 3. 폴더에 저장
				File file = new File(PathConstant.UPLOAD_PATH+"/"+saveName+"."+ext); // 빈파일
				try {
					multipartFile.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
				boardFileMapper.insertBoardFile(boardFile);
			}
		}
	}
}


