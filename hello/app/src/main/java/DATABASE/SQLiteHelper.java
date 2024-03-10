package DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.helloworld.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.GiaoDich;
import model.LoaiGD;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="ChiTieu.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table loaiGD( "+
                "id integer primary key autoincrement,"+
                "src integer, " +
                "name TEXT " +
                ")";
        db.execSQL(sql);
        String insertValues = "INSERT INTO loaiGD (src, name) VALUES " +
                "("+ R.drawable.eating+",'Ăn Uống'), " +
                "("+ R.drawable.ecology+",'Tiền Nước'), " +
                "("+ R.drawable.education+",'Học tập'), " +
                "("+ R.drawable.eletric+",'Tiền điện'), " +
                "("+ R.drawable.giftbox+",'Quà Tặng'), " +
                "("+ R.drawable.gym+",'GYM'), " +
                "("+ R.drawable.heart+",'Sức khỏe'), " +
                "("+ R.drawable.giaitri+",'Giải trí'), " +
                "("+ R.drawable.moving+",'Di chuyển'), " +
                "("+ R.drawable.plus+",'Khác')";
        db.execSQL(insertValues);
        String createTableNew = "CREATE TABLE GiaoDich (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date DATE," +
                "sotien REAL," +
                "loaigd INTEGER," +
                "chitiet TEXT," +
                "inorout INTEGER," +
                "FOREIGN KEY(loaigd) REFERENCES loaiGD(id)" +
                ")";
        db.execSQL(createTableNew);
        String insertValues1 = "INSERT INTO GiaoDich (date, sotien, loaigd, chitiet,inorout) VALUES " +
                "('1111-01-01', 10000, 1, 'ádfasdf',1)," + // Đảm bảo định dạng ngày tháng YYYY-MM-DD
                "('1234-02-02', 11234, 2, 'qwerqwer',2)," +
                "('4124-03-03', 152131, 3, 'xvczxcv',1)," +
                "('2002-12-05', 234562, 5, 'uenciao',2)," +
                "('2024-01-01', 123512, 8, '274icujw3ixj',1)";
        db.execSQL(insertValues1);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public List<LoaiGD> getAllLoaiGD(){
        List<LoaiGD> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "id ASC";
        Cursor rs = sqLiteDatabase.query("loaiGD",null,null,
                                            null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            int src = rs.getInt(1);
            String name = rs.getString(2);
            list.add(new LoaiGD(id,src,name));
        }
        return list;
    }
    public LoaiGD getLoaiGD(String id){
        LoaiGD list = null;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] columns = {"id", "src", "name"};
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor rs = sqLiteDatabase.query("loaiGD", columns, selection,
                selectionArgs, null, null, null);
        while (rs!=null && rs.moveToNext()){
            int src = rs.getInt(1);
            String name = rs.getString(2);
            list = new LoaiGD(Integer.valueOf(id),src,name);
        }
        return list;
    }
    public List<GiaoDich> getAllGD(String order) {
        List<GiaoDich> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String whereClause = "loaigd = ?";
        String[] whereArgs = {order};
        Cursor rs = null;
        if(order.equals("")){
            rs = sqLiteDatabase.query("GiaoDich", null, null,
                    null, null, null, null);
        }else{
            rs = sqLiteDatabase.query("GiaoDich", null, whereClause,
                    whereArgs, null, null, null);
        }
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String date = rs.getString(1);
            String sotien = rs.getString(2);
            String loaigd = rs.getString(3);
            String mota = rs.getString(4);
            int inorout = rs.getInt(5);
            LoaiGD loaiGD1 = getLoaiGD(loaigd);
//            System.out.println(loaiGD1.getID()+" ahshsj "+loaiGD1.getNameIcon());
            list.add(new GiaoDich(id, Date.valueOf(date), Float.parseFloat(sotien), loaiGD1, mota,inorout));
        }
        return list;
    }
    public GiaoDich getGD(String id){
        GiaoDich list = null;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] columns = {"id", "date", "sotien", "loaigd","inorout"};
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor rs = sqLiteDatabase.query("GiaoDich", columns, selection,
                selectionArgs, null, null, null);

        while (rs!=null && rs.moveToNext()){
            String date = rs.getString(1);
            String sotien = rs.getString(2);
            String loaigd = rs.getString(3);
            String mota = rs.getString(4);
            int inorout = rs.getInt(5);
            LoaiGD loaiGD1 = getLoaiGD(loaigd);
            list = new GiaoDich(Integer.valueOf(id), Date.valueOf(date),Float.parseFloat(sotien),
                    loaiGD1,mota,inorout);
        }
        return list;
    }
    public long insertGiaoDich(GiaoDich giaoDich) {
        SQLiteDatabase db = getReadableDatabase();
//        System.out.println(giaoDich.getID()+" "+giaoDich.getSotien()+" "+giaoDich.getDate().toString()
//                +" "+giaoDich.getLoaiGD().getID()+" "+
//                giaoDich.getMota()+" "+giaoDich.getKieugd());
        ContentValues values = new ContentValues();
        values.put("date", giaoDich.getDate().toString());
        values.put("sotien", giaoDich.getSotien());
        values.put("loaigd", giaoDich.getLoaiGD().getID());
        values.put("chitiet", giaoDich.getMota());
        values.put("inorout",giaoDich.getKieugd());
        long id = db.insert("GiaoDich", null, values);
        db.close();
        return id;
    }
}
