package via.sep4.data.webapi.networking;

import via.sep4.data.webapi.util.Constants;
import via.sep4.data.webapi.util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketClient implements WebSocket.Listener, PropertyChangeSubject {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketClient.class);

    private WebSocket server = null;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void sendDownLink(String data) {
        server.sendText(data, true);
    }

    public WebSocketClient() {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create(Constants.WEB_SOCKET_URL), this);
        server = ws.join();
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        webSocket.request(1);
        logger.info("WebSocket Listener has been opened for requests.");
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        logger.error("A {} exception was thrown.", error.getCause(), error);
        logger.info("Message: {}", error.getLocalizedMessage());
        webSocket.abort();
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        logger.warn("WebSocket closed!");
        logger.debug("Status: {} Reason: {}", statusCode, reason);
        return CompletableFuture.completedFuture("onClose() completed.").thenAccept(System.out::println);
    }

    @Override
    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        logger.info("Ping: Client ---> Server");
        String msg = message.asCharBuffer().toString();
        logger.debug(msg);
        return CompletableFuture.completedFuture("Ping completed.").thenAccept(System.out::println);
    }

    @Override
    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        logger.info("Pong: Client ---> Server");
        String msg = message.asCharBuffer().toString();
        logger.debug(msg);
        return CompletableFuture.completedFuture("Pong completed.").thenAccept(System.out::println);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        String indented = data.toString();
        logger.info(indented);
        support.firePropertyChange("Receive data", null, indented);
        webSocket.request(1);
        return CompletableFuture.completedFuture("Data received successfully.").thenAccept(System.out::println);
    }

    @Override
    public void addPropertyChangeListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }
}
