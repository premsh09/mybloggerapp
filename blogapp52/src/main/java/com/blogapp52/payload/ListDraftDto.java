package com.blogapp52.payload;

import lombok.Data;

import java.util.List;
@Data
public class ListDraftDto {
    private List<DraftDto> draftDto;
    private int totalPage;
    private int totalElements;
    private  int pageNumber;
    private boolean firstPage;
    private boolean lastPage;
}
