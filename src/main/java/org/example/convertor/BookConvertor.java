package org.example.convertor;

import org.example.dto.AudioBookDto;
import org.example.dto.AuthorDto;
import org.example.dto.BookDto;
import org.example.dto.HardCoverBookDto;
import org.example.entity.AudioBookEntity;
import org.example.entity.AuthorEntity;
import org.example.entity.BookEntity;
import org.example.entity.HardCoverBookEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Map;

@Component
public class BookConvertor {
    private final ModelMapper modelMapper;
    private final Map<Type, Type> typeMap = Map.of(
            HardCoverBookEntity.class, HardCoverBookDto.class,
            AudioBookEntity.class, AudioBookDto.class);
    private Converter<String, String> isbnRemover = (src) -> src.getSource().replaceAll("ISBN: ", "");
    private Converter<Integer, String> playTimeConverter = (src) -> src.getSource() + " минут";

    public BookConvertor() {
        this.modelMapper = new ModelMapper();
        TypeMap<BookEntity, BookDto> baseTypeMap = modelMapper.createTypeMap(BookEntity.class, BookDto.class);
        modelMapper.createTypeMap(AuthorEntity.class, AuthorDto.class);
        baseTypeMap
                .addMapping(BookEntity::getComment, BookDto::setReview)
                .addMappings(mapper -> mapper.using(isbnRemover).map(BookEntity::getIndex, BookDto::setIndex));

        baseTypeMap
                .include(AudioBookEntity.class, AudioBookDto.class)
                .include(HardCoverBookEntity.class, HardCoverBookDto.class);
        modelMapper.typeMap(AudioBookEntity.class, AudioBookDto.class)
                .addMappings(mapper -> mapper.using(playTimeConverter).map(AudioBookEntity::getPlayLength, AudioBookDto::setPlayTime))
                .setProvider(provisionRequest -> new AudioBookDto());
        modelMapper.typeMap(HardCoverBookEntity.class, HardCoverBookDto.class)
                .addMapping(HardCoverBookEntity::getPages, HardCoverBookDto::setPages);
    }

    public BookDto convertToDto(BookEntity entity) {
        return modelMapper.map(entity, typeMap.get(entity.getClass()));
    }
}
