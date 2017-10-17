package cn.jondai.thread.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jondai on 2017/10/17.
 *
 * 控制台工具类
 */
public class ScannerUtil {

    public static String readFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
