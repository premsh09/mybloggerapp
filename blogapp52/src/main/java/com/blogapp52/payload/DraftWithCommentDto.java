package com.blogapp52.payload;

import lombok.Data;

import java.util.List;

@Data
public class DraftWithCommentDto {

    private DraftDto draft;
    private List<CommentDto> commentDto;
}
