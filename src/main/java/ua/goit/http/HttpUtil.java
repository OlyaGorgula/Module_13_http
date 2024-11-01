package ua.goit.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;


public class HttpUtil {

    private  static final HttpClient CLIENT = HttpClient.newHttpClient();//HttpClient.newBuilder()
    private static final Gson GSON = new Gson();

    public static User sendGet(URI uri) throws IOException, InterruptedException {
        // Отримати об'єкт
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                //.version( HttpClient.Version.HTTP_1_1 )
                //.version( HttpClient.Version.HTTP_2 )
                //.timeout( Duration.of(5, SECONDS) )  // - задать время выполнения запроса.
                                                // Если оно пройдет, а запрос так и не будет выполнен, то выкинется исключение HttpTimeoutException.
                .header("Content-type", "application/json") // до будь-якого запиту можна додати скільки завгодно заголовків.
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(response.body(), User.class);
    }

    public static User sendPost(URI uri, User user) throws IOException, InterruptedException {
//        byte[] data = "Мое секретное сообщение".getBytes();
//        byte[] dataEncripted = SuperEncriptor.encript(data);

        // Створити новий об'єкт
        final String requestBody = GSON.toJson(user);
        //http-запит
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody)) // POST(HttpRequest.BodyPublishers.noBody())
                //.POST(HttpRequest.BodyPublishers.ofString("Привет", Charset. US-ASCII)))
                //.POST(HttpRequest.BodyPublishers.ofFile(pathAvatar)) //файл. Саме таким чином, зазвичай, твої аватарки і заливаються на сервер
                //.POST(HttpRequest.BodyPublishers.ofByteArray(dataEncripted)) // - відправити на сервер набір байт. Наприклад, ти серіалізував якийсь об'єкт
                //.POST(HttpRequest.BodyPublishers.ofInputStream (() -> is;)) // - потрібно передати функцію, яка як результат поверне потік InputStream
                .header("Content-type", "application/json")
                .build();

        //відправлення цього запиту
        //відповідь на запит response body: рядок, файл, масив байт, InputStream.
        //response body відповіді 8:
//        BodyHandlers.ofByteArray
//        BodyHandlers.ofString  // body это строка
//        BodyHandlers.ofFile    //body это файл HttpResponse<Path> response = client.send(request, BodyHandlers.ofFile(Paths.get("readme.txt")));
//        BodyHandlers.discarding // body игнорируется
//        BodyHandlers.replacing
//        BodyHandlers.ofLines
//        BodyHandlers.fromLineSubscriber
//        BodyHandlers.ofInputStream //body это InputStream // HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        //Залежно від цього, який тип BodyHandlersти передав у метод send()

        //CookieHandler handler = CLIENT.cookieHandler();
        //CookieManager manager = (CookieManager)  handler;
        //CookieStore store = manager.getCookieStore();
        /*
        CookieStore – це інтерфейс, який має такі методи:
            add()
            get()
            getCookies()
            remove()
            removeAll()
         */

        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        /*
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                    "username",
                    "password".toCharArray());
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        HttpResponse response = HttpClient.newBuilder() // - це CLIENT
                .followRedirects( HttpClient.Redirect.ALWAYS ) // вказати, що робити HttpClient'у,
                                                    якщо сервер у відповідь надішле 301 або 302
                                                    (тимчасовий або постійний редирект) пересилає на інший сайт
                                                    Є лише 3 варіанти для редиректу:
                                                        ALWAYS – завжди;
                                                        NEVER – ніколи;
                                                        NORMAL – завжди, окрім HTTPS -> HTTP.
                .proxy( ProxySelector.getDefault()) // .proxy(ProxySelector.of(new InetSocketAddress("proxy.microsoft.com", 80)))
                .authenticator(auth).build() //підтримує автентифікацію
                .executor(executorService) //для sendAsync// executorService = 2 пула
                                          // Якщо пул потоків не заданий, то використовується .java.util.concurrent.Executors.newCachedThreadPool()
                .build()
                .send(request, BodyHandlers.ofString());
                .sendAsync(request, HttpResponse.BodyHandlers.ofString()); // повертає CompletableFuture<HttpResponse<String>> - який містить HttpResponse
                                                                            // асинхронний запит: запит буде виконуватися дуже довго;
                                                                            //потрібно надсилати запити дуже часто;
                                                                            // не важливий результат твого запиту
         */

        return GSON.fromJson(response.body(), User.class);
    }

    public static List<User> sendGetWithListOfResults(URI uri) throws IOException, InterruptedException {
        // Отримати список об'єктів
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>(){}.getType());
        return users;
    }

    public static void sendDelete(URI uri, User user) throws IOException,InterruptedException{
        // Видалити об'єкт
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                //.version( HttpClient.Version.HTTP_2 )
                .header("Content-type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
