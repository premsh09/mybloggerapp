package com.blogapp52.service;

import com.blogapp52.entity.Comment;
import com.blogapp52.entity.Draft;
import com.blogapp52.exception.ResourceNotFound;
import com.blogapp52.payload.CommentDto;
import com.blogapp52.payload.DraftDto;
import com.blogapp52.payload.DraftWithCommentDto;
import com.blogapp52.repository.CommentRepository;
import com.blogapp52.repository.DraftRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private DraftRepository draftRepository;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    Comment mapToEntity(CommentDto dto){
      return modelMapper.map(dto, Comment.class);
    }

    CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long draftId) {
        Optional<Draft> byId = draftRepository.findById(draftId);
        Draft draft = byId.get();
        Comment comment = mapToEntity(commentDto);
        comment.setDraft(draft);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    @Override
    public DraftWithCommentDto getAllCommentsByDraftId(long id) {
        Draft draft = draftRepository.findById(id).orElseThrow(()->new ResourceNotFound("Comment not found with id :"+id));
        DraftDto draftDto = new DraftDto();
        draftDto.setId(draft.getId());
        draftDto.setTitle(draft.getTitle());
        draftDto.setDescription(draft.getDescription());
        draftDto.setContent(draft.getContent());
        List<Comment> comments = commentRepository.findByDraftId(id);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        DraftWithCommentDto draftWithCommentDto = new DraftWithCommentDto();

        draftWithCommentDto.setCommentDto(dtos);
        draftWithCommentDto.setDraft(draftDto);
        return draftWithCommentDto;
    }

    @Override
    public CommentDto getCommentById(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Comment not found with id: " + id));
        return mapToDto(comment);
    }
}
