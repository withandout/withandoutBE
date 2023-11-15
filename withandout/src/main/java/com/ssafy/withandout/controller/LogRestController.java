package com.ssafy.withandout.controller;

import com.ssafy.withandout.model.dto.Board;
import com.ssafy.withandout.model.dto.SearchCondition;
import com.ssafy.withandout.model.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "게시판 컨트롤러")
@CrossOrigin("*")
public class ScheduleRestController {
	
	@Autowired
	private BoardService boardService;
	
	@ApiOperation(value="게시글 조회")
	@GetMapping("/journey")
	public ResponseEntity<?> list(){
		List<Board> list = boardService.getBoardList();
		
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="게시글  검색")
	@GetMapping("/search")
	public ResponseEntity<?> search(SearchCondition condition){
		List<Board> list = boardService.search(condition); 
		
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="게시글 상세")
	@GetMapping("/journey/{id}")
	public ResponseEntity<Board> detail(@PathVariable int id){
		Board board = boardService.readBoard(id);
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	@ApiOperation(value="게시글 등록")
	@PostMapping("/journey")
	public ResponseEntity<Board> write(Board board){
		boardService.writeBoard(board);
		return new ResponseEntity<Board>(board, HttpStatus.CREATED);
	}
	
	@ApiOperation(value="게시글 삭제")
	@DeleteMapping("/journey/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		boardService.removeBoard(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="게시글 수정")
	@PutMapping("/journey") 
	public ResponseEntity<Void> update(Board board){
		boardService.modifyBoard(board);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
