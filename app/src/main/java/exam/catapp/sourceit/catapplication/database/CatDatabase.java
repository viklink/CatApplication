package exam.catapp.sourceit.catapplication.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import exam.catapp.sourceit.catapplication.model.Cat;

@Database(entities = {Cat.class}, version = 2)
public abstract class CatDatabase extends RoomDatabase {

    public abstract CatDao catDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Cat ADD COLUMN gender VARCHAR(100)");
            database.execSQL("delete from Cat");
        }
    };
}
