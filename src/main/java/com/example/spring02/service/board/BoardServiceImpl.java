package com.example.spring02.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring02.model.board.dao.BoardDAO;
import com.example.spring02.model.board.dto.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	
	@Inject
	BoardDAO boardDao;
	
	@Transactional  //트랜잭션 처리 method로 설정
	@Override
	public void create(BoardVO vo) throws Exception {
		String title = vo.getTitle();
		String writer = vo.getWriter();
		String content=vo.getContent();
// replace(A,B) A를 B로 변경		
		// 태그 문자 처리( < => &lt;  > => &gt; )
		title = title.replace("<","&lt;");
		title = title.replace(">","&gt;");
		writer = writer.replace("<","&lt;");
		writer = writer.replace(">","&gt;");
		content = content.replace("<","&lt;");
		content = content.replace(">","&gt;");
		// 공백 문자 처리( 공백 => &nbsp; )
		title = title.replace("  ","&nbsp;&nbsp;");
		writer = writer.replace("  ","&nbsp;&nbsp;");
		// 줄바꿈 문자 처리 ( \n => <br> )
		content = content.replace("\n","<br>");
		vo.setContent(content);  
		
		vo.setTitle(title);
		vo.setWriter(writer);		
		//게시물 등록
		boardDao.create(vo); 
		//첨부파일 정보 등록
		String[] files=vo.getFiles(); //첨부파일 배열
		//첨부파일이 없으면 종료
		if(files==null) return;
		//첨부파일들의 정보를 tbl_attach 테이블에 insert
		for(String name : files){
			boardDao.addAttach(name);
		}
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return boardDao.read(bno);
	}
	@Transactional
	@Override
	public void update(BoardVO vo) throws Exception {
		boardDao.update(vo); 
		//첨부파일 정보 등록
		String[] files=vo.getFiles(); //첨부파일 배열
		//첨부파일이 없으면 종료
		if(files==null) return;
		//첨부파일들의 정보를 tbl_attach 테이블에 insert
		for(String name : files){ 
			boardDao.updateAttach(name, vo.getBno());
		}
	}

	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno); 
	}

	@Override
	public List<BoardVO> listAll(
			int start, int end,
			String search_option,String keyword) throws Exception {
		return 
boardDao.listAll(start, end, search_option,keyword);
	}

	@Override
	public void increaseViewcnt(
			int bno,HttpSession session) throws Exception {
		long update_time=0;
		//세션에 저장된 조회시간 검색
		if(session.getAttribute("update_time_"+bno)
				!= null){
			update_time=(long)session.getAttribute(
					"update_time_"+bno);
		}
		//시스템의 현재 시간
		long current_time=System.currentTimeMillis();
		//일정 시간이 경과 후 조회수 증가 처리
		if(current_time - update_time > 5*1000){
			boardDao.increaseViewcnt(bno);  
			session.setAttribute(
					"update_time_"+bno, current_time);
		}
	}

	@Override
	public int countArticle(String search_option, 
			String keyword) throws Exception {
		return 
boardDao.countArticle(search_option, keyword);
	}

	@Override
	public List<String> getAttach(int bno) {
		return boardDao.getAttach(bno); 
	}

	@Override
	public void deleteFile(String fullName) {
		boardDao.deleteFile(fullName); 
	}

}












