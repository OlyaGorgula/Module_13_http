package ua.goit.tasks;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Task1 {
    private  static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static final String BASE_HTTP_USER = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws IOException, InterruptedException {

        createUser();

        List<User> listUser = getListUser();

        if (listUser.size()>=4){
            updateUser(listUser.get(0));
            getUserById(listUser.get(1).getId());
            getUserByName(listUser.get(2).getUserName());
            deleteUser(listUser.get(3));
        }

    }

    private static void createUser() throws IOException, InterruptedException {
        /* #1
        створення нового об'єкта в https://jsonplaceholder.typicode.com/users.
        Можливо, ви не побачите одразу змін на сайті. Метод працює правильно,
        якщо у відповідь на JSON з об'єктом повернувся такий самий JSON, але зі значенням id більшим на 1,
        ніж найбільший id на сайті.
         */
        User user = new User();
        user.setDefaultUser();

        URI uri = URI.create(BASE_HTTP_USER);
        final String requestBody = GSON.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("resul create: "+response);
        System.out.println("resul new User = "+response.body());
    }

    private static void updateUser(User user) throws IOException, InterruptedException {
        /* #2
            оновлення об'єкту в https://jsonplaceholder.typicode.com/users.
            Можливо, ви не побачите одразу змін на сайті.
            Вважаємо, що метод працює правильно, якщо у відповідь ви отримаєте оновлений
            JSON (він повинен бути таким самим, що ви відправили).
         */

        user.setPhone("1111111111111");
        URI uri = URI.create(BASE_HTTP_USER+"/"+user.getId());
        final String requestBody = GSON.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("resul update = "+response);
        System.out.println("resul update User = "+response.body());
    }

    private static void deleteUser(User user) throws IOException, InterruptedException {
        /* #3
        видалення об'єкта з https://jsonplaceholder.typicode.com/users.
        Тут будемо вважати коректним результат - статус відповіді з групи 2xx (наприклад, 200).
         */
        URI uri = URI.create(BASE_HTTP_USER+"/"+user.getId());
        final String requestBody = GSON.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        final HttpResponse<String> response =  CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("resul delete = "+response);
    }

    private static List<User> getListUser() throws IOException, InterruptedException {
        // #4 отримання інформації про всіх користувачів https://jsonplaceholder.typicode.com/users

        URI uri = URI.create(BASE_HTTP_USER);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        List<User> listUser = GSON.fromJson(response.body(), new TypeToken<List<ua.goit.tasks.User>>(){}.getType());

        System.out.println("resul list user = "+response);
        System.out.println("list users:");
        for (User user : listUser) {
            System.out.println("id = "+user.getId()+" userName = "+user.getUserName());
        }

        return listUser;
    }

    private static User getUserById(int id) throws IOException, InterruptedException {
        // #5 отримання інформації про користувача за id https://jsonplaceholder.typicode.com/users/{id}

        URI uri = URI.create(BASE_HTTP_USER+"/"+id);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        User user = GSON.fromJson(response.body(), ua.goit.tasks.User.class);
        System.out.println("resul by id = "+response);
        System.out.println("get user by id = "+user.getId()+" userName = "+user.getUserName());
        return user;
    }

    private static List<User> getUserByName(String username) throws IOException, InterruptedException {
        // #6 отримання інформації про користувача за username - https://jsonplaceholder.typicode.com/users?username={username}

        URI uri = URI.create(BASE_HTTP_USER+"?username="+username);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<User> listUser = GSON.fromJson(response.body(), new TypeToken<List<ua.goit.tasks.User>>(){}.getType());

        System.out.println("resul by username = "+response);
        for (User user : listUser) {
            System.out.println("id = "+user.getId()+" userName = "+user.getUserName());
        }
        return listUser;
    }

}
