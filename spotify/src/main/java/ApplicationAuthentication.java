import java.io.IOException;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
public class ApplicationAuthentication {

  public static void main(String[] strings) throws IOException, WebApiException {

    final String clientId = "marco.calabretta86@gmail.com";
    final String clientSecret = "@multicentrum@86";

    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();

    /* Create a request object. */
    final ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();

    /* Use the request object to make the request, either asynchronously (getAsync) or synchronously (get) */
    final ClientCredentials responseFuture = request.get();
    System.out.println(responseFuture.getAccessToken());
    
     
  }
}