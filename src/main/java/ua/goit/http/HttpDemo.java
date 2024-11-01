package ua.goit.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HttpDemo {
    private static final String CREATE_USER_URL = "https://pingponggoit.herokuapp.com/createUser";
    private static final String GET_USER_BY_ID_URL = "https://pingponggoit.herokuapp.com/getUserById";
    private static final String GET_USERS_URL = "https://pingponggoit.herokuapp.com/getUsers";
    private static final String DELETE_USERS_URL = "https://pingponggoit.herokuapp.com/removeUser";

    public static void main(String[] args) throws IOException, InterruptedException {
//        HttpRequest;
//        HttpClient;
//        HttpResponse;

        //HttpClient client = HttpClient.newBuilder()
                //.authenticator(); // аутифікація користувача
                //.connectTimeout() // очікування відпоаіді від сервера

//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://pingponggoit.herokuapp.com/getDefaultUser"))
//                .GET()
//                .timeout(Duration.of(10, ChronoUnit.SECONDS))
//                .build();
//
//        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println("Header "+response.headers());
//        System.out.println("Body "+response.body());
//        System.out.println("Status "+response.statusCode());

        //TASK #1
        User user = createDefaultUser();
        final User createdUser = HttpUtil.sendPost(URI.create(CREATE_USER_URL),user);
        System.out.println("Task 1 = "+createdUser);

        //TASK #2
        final User task2 = HttpUtil.sendGet(URI.create(String.format("%s?id=%d", GET_USER_BY_ID_URL,createdUser.getId())));
        System.out.println("Task 2 = "+task2);

        //TASK #3
        //1. Створюємо користувача 2. отримуємо список користувачів
        // 3. беремо любого користувача зі списку і видаляємо.
        // 4. отримуємо список заново і отримуємо, що користувач видалений.
        User task3User = createDefaultUser();
        final User task3CreateUser = HttpUtil.sendPost(URI.create(CREATE_USER_URL),task3User);
        System.out.println("Created User "+task3CreateUser);
        final List<User> users = HttpUtil.sendGetWithListOfResults(URI.create(GET_USERS_URL));
        System.out.println("Users list before delete "+users);
        HttpUtil.sendDelete(URI.create(DELETE_USERS_URL),task3CreateUser);
        final List<User> usersAfterDelete = HttpUtil.sendGetWithListOfResults(URI.create(GET_USERS_URL));
        System.out.println("Users list after delete "+usersAfterDelete);
    }

    private static User createDefaultUser(){
        User user = new User();
        user.setId(1);
        user.setName("Olya");
        user.setSurname("Gorgula");
        user.setGender("mail");
        user.setSalary(10500);
        return user;
    }
}
