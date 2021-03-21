package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String bookName;
    @ManyToOne
    private AuthorEntity author;
    private String index;
    private String comment;

    public BookEntity(Long id, String bookName, AuthorEntity author, String index, String comment) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.index = index;
        this.comment = comment;
    }
}
