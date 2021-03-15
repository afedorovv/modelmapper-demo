package org.example.convertor;

import org.example.dto.AuthorDto;
import org.example.dto.BookDto;
import org.example.entity.AuthorEntity;
import org.example.entity.BookEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookConvertor {
    private final ModelMapper modelMapper;
    private Converter<String, String> isbnRemover = (src) -> src.getSource().replaceAll("ISBN: ", "");

    public BookConvertor() {
        this.modelMapper = new ModelMapper();

        modelMapper.createTypeMap(BookEntity.class, BookDto.class)
                .addMapping(BookEntity::getComment, BookDto::setReview)
                .addMappings(mapper -> mapper.using(isbnRemover).map(BookEntity::getIndex, BookDto::setIndex));
        modelMapper.createTypeMap(AuthorEntity.class, AuthorDto.class);
    }

    public BookDto convertToDto(BookEntity entity) {
        return modelMapper.map(entity, BookDto.class);
    }
}
