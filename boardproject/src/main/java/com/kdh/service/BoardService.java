package com.kdh.service;

import java.util.List;

import com.kdh.domain.BoardVO;
import com.kdh.domain.Criteria;

public interface BoardService{
	
	
	public void register(BoardVO board); //등록
	
	public BoardVO get(Long bno); //글 조회
	
	public boolean modify(BoardVO board); //수정
	
	public boolean remove(Long bno); //삭제
	
	public List<BoardVO> getList(); //리스트 조회
	
	public List<BoardVO> getList(Criteria cri); //리스트 조회
	
	public int getTotal(Criteria cri);

}
