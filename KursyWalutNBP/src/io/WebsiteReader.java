package io;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class WebsiteReader {

    private static final String website = "https://www.nbp.pl/home.aspx?f=/kursy/kursya.html";

    private Connection connect;

    public Document getNewCurrencyList() throws IOException {
        connect = Jsoup.connect(website);

        return connect.get();
    }


}
