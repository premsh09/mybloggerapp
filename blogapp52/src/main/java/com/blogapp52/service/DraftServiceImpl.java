package com.blogapp52.service;

import com.blogapp52.entity.Draft;
import com.blogapp52.exception.ResourceNotFound;
import com.blogapp52.payload.DraftDto;
import com.blogapp52.payload.ListDraftDto;
import com.blogapp52.repository.DraftRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DraftServiceImpl implements DraftService{

    private DraftRepository draftRepository;

    private ModelMapper modelMapper;

    public DraftServiceImpl(DraftRepository draftRepository, ModelMapper modelMapper) {
        this.draftRepository = draftRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DraftDto createDraft(DraftDto draftDto) {
        Draft draft = mapToEntity(draftDto);

        Draft savedDraft = draftRepository.save(draft);

        DraftDto dto = mapToDto(savedDraft);

        return dto;
    }

    @Override
    public void deleteDraft(long id) {
        draftRepository.deleteById(id);
    }

    @Override
    public ListDraftDto fetchAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
       Pageable pageable =  PageRequest.of(pageNo,pageSize, sort);
        Page<Draft> all = draftRepository.findAll(pageable);
        List<Draft> drafts = all.getContent();
        List<DraftDto> draftDtos = drafts.stream().map(d -> mapToDto(d)).collect(Collectors.toList());
        ListDraftDto listDraftDto = new ListDraftDto();
        listDraftDto.setDraftDto(draftDtos);
        listDraftDto.setTotalPage(all.getTotalPages());
        listDraftDto.setTotalElements((int) all.getTotalElements());
        listDraftDto.setPageNumber(all.getNumber());
        listDraftDto.setFirstPage(all.isFirst());
        listDraftDto.setLastPage(all.isLast());
        return listDraftDto;
    }

    @Override
    public DraftDto getDraftById(long id) {
        Draft draft = draftRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Draft is not found with id:" + id));
        return mapToDto(draft);
    }

    Draft mapToEntity(DraftDto draftDto){
       Draft draft = modelMapper.map(draftDto, Draft.class);
       return draft;
    }

    DraftDto mapToDto(Draft draft){
        DraftDto dto = modelMapper.map(draft, DraftDto.class);
        return dto;
    }
}
