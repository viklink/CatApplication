package exam.catapp.sourceit.catapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import exam.catapp.sourceit.catapplication.model.Cat;

@Database(entities = {Cat.class}, version = 1)
public abstract class CatDatabase extends RoomDatabase {

    public abstract CatDao catDao();
}
