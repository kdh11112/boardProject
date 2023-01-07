package com.kdh.mapper;

import java.util.List;

import com.kdh.domain.BoardVO;
import com.kdh.domain.Criteria;

public interface BoardMapper {
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public BoardVO read(Long bno);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public int getTotalCount(Criteria cri);
	
	

}
