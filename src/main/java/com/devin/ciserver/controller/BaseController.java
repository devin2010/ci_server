package com.devin.ciserver.controller;
import com.thoughtworks.xstream.XStream;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;

/**
 * Created by devin on 2018/8/8.
 */
public class BaseController {
    private String parseXmlOrJson(String dataType,Object obj,String alias){
        if("xml".equals(dataType)){
            //XStream
            //创建xStream对象
            XStream xStream = new XStream();
            //给指定类起别名
            xStream.alias(alias,obj.getClass());
            return xStream.toXML(obj);
        }else{
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
    }
}
