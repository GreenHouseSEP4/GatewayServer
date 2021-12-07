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

public class WebSocketClient implements WebSocket.Listener, PropertyChangeSubject {

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

    public void onOpen(WebSocket webSocket) {
        webSocket.request(1);
        System.out.println("WebSocket Listener has been opened for requests.");
    }

    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println("A " + error.getCause() + " exception was thrown.");
        System.out.println("Message: " + error.getLocalizedMessage());
        webSocket.abort();
    }

    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.out.println("WebSocket closed!");
        System.out.println("Status:" + statusCode + " Reason: " + reason);
        return new CompletableFuture().completedFuture("onClose() completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Ping: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Ping completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Pong: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Pong completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        String indented = data.toString();
        System.out.println(indented);
        support.firePropertyChange("Receive data", null, indented);
        webSocket.request(1);
        return new CompletableFuture().completedFuture("onText() completed.").thenAccept(System.out::println);
    }

    @Override
    public void addPropertyChangeListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }
}
