package io;

import model.CurrencyCodes;
import model.CurrencyRow;
import model.CurrencyRowElem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WebsiteReader {

    private static final String NBP_WEBSITE = "https://www.nbp.pl/home.aspx?f=/kursy/kursya.html";
    private static String uniqueIdStart = "Tabela nr ";
    private static String uniqueIdEnd = " z dnia ";

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

    public CurrencyRow readAndParseNBPWebsite(CurrencyCodes codes) throws IOException {
        CurrencyRow currencyRow = new CurrencyRow();
        Document document = Jsoup.connect(NBP_WEBSITE).get();

        // ====== SIMPLE CHECK WEBSITE IS GOOD ;) ====== //
        String input = document.select("p").get(3).text();
        if (!(input.contains(uniqueIdStart) && input.contains(uniqueIdEnd))) {
            throw new IllegalArgumentException("Wrong input data (unique table name of currencies rate)");
        }
        else {
            System.out.println("Odczytano tabele o numerze: " + getUniqueTableIdFromString(input));

            // ======  GET DATE OF TABLE ====== //
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(getDateOfTableIdFromString(input), formatter);
            currencyRow.setDate(date);

            Element table = document.select("table").get(4);
            Elements rows = table.select("tr");
            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements cols = row.select("td");
                double exchangeRate = Double.parseDouble((cols.get(2).text()).replace(",","."));
                String support = cols.get(1).text(); //Kod waluty
                if(codes.checkSupport(support)){  //check we have support for this currency :)
                    currencyRow.add(new CurrencyRowElem(codes.getCode(support),exchangeRate));
                }
            }

            System.out.println(currencyRow.toString());

            return currencyRow;
        }
    }




}
