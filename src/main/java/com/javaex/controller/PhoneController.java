package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {
	@Autowired
	private PhoneDao phoneDao;
	
	//메소드 일반
	@RequestMapping(value="/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() { // 포워드할 주소 리턴할꺼라 String
		System.out.println("PhoneController>writeForm");
		return "writeForm";
	}
	
	
	
	@RequestMapping(value = "/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>write");
		
		System.out.println(personVo);

		
		phoneDao.personInsert(personVo);
		
		//리스트로 리다이렉트
		return "redirect:/phone/list";
	}
	
	//vo 클래스가 아니라 map으로 묶는 예제.
	@RequestMapping(value="/write2", method= {RequestMethod.GET, RequestMethod.POST} )
	public String write2(@RequestParam("name") String name,
						 @RequestParam("hp") String hp,
						 @RequestParam("company") String company
			) {
		System.out.println("PhoneController>write2()");
		
		//저장
		phoneDao.personInsert2(name, hp, company);
		
		//리다이렉트
		return "redirect:/phone/list";
	}

		
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhoneController>list");
		
		//Dao에서 리스트 가져옴
		
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList);
		
		//컨트롤러에서 ds로 데이터 보냄
		model.addAttribute("personList", personList); //model
		
		return "list"; //view
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("personId") int personId) {
		System.out.println("PhoneController>delete");
		
		phoneDao.personDelete(personId);
		
		return "redirect:/phone/list";
		
	}
	

	
	
	
	

}
