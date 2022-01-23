package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//파라미터 vo클래스가 아니라 map으로 묶는 예제.
	public int personInsert2(String name, String hp, String company) {
		System.out.println("PhoneDao.personInsert() 파라미터 여러개로 받을때");
		
		Map<String, String> personMap = new HashMap<String, String>();
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		System.out.println(personMap);
		
		int count = sqlSession.insert("phonebook.insert2", personMap);
		//System.out.println(count + "건 저장");
		return 0;
	}
	
	public int personDelete(int personId) {
		System.out.println("PhoneDao.personDelete()");
		
		int count = sqlSession.delete("phonebook.delete", personId);
		System.out.println(count + "건 삭제 성공");
		
		return count;
	}
	
	// 전화번호 1명정보 -> vo 클래스 아닌 map 사용
		public PersonVo getPerson(int personId) {
			System.out.println("PhoneDao.getPerson()");

			//PersonVo personVo = sqlSession.selectOne("phonebook.selectPerson", personId);
			Map<String, Object> personMap =  sqlSession.selectOne("phonebook.selectPerson2", personId);
			System.out.println(personMap.keySet());
			System.out.println(personMap);
			
			
			System.out.println(personMap.get("PERSON_ID"));
			System.out.println(personMap.get("NAME"));
			System.out.println(personMap.get("HP"));
			System.out.println(personMap.get("COMPANY"));
			
			return null;
		}
	

}
