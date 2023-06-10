package com.ybllcodes.chapplication.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class ProductDBHelper(var context:Context,var DB_NAME: String = "product.db",val DB_VERSION:Int = 1) : SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION){
    val TABLE_PRODUCT = "product_info"
    val TABLE_SALE = "sale_info"
    lateinit var mRDB:SQLiteDatabase
    lateinit var mWDB:SQLiteDatabase
    companion object{
        private lateinit var mHelper:ProductDBHelper
        fun getInstance(context:Context):ProductDBHelper{
            if(mHelper == null){
                mHelper = ProductDBHelper(context)
            }
            return mHelper
        }
    }

    fun openReadLink():SQLiteDatabase{
        if(!mRDB.isOpen){
            mRDB = mHelper.readableDatabase
        }
        return mRDB
    }
    fun openWriteLink():SQLiteDatabase{
        if(!mWDB.isOpen){
            mWDB = mHelper.writableDatabase
        }
        return mWDB
    }
    fun closeLink(){
        if(mRDB.isOpen){
            mRDB.close()
        }
        if(mWDB.isOpen){
            mWDB.close()
        }
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val sqlProduct:String = """
            create table if not exists ${TABLE_PRODUCT} (
                id integer primary key autoincrement not null,
                name text not null,
                image text not null,
                stock text not null,
                price real not null,
                alls text not null,
                sold integer not null,
                create_time text not null
            );
        """.trimIndent()
        db?.execSQL(sqlProduct)
        Toast.makeText(context,"Create ${TABLE_PRODUCT} Success!",Toast.LENGTH_SHORT).show()

        val sqlSale:String = """
            create table if not exists ${TABLE_SALE} (
                id integer primary key autoincrement not null,
                p_id integer not null,
                type integer not null,
                time text not null
            );
        """.trimIndent()
        db?.execSQL(sqlSale)
        Toast.makeText(context,"Create ${TABLE_SALE} Success!",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}