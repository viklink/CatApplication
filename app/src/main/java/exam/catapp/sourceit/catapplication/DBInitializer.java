package exam.catapp.sourceit.catapplication;

import android.arch.persistence.room.Room;
import android.content.Context;

import exam.catapp.sourceit.catapplication.database.CatDatabase;

public class DBInitializer {

    public static CatDatabase initialize(Context context) {
            CatDatabase catDatabase = Room.
                    databaseBuilder(context, CatDatabase.class, "cats.db").
                    addMigrations(CatDatabase.MIGRATION_1_2).
                    build();
            return catDatabase;
    }
}
