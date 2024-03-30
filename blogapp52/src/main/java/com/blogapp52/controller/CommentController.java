package com.blogapp52.controller;

import com.blogapp52.payload.CommentDto;
import com.blogapp52.payload.DraftWithCommentDto;
import com.blogapp52.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    // http://localhost:8080/api/comments/1
    @PostMapping("/{draftId}")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable long draftId){
        CommentDto dto = commentService.createComment(commentDto, draftId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{draftId}")
    public ResponseEntity<DraftWithCommentDto> getAllCommentsByDraftId(@PathVariable long draftId){
        DraftWithCommentDto allCommentsByDraftId = commentService.getAllCommentsByDraftId(draftId);
        return new ResponseEntity<>(allCommentsByDraftId, HttpStatus.OK);
    }

    // http://localhost:8080/api/comments?id=1
    @GetMapping
    public ResponseEntity<CommentDto> getCommentById(@RequestParam long id){
        CommentDto dto = commentService.getCommentById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
