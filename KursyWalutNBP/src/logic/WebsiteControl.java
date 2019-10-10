package logic;

import io.WebsiteReader;
import model.CurrencyRow;
import model.CurrencyRowElem;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WebsiteControl {

    private static final String website = "https://www.nbp.pl/home.aspx?f=/kursy/kursya.html";
    private final static String uniqueIdStart = "Tabela nr ";
    private final static String uniqueIdEnd = " z dnia ";

    WebsiteReader websiteReader = new WebsiteReader();

    private String getUniqueTableIdFromString(String input) {
        int indexUniqueIdStart = input.indexOf(uniqueIdStart);
        int indexUniqueIdEnd = input.indexOf(uniqueIdEnd);
        return input.substring(indexUniqueIdStart + uniqueIdStart.length(), indexUniqueIdEnd);
    }

    private String getDateOfTableIdFromString(String input) {
        int indexUniqueIdStart = input.indexOf(uniqueIdEnd);
        int indexUniqueIdEnd = input.length();
        return input.substring(indexUniqueIdStart + uniqueIdEnd.length(), indexUniqueIdEnd);
    }

    public CurrencyRow parseWebsite() throws RuntimeException, IOException {

        Document document = websiteReader.getNewWebsite(website);

        CurrencyRow currencyRow = new CurrencyRow();
        String input = document.select("p").get(3).text();

        if (!(input.contains(uniqueIdStart) && input.contains(uniqueIdEnd))) {
            throw new IllegalArgumentException("Wrong input data (unique table name of currencies rate)");
        }

        //set name
        String name = getUniqueTableIdFromString(input);
        currencyRow.setName(name);

        //set date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(getDateOfTableIdFromString(input), formatter);
        currencyRow.setDate(date);

//      System.out.println(uniqueTableName + " : " + tabelOfDate);
        Element table = document.select("table").get(4);
        Elements rows = table.select("tr");
        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
            Element row = rows.get(i);
            Elements cols = row.select("td");

            double exchangeRate = Double.parseDouble((cols.get(2).text()).replace(",","."));
            String code = cols.get(1).text();
            currencyRow.add(new CurrencyRowElem(code,exchangeRate));
        }

        //System.out.println("Pobrano tabele " + name + " + z dnia " + date);

        System.out.println(currencyRow.getInfo());

//        Elements allH1 = document.select("p");
//        for (Element elem : allH1) {
//            System.out.println(elem.text());
//        }

        return currencyRow;
    }

    public void close(){

    }

}
