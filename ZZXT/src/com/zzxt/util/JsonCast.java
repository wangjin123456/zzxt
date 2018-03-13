package com.zzxt.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzxt.bean.OrgBean;
import com.zzxt.bean.TypeContent;
import com.zzxt.bean.AppEntity;

/**
 * @author hlj
 */
public class JsonCast {

	/**
     * 将javabean转化为序列化的json字符串
     * @param keyvalue
     * @return
     */
    public static Object beanToJson(Object keyvalue) {
        String textJson = JSON.toJSONString(keyvalue);
        Object objectJson  = JSON.parse(textJson);
        return objectJson;
    }
    
    /**
     * 将string转化为序列化的json字符串
     * @param keyvalue
     * @return
     */
    public static Object textToJson(String text) {
        Object objectJson  = JSON.parse(text);
        return objectJson;
    }
    
    /**
     * json字符串转化为map
     * @param s
     * @return
     */
    public static Map stringToCollect(String s) {
        Map m = (Map) JSONObject.parseObject(s);
        return m;
    }
    
    /**
     * 将map转化为string
     * @param m
     * @return
     */
    public static String collectToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }
    
    static ObjectMapper objectMapper;  
    /** 
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。 转换为普通JavaBean：readValue(json,Student.class) 
     * 转换为List:readValue(json,List.class ).但是如果我们想把json转换为特定类型的List，比如List 
     * <Student>，就不能直接进行转换了。 因为readValue(json,List .class)返回其实是List 
     * <Map>类型，你不能指定readValue()的第二个参数是List<Student >.class，所以不能直接转换。 
     * 我们可以把readValue()的第二个参数传递为Student[].class.然后使用Arrays.asList ();方法把得到的数组转换为特定类型的List。 
     * 转换为Map：readValue(json,Map.class) 我们使用泛型，得到的也是泛型 
     *  
     * @param content 要转换的JavaBean类型 
     * @param valueType 原始json字符串数据 
     * @return JavaBean对象 
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */  
    public static <T> T readValue(String content, Class<T> valueType)  
            throws JsonParseException, JsonMappingException, IOException {  
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
        return objectMapper.readValue(content, valueType);  
    }  
  
  
    public static <T> T readValue(String content, JavaType javaType)  
            throws JsonParseException, JsonMappingException, IOException {  
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
        return objectMapper.readValue(content, javaType);  
    }  
  
  
    /** 
     * 把JavaBean转换为json字符串 普通对象转换：toJson(Student) List转换：toJson(List) Map转换:toJson(Map) 
     * 我们发现不管什么类型，都可以直接传入这个方法 
     *  
     * @param object JavaBean对象 
     * @return json字符串 
     * @throws JsonProcessingException 
     */  
    public static String toJson(Object object) throws JsonProcessingException {  
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
        return objectMapper.writeValueAsString(object);  
    }  
  
  
    public static JavaType getCollectionType(Class<?> collectionClass,  
            Class<?>... elementClasses) {  
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
        return objectMapper.getTypeFactory().constructParametricType(collectionClass,  
                elementClasses);  
    }  
  
    /**
     * List<T> 转 json 
     */
    public static <T> String listToJson(List<T> ts) {
        String jsons = JSON.toJSONString(ts);
        return jsons;
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<Map<String, Object>> jsonToList(String jsonString, Class<T> clazz) {
    	List<Map<String, Object>>  ts = (List<Map<String, Object>> ) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
    /**
     * json 转 TypeContent List<T>
     */
    public static <T> List<TypeContent> jsonToTypeContentList(String jsonString, Class<T> clazz) {
    	List<TypeContent>  ts = (List<TypeContent> ) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
    
    /**
     * json 转 orgBean List<T>
     */
    public static <T> List<OrgBean> jsonToOrgBeanList(String jsonString, Class<T> clazz) {
    	List<OrgBean>  ts = (List<OrgBean> ) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
    /**
     * json 转 appEntity List<T>
     */
    public static <T> List<AppEntity> jsonToAppEntityList(String jsonString, Class<T> clazz) {
    	List<AppEntity>  ts = (List<AppEntity> ) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
 
}
