package privatBank;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import privatBank.dto.CurrencyItem;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class CurrencyParser {
    public static void main(String[] args) throws IOException {
        String url = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter currency (USD, EUR) or exit:");
            String input = scanner.nextLine();

            if (input.equals("exit")){
                System.out.println("Bye!");
                System.exit(0);
            }

            // Get JSON
            String json = Jsoup
                    .connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();

            //Convert json => Java Object
            Type typeToken = TypeToken
                    .getParameterized(List.class, CurrencyItem.class)
                    .getType();

            Gson gson = new Gson();
            List<CurrencyItem> currencyItems = gson.fromJson(json, typeToken);

            //Find UAH/USD
            Float uahUsd = currencyItems.stream()
                    .filter(it -> it.getCcy() == CurrencyItem.CCY.valueOf(input))
                    .filter(it -> it.getBase_ccy() == CurrencyItem.CCY.UAH)
                    .map(it -> it.getBuy())
                    .findFirst()
                    .orElseThrow();

            System.out.println("UAH/"+input+" buy: "+uahUsd);
        }

//        for (CurrencyItem currencyItem: currencyItems){
//            System.out.println(currencyItem);
//        }
//
//        System.out.println("UAH/USD buy: "+uahUsd);
    }
}
