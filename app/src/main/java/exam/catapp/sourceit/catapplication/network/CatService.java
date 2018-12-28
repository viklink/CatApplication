package exam.catapp.sourceit.catapplication.network;

import java.util.List;

import exam.catapp.sourceit.catapplication.model.Cat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface CatService {

    @GET(".json")
    Call<List<Cat>> getAllCats();
}
