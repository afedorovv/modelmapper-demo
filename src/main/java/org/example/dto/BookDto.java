package org.example.dto;

import lombok.Data;

@Data
public class BookDto {
    private String bookName;
    private String authorName;
    private Integer pages;
    private String index;
}
