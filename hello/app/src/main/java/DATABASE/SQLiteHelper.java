package DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.helloworld.R;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import model.GiaoDich;
import model.LoaiGD;
import model.NganSach;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="ChiTieu.db";
    private static int DATABASE_VERSION = 1;
    private final Context mContext;
    public SQLiteHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        mContext = context;
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
                "("+ R.drawable.all+",'Tất cả'), " +
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
                // Đảm bảo định dạng ngày tháng YYYY-MM-DD
                "('2024-01-01', 73000, 6, '', 2)," +
                "('2024-01-02', 80000, 10, '', 1)," +
                "('2024-01-03', 94000, 6, '', 1)," +
                "('2024-01-04', 85000, 8, '', 2)," +
                "('2024-01-05', 46000, 5, '', 2)," +
                "('2024-01-06', 79000, 5, '', 1)," +
                "('2024-01-07', 90000, 8, '', 2)," +
                "('2024-01-08', 68000, 5, '', 2)," +
                "('2024-01-09', 30000, 6, '', 2)," +
                "('2024-01-10', 17000, 3, '', 1)," +
                "('2024-01-11', 23000, 7, '', 2)," +
                "('2024-01-12', 36000, 2, '', 1)," +
                "('2024-01-13', 100000, 4, '', 1)," +
                "('2024-01-14', 37000, 3, '', 2)," +
                "('2024-01-15', 19000, 4, '', 2)," +
                "('2024-01-16', 70000, 8, '', 2)," +
                "('2024-01-17', 32000, 10, '', 2)," +
                "('2024-01-18', 85000, 10, '', 2)," +
                "('2024-01-19', 88000, 4, '', 1)," +
                "('2024-01-20', 54000, 2, '', 1)," +
                "('2024-01-21', 78000, 7, '', 1)," +
                "('2024-01-22', 57000, 10, '', 1)," +
                "('2024-01-23', 15000, 4, '', 1)," +
                "('2024-01-24', 91000, 3, '', 1)," +
                "('2024-01-25', 28000, 10, '', 2)," +
                "('2024-01-26', 25000, 5, '', 2)," +
                "('2024-01-27', 44000, 3, '', 1)," +
                "('2024-01-28', 75000, 4, '', 1)," +
                "('2024-01-29', 31000, 8, '', 2)," +
                "('2024-01-30', 50000, 7, '', 2)," +
                "('2024-01-31', 59000, 4, '', 1)," +
                "('2024-02-01', 32000, 2, '', 2)," +
                "('2024-02-02', 49000, 7, '', 1)," +
                "('2024-02-03', 20000, 10, '', 2)," +
                "('2024-02-04', 17000, 8, '', 1)," +
                "('2024-02-05', 30000, 5, '', 1)," +
                "('2024-02-06', 82000, 5, '', 2)," +
                "('2024-02-07', 72000, 7, '', 2)," +
                "('2024-02-08', 12000, 8, '', 2)," +
                "('2024-02-09', 46000, 3, '', 2)," +
                "('2024-02-10', 97000, 2, '', 2)," +
                "('2024-02-11', 63000, 8, '', 2)," +
                "('2024-02-12', 69000, 5, '', 2)," +
                "('2024-02-13', 67000, 2, '', 1)," +
                "('2024-02-14', 51000, 7, '', 2)," +
                "('2024-02-15', 31000, 6, '', 1)," +
                "('2024-02-16', 74000, 8, '', 1)," +
                "('2024-02-17', 67000, 7, '', 2)," +
                "('2024-02-18', 28000, 8, '', 1)," +
                "('2024-02-19', 10000, 3, '', 2)," +
                "('2024-02-20', 82000, 5, '', 2)," +
                "('2024-02-21', 83000, 8, '', 2)," +
                "('2024-02-22', 31000, 2, '', 1)," +
                "('2024-02-23', 20000, 9, '', 2)," +
                "('2024-02-24', 60000, 5, '', 1)," +
                "('2024-02-25', 76000, 9, '', 2)," +
                "('2024-02-26', 12000, 4, '', 2)," +
                "('2024-02-27', 72000, 5, '', 2)," +
                "('2024-02-28', 54000, 3, '', 1)," +
                "('2024-02-29', 26000, 7, '', 2)," +
                "('2024-03-01', 83000, 8, '', 2)," +
                "('2024-03-02', 13000, 9, '', 2)," +
                "('2024-03-03', 10000, 6, '', 2)," +
                "('2024-03-04', 98000, 5, '', 2)," +
                "('2024-03-05', 83000, 4, '', 2)," +
                "('2024-03-06', 87000, 6, '', 1)," +
                "('2024-03-07', 21000, 2, '', 1)," +
                "('2024-03-08', 16000, 7, '', 1)," +
                "('2024-03-09', 35000, 4, '', 1)," +
                "('2024-03-10', 95000, 9, '', 2)," +
                "('2024-03-11', 71000, 6, '', 2)," +
                "('2024-03-12', 63000, 5, '', 2)," +
                "('2024-03-13', 47000, 6, '', 1)," +
                "('2024-03-14', 10000, 7, '', 1)," +
                "('2024-03-15', 98000, 8, '', 2)," +
                "('2024-03-16', 79000, 10, '', 2)," +
                "('2024-03-17', 62000, 8, '', 2)," +
                "('2024-03-18', 61000, 8, '', 2)," +
                "('2024-03-19', 49000, 8, '', 2)," +
                "('2024-03-20', 87000, 4, '', 2)," +
                "('2024-03-21', 74000, 3, '', 1)," +
                "('2024-03-22', 33000, 9, '', 2)," +
                "('2024-03-23', 44000, 8, '', 2)," +
                "('2024-03-24', 67000, 2, '', 1)," +
                "('2024-03-25', 34000, 10, '', 2)," +
                "('2024-03-26', 64000, 6, '', 1)," +
                "('2024-03-27', 47000, 10, '', 2)," +
                "('2024-03-28', 74000, 4, '', 2)," +
                "('2024-03-29', 40000, 6, '', 1)," +
                "('2024-03-30', 77000, 8, '', 1)," +
                "('2024-03-31', 53000, 4, '', 1)," +
                "('2024-04-01', 44000, 5, '', 1)," +
                "('2024-04-02', 72000, 6, '', 2)," +
                "('2024-04-03', 58000, 4, '', 2)," +
                "('2024-04-04', 88000, 9, '', 2)," +
                "('2024-04-05', 26000, 8, '', 2)," +
                "('2024-04-06', 78000, 5, '', 1)," +
                "('2024-04-07', 14000, 2, '', 2)," +
                "('2024-04-08', 71000, 5, '', 1)," +
                "('2024-04-09', 18000, 6, '', 2)," +
                "('2024-04-10', 42000, 2, '', 1)," +
                "('2024-04-11', 75000, 6, '', 1)," +
                "('2024-04-12', 67000, 4, '', 2)," +
                "('2024-04-13', 15000, 3, '', 1)," +
                "('2024-04-14', 52000, 7, '', 2)," +
                "('2024-04-15', 61000, 8, '', 2)," +
                "('2024-04-16', 36000, 3, '', 2)," +
                "('2024-04-17', 93000, 8, '', 2)," +
                "('2024-04-18', 16000, 4, '', 2)," +
                "('2024-04-19', 44000, 8, '', 2)," +
                "('2024-04-20', 78000, 2, '', 2)," +
                "('2024-04-21', 23000, 5, '', 2)," +
                "('2024-04-22', 100000, 9, '', 2)," +
                "('2024-04-23', 40000, 5, '', 2)," +
                "('2024-04-24', 50000, 10, '', 1)," +
                "('2024-04-25', 10000, 10, '', 1)," +
                "('2024-04-26', 36000, 5, '', 1)," +
                "('2024-04-27', 74000, 3, '', 2)," +
                "('2024-04-28', 95000, 6, '', 2)," +
                "('2024-04-29', 42000, 6, '', 2)," +
                "('2024-04-30', 21000, 2, '', 1)," +
                "('2024-05-01', 66000, 5, '', 1)," +
                "('2024-05-02', 51000, 3, '', 2)," +
                "('2024-05-03', 86000, 8, '', 1)," +
                "('2024-05-04', 28000, 7, '', 1)," +
                "('2024-05-05', 58000, 7, '', 2)," +
                "('2024-05-06', 81000, 6, '', 2)," +
                "('2024-05-07', 31000, 9, '', 2)," +
                "('2024-05-08', 65000, 6, '', 1)," +
                "('2024-05-09', 24000, 9, '', 2)," +
                "('2024-05-10', 66000, 10, '', 1)," +
                "('2024-05-11', 40000, 8, '', 1)," +
                "('2024-05-12', 77000, 2, '', 2)," +
                "('2024-05-13', 83000, 10, '', 1)," +
                "('2024-05-14', 44000, 5, '', 1)," +
                "('2024-05-15', 61000, 2, '', 2)," +
                "('2024-05-16', 85000, 8, '', 1)," +
                "('2024-05-17', 50000, 5, '', 1)," +
                "('2024-05-18', 50000, 2, '', 2)," +
                "('2024-05-19', 72000, 3, '', 1)," +
                "('2024-05-20', 16000, 10, '', 2)," +
                "('2024-05-21', 89000, 7, '', 1)," +
                "('2024-05-22', 34000, 5, '', 1)," +
                "('2024-05-23', 76000, 8, '', 2)," +
                "('2024-05-24', 65000, 6, '', 1)," +
                "('2024-05-25', 57000, 3, '', 1)," +
                "('2024-05-26', 31000, 4, '', 1)," +
                "('2024-05-27', 66000, 4, '', 2)," +
                "('2024-05-28', 93000, 5, '', 1)," +
                "('2024-05-29', 96000, 3, '', 2)," +
                "('2024-05-30', 48000, 2, '', 2)," +
                "('2024-05-31', 28000, 9, '', 2)," +
                "('2024-06-01', 28000, 2, '', 1)," +
                "('2024-06-02', 31000, 7, '', 1)," +
                "('2024-06-03', 63000, 10, '', 2)," +
                "('2024-06-04', 62000, 7, '', 1)," +
                "('2024-06-05', 90000, 3, '', 2)," +
                "('2024-06-06', 33000, 7, '', 2)," +
                "('2024-06-07', 79000, 7, '', 1)," +
                "('2024-06-08', 55000, 5, '', 2)," +
                "('2024-06-09', 82000, 10, '', 1)," +
                "('2024-06-10', 91000, 2, '', 2)," +
                "('2024-06-11', 25000, 5, '', 1)," +
                "('2024-06-12', 64000, 7, '', 1)," +
                "('2024-06-13', 48000, 4, '', 1)," +
                "('2024-06-14', 55000, 8, '', 2)," +
                "('2024-06-15', 51000, 10, '', 2)," +
                "('2024-06-16', 71000, 10, '', 1)," +
                "('2024-06-17', 42000, 10, '', 2)," +
                "('2024-06-18', 77000, 8, '', 2)," +
                "('2024-06-19', 86000, 4, '', 1)," +
                "('2024-06-20', 70000, 6, '', 2)," +
                "('2024-06-21', 39000, 10, '', 1)," +
                "('2024-06-22', 25000, 3, '', 2)," +
                "('2024-06-23', 73000, 3, '', 1)," +
                "('2024-06-24', 69000, 9, '', 1)," +
                "('2024-06-25', 62000, 10, '', 2)," +
                "('2024-06-26', 64000, 2, '', 1)," +
                "('2024-06-27', 100000, 8, '', 2)," +
                "('2024-06-28', 56000, 8, '', 2)," +
                "('2024-06-29', 62000, 4, '', 2)," +
                "('2024-06-30', 67000, 7, '', 2)," +
                "('2024-07-01', 84000, 4, '', 1)," +
                "('2024-07-02', 90000, 5, '', 1)," +
                "('2024-07-03', 69000, 8, '', 2)," +
                "('2024-07-04', 62000, 5, '', 2)," +
                "('2024-07-05', 88000, 3, '', 2)," +
                "('2024-07-06', 64000, 6, '', 2)," +
                "('2024-07-07', 79000, 9, '', 2)," +
                "('2024-07-08', 85000, 9, '', 2)," +
                "('2024-07-09', 35000, 8, '', 2)," +
                "('2024-07-10', 54000, 5, '', 1)," +
                "('2024-07-11', 58000, 5, '', 2)," +
                "('2024-07-12', 31000, 5, '', 1)," +
                "('2024-07-13', 57000, 7, '', 2)," +
                "('2024-07-14', 97000, 2, '', 1)," +
                "('2024-07-15', 18000, 10, '', 2)," +
                "('2024-07-16', 44000, 2, '', 2)," +
                "('2024-07-17', 73000, 9, '', 2)," +
                "('2024-07-18', 60000, 5, '', 2)," +
                "('2024-07-19', 58000, 6, '', 1)," +
                "('2024-07-20', 99000, 8, '', 1)," +
                "('2024-07-21', 84000, 3, '', 1)," +
                "('2024-07-22', 93000, 4, '', 1)," +
                "('2024-07-23', 13000, 6, '', 1)," +
                "('2024-07-24', 62000, 6, '', 2)," +
                "('2024-07-25', 100000, 6, '', 2)," +
                "('2024-07-26', 79000, 6, '', 1)," +
                "('2024-07-27', 19000, 5, '', 2)," +
                "('2024-07-28', 62000, 2, '', 1)," +
                "('2024-07-29', 27000, 9, '', 1)," +
                "('2024-07-30', 100000, 6, '', 1)," +
                "('2024-07-31', 96000, 10, '', 1)," +
                "('2024-08-01', 94000, 7, '', 1)," +
                "('2024-08-02', 95000, 3, '', 2)," +
                "('2024-08-03', 62000, 2, '', 2)," +
                "('2024-08-04', 94000, 6, '', 2)," +
                "('2024-08-05', 55000, 4, '', 2)," +
                "('2024-08-06', 27000, 2, '', 1)," +
                "('2024-08-07', 66000, 10, '', 1)," +
                "('2024-08-08', 67000, 10, '', 2)," +
                "('2024-08-09', 96000, 4, '', 1)," +
                "('2024-08-10', 14000, 10, '', 1)," +
                "('2024-08-11', 15000, 7, '', 1)," +
                "('2024-08-12', 10000, 9, '', 1)," +
                "('2024-08-13', 70000, 5, '', 1)," +
                "('2024-08-14', 34000, 9, '', 2)," +
                "('2024-08-15', 33000, 7, '', 1)," +
                "('2024-08-16', 72000, 9, '', 1)," +
                "('2024-08-17', 31000, 8, '', 1)," +
                "('2024-08-18', 39000, 8, '', 2)," +
                "('2024-08-19', 26000, 5, '', 2)," +
                "('2024-08-20', 42000, 3, '', 1)," +
                "('2024-08-21', 63000, 9, '', 1)," +
                "('2024-08-22', 85000, 10, '', 1)," +
                "('2024-08-23', 56000, 7, '', 1)," +
                "('2024-08-24', 85000, 6, '', 2)," +
                "('2024-08-25', 92000, 3, '', 2)," +
                "('2024-08-26', 18000, 7, '', 1)," +
                "('2024-08-27', 55000, 10, '', 2)," +
                "('2024-08-28', 60000, 10, '', 2)," +
                "('2024-08-29', 83000, 10, '', 2)," +
                "('2024-08-30', 68000, 9, '', 1)," +
                "('2024-08-31', 30000, 9, '', 2)," +
                "('2024-09-01', 22000, 6, '', 2)," +
                "('2024-09-02', 87000, 7, '', 1)," +
                "('2024-09-03', 10000, 10, '', 2)," +
                "('2024-09-04', 42000, 5, '', 1)," +
                "('2024-09-05', 26000, 6, '', 2)," +
                "('2024-09-06', 15000, 3, '', 2)," +
                "('2024-09-07', 82000, 9, '', 2)," +
                "('2024-09-08', 40000, 8, '', 1)," +
                "('2024-09-09', 97000, 5, '', 1)," +
                "('2024-09-10', 16000, 5, '', 1)," +
                "('2024-09-11', 76000, 4, '', 2)," +
                "('2024-09-12', 74000, 4, '', 1)," +
                "('2024-09-13', 67000, 8, '', 1)," +
                "('2024-09-14', 25000, 7, '', 1)," +
                "('2024-09-15', 68000, 9, '', 2)," +
                "('2024-09-16', 16000, 10, '', 1)," +
                "('2024-09-17', 25000, 10, '', 2)," +
                "('2024-09-18', 79000, 10, '', 2)," +
                "('2024-09-19', 85000, 3, '', 1)," +
                "('2024-09-20', 23000, 6, '', 2)," +
                "('2024-09-21', 12000, 5, '', 2)," +
                "('2024-09-22', 45000, 8, '', 2)," +
                "('2024-09-23', 18000, 9, '', 2)," +
                "('2024-09-24', 51000, 9, '', 2)," +
                "('2024-09-25', 64000, 10, '', 1)," +
                "('2024-09-26', 63000, 2, '', 2)," +
                "('2024-09-27', 87000, 4, '', 2)," +
                "('2024-09-28', 43000, 4, '', 2)," +
                "('2024-09-29', 73000, 2, '', 2)," +
                "('2024-09-30', 63000, 9, '', 2)," +
                "('2024-10-01', 45000, 5, '', 1)," +
                "('2024-10-02', 44000, 2, '', 1)," +
                "('2024-10-03', 35000, 3, '', 1)," +
                "('2024-10-04', 90000, 2, '', 1)," +
                "('2024-10-05', 20000, 10, '', 2)," +
                "('2024-10-06', 65000, 10, '', 1)," +
                "('2024-10-07', 58000, 4, '', 2)," +
                "('2024-10-08', 63000, 8, '', 2)," +
                "('2024-10-09', 48000, 8, '', 2)," +
                "('2024-10-10', 15000, 10, '', 1)," +
                "('2024-10-11', 60000, 9, '', 1)," +
                "('2024-10-12', 29000, 8, '', 2)," +
                "('2024-10-13', 38000, 2, '', 2)," +
                "('2024-10-14', 43000, 6, '', 1)," +
                "('2024-10-15', 56000, 3, '', 1)," +
                "('2024-10-16', 45000, 8, '', 1)," +
                "('2024-10-17', 40000, 10, '', 2)," +
                "('2024-10-18', 80000, 10, '', 2)," +
                "('2024-10-19', 43000, 4, '', 1)," +
                "('2024-10-20', 73000, 10, '', 1)," +
                "('2024-10-21', 28000, 8, '', 2)," +
                "('2024-10-22', 18000, 3, '', 1)," +
                "('2024-10-23', 13000, 8, '', 1)," +
                "('2024-10-24', 43000, 2, '', 1)," +
                "('2024-10-25', 80000, 9, '', 2)," +
                "('2024-10-26', 11000, 3, '', 1)," +
                "('2024-10-27', 38000, 10, '', 2)," +
                "('2024-10-28', 45000, 6, '', 2)," +
                "('2024-10-29', 76000, 10, '', 2)," +
                "('2024-10-30', 60000, 7, '', 2)," +
                "('2024-10-31', 30000, 8, '', 1)," +
                "('2024-11-01', 86000, 4, '', 1)," +
                "('2024-11-02', 96000, 8, '', 2)," +
                "('2024-11-03', 69000, 9, '', 1)," +
                "('2024-11-04', 87000, 4, '', 1)," +
                "('2024-11-05', 70000, 2, '', 2)," +
                "('2024-11-06', 59000, 7, '', 1)," +
                "('2024-11-07', 90000, 3, '', 1)," +
                "('2024-11-08', 58000, 7, '', 2)," +
                "('2024-11-09', 68000, 9, '', 2)," +
                "('2024-11-10', 51000, 7, '', 2)," +
                "('2024-11-11', 88000, 10, '', 1)," +
                "('2024-11-12', 63000, 4, '', 2)," +
                "('2024-11-13', 32000, 5, '', 1)," +
                "('2024-11-14', 47000, 8, '', 1)," +
                "('2024-11-15', 16000, 10, '', 2)," +
                "('2024-11-16', 90000, 7, '', 1)," +
                "('2024-11-17', 45000, 9, '', 2)," +
                "('2024-11-18', 13000, 7, '', 1)," +
                "('2024-11-19', 18000, 10, '', 1)," +
                "('2024-11-20', 76000, 6, '', 2)," +
                "('2024-11-21', 80000, 3, '', 1)," +
                "('2024-11-22', 65000, 3, '', 2)," +
                "('2024-11-23', 85000, 4, '', 1)," +
                "('2024-11-24', 85000, 2, '', 2)," +
                "('2024-11-25', 72000, 7, '', 1)," +
                "('2024-11-26', 83000, 4, '', 2)," +
                "('2024-11-27', 76000, 4, '', 1)," +
                "('2024-11-28', 82000, 6, '', 1)," +
                "('2024-11-29', 100000, 2, '', 1)," +
                "('2024-11-30', 59000, 8, '', 1)," +
                "('2024-12-01', 57000, 9, '', 2)," +
                "('2024-12-02', 77000, 3, '', 1)," +
                "('2024-12-03', 75000, 3, '', 1)," +
                "('2024-12-04', 41000, 6, '', 1)," +
                "('2024-12-05', 67000, 5, '', 1)," +
                "('2024-12-06', 97000, 2, '', 1)," +
                "('2024-12-07', 40000, 9, '', 2)," +
                "('2024-12-08', 65000, 8, '', 2)," +
                "('2024-12-09', 57000, 4, '', 1)," +
                "('2024-12-10', 99000, 9, '', 1)," +
                "('2024-12-11', 47000, 2, '', 2)," +
                "('2024-12-12', 51000, 7, '', 1)," +
                "('2024-12-13', 19000, 10, '', 2)," +
                "('2024-12-14', 51000, 3, '', 2)," +
                "('2024-12-15', 28000, 8, '', 1)," +
                "('2024-12-16', 49000, 8, '', 2)," +
                "('2024-12-17', 98000, 7, '', 2)," +
                "('2024-12-18', 53000, 3, '', 1)," +
                "('2024-12-19', 19000, 9, '', 1)," +
                "('2024-12-20', 58000, 7, '', 1)," +
                "('2024-12-21', 32000, 8, '', 2)," +
                "('2024-12-22', 64000, 7, '', 1)," +
                "('2024-12-23', 50000, 8, '', 1)," +
                "('2024-12-24', 84000, 8, '', 1)," +
                "('2024-12-25', 80000, 9, '', 2)," +
                "('2024-12-26', 67000, 10, '', 2)," +
                "('2024-12-27', 91000, 9, '', 1)," +
                "('2024-12-28', 49000, 3, '', 2)," +
                "('2024-12-29', 77000, 9, '', 1)," +
                "('2024-12-30', 69000, 4, '', 1)," +
                "('2024-12-31', 98000, 7, '', 2)" ;
        db.execSQL(insertValues1);
        String createTableNS = "CREATE TABLE NganSach (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ngansach REAL," +
                "loaigd INTEGER," +
                "thongbao TEXT," +
                "FOREIGN KEY(loaigd) REFERENCES loaiGD(id)" +
                ")";
        db.execSQL(createTableNS);
        String insert1 = "INSERT INTO NganSach (ngansach, loaigd, thongbao) VALUES " +
                "(2000000, 1, '1111')," +
                "(10000, 2, '1000')," +
                "(500000, 4, '0110')," +
                "(80000, 3, '0011')," +
                "(1000000, 9, '1001')";
        db.execSQL(insert1);
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
    public List<LoaiGD> getAllLoaiGDExcept1(){
        List<LoaiGD> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "id ASC";
        String whereClause = "id != 1";
        Cursor rs = sqLiteDatabase.query("loaiGD",null,whereClause,
                null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            int src = rs.getInt(1);
            String name = rs.getString(2);
            list.add(new LoaiGD(id,src,name));
        }
        return list;
    }
    public long insertLoaiGiaoDich(LoaiGD loaiGD){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("src", loaiGD.getSrcIcon());
        values.put("name", loaiGD.getNameIcon());
        long id = db.insert("loaiGD", null, values);
        db.close();
        return id;
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
        if(order.equals("1")) order="";
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

    public List<GiaoDich> getListOrderGD(String idloaigd, String sotienmin,String sotienmax, String ngayStart, String ngayEnd) {
        List<GiaoDich> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        StringBuilder whereClauseBuilder = new StringBuilder();
        List<String> whereArgsList = new ArrayList<>();

        if(idloaigd.equals("1")) idloaigd="";
        if (!idloaigd.equals("")) {
            System.out.println(idloaigd+" id loai gd");
            whereClauseBuilder.append("loaigd = ?");
            whereArgsList.add(idloaigd);
        }

        if (!sotienmin.equals("")) {
            if (whereClauseBuilder.length() > 0) {
                whereClauseBuilder.append(" AND ");
            }
            whereClauseBuilder.append("sotien >= ?");
            whereArgsList.add(sotienmin);
        }

        if (!sotienmax.equals("")) {
            if (whereClauseBuilder.length() > 0) {
                whereClauseBuilder.append(" AND ");
            }
            whereClauseBuilder.append("sotien <= ?");
            whereArgsList.add(sotienmax);
        }

        if (!ngayStart.equals("")) {
            if (whereClauseBuilder.length() > 0) {
                whereClauseBuilder.append(" AND ");
            }
            whereClauseBuilder.append("date >= ?");
            whereArgsList.add(ngayStart);
        }

        if (!ngayEnd.equals("")) {
            if (whereClauseBuilder.length() > 0) {
                whereClauseBuilder.append(" AND ");
            }
            whereClauseBuilder.append("date <= ?");
            whereArgsList.add(ngayEnd);
        }

        String[] whereArgs = whereArgsList.toArray(new String[0]);

        Cursor rs = sqLiteDatabase.query("GiaoDich", null,
                whereClauseBuilder.length() > 0 ? whereClauseBuilder.toString() : null,
                whereClauseBuilder.length() > 0 ? whereArgs : null,
                null, null, null);

        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String date = rs.getString(1);
            String sotien = rs.getString(2);
            String loaigd = rs.getString(3);
            String mota = rs.getString(4);
            int inorout = rs.getInt(5);
            LoaiGD loaiGD1 = getLoaiGD(loaigd);

            list.add(new GiaoDich(id, Date.valueOf(date), Float.parseFloat(sotien), loaiGD1, mota, inorout));
        }
        return list;
    }
    public List<NganSach> getAllNS(){
        List<NganSach> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order="id ASC";
        Cursor rs = st.query("NganSach", null, null,
                null, null, null, order);
        while (rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            float nganSach = rs.getFloat(1);
            String idgd = rs.getString(2);
            String thongBao = rs.getString(3);
            if(Objects.equals(idgd, "1")){
                continue;
            }
            LoaiGD loaiGD = getLoaiGD(idgd);
            list.add(new NganSach(id, nganSach, loaiGD, thongBao));
        }
        return list;
    }

    public List<String> getAllNSLoaiName(){
        List<String> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order="id ASC";
        Cursor rs = st.query("NganSach", null, null,
                null, null, null, order);
        while (rs != null && rs.moveToNext()){
            String idgd = rs.getString(2);
            if(Objects.equals(idgd, "1")){
                continue;
            }
            LoaiGD loaiGD = getLoaiGD(idgd);
            list.add(loaiGD.getNameIcon());
        }
        return list;
    }

    public long addNS(NganSach ns){
        ContentValues values = new ContentValues();
        values.put("ngansach", ns.getNganSach());
        values.put("loaigd", ns.getLoaiGD().getID());
        values.put("thongbao" ,ns.getThongBao());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("NganSach", null, values);
    }

    public NganSach getNganSachByLoaiGD(String loaigd){
        String whereClause = "loaigd = ?";
        String[] whereArgs = {loaigd};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("NganSach", null, whereClause, whereArgs,
                null, null, null);
        if (rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            float nganSach = rs.getFloat(1);
            String thonBao = rs.getString(3);
            LoaiGD loaiGD = getLoaiGD(loaigd);
            NganSach ns = new NganSach(id, nganSach, loaiGD, thonBao);
            return ns;
        } else {
            return null;
        }
    }

    public float getTongNganSach(){
        String whereClause = "loaigd = 1";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("NganSach", null, whereClause,
                null, null, null, null);
        if (rs != null && rs.moveToNext()){
            return rs.getFloat(1);
        } else {
            return 0;
        }
    }

    public float getLuuDong(){
        float tongSet = 0, tongReal = 0;
        String whereClause1 = "loaigd = 1";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("NganSach", null, whereClause1,
                null, null, null, null);
        if (rs != null && rs.moveToNext()){
            tongSet = rs.getFloat(1);
        }
        String whereClause2 = "loaigd != 1";
        st = getReadableDatabase();
        rs = st.query("NganSach", null, whereClause2,
                null, null, null, null);
        while (rs != null && rs.moveToNext()){
            tongReal += rs.getFloat(1);
        }
        return tongSet - tongReal;
    }

    public int updateNS(NganSach ns){
        ContentValues values = new ContentValues();
        values.put("ngansach", ns.getNganSach());
        values.put("loaigd", ns.getLoaiGD().getID());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause ="id = ?";
        String[] whereArgs = {Integer.toString(ns.getId())};
        return sqLiteDatabase.update("NganSach", values, whereClause, whereArgs);
    }

    public int deleteNS(int id){
        String whereClause ="id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("NganSach", whereClause, whereArgs);
    }

    public float tongChi1DanhMuc(LoaiGD loaiGD,String date1, String date2) {
        float tong = 0;
        StringBuilder whereClause = new StringBuilder();
        whereClause.append("inorout = 1 AND date >=? AND date <=?");
        List<String> whereArgsList = new ArrayList<>();
        whereArgsList.add(date1);
        whereArgsList.add(date2);
        if (loaiGD != null){
            whereClause.append("AND loaigd = ?");
            whereArgsList.add(String.valueOf(loaiGD.getID()));
        }
        String[] whereArgs = whereArgsList.toArray(new String[0]);
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("GiaoDich", null, whereClause.toString(), whereArgs,
                null, null, null);
        while (rs != null && rs.moveToNext()){
            tong += rs.getFloat(2);
        }
        return tong;
    }

    public int updateNSThongBao(int id, String thongBao){
        ContentValues values = new ContentValues();
        values.put("thongbao", thongBao);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause ="id = ?";
        String[] whereArgs = {Integer.toString(id)};
        return sqLiteDatabase.update("NganSach", values, whereClause, whereArgs);
    }
    public List<GiaoDich> getTransactionsInRange(String startDate, String endDate, int inorout) {
        List<GiaoDich> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT id, date, sotien, loaigd, chitiet, inorout ");
        queryBuilder.append("FROM GiaoDich ");

        List<String> whereArgsList = new ArrayList<>();

        // Xây dựng điều kiện cho truy vấn
        queryBuilder.append("WHERE date >= ? AND date <= ? ");
        whereArgsList.add(startDate); // Không cần chuyển đổi định dạng ngày tháng ở đây
        whereArgsList.add(endDate); // Không cần chuyển đổi định dạng ngày tháng ở đây

        // Thêm điều kiện cho inorout
        queryBuilder.append("AND inorout = ? ");
        whereArgsList.add(String.valueOf(inorout));

        String[] whereArgs = whereArgsList.toArray(new String[0]);

        Cursor rs = sqLiteDatabase.query("GiaoDich", null, "date >= ? AND date <= ? AND inorout = ?", whereArgs, null, null, null);

        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String dateString = rs.getString(1); // Lấy dữ liệu ngày dưới dạng chuỗi ngày tháng
            String sotien = rs.getString(2);
            String loaigd = rs.getString(3); // Lấy tên loại giao dịch từ cột "loaigd"
            String mota = rs.getString(4);
            int inoroutValue = rs.getInt(5);

            // Chuyển đổi từ chuỗi ngày tháng sang java.sql.Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            java.sql.Date sqlDate = null;
            try {
                java.util.Date parsedDate = dateFormat.parse(dateString);
                sqlDate = new java.sql.Date(parsedDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            LoaiGD loaiGD = new LoaiGD();
            loaiGD.setNameIcon(loaigd); // Sử dụng setter để thiết lập tên loại giao dịch

            list.add(new GiaoDich(id, sqlDate, Float.parseFloat(sotien), loaiGD, mota, inoroutValue));
        }
        Log.d("SQL_QUERY", queryBuilder.toString()); // In ra câu truy vấn SQL

        return list;
    }

}
