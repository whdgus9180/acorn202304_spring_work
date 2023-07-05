package com.gura.spring04.gallery.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;


import com.gura.spring04.gallery.dto.GalleryDto;


public interface GalleryService {
	    //갤러리의 list 가져오기
		public void getList(HttpServletRequest request);
		
		//갤러리에 사진 upload & DB저장하기
		public void saveImage(GalleryDto dto, HttpServletRequest request);
		
		//갤러리에 detail 페이지에 필요한 data를 ModelAndView 에 저장
		public void getDetail(ModelAndView mView, int num);

}
