import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    String getData(String url) {

        try {
            URI address = URI.create(url);
            var httpClient = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(address).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var body = response.body();
            return body;

        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);

        }
    }
}
