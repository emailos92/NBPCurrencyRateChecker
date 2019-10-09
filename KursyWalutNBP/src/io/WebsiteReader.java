package io;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class WebsiteReader {

    private Connection connect;

    public Document getNewWebsite(String website) throws IOException {
        connect = Jsoup.connect(website);

        return connect.get();
    }


}
