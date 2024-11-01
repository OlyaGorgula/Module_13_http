package ua.goit.tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;

public class Task2 {
    private  static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static void main(String[] args) throws IOException, InterruptedException {
        commentsByUserAndPost();
    }

    private static void commentsByUserAndPost() throws IOException, InterruptedException {
        /*
        Доповніть програму методом, що буде виводити всі коментарі до останнього поста певного користувача і записувати їх у файл.
        https://jsonplaceholder.typicode.com/users/1/posts Останнім вважаємо пост з найбільшим id.
        https://jsonplaceholder.typicode.com/posts/10/comments
        Файл повинен називатись user-X-post-Y-comments.json, де Х - id користувача, Y - номер посту.
         */

        saveComments(getMaxPostById(2));

    }

    private static Post getMaxPostById(int idUser) throws IOException, InterruptedException {
        //https://jsonplaceholder.typicode.com/users/1/posts

        URI uri = URI.create("https://jsonplaceholder.typicode.com/users/"+idUser+"/posts");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        List<Post> listPosts = GSON.fromJson(response.body(), new TypeToken<List<Post>>(){}.getType());

        Post maxPostById = listPosts
                .stream()
                .max(Comparator.comparing(Post::getId))
                .get();

        maxPostById.setUserId(idUser);

        System.out.println("result posts = "+response);
        System.out.println("get max Post by id = "+ maxPostById);

        return maxPostById;
    }

    private static void saveComments(Post post) throws IOException, InterruptedException {
        //https://jsonplaceholder.typicode.com/posts/10/comments
        //Файл повинен називатись user-X-post-Y-comments.json, де Х - id користувача, Y - номер посту.

        URI uri = URI.create("https://jsonplaceholder.typicode.com/posts/"+post.getId()+"/comments");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("result Comments = "+response);

        FileWriter fileWriter = new FileWriter("user-"+post.getUserId()+"-post-"+post.getId()+"-comments.json");
        fileWriter.write(response.body());
        fileWriter.close();

    }


}
