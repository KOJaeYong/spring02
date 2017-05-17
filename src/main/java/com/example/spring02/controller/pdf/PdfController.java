package com.example.spring02.controller.pdf;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring02.model.board.dto.BoardVO;
import com.example.spring02.service.board.BoardService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@RequestMapping("/pdf/*")
public class PdfController {
	
	@Inject
	BoardService boardService;
	
	@RequestMapping("list.do")
	public String list() throws Exception {
		//com.itextpdf.text.Document
		Document document=new Document(); //pdf 문서 객체 생성
		//pdf writer 객체
		PdfWriter writer=PdfWriter.getInstance(document, 
				new FileOutputStream("d:\\sample.pdf"));
		document.open(); //pdf 문서 열기
		BaseFont baseFont=BaseFont.createFont(
"c:/windows/fonts/malgun.ttf"
				, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font=new Font(baseFont,12); //폰트 설정
		PdfPTable table=new PdfPTable(5); //5 컬럼의 테이블
		Chunk chunk = new Chunk("게시판", font);//출력할 내용
		Paragraph ph=new Paragraph(chunk);//문단
		ph.setAlignment(Element.ALIGN_CENTER);//가운데 정렬
		document.add(ph);

		
		document.add(Chunk.NEWLINE); //줄바꿈 처리
		document.add(Chunk.NEWLINE);
		// document.newPage(); //페이지 나누기
		
		//테이블의 타이틀 행 생성
		PdfPCell cell1=new PdfPCell(new Phrase("번호",font));
		//왼쪽 정렬
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell1); 
		PdfPCell cell2=new PdfPCell(new Phrase("제목",font));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell2); 
		PdfPCell cell3=new PdfPCell(new Phrase("이름",font));
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell3); 
		PdfPCell cell4=new PdfPCell(new Phrase("날짜",font));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell4); 
		PdfPCell cell5=new PdfPCell(new Phrase("조회수",font));
		cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell5); 		
		
		List<BoardVO> items
			=boardService.listAll(1, 10, "all", "");
		for(int i=0; i<items.size(); i++){
			BoardVO vo=items.get(i);
			table.addCell(""+vo.getBno()); //번호
			PdfPCell cellTitle
			=new PdfPCell(new Phrase(vo.getTitle(),font));
			table.addCell(cellTitle); //제목
			PdfPCell cellName
				=new PdfPCell(new Phrase(vo.getUsername(),font));
			table.addCell(cellName); //이름
			// Date type을 String으로 변환
			Date date=vo.getRegdate(); //java.util.Date
			SimpleDateFormat sdf
				=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate=sdf.format(date);
			PdfPCell cellDate
				=new PdfPCell(new Phrase(strDate,font));
			table.addCell(cellDate);						
			PdfPCell cellCnt
=new PdfPCell(new Phrase(""+vo.getViewcnt(),font));
			table.addCell(cellCnt);			
		}
		
		document.add(table);
		
		
		
		document.close(); //pdf 문서 닫기 
		return "pdf/list"; // views/pdf/list.jsp로 포워딩
	}
}












