package com.ssafy.withandout.model.dao;

import com.ssafy.withandout.model.dto.Board;
import com.ssafy.withandout.model.dto.SearchCondition;

import java.util.List;

public interface LogDao {
	public List<Board> selectAll();

	public Board selectOne(int id);

	public void insertBoard(Board board);

	public void deleteBoard(int id);

	public void updateBoard(Board board);

	public void updateViewCnt(int id);
	
	public List<Board> search(SearchCondition condition);
	
}
