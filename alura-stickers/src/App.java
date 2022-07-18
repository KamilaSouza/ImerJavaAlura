import java.net.URI;
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
        System.out.println(movieList.size());
        System.out.println(movieList.get(0));


        // show and manipulate data

        for (Map<String, String> movie : movieList) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
            System.out.println();
        }

    }

}
