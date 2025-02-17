package com.ahu.coding.beast.testcase;

import com.alibaba.fastjson2.JSON;

/**
 * @author jianzhang
 * 2025/02/17/下午2:46
 */
public class JsonTestCase {

    public static void main(String[] args) {
        String reqParam = "123";
        boolean isJsonParam;
        try  {
            JSON.parseObject(reqParam);
            isJsonParam = true;
        } catch (Exception e) {
            // 发生异常说明不是合法的JSON字符串
            isJsonParam = false;
        }
        System.out.println(isJsonParam);
    }
}
