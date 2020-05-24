package com.jtw.homestay.utils;

/*import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;*/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * 字符串工具类
 *
 * @author Hill
 */
public class StringUtil {

    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
        if (obj != null && obj.toString() != null && !"".equals(obj.toString().trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否为空(自动截取首尾空白)
     *
     * @param str 源字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 是否为空
     *
     * @return 为空返回true
     */
    public static boolean isEmpty(Collection list) {
        return list == null || list.size() == 0;
    }

    /**
     * 是否为空
     *
     * @return 为空返回true
     */
    public static boolean notEmpty(Collection list) {
        return !isEmpty(list);
    }

    /**
     * 判断字符串是否为纯汉字
     *
     * @param str 源字符串
     * @return 纯汉字返回true, 否则返回false
     */
    public static boolean isChinese(String str) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否有汉字
     *
     * @param str 源字符串
     * @return 纯汉字返回true, 否则返回false
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 用于字符串替换
     *
     * @param target      目标对象 需要替换的字符串
     * @param replacement 要替换的字符串
     * @param value       替换的值
     * @return
     */
    public static String replacement(String target, String replacement, String value) {
        if (target != null)
            return target.replace(replacement, value);
        return null;
    }

    /**
     * 函数功能说明  把字符串转成list<String>
     * 函数名称  toStringList
     * 修改者名字 lp
     * 修改日期 2018-7-10
     * 修改内容
     *
     * @param obj
     * @return List<String>
     */
    public static List<String> toStringList(String obj) {
        List<String> list = new ArrayList<String>();
        if (obj == null || obj.equals("")) {
            return list;
        }
        String[] split = obj.split("[,，]");
        Collections.addAll(list, split);
        return list;
    }

    /**
     * 函数功能说明  把字符串转成list<Integer>
     * 函数名称  toIntegerList
     * 修改者名字 lp
     * 修改日期 2018-7-10
     * 修改内容
     *
     * @param obj
     * @return List<Integer>
     */
    public static List<Integer> toIntegerList(String obj) {
        List<Integer> list = new ArrayList<Integer>();
        if (obj == null || obj.equals("")) {
            return list;
        }
        String[] split = obj.split("[,，]");
        for (String s : split) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }

    /**
     * 函数功能说明  验证浏览器（防止导出文件名是中文时乱码）
     * 函数名称  browser
     * 修改者名字 lp
     * 修改日期 2018-7-25
     * 修改内容
     *
     * @param response
     * @param request
     * @param filename 导出文件名  void
     */
    public static void browser(HttpServletResponse response, HttpServletRequest request, String filename) {
        try {
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            response.setContentType("application/vnd.ms-excel");
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-disposition", "attachment; filename=" + new String(filename.getBytes(), "ISO8859-1"));
            } else {
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "utf-8"));
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * 函数功能说明  把集合转成String字符串
     * 函数名称  ListToString
     * 修改者名字 lp
     * 修改日期 2018-7-10
     * 修改内容
     *
     * @param list  集合
     * @param delim 用来分割的字符
     * @return String
     */
    public static String ListToString(Collection<?> list, String delim) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    /**
     * 校验手机号格式是否正确，手机号错误时返回false.<br/>
     *
     * @param mobiles 要检验的手机号
     * @return true or false .
     */
    public static boolean isMobileNO(String mobiles) {
        try {
            //Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2,3,5-9])|(147))\\d{8}$");
            Pattern p = Pattern.compile("^1[0-9]{10}$");
            Matcher m = p.matcher(mobiles);
            return m.matches();
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 获取文件后缀名（不带点）.
     *
     * @return 如："jpg" or "".
     */
    public static String getFileExt(String fileName) {
        if (isEmpty(fileName) || !fileName.contains(".")) {
            return "";
        } else {
            return fileName.substring(fileName.lastIndexOf(".") + 1); // 不带最后的点
        }
    }

    /**
     * 函数功能说明  汉字转换为汉语拼音首字母
     * 函数名称  getFirstSpell
     * 修改者名字 lp
     * 修改日期 2018-7-14
     * 修改内容
     *
     * @param chinese
     * @return String
     *//*
    public static String getFirstSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char curchar : arr) {
            if (curchar > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(curchar, defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(curchar);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }*/

    /**
     * string字符串 压缩
     */
    public static String compress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 使用默认缓冲区大小创建新的输出流
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        // 将 b.length 个字节写入此输出流
        gzip.write(str.getBytes());
        gzip.close();
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("ISO-8859-1");
    }

    /**
     * string字符串解压
     */
    public static String unCompress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
        ByteArrayInputStream in = new ByteArrayInputStream(str
                .getBytes("ISO-8859-1"));
        // 使用默认缓冲区大小创建新的输入流
        GZIPInputStream gzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n = 0;
        while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
            // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
            out.write(buffer, 0, n);
        }
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("utf-8");
    }

    /**
     * 函数功能说明 根据参数获取对应的MD5加密 函数名称 getMd5 修改者名字 lp 修改日期 2018-1-4 修改内容
     *
     * @param plainText
     * @return
     * @返回类型 String
     */
    public static String getMd5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return buf.toString();
            // 16位的加密
            // return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
