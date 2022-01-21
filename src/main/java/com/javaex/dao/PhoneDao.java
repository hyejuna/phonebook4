package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//전체리스트 가져오기
	public List<PersonVo> getPersonList() {
		
		//System.out.println("PhoneDao.getPersonList()");
		
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList"); //가운데 selectList는 메소드. ? 포함한 코드 + try catch 문 써줌
		//System.out.println(personList);
		
		return personList;
	
	}

	//전화번호 추가
	public int personInsert(PersonVo personVo) {
		//System.out.println("PhoneDao.personInsert()");
		
		int count = sqlSession.insert("phonebook.insert", personVo);
		//System.out.println(count + "건 입력 성공");
		
		return count; //성공한 건수 

	}
	
	public int personDelete(int personId) {
		System.out.println("PhoneDao.personDelete()");
		
		int count = sqlSession.delete("phonebook.delete", personId);
		System.out.println(count + "건 삭제 성공");
		
		return count;
	}
	

}
