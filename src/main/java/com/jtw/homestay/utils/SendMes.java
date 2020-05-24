package com.jtw.homestay.utils;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SendMes {

    public static String interfaceUtil(String path,String data) {
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
            //请求方式
//          conn.setRequestMethod("POST");
//           //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
//            RequestEntity entity=new StringRequestEntity(data,"application/json","UTF-8");
            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数即数据
            out.print(data);
            //缓冲数据
            out.flush();
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();

            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String str = "";
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                System.out.println(str);
                sb.append(str);
            }
            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("完整结束");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR:"+e.getMessage();
        }
    }

    public static String sendSMS(String phone,String code){
        JSONArray jsonArray = new JSONArray();
        Map map = new HashMap();
        map.put("Action","TestSendSms");
        Map map1 = new HashMap();
        map1.put("TemplateCode","A4");
        map1.put("PhoneNumbers",new String[]{phone});

        map1.put("OpenCode",new String[]{code,"标准单间","3","3","123"});
        JSONArray array = JSONArray.fromObject(map1);

        map.put("Data",array);
        JSONObject obj = JSONObject.fromObject(map);
        System.out.println(obj);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PhoneNumbers",phone);
        jsonObject.put("TemplateCode","A4");
        jsonObject.put("OpenCode","2019-12-31");
        jsonArray.add(jsonObject);
        System.out.println(jsonArray);
        String result =interfaceUtil("http://api.tzyhdjd.com/OpenApi/Inlet/",obj.toString());
        if(result!=null && !result.startsWith("ERROR:"))
        {
            JSONArray resultArray = JSONArray.fromObject(result);
            if(resultArray!=null && resultArray.size()>0)
            {
                if("Yes".equals(resultArray.getJSONObject(0).getString("State")))
                {
                    //短信发送成功
                    return "OK";
                }
            }
            return result;
        }else
        {
            return result;
        }
    }
    public static void main(String[] args) {

        JSONArray jsonArray = new JSONArray();
        Map map = new HashMap();
        map.put("Action","TestSendSms");
        Map map1 = new HashMap();
        map1.put("TemplateCode","A4");
        map1.put("PhoneNumbers",new String[]{"15938375666"});

        map1.put("OpenCode",new String[]{"5637","标准单间","3","3","123"});
        JSONArray array = JSONArray.fromObject(map1);

        map.put("Data",array);
        JSONObject obj = JSONObject.fromObject(map);
        System.out.println(obj);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PhoneNumbers","15938375666");
        jsonObject.put("TemplateCode","A4");
        jsonObject.put("OpenCode","2019-12-31");
        jsonArray.add(jsonObject);
        System.out.println(jsonArray);
        interfaceUtil("http://api.tzyhdjd.com/OpenApi/Inlet/",obj.toString());

    }

}
