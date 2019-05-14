package cafe.jjdev.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.BoardFile;

@Mapper
public interface BoardFileMapper {
	public List<BoardFile> selectBoardFileByFK(int boardNo);
	public int deleteBoardFileByFileNo(int boardFileNo);
	public int deleteBoardFileByBoardNo(int BoardNo);
	public int insertBoardFile(BoardFile boardFile);
}
