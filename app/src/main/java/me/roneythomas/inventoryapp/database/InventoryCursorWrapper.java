package me.roneythomas.inventoryapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import me.roneythomas.inventoryapp.Inventory;
import me.roneythomas.inventoryapp.database.InventorySchema.InventoryTable;

/**
 * Created by roneythomas on 2016-07-22.
 */
public class InventoryCursorWrapper extends CursorWrapper {

    public InventoryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Inventory getInventory() {
        String name = getString(getColumnIndex(InventoryTable.Cols.NAME));
        String quantity = getString(getColumnIndex(InventoryTable.Cols.QUANTITY));
        String price = getString(getColumnIndex(InventoryTable.Cols.PRICE));
        String uri = getString(getColumnIndex(InventoryTable.Cols.URI));

        return new Inventory(name, quantity, price, uri);
    }
}
