package com.example.spring02;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.spring02.model.board.dao.BoardDAO;
import com.example.spring02.model.board.dto.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=
{"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOImplTest {

	private static final Logger logger
	=LoggerFactory.getLogger(BoardDAOImplTest.class); 
	@Inject
	BoardDAO boardDao;
	
	@Test
	public void testCreate() throws Exception {
		BoardVO vo=new BoardVO();
		vo.setBno(10000);
		vo.setTitle("테스트");
		vo.setWriter("kim");
		vo.setContent("내용.....");
		boardDao.create(vo);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAll() {
		fail("Not yet implemented");
	}

}
