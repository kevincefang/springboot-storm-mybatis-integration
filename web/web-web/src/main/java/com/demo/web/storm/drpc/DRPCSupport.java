package com.demo.web.storm.drpc;


import com.demo.web.storm.StormConfig;
import org.apache.storm.utils.DRPCClient;

import java.util.HashMap;
import java.util.Map;


public class DRPCSupport {




    /**
     * 每次请求new 一个drpc client
     * @param func
     * @param params
     * @param stormConfig
     * @return
     * @throws Exception
     */
     public static Map<String, Object> doBussiness(String func, String params, StormConfig stormConfig) throws Exception {

        DRPCClient client = DrpcClientFactory.getInstance(stormConfig).getDefaultDRPCClient();
        Map<String, Object> map = new HashMap<>();
        try {
            String result = client.execute(func, params);
            map.put(DRPCConstants.RESULT, result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }

        return map;
    }

}
