package cafe.jjdev.mall.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardRequest { 
    private int boardNo;
    private String boardPw;
    private String boardTitle;
    private String boardContent;
    private String boardUser;
    private String boardDate;
    private List<MultipartFile> boardFile;
	public BoardRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardPw() {
		return boardPw;
	}
	public void setBoardPw(String boardPw) {
		this.boardPw = boardPw;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardUser() {
		return boardUser;
	}
	public void setBoardUser(String boardUser) {
		this.boardUser = boardUser;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public List<MultipartFile> getBoardFile() {
		return boardFile;
	}
	public void setBoardFile(List<MultipartFile> boardFile) {
		this.boardFile = boardFile;
	}
	@Override
	public String toString() {
		return "BoardRequest [boardNo=" + boardNo + ", boardPw=" + boardPw + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", boardUser=" + boardUser + ", boardDate=" + boardDate
				+ ", boardFile=" + boardFile + "]";
	}
	
	
}
