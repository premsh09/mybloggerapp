package com.blogapp52.service;

import com.blogapp52.payload.CommentDto;
import com.blogapp52.payload.DraftWithCommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, long draftId);

    DraftWithCommentDto getAllCommentsByDraftId(long id);

    CommentDto getCommentById(long id);
}
