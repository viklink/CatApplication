package exam.catapp.sourceit.catapplication.network;

import java.util.List;

import exam.catapp.sourceit.catapplication.model.Cat;

public interface CatLoadingListener {

    void startLoading();

    void finishLoading(List<Cat> cats);
}
