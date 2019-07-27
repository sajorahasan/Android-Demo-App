package com.sajorahasan.demoapp.api;

import com.sajorahasan.demoapp.model.DemoItem;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    @GET("posts")
    Single<List<DemoItem>> getList();

    @FormUrlEncoded
    @POST("login")
    Single<ResponseBody> login(@Field("uid") String uid,
                               @Field("password") String password);
}
