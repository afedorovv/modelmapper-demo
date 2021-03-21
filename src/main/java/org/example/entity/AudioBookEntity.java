package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class AudioBookEntity extends BookEntity{
    private Integer playLength;
    private String reader;

    @Builder()
    public AudioBookEntity(Long id, String bookName, AuthorEntity author, String index, String comment, Integer playLength, String reader) {
        super(id, bookName, author, index, comment);
        this.playLength = playLength;
        this.reader = reader;
    }
}
