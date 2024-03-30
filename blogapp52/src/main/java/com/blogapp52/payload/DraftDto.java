package com.blogapp52.payload;

import lombok.Data;

@Data
public class DraftDto {

    private long id;
    private String title;
    private String description;
    private String content;
}
