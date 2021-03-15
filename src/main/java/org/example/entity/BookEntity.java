package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String bookName;
    @ManyToOne
    private AuthorEntity author;
    private Integer pages;
    private String index;
    private String comment;

    @Builder

    public BookEntity(Long id, String bookName, AuthorEntity author, Integer pages, String index, String comment) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.pages = pages;
        this.index = index;
        this.comment = comment;
    }
}
