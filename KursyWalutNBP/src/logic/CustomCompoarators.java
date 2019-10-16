package logic;

import model.CurrencyCol;
import model.CurrencyColElem;
import model.CurrencyRow;
import model.CurrencyRowElem;

import java.util.Comparator;

public class CustomCompoarators {

    public static class CurrencyRowComparator implements Comparator<CurrencyRow> {
        @Override
        public int compare(CurrencyRow o1, CurrencyRow o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }

    public static class CurrencyRowElemComparator implements Comparator<CurrencyRowElem> {
        @Override
        public int compare(CurrencyRowElem o1, CurrencyRowElem o2) {
            return o1.getCode().compareTo(o2.getCode());
        }
    }

    public static class CurrencyColComparator implements Comparator<CurrencyCol> {
        @Override
        public int compare(CurrencyCol o1, CurrencyCol o2) {
            return o1.getCode().compareTo(o2.getCode());
        }
    }

    public static class CurrencyColElemComparator implements Comparator<CurrencyColElem> {
        @Override
        public int compare(CurrencyColElem o1, CurrencyColElem o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }
}
