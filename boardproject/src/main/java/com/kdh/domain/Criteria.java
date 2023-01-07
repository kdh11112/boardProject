package com.kdh.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum; //페이징
	private int amount; //페이징
	
	private String type; //검색
	private String keyword; //검색
	
	public Criteria() { //페이징
		this(1,10);
	}

	public Criteria(int pageNum, int amount) { //페이징
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	

}
