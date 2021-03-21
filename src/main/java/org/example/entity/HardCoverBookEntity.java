package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class HardCoverBookEntity extends BookEntity {
    private Integer pages;

    @Builder
    public HardCoverBookEntity(Long id, String bookName, AuthorEntity author, String index, String comment, Integer pages) {
        super(id, bookName, author, index, comment);
        this.pages = pages;
    }
}
