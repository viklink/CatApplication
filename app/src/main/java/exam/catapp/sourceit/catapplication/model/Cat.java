package exam.catapp.sourceit.catapplication.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Cat {

    public static String COLUMN_ID = "id";

    public static String COLUMN_AGE = "age";

    public static String COLUMN_NAME = "name";

    public static String COLUMN_BREED = "breed";

    public static String COLUMN_IMAGE_URL = "imageUrl";

    public static String COLUMN_DESCRIPTION = "description";

    public static String COLUMN_GENDER = "gender";



    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private int age;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String breed;

    @ColumnInfo
    private String imageUrl;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private String gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {this.gender = gender;}
}
