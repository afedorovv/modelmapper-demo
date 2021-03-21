package org.example.dto;

import lombok.Data;

@Data
public class AudioBookDto extends BookDto{
    private String playTime;
    private String reader;
}
