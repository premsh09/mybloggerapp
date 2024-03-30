package com.blogapp52.controller;

import com.blogapp52.payload.DraftDto;
import com.blogapp52.payload.ListDraftDto;
import com.blogapp52.service.DraftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/draft")
public class DraftController {

    private DraftService draftService;

    public DraftController(DraftService draftService) {
        this.draftService = draftService;
    }

    // http://localhost:8080/api/draft
    @PostMapping
    public ResponseEntity<DraftDto> createDraft(@RequestBody DraftDto draftDto){
        DraftDto dto = draftService.createDraft(draftDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/draft/2
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDraft(@PathVariable long id){
        draftService.deleteDraft(id);
        return new ResponseEntity<>("Post is deleted", HttpStatus.OK);
    }

    //http://localhost:8080/api/draft?pageNo=0&pageSize=3&sortBy=title&sortDir=desc
    @GetMapping
    public ResponseEntity<ListDraftDto> fetchAll(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "5",required = false)int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false)String sortDir
    ){
        ListDraftDto listDraftDto = draftService.fetchAll(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(listDraftDto, HttpStatus.OK);
    }

   // http://localhost:8080/api/draft/1
    @GetMapping("/{id}")
    public ResponseEntity<DraftDto> getDraftById(@PathVariable long id){
        DraftDto dto = draftService.getDraftById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
