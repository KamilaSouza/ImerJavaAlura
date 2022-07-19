import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // connection link for api
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI address = URI.create(url);

        //  HttpClient httpClient = HttpClient.newHttpClient();
        var httpClient = HttpClient.newHttpClient();
        //  HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        var body = response.body();


        // extract data (title, image, imDbRating)
        var parser = new JsonParser();   // extract
        List<Map<String, String>> movieList = parser.parse(body);


        // show and manipulate data
        var generator = new StickerFactory();
        for (Map<String, String> movie : movieList) {

            String urlImage = movie.get("image");
            String title = movie.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = title.replaceAll(":", " -") + ".png";

            generator.create(inputStream, fileName);

            System.out.println(title);
            System.out.println();
        }

    }

}
