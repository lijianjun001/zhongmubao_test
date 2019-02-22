package com.zhongmubao.api.test;

import java.io.*;

public class FileUtil {

    public static String readStringByFile(String filePath) throws IOException {
        File file = new File(filePath);
        StringBuffer stringBuffer = new StringBuffer();
        FileReader reader = new FileReader(file); //字符流
        char[] chars = new char[128];

        int len = reader.read(chars);
        while (len != -1) {
            stringBuffer.append(new String(chars, 0, len));
            len = reader.read(chars);
        }
        reader.close();

//        FileInputStream fileInputStream=new FileInputStream(file);
//        BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
//
//        byte []bytes=new byte[1024];
//
//        int length=bufferedInputStream.read(bytes);
//        System.out.println(length);
//        while (length != -1) {
//            stringBuffer.append(new String(bytes, 0, length));
//            length = bufferedInputStream.read(bytes);
//        }
//        bufferedInputStream.close();
        return stringBuffer.toString();
    }
}
