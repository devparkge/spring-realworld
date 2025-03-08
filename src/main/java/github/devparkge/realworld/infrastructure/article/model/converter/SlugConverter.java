package github.devparkge.realworld.infrastructure.article.model.converter;

import github.devparkge.realworld.domain.article.model.Slug;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SlugConverter implements AttributeConverter<Slug, String> {

    @Override
    public String convertToDatabaseColumn(Slug slug) {
        return slug.value();
    }

    @Override
    public Slug convertToEntityAttribute(String dbData) {
        return Slug.from(dbData);
    }
}
