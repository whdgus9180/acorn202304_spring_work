package com.gura.spring02.guest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.guest.dao.GuestDao;
import com.gura.spring02.guest.dto.GuestDto;
import com.gura.spring02.guest.service.GuestService;

@Controller
public class GuestController {
	//컨트롤러는 비즈니스 로직을 대신 처리해주는 서비스에 의존한다.
	@Autowired
	private GuestService service;
	
	@RequestMapping("/guest/getData")
	public String getData(ModelAndView mView, int num) {
		service.getGuestInfo(mView, num);
		return "guest/getData";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/guest/update")
	public String update(GuestDto dto) {
		//서비스를 이용해서 글정보를 추가한다.
		service.addGuest(dto);
		return "guest/update";
	}
	
	@RequestMapping("/guest/updateform")
	public ModelAndView updateform(ModelAndView mView, int num) {
		//서비스를 이용해서 ModelAndView 객체에 글하나의 정보를 담아온다.
		service.getGuestInfo(mView, num);
		//view page 로 forward 이용해서 수정폼 응답하기
		mView.setViewName("guest/updateform");
		return mView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/guest/delete") //post요청 같은경우에는 메소드를 적어주는게 좋다
	public String delete(GuestDto dto) {
		service.deleteGuest(dto);
		return "redirect:/guest/list";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/guest/insert")
	public String insert(GuestDto dto) { 
		service.addGuest(dto);
		return "guest/insert";
	}
	
	@RequestMapping("/guest/insertform")
	public String insertform() {
		return "guest/insertform";
	}
	
//	@RequestMapping("/guest/list")
//	public String list(HttpServletRequest request) {
//		List<GuestDto> list = dao.getList();
//		request.setAttribute("list", list);
//		return "guest/list";
//	}
	
	@RequestMapping("/guest/list")
	public ModelAndView list(ModelAndView mView) {
		//서비스의 메소드를 호출해서 ModeAndView 객체의 참조값을 전달하면 방명록 목록이 담긴다.
		service.getGuestList(mView);
		
		//view page 정보도 담고
		mView.setViewName("guest/list");
		/*
		 *  두개의 정보가 모두 담긴 ModelAndView 객체를 리턴해주면
		 *  addObject(key, value) 를 통해서 담은 정보는 request scope 에 담기고
		 *  setViewName(view page 위치)를 통해서 담은 정보는 해당 view page 로 forward 이동해서 응답한다.
		 */
		return mView;
	}
}






























