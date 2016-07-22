package me.roneythomas.inventoryapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import me.roneythomas.inventoryapp.Inventory;
import me.roneythomas.inventoryapp.database.InventorySchema.InventoryTable;

/**
 * Created by roneythomas on 2016-07-22.
 */
public class InventoryCursorHelper extends CursorWrapper {

    public InventoryCursorHelper(Cursor cursor) {
        super(cursor);
    }

    public Inventory getInventory() {
        String name = getString(getColumnIndex(InventoryTable.Cols.NAME));
        int quantity = getInt(getColumnIndex(InventoryTable.Cols.QUANTITY));
        double price = getDouble(getColumnIndex(InventoryTable.Cols.PRICE));

        return new Inventory(name, quantity, price);
    }
}
