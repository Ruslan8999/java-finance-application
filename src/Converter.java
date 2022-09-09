import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Converter {
    private final HttpClient client;

    public Converter() {
        this.client = HttpClient.newHttpClient();
    }

    public void convert(double rubles, String currency) {
        double kurs = getRate(currency);
        if (kurs != 0){
            System.out.println("Ваши сбережения: " + rubles * kurs + " " + currency);
        }
    }

    private double getRate(String symbols){
        double rate = 0;
        URI url = URI.create("https://api.exchangerate.host/latest?base=RUB&symbols="+symbols);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonElement jsonElement = JsonParser.parseString(response.body());
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonObject rates = jsonObject.get("rates").getAsJsonObject();
                rate = rates.get(symbols).getAsDouble();
            } else {
                System.out.println("Что-то пошло не так. Сервер вернул код состояния: " + response.statusCode());
            }
        } catch (IOException | InterruptedException | NullPointerException e) { // обрабатываем ошибки отправки запроса
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
        return rate;
    }
}
