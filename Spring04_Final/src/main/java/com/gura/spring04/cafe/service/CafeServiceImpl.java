package com.gura.spring04.cafe.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gura.spring04.cafe.dao.CafeCommentDao;
import com.gura.spring04.cafe.dao.CafeDao;
import com.gura.spring04.cafe.dto.CafeCommentDto;
import com.gura.spring04.cafe.dto.CafeDto;
import com.gura.spring04.exception.NotDeleteException;

@Service
public class CafeServiceImpl implements CafeService{
	
	@Autowired
	private CafeDao cafeDao;
	
	@Autowired
	private CafeCommentDao commentDao;
	
	//페이징 처리, 검색어 기능을 고려한 비즈니스 로직 처리를 하는 메소드 
	@Override
	public void getList(HttpServletRequest request) {
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=5;
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=5;
		
		//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
		int pageNum=1;
		//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
		String strPageNum=request.getParameter("pageNum");
		//만일 페이지 번호가 파라미터로 넘어 온다면
		if(strPageNum != null){
			//숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		/*
			[ 검색 키워드에 관련된 처리 ]
			-검색 키워드가 파라미터로 넘어올수도 있고 안넘어 올수도 있다.		
		*/
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		//만일 키워드가 넘어오지 않는다면 
		if(keyword==null){
			//키워드와 검색 조건에 빈 문자열을 넣어준다. 
			//클라이언트 웹브라우저에 출력할때 "null" 을 출력되지 않게 하기 위해서  
			keyword="";
			condition=""; 
		}

		//특수기호를 인코딩한 키워드를 미리 준비한다. 
		String encodedK=URLEncoder.encode(keyword);
			
		//CafeDto 객체에 startRowNum 과 endRowNum 을 담는다.
		CafeDto dto=new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		//만일 검색 키워드가 넘어온다면 
		if(!keyword.equals("")){
			//검색 조건이 무엇이냐에 따라 분기 하기
			if(condition.equals("title_content")){//제목 + 내용 검색인 경우
				//검색 키워드를 CafeDto 에 담아서 전달한다.
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")){ //제목 검색인 경우
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){ //작성자 검색인 경우
				dto.setWriter(keyword);
			} // 다른 검색 조건을 추가 하고 싶다면 아래에 else if() 를 계속 추가 하면 된다.
		}
		//글 목록 얻어오기 
		List<CafeDto> list=cafeDao.getList(dto);
		//전체글의 갯수
		int totalRow=cafeDao.getCount(dto);
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		

		//전체 페이지의 갯수
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 전체 페이지 갯수보다 크다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다.
		}
		//view page 에서 필요한 값을 request 에 담아준다. 
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("list", list);
		request.setAttribute("totalRow", totalRow);
	}

	@Override
	public void getDetail(HttpServletRequest request) {
		//자세히 보여줄 글번호를 읽어온다. 
		int num=Integer.parseInt(request.getParameter("num"));
		//조회수 올리기
		cafeDao.addViewCount(num);
		
		/*
			[ 검색 키워드에 관련된 처리 ]
			-검색 키워드가 파라미터로 넘어올수도 있고 안넘어 올수도 있다.		
		*/
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		//만일 키워드가 넘어오지 않는다면 
		if(keyword==null){
			//키워드와 검색 조건에 빈 문자열을 넣어준다. 
			//클라이언트 웹브라우저에 출력할때 "null" 을 출력되지 않게 하기 위해서  
			keyword="";
			condition=""; 
		}
		//CafeDto 객체를 생성해서 
		CafeDto dto=new CafeDto();
		//자세히 보여줄 글번호를 넣어준다. 
		dto.setNum(num);
		//만일 검색 키워드가 넘어온다면 
		if(!keyword.equals("")){
			//검색 조건이 무엇이냐에 따라 분기 하기
			if(condition.equals("title_content")){//제목 + 내용 검색인 경우
				//검색 키워드를 CafeDto 에 담아서 전달한다.
				dto.setTitle(keyword);
				dto.setContent(keyword);			
			}else if(condition.equals("title")){ //제목 검색인 경우
				dto.setTitle(keyword);	
			}else if(condition.equals("writer")){ //작성자 검색인 경우
				dto.setWriter(keyword);	
			} // 다른 검색 조건을 추가 하고 싶다면 아래에 else if() 를 계속 추가 하면 된다.
		}
		
		//글하나의 정보를 얻어온다.
		CafeDto resultDto=cafeDao.getData(dto);
		
		//특수기호를 인코딩한 키워드를 미리 준비한다. 
		String encodedK=URLEncoder.encode(keyword);
		
		//request scope 에 글 하나의 정보 담기
		request.setAttribute("dto", resultDto);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
	}

	@Override
	public void saveContent(CafeDto dto) {
		//title, content, writer 정보가 들어 있는 CafeDto 를 dao 에 전달해서 DB 에 저장되도록한다.
		cafeDao.insert(dto);
	}

	@Override
	public void updateContent(CafeDto dto) {
		cafeDao.update(dto);
	}

	@Override
	public void deleteContent(int num, HttpServletRequest request) {
		//세션에서 로그인된 아이디를 읽어와서 
		String id=(String)request.getSession().getAttribute("id");
		//글 작성자와 로그인된 아이디가 다르다면 
		CafeDto dto=cafeDao.getData(num);
		//예외를 발생시켜서 삭제가 안되도록 한다. 
		if(!id.equals(dto.getWriter())) {
			throw new NotDeleteException("남의 글 지우기 없기!");
		}
		//글 삭제하기 
		cafeDao.delete(num);
	}
	//글수정 폼에 필요한 값을 HttpServletRequest 에 담아주는 메소드 
	@Override
	public void getData(HttpServletRequest request) {
		//수정할 글번호
		int num=Integer.parseInt(request.getParameter("num"));
		//수정할 글의 정보 얻어와서 
		CafeDto dto=cafeDao.getData(num);
		//request 에 담아준다.
		request.setAttribute("dto", dto);
	}

	@Override
	public void saveComment(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateComment(CafeCommentDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moreCommentList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

}





