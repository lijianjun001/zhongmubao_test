package com.zhongmubao.api.service;

import com.zhongmubao.api.exception.ApiException;

import java.io.IOException;

public interface WebCaptureService {
    void autoBuySheep(String tel, String pass,int projectCount) throws ApiException, IOException;
}
