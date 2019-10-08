package io;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WebsiteReader {

    private static final String website = "https://www.nbp.pl/home.aspx?f=/kursy/kursya.html";

    private Connection connect;
    private Document document;

    private final static String uniqueIdStart = "Tabela nr ";
    private final static String uniqueIdEnd = " z dnia ";

    private String getUniqueTableIdFromString(String input) {
        int indexUniqueIdStart = input.indexOf ( uniqueIdStart );
        int indexUniqueIdEnd = input.indexOf ( uniqueIdEnd );
        return input.substring ( indexUniqueIdStart + uniqueIdStart.length ( ), indexUniqueIdEnd );
    }

    private String getDateOfTableIdFromString(String input) {
        int indexUniqueIdStart = input.indexOf ( uniqueIdEnd );
        int indexUniqueIdEnd = input.length();
        return input.substring ( indexUniqueIdStart + uniqueIdEnd.length ( ), indexUniqueIdEnd );
    }

    public void getNewCurrencyList() throws IOException {
        connect = Jsoup.connect(website);
        document = connect.get();

        Element tableId = document.select("p").get(3);
        String input = tableId.text();

        if (!(input.contains(uniqueIdStart) && input.contains(uniqueIdEnd))) {
            // …handle error…
            System.out.println("ERROR - unexpected input");
            return;
        }

        String uniqueId = getUniqueTableIdFromString(input);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate tabelDate = LocalDate.parse(getDateOfTableIdFromString(input), formatter);

        System.out.println(tabelDate);
        System.out.println(uniqueId);

//        Element table = document.select("table").get(4);
//        Elements rows = table.select("tr");
//        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
//            Element row = rows.get(i);
//            Elements cols = row.select("td");
//            for (int j = 0; j < cols.size(); j++) {
//                System.out.println(cols.get(j).text());
//            }
//        }



//        Elements allH1 = document.select("p");
//        for (Element elem : allH1) {
//            System.out.println(elem.text());
//        }

    }
}
