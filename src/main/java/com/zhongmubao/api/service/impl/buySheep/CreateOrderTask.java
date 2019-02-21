package com.zhongmubao.api.service.impl.buySheep;

import com.google.gson.Gson;
import com.zhongmubao.api.test.CreateResultModel;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderTask {
    private static ProjectModel2 projectModel2 = null;

    public void createOrder(String tel,BonusModel bonusModel) {

        System.out.println("start createOrder");
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
        mOkHttpClientBuilder.cookieJar(new MyCookieJar(tel));
        final OkHttpClient mOkHttpClient = mOkHttpClientBuilder.build();

        FormBody.Builder formBodyBuilder = new FormBody.Builder()
                .add("r", Math.random() + "")
                .add("id", projectModel2.getId())
                .add("libraryCount", 0 + "")
                .add("bouns", bonusModel.getTotalAmount() + "")
                .add("redPacketList", bonusModel.getRedPackageList().toArray().toString())
                .add("count", Constant.sheepCount + "");
        RequestBody requestBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .url(Constant.hostUrl + "Sheep/CreateOrder?")
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();

                InputStream inputStream = responseBody.byteStream();
                String str = StringUtils.getString(inputStream);
                System.out.println(str);
                CreateResultModel resultModel = new Gson().fromJson(str, CreateResultModel.class);

                if (resultModel.getResult() == 0) {
                    System.out.println("createOrder" + "成功");
                }else{
                    System.out.println("createOrder" + resultModel.getMessage());
                }

            }
        });
    }

    public void chooseRedPackets(String tel, ProjectModel2 projectModel2) {
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
        mOkHttpClientBuilder.cookieJar(new MyCookieJar(tel));
        final OkHttpClient mOkHttpClient = mOkHttpClientBuilder.build();

        FormBody.Builder formBodyBuilder = new FormBody.Builder()
                .add("r", Math.random() + "")
                .add("ProjectId", projectModel2.getId())
                .add("SheepCount", Constant.sheepCount + "")
                .add("SortType", 0 + "");
        RequestBody requestBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .url(Constant.hostUrl + "Sheep/DefaultChooseRedPackage?")
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();

                InputStream inputStream = responseBody.byteStream();
                String str = StringUtils.getString(inputStream);
                System.out.println(str);
                BonusResultModel resultModel = new Gson().fromJson(str, BonusResultModel.class);

                if (resultModel.getResult() == 0) {
                    System.out.println("chooseRedPackets" + resultModel.getData());
                }
                createOrder(tel,resultModel.getData());
            }
        });
    }



    public void getProducts() {
        System.out.println("start getProducts");
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
//        mOkHttpClientBuilder.cookieJar(new MyCookieJar());
        final OkHttpClient mOkHttpClient = mOkHttpClientBuilder.build();

        FormBody.Builder formBodyBuilder = new FormBody.Builder()
                .add("r", Math.random() + "");
        RequestBody requestBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .url(Constant.hostUrl + "Sheep/ProjectListV2?")
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                InputStream inputStream = responseBody.byteStream();
                String str = StringUtils.getString(inputStream);
                ProjectResultModel resultModel = new Gson().fromJson(str, ProjectResultModel.class);
                List<ProjectModel2> projectModel2s = resultModel.getData().getList().stream().filter(it -> !it.getState().equals("专享") &&!it.getState().equals("新人")).collect(Collectors.toList());
                projectModel2 = projectModel2s.get(1);//要抢购的标,默认抢购第一条
                System.out.println("getProducts" + resultModel.getData().getList().toString());
            }
        });
    }

    public void run() {

        if (projectModel2 == null) {
            getProducts();
        }else{
            AccountManager.getInstance().getUserInfoMap().forEach((s, accountInfo) -> chooseRedPackets(s,projectModel2));
        }

    }

}
