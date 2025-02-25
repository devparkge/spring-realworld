package github.devparkge.realworld.domain.article.model;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public record Slug(
        String value
) {
    private static final Pattern NON_ALPHANUMERIC = Pattern.compile("[^a-z0-9\\-]");
    private static final Pattern MULTIPLE_DASHES = Pattern.compile("-+");

    public Slug {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Slug cannot be null or empty.");
        }
    }

    private static String generateSlug(String input) {
        String slug = input.toLowerCase(Locale.ENGLISH);

        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = NON_ALPHANUMERIC.matcher(slug).replaceAll("-");

        slug = MULTIPLE_DASHES.matcher(slug).replaceAll("-");

        slug = slug.replaceAll("^-|-$", "");

        return slug;
    }

    public static Slug from(String input) {
        return new Slug(generateSlug(input));
    }
}
