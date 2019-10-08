package logic;

import model.Currency;
import model.CurrencyTable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WebsiteParser {

    private final static String uniqueIdStart = "Tabela nr ";
    private final static String uniqueIdEnd = " z dnia ";

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

    public CurrencyTable parseWebsite(Document document) throws RuntimeException {
        CurrencyTable currenciesTable = new CurrencyTable();
        Currency currency = new Currency();
        String input = document.select("p").get(3).text();

        if (!(input.contains(uniqueIdStart) && input.contains(uniqueIdEnd))) {
            throw new IllegalArgumentException("Wrong input data (unique table name of currencies rate)");
        }

        String uniqueTableName = getUniqueTableIdFromString(input);
        currenciesTable.setUniqueTableName(uniqueTableName);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate tabelOfDate = LocalDate.parse(getDateOfTableIdFromString(input), formatter);
        currenciesTable.setDateOfTable(tabelOfDate);

//        System.out.println(uniqueTableName + " : " + tabelOfDate);
        Element table = document.select("table").get(4);
        Elements rows = table.select("tr");
        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
            Element row = rows.get(i);
            Elements cols = row.select("td");

            double exchangeRate = Double.parseDouble((cols.get(2).text()).replace(",","."));
            String code = cols.get(1).text();
            currenciesTable.addCurrency(new Currency(code,exchangeRate));
//            for (int j = 0; j < cols.size(); j++) {
//                System.out.println(cols.get(j).text());
//            }
        }

//        currenciesTable.printCurrencies();
//        Elements allH1 = document.select("p");
//        for (Element elem : allH1) {
//            System.out.println(elem.text());
//        }

        return currenciesTable;
    }

}
