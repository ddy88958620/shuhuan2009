/**
 * 
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * <br>
 * @Company: iqiyi.com
 * @Created on 下午3:18:05
 * @author zhiyuan
 */
package com.happinesstree.oauth2.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 
 *
 */
public class RequestUtils {
	  /**
     * 获取客户端IP地址，支持proxy
     * 
     * @param req
     *            HttpServletRequest
     * @return IP地址
     */
    public static String getRemoteAddr(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip)) {
            String[] ips = StringUtils.split(ip, ',');
            if (ips != null) {
                for (String tmpip : ips) {
                    if (StringUtils.isBlank(tmpip))
                        continue;
                    tmpip = tmpip.trim();
                    if (isIPAddr(tmpip) && !tmpip.startsWith("10.") && !tmpip.startsWith("192.168.") && !"127.0.0.1".equals(tmpip)) {
                        return tmpip.trim();
                    }
                }
            }
        }
        ip = req.getHeader("x-real-ip");
        if (isIPAddr(ip))
            return ip;
        ip = req.getRemoteAddr();
        if (ip.indexOf('.') == -1)
            ip = "127.0.0.1";
        return ip;
    }

    public static String getRemotePrivateAddr(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip)) {
            String[] ips = StringUtils.split(ip, ',');
            if (ips != null) {
                for (String tmpip : ips) {
                    if (StringUtils.isBlank(tmpip))
                        continue;
                    tmpip = tmpip.trim();
                    if (isIPAddr(tmpip)) {
                        return tmpip.trim();
                    }
                }
            }
        }
        ip = req.getHeader("x-real-ip");
        if (isIPAddr(ip))
            return ip;
        ip = req.getRemoteAddr();
        if (ip.indexOf('.') == -1)
            ip = "127.0.0.1";
        return ip;
    }

    /**
     * 判断是否为搜索引擎
     * 
     * @param req
     *            HttpServletRequest
     * @return ture:robot, false,is not robot
     */
    public static boolean isRobot(HttpServletRequest req) {
        String ua = req.getHeader("user-agent");
        if (StringUtils.isBlank(ua))
            return false;
        return (ua != null && (ua.indexOf("Baiduspider") != -1 || ua.indexOf("Googlebot") != -1 || ua.indexOf("sogou") != -1 || ua.indexOf("sina") != -1 || ua.indexOf("iaskspider") != -1 || ua.indexOf("ia_archiver") != -1 || ua.indexOf("Sosospider") != -1 || ua.indexOf("YoudaoBot") != -1 || ua.indexOf("yahoo") != -1 || ua.indexOf("yodao") != -1 || ua.indexOf("MSNBot") != -1 || ua.indexOf("spider") != -1 || ua.indexOf("Twiceler") != -1 || ua.indexOf("Sosoimagespider") != -1 || ua.indexOf("naver.com/robots") != -1 || ua.indexOf("Nutch") != -1 || ua.indexOf("spider") != -1));
    }

    /**
     * 获取COOKIE
     * 
     * @param request
     *            HttpServletRequest
     * @param name
     *            Cookie的名称
     * @return Cookie or null
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie ck : cookies) {
            if (StringUtils.equalsIgnoreCase(name, ck.getName()))
                return ck;
        }
        return null;
    }

    /**
     * 获取COOKIE的值
     * 
     * @param request
     *            HttpServletRequest
     * @param name
     *            Cookie的名称
     * @return CookieValue or null
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie ck : cookies) {
            if (StringUtils.equalsIgnoreCase(name, ck.getName()))
                return ck.getValue();
        }
        return null;
    }

    /**
     * 设置COOKIE
     * 
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @param name
     *            Cookie的名称
     * @param value
     *            Cookie的值
     * @param maxAge
     *            有效时长
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
        setCookie(request, response, name, value, maxAge, true);
    }

    /**
     * 设置COOKIE
     * 
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @param name
     *            Cookie的名称
     * @param value
     *            Cookie的值
     * @param maxAge
     *            有效时长
     * @param all_sub_domain
     *            域名
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge, boolean all_sub_domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        if (all_sub_domain) {
            String serverName = request.getServerName();
            String domain = getDomainOfServerName(serverName);
            if (domain != null && domain.indexOf('.') != -1) {
                cookie.setDomain('.' + domain);
            }
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     * 
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @param name
     *            Cookie的名称
     * @param all_sub_domain
     *            域名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name, boolean all_sub_domain) {
        setCookie(request, response, name, "", 0, all_sub_domain);
    }

    /**
     * 获取用户访问URL中的根域名 例如: www.dlog.cn -> dlog.cn
     * 
     * @param host
     *            域名
     * @return 根域名
     */
    public static String getDomainOfServerName(String host) {
        if (isIPAddr(host))
            return null;
        String[] names = StringUtils.split(host, '.');
        int len = names.length;
        if (len == 1)
            return null;
        if (len == 3) {
            return makeup(names[len - 2], names[len - 1]);
        }
        if (len > 3) {
            String dp = names[len - 2];
            if (dp.equalsIgnoreCase("com") || dp.equalsIgnoreCase("gov") || dp.equalsIgnoreCase("net") || dp.equalsIgnoreCase("edu") || dp.equalsIgnoreCase("org"))
                return makeup(names[len - 3], names[len - 2], names[len - 1]);
            else
                return makeup(names[len - 2], names[len - 1]);
        }
        return host;
    }

    /**
     * 判断字符串是否是一个IP地址
     * 
     * @param addr
     *            字符串
     * @return true:IP地址，false：非IP地址
     */
    public static boolean isIPAddr(String addr) {
        if (StringUtils.isEmpty(addr))
            return false;
        String[] ips = StringUtils.split(addr, '.');
        if (ips.length != 4)
            return false;
        try {
            int ipa = Integer.parseInt(ips[0]);
            int ipb = Integer.parseInt(ips[1]);
            int ipc = Integer.parseInt(ips[2]);
            int ipd = Integer.parseInt(ips[3]);
            return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0 && ipc <= 255 && ipd >= 0 && ipd <= 255;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 把字符串数组串起来，中间加“.”，用于域名的串接
     * 
     * @param ps
     *            字符串数组
     * @return 字符串
     */
    private static String makeup(String... ps) {
        StringBuilder s = new StringBuilder();
        for (int idx = 0; idx < ps.length; idx++) {
            if (idx > 0)
                s.append('.');
            s.append(ps[idx]);
        }
        return s.toString();
    }

    /**
     * 获取HTTP端口
     * 
     * @param req
     *            HttpServletRequest
     * @return 端口数值
     * @throws java.net.MalformedURLException
     */
    public static int getHttpPort(HttpServletRequest req) {
        try {
            return new URL(req.getRequestURL().toString()).getPort();
        } catch (MalformedURLException excp) {
            return 80;
        }
    }

    /**
     * 获取浏览器提交的整形参数
     * 
     * @param req
     *            HttpServletRequest
     * @param param
     *            参数名
     * @param defaultValue
     *            默认值
     * @return int
     */
    public static int getParam(HttpServletRequest req, String param, int defaultValue) {
        return NumberUtils.toInt(req.getParameter(param), defaultValue);
    }

    /**
     * 获取浏览器提交的整形参数
     * 
     * @param req
     *            HttpServletRequest
     * @param param
     *            参数名
     * @param defaultValue
     *            默认值
     * @return long
     */
    public static long getParam(HttpServletRequest req, String param, long defaultValue) {
        return NumberUtils.toLong(req.getParameter(param), defaultValue);
    }
    
    public static long getQipaVideoDuration(HttpServletRequest req, String param, long defaultValue){
    	
    	try{
    		String value=req.getParameter(param);
    		if(value!=null){
    			Double rawValue=Double.parseDouble(value);
        		return rawValue.longValue();
        	}
    	}catch(Exception e){
    	}
    	
    	return defaultValue;
    	
    }
    
    /**
     * 获取浏览器提交的整形参数
     * 
     * @param req
     *            HttpServletRequest
     * @param param
     *            参数名
     * @param defaultValue
     *            默认值
     * @return long
     */
    public static Double getDoubleParam(HttpServletRequest req, String param, Double defaultValue) {
    	String value = RequestUtils.getParam(req, param, null);
    	if(value!=null){
    		return NumberUtils.toDouble(value);
    	}
    	
    	return defaultValue;
    	
    }

    /**
     * 对getParameterValues的封装，转换成long数组
     * 
     * @param req
     *            HttpServletRequest
     * @param name
     *            参数名
     * @return long数组
     */
    public static long[] getParamValues(HttpServletRequest req, String name) {
        String[] values = req.getParameterValues(name);
        if (values == null)
            return null;
        return (long[]) ConvertUtils.convert(values, long.class);
    }

    /**
     * 获取浏览器提交的字符串参数
     * 
     * @param req
     *            HttpServletRequest
     * @param param
     *            参数名
     * @param defaultValue
     *            默认值
     * @return String
     */
    public static String getParam(HttpServletRequest req, String param, String defaultValue) {
        String value = req.getParameter(param);
        return (StringUtils.isEmpty(value)) ? defaultValue : value;
    }

    /**
     * 格式化中文字符，防止出现乱码
     * 
     * @param str
     * @return
     */
    private static String codeToString(String str) {
        String strString = str;
        try {
            byte tempB[] = strString.getBytes("ISO-8859-1");
            strString = new String(tempB);
            return strString;
        } catch (Exception e) {
            return strString;
        }
    }

    /**
     * 获取完整的Url
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public static String getBackUrl(HttpServletRequest request) throws Exception {
        String strBackUrl = "";
        try {
            strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + request.getServletPath() + "?" + codeToString(request.getQueryString());
            strBackUrl = java.net.URLEncoder.encode(strBackUrl, "gbk");
        } catch (Exception e) {
            throw e;
        }
        return strBackUrl;
    }

    /**
     * 获得POST 过来参数设置到新的params中
     * 
     * @param requestParams
     *            POST 过来参数Map
     * @return 新的Map
     */
    public static Map<String, String> genMapByRequestParas(Map requestParams) {

        Map<String, String> params = new HashMap<String, String>();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        return params;
    }
    
    /**
     * 验证定长字符有效性
     * @param args
     */
    public static void main(String[] args){
    	String value="9.613";
    	Double raw=Double.parseDouble(value);
    	System.out.println(raw.longValue());
    }
}
