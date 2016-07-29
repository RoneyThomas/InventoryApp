package me.roneythomas.inventoryapp.database;

/**
 * Created by roneythomas on 2016-07-22.
 */
public class InventorySchema {

    public static final class InventoryTable {
        public static final String TABLE_NAME = "inventory";

        public static final class Cols {
            public static final String NAME = "name";
            public static final String QUANTITY = "quantity";
            public static final String PRICE = "price";
            public static final String URI = "uri";
        }
    }
}
