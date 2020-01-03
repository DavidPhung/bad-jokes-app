import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.HttpUrlConnectorProvider.ConnectionFactory;

public class RESTHelper {
	
	public enum MediaType {TEXT_PLAIN, JSON}

	public static String get(String uri, String proxyUrl, int proxyPort, MediaType mediaType) {
		HttpUrlConnectorProvider connectorProvider = new HttpUrlConnectorProvider();
		if (proxyUrl != null) {
			final Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyUrl, proxyPort));
			connectorProvider.connectionFactory(new ConnectionFactory() {

				@Override
				public HttpURLConnection getConnection(URL arg0) throws IOException {
					return (HttpURLConnection) arg0.openConnection(proxy);
				}
			});
		}
		ClientConfig config = new ClientConfig();
		config.connectorProvider(connectorProvider);
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(uri);

		switch (mediaType) {
		case TEXT_PLAIN:
			return target.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
		case JSON:
			return target.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
		default:
			throw new RuntimeException("Unexpected media type");
		}
	}
	
	public static String get(String uri, MediaType mediaType) {
		return get(uri, null, 0, mediaType);
	}

}
