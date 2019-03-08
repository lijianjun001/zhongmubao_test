package com.zhongmubao.api.service.impl.buySheep;

import com.google.gson.Gson;
import com.zhongmubao.api.test.CreateResultModel;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderTask {
    private static List<ProjectModel2> projectModel2 = null;

    public void run() {
        if (projectModel2 == null) {
            getProducts();
        }

    }


    public void buySheep() {
        AccountManager.getInstance().getUserInfoMap().forEach((s, accountInfo) -> {
            ProjectModel2 selectProject;
            int sheepCount=accountInfo.getSheepCount()>0?accountInfo.getSheepCount():Constant.sheepCount;
            if (accountInfo.getProjectNum() < 1) {
                selectProject = projectModel2.get(0);
            } else {
                selectProject = projectModel2.get(accountInfo.getProjectNum() - 1);
            }
            chooseRedPackets(s,
                    selectProject,sheepCount+"");
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
                projectModel2 = resultModel.getData().getList().stream().filter(it -> !it.getState().equals("专享") && !it.getState().equals("新人")).collect(Collectors.toList());
                System.out.println("getProducts" + resultModel.getData().getList().toString());
            }
        });
    }

    public void chooseRedPackets(String tel, ProjectModel2 projectModel2,String sheepCount) {
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
        mOkHttpClientBuilder.cookieJar(new MyCookieJar(tel));
        final OkHttpClient mOkHttpClient = mOkHttpClientBuilder.build();

        FormBody.Builder formBodyBuilder = new FormBody.Builder()
                .add("r", Math.random() + "")
                .add("ProjectId", projectModel2.getId())
                .add("SheepCount", sheepCount)
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
                createOrder(tel, resultModel.getData(), projectModel2.getId(),sheepCount);
            }
        });
    }

    public void createOrder(String tel, BonusModel bonusModel, String projectId,String sheepCount) {

        System.out.println("start createOrder");
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
        mOkHttpClientBuilder.cookieJar(new MyCookieJar(tel));
        final OkHttpClient mOkHttpClient = mOkHttpClientBuilder.build();

        FormBody.Builder formBodyBuilder = new FormBody.Builder()
                .add("r", Math.random() + "")
                .add("id", projectId)
                .add("libraryCount", 0 + "")
                .add("bouns", bonusModel.getTotalAmount() + "")
                .add("redPacketList", bonusModel.getRedPackageList().toArray().toString())
                .add("count", sheepCount);
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
                } else {
                    System.out.println("createOrder" + resultModel.getMessage());
                }

            }
        });
    }


}
