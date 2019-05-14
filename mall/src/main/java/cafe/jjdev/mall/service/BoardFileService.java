package cafe.jjdev.mall.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cafe.jjdev.mall.commons.PathConstant;
import cafe.jjdev.mall.mapper.BoardFileMapper;
import cafe.jjdev.mall.vo.BoardFile;

@Service
@Transactional
public class BoardFileService {
	@Autowired
	private BoardFileMapper boardFileMapper;

	public int addBoardFile(MultipartFile boardFile, int boardNo){
		System.out.println("BoardFileService.addBoardFile boardFile : "+boardFile);
		MultipartFile multipartFile = boardFile;
		if(multipartFile.getSize() != 0) {
			String originalFileName = multipartFile.getOriginalFilename();
			int i = originalFileName.lastIndexOf(".");
			String originName = originalFileName.substring(0, i);
			String ext = originalFileName.substring(i+1).toLowerCase();
			UUID uuid = UUID.randomUUID();
			String saveName = uuid.toString().replace("-", "");
			
			BoardFile boardFiletwo = new BoardFile();
			boardFiletwo.setBoardNo(boardNo);
			boardFiletwo.setBoardFileOriginName(originName);
			//boardFile.setBoardFileSaveName(saveName);
			boardFiletwo.setBoardFileSaveName(saveName);  
			boardFiletwo.setBoardFileExt(ext);
			boardFiletwo.setBoardFileSize(multipartFile.getSize());
			boardFiletwo.setBoardFileType(multipartFile.getContentType());
			System.out.println("BoardService.addBoard boardFile : "+boardFile);
			// 3. 폴더에 저장
			File file = new File(PathConstant.UPLOAD_PATH+"/"+saveName+"."+ext); // 빈파일
			try {
				multipartFile.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			boardFileMapper.insertBoardFile(boardFiletwo);
		}
		return 0;
	}	
	
	public int removeBoardFile(int boardFileNo) {
		
		return boardFileMapper.deleteBoardFileByFileNo(boardFileNo);
	}
	
}
