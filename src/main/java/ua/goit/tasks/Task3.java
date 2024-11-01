package ua.goit.tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task3 {
    private  static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static void main(String[] args) throws IOException, InterruptedException {
        openTasksByUser(1);
    }

    private static void openTasksByUser(int idUser) throws IOException, InterruptedException {
        /*
        Доповніть програму методом, що буде виводити всі відкриті задачі для користувача з ідентифікатором X.
        https://jsonplaceholder.typicode.com/users/1/todos.
        Відкритими вважаються всі задачі, у яких completed = false.
         */

        URI uri = URI.create("https://jsonplaceholder.typicode.com/users/"+idUser+"/todos");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        List<TODO> listTODO = GSON.fromJson(response.body(), new TypeToken<List<TODO>>(){}.getType());

        List<TODO> listOpenTODO = listTODO
                .stream()
                .filter(it -> it.isCompleted())
                .collect(Collectors.toList());

        System.out.println("result TODO = "+response);
        for (TODO todo : listOpenTODO) {
            System.out.println("get open todo = "+ todo);
        }

    }
}
