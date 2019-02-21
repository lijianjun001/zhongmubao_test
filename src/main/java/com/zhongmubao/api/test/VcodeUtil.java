package com.zhongmubao.api.test;

import net.sourceforge.tess4j.TesseractException;

import java.io.*;

public class VcodeUtil {

    public static void main(String[] args) throws TesseractException {
//        File file = new File("/Users/mac/Desktop/b.png");
//        ITesseract instance = new Tesseract();
//        instance.setDatapath("/usr/local/Cellar/tesseract/4.0.0_1/share/tessdata");//设置你的Tess4J下的tessdata目录
//        instance.setLanguage("eng");//指定需要识别的语种
//        String result = instance.doOCR(file);
//        System.out.println(result);

        String command = "tesseract /Users/mac/Desktop/a.png /Users/mac/Desktop/a";
        try {
            Process process = Runtime.getRuntime().exec(command);
//            BufferedInputStream bis = new BufferedInputStream(
//                    process.getInputStream());
//            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//
            process.waitFor();
//            if (process.exitValue() != 0) {
//                System.out.println("error!");
//            }
            System.out.println(FileUtil.readStringByFile("/Users/mac/Desktop/a.txt"));
//            bis.close();
//            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
