package com.codingfly.config;

import com.codingfly.model.MenuModel;
import com.codingfly.utils.ResourcesUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class Config {
    // 1080p=1920X1080，横宽设置80%
    public static double width = 1920*0.8;
    public static double height = 1080*0.8;
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static List<MenuModel> menuModels = null;

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            String menuStr = ResourcesUtils.readFileToString("classpath:config/menus.json");
            menuModels = objectMapper.readValue(menuStr, new TypeReference<List<MenuModel>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
