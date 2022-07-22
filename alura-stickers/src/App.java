import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // connection link for api
        // String url = "https://api.mocki.io/v2/549a5d8b";
        //  ContentExtractor extractor = new ContentExtractorIMDB();

        String url = "https://api.nasa.gov/planetary/apod?api_key=PILs0dWbupukdWJ7YrMQ0CMHfWxqVbw88kQQuP12&start_date=2022-06-12&end_date=2022-06-14";
        ContentExtractor extractor = new ContentExtractorNasa();

        var http = new ClientHttp();
        String json = http.getData(url);

        // extract data (title, image, imDbRating)
        var parser = new JsonParser();   // extract
        List<Map<String, String>> contentList = parser.parse(json);


        // show and manipulate data
        List<Content> contents = extractor.extractContent(json);

        var generator = new StickerFactory();

        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String fileName = content.getTitle().replaceAll(":", " -") + ".png";

            generator.create(inputStream, fileName);

            System.out.println(content.getTitle());
            System.out.println();
        }

    }

}
