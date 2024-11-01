package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HabrJsoupTest {
    public static void main(String[] args) throws IOException {
        String url = "https://habr.com/ru/companies/agima/articles/854948/";

        Document document = Jsoup.connect(url).get();

//        Element head = document.getElementsByTag("head").first();
//        Element title = head.getElementsByTag("title").first();
//        System.out.println("title = " + title.text());

        // Парсінг // HTML - doc
        Elements articles = document.selectFirst("div.tm-articles-list")
                .select("article");
        for (Element article : articles) {
            Element rating = article
                    .selectFirst("div.tm-votes-meter")
                    .selectFirst("span.tm-votes_value");
            String textRating = rating.text().replace("+", "");
            int intRating = Integer.parseInt(textRating);
        }



//        Element body = document.body();
//        System.out.println("body = " + body.html());
    }
}
