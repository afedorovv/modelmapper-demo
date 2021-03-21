package org.example;

import org.example.entity.AudioBookEntity;
import org.example.entity.AuthorEntity;
import org.example.entity.BookEntity;
import org.example.entity.HardCoverBookEntity;
import org.example.repositories.AuthorRepository;
import org.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackageClasses = BookEntity.class)
public class Application {
    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        AuthorEntity authorEntity1 = AuthorEntity.builder().name("Чарльз Диккенс").build();
        AuthorEntity authorEntity2 = AuthorEntity.builder().name("Джейн Остин").build();
        AuthorEntity authorEntity3 = AuthorEntity.builder().name("Иоганн Вольфганг фон Гёте").build();

        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        authorEntity1 = authorRepository.save(authorEntity1);
        authorEntity2 = authorRepository.save(authorEntity2);
        authorEntity3 = authorRepository.save(authorEntity3);

        BookEntity bookEntity1 = HardCoverBookEntity.builder()
                .bookName("Приключения Оливера Твиста")
                .author(authorEntity1)
                .pages(220)
                .comment("Отличный приключенчиский роман")
                .index("ISBN: 978-5-91921-226-3")
                .build();
        BookEntity bookEntity2 = HardCoverBookEntity.builder()
                .bookName("Гордость и предубеждение")
                .author(authorEntity2)
                .pages(400)
                .comment("Занудная история про богатеев в Америке")
                .index("ISBN: 978-5-699-52151-7")
                .build();
        BookEntity bookEntity3 = AudioBookEntity.builder()
                .bookName("Фауст")
                .author(authorEntity3)
                .playLength(873)
                .comment("Пища для ума")
                .index("ISBN: 5-699-07346-9")
                .reader("Илья Прудовский")
                .build();


        context.getBean(BookRepository.class).saveAll(List.of(bookEntity1, bookEntity2, bookEntity3));
    }
}
