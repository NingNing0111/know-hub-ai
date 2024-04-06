package com.ningning0111.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: com.ningning0111.common
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 19:37
 * @Description: 对话模型
 */
public enum LLMModels {
    GPT_3_5_TURBO("gpt-3.5-turbo"),
    GPT_3_5_TURBO_16K("gpt-3.5-turbo-16k"),
    GPT_4("gpt-4");

    private final String modelName;

    LLMModels(String modelName){
        this.modelName = modelName;
    }

    public String getModelName(){
        return this.modelName;
    }

    public static List<String> getModels(){
        LLMModels[] values = LLMModels.values();
        List<String> models = new ArrayList<>();
        for(LLMModels model: values){
            models.add(model.getModelName());
        }
        return models;
    }

    public static LLMModels getLLMModels(String modelName){
        LLMModels[] values = LLMModels.values();
        for(LLMModels model: values){
            if(model.getModelName().equals(modelName)){
                return model;
            }
        }
        return null;
    }
}
