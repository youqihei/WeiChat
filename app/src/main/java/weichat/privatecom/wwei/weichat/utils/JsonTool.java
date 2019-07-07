package weichat.privatecom.wwei.weichat.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonTool {
    public static<T>  T getPerson(String jsonstring, Class cls) {
        T t = null;
        try {
            t = (T)JSON.parseObject(jsonstring, cls);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return t;
    }

    public static <T> List<T> getPersonList(String jsonstring, Class cls) {
        List<T> list = new ArrayList();
        try {
            list = JSON.parseArray(jsonstring, cls);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }
    /**
     * 解析JsonArray数据，返回Map类型的List
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> parseObjectListKeyMaps(
            String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = JSON.parseObject(jsonString,
                    new TypeReference<List<Map<String, Object>>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
