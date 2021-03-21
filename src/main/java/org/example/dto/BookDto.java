package org.example.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookDto {
    private String bookName;
    private String index;
    private String review;
    private AuthorDto author;
}
