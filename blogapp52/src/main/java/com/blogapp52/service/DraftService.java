package com.blogapp52.service;

import com.blogapp52.payload.DraftDto;
import com.blogapp52.payload.ListDraftDto;

public interface DraftService {

public DraftDto createDraft(DraftDto draftDto);

    void deleteDraft(long id);

    ListDraftDto fetchAll(int pageNo, int pageSize, String sortBy, String sortDir);

    DraftDto getDraftById(long id);
}
