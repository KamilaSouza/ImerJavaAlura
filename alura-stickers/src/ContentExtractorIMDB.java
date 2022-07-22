import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorIMDB implements ContentExtractor {

    public List<Content> extractContent(String json) {

        // extract data (title, image, imDbRating)
        var parser = new JsonParser();   // extract
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        // fill the list
        for (Map<String, String> attributes : attributesList) {
            String title = attributes.get("title");
            String urlImage = attributes.get("image");
            var content = new Content(title, urlImage);

            contents.add(content);
        }

        return contents;

    }
}
