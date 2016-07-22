package me.roneythomas.inventoryapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.roneythomas.inventoryapp.database.InventorySchema.InventoryTable;

/**
 * Created by roneythomas on 2016-07-22.
 */
public class InventoryDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory.db";
    private static final int VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + InventoryTable.TABLE_NAME + "(" +
                "id integer primary key autoincrement, " +
                InventoryTable.Cols.NAME + ", " +
                InventoryTable.Cols.QUANTITY + ", " +
                InventoryTable.Cols.PRICE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + InventoryTable.TABLE_NAME);
        onCreate(db);
    }
}
