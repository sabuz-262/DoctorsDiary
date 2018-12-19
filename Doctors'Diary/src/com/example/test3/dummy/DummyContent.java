package com.example.test3.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {

    public static class DummyItem {

        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        addItem(new DummyItem("1", "Add Pateint"));
        addItem(new DummyItem("2", "Add Visit"));
        addItem(new DummyItem("3", "Patient List"));
        addItem(new DummyItem("4", "Search Patient"));
        addItem(new DummyItem("5", "Appointment"));
        addItem(new DummyItem("6", "Message"));
        addItem(new DummyItem("7", "Data Sink"));
       
        
      
        
       
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
