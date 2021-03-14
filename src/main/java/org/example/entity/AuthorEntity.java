package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class AuthorEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Builder
    public AuthorEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
