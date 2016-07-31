package me.roneythomas.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import me.roneythomas.inventoryapp.database.InventoryCursorWrapper;
import me.roneythomas.inventoryapp.database.InventoryDbHelper;
import me.roneythomas.inventoryapp.database.InventorySchema.InventoryTable;

/**
 * Created by roneythomas on 2016-07-23.
 */
public class InventoryLab {

    private static InventoryLab mInventoryLab;
    private SQLiteDatabase mDatabase;

    private InventoryLab(Context context) {
        mDatabase = new InventoryDbHelper(context).getWritableDatabase();
    }

    public static InventoryLab get(Context context) {
        if (mInventoryLab == null) {
            mInventoryLab = new InventoryLab(context.getApplicationContext());
        }
        return mInventoryLab;
    }

    public void addInventory(Inventory inventory) {
        ContentValues values = getContentValues(inventory);
        mDatabase.insert(InventoryTable.TABLE_NAME, null, values);
    }

    public Inventory getInventory(String name) {
        InventoryCursorWrapper cursor = query(InventoryTable.Cols.NAME + " = ?", new String[]{name});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getInventory();
        } finally {
            cursor.close();
        }
    }

    public void updateInventory(Inventory inventory) {
        ContentValues values = getContentValues(inventory);
        mDatabase.update(InventoryTable.TABLE_NAME, values, InventoryTable.Cols.NAME + " = ?", new String[]{inventory.getName()});
    }

    public void deleteInventory(Inventory inventory) {
        mDatabase.delete(InventoryTable.TABLE_NAME, InventoryTable.Cols.NAME + " = ?", new String[]{inventory.getName()});
    }

    public ArrayList<Inventory> getInventorys() {
        InventoryCursorWrapper cursor = query(null, null);
        ArrayList<Inventory> inventories = new ArrayList<>();
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                inventories.add(cursor.getInventory());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return inventories;
    }

    private ContentValues getContentValues(Inventory inventory) {
        ContentValues values = new ContentValues();
        values.put(InventoryTable.Cols.NAME, inventory.getName());
        values.put(InventoryTable.Cols.QUANTITY, inventory.getQuantity());
        values.put(InventoryTable.Cols.PRICE, inventory.getPrice());
        values.put(InventoryTable.Cols.URI, inventory.getUri());
        values.put(InventoryTable.Cols.PHONE, inventory.getPhone());
        return values;
    }

    private InventoryCursorWrapper query(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                InventoryTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new InventoryCursorWrapper(cursor);
    }
}
