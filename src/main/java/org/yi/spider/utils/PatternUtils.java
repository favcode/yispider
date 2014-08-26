package org.yi.spider.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yi.spider.model.RuleModel;

public class PatternUtils {
	
	/**
	 * 
	 * <p>采集目录</p>
	 * @param content	目录页源码
	 * @param rule		目录采集规则
	 * @param replace	是否对解析出的目录进行字符串替换, 默认为true
	 * @return			解析后的目录集合
	 */
	public static List<String> getValues(String content, RuleModel rule, boolean replace) {
		if(rule  == null || rule.getPattern()==null || rule.getPattern().isEmpty()) {
    		return null;
    	}
        Pattern p = Pattern.compile(rule.getPattern());
        Matcher m = p.matcher(content);
        List<String> valueList = new ArrayList<String>();
        while (m.find()) {
        	if(replace) {
        		valueList.add(replaceDestStr(rule, m));
        	} else {
        		valueList.add(m.group(1));
        	}
        }
        return valueList;
    }
	
	/**
	 * 
	 * <p>采集目录</p>
	 * @param content	目录页源码
	 * @param rule		目录采集规则
	 * @return			解析后的目录集合, 不进行字符串替换
	 */
	public static List<String> getValues(String content, String pattern) {
		List<String> valueList = new ArrayList<String>();
		if(StringUtils.isNotBlank(pattern)) {
	        Pattern p = Pattern.compile(pattern);
	        Matcher m = p.matcher(content);
	        try {
				while (m.find()) {
					for (int i = 1 ; i <= m.groupCount(); i++) {
		                valueList.add(m.group(i));
		            }
				}
			} catch (Exception e) {
				valueList.add(String.valueOf(m.matches()));
			}
		}
        return valueList;
    }

	/**
	 * 
	 * <p>采集目录</p>
	 * @param content	目录页源码
	 * @param rule		目录采集规则
	 * @return			解析后的目录集合
	 */
	public static List<String> getValues(String content, RuleModel rule) {
		return getValues(content, rule, true);
	}

	
	/**
	 * 
	 * <p>采集章节内容</p>
	 * @param content	章节内容源码
	 * @param rule		章节内容解析规则
	 * @param replace	是否对解析出的章节内容进行字符串替换， 默认为true
	 * @return
	 */
	public static String getValue(String content, RuleModel rule, boolean replace) {
		
		String result = null;
		
    	if(rule != null && StringUtils.isNotEmpty(rule.getPattern())) {
    		// 对应JDK的BUG，把常用的((.|\n)+?)换成([\s\S]*?)
            String pattern = rule.getPattern().replace("(.|\\n)", "[\\s\\S]");
            Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(content);
            if (m.find()) {
            	if(replace) {
            		result = replaceDestStr(rule, m);
            	} else {
            		result = m.group(1);
            	}
            }
    	}
        
        return result;
    }
	
	/**
	 * 
	 * <p>根据正则表达式解析传入的内容</p>
	 * @param content	需要解析的字符串
	 * @param pattern	解析用的正则表达式
	 * @return
	 */
	public static String getValue(String content, String pattern) {
		
		String result = null;
		
    	if(StringUtils.isNotEmpty(pattern)) {
    		// 对应JDK的BUG，把常用的((.|\n)+?)换成([\s\S]*?)
            pattern = pattern.replace("(.|\\n)", "[\\s\\S]");
            Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(content);
            try {
				if (m.find()) {
					result = m.group(1);
				}
			} catch (Exception e) {
				result = String.valueOf(m.matches());
			}
    	}
        
        return result;
    }
	
	/**
	 * 
	 * <p>采集章节内容</p>
	 * @param content	章节内容源码
	 * @param rule		章节内容解析规则
	 * @return
	 */
	public static String getValue(String content, RuleModel rule) {
		
		String result = null;
		
    	if(rule != null && StringUtils.isNotEmpty(rule.getPattern())) {
    		// 对应JDK的BUG，把常用的((.|\n)+?)换成([\s\S]+?)
            String pattern = rule.getPattern().replace("(.|\\n)", "[\\s\\S]");
            Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(content);
            if (m.find()) {
        		result = replaceDestStr(rule, m);
            }
    	}
        
        return StringUtils.isNotBlank(result) ? result.toLowerCase() : result;
    }
	
	/**
	 * 
	 * <p>对解析出的内容进行字符串替换， 私有方法， 仅在此类中用</p>
	 * @param rule
	 * @param m
	 * @return
	 */
	private static String replaceDestStr(RuleModel rule, Matcher m) {
		String result = null;
		try {
		    result = m.group(1);
		    String filterPattern = rule.getFilterPattern();
		    if(filterPattern!=null && !filterPattern.isEmpty()) {
		    	String[] filter = filterPattern.split("\\n");
		    	for(String f:filter){
		    		//关关采集规则中使用♂表示替换， 如：aaa♂bbb即使用bbb替换aaa
		    		if(f.indexOf("♂")<0 || f.indexOf("♂")==(f.length())) {
		    			result = result.toLowerCase().replaceAll(f, "");
		    		} else {
		    			String[] ff = f.split("♂");
		    			if(ff.length==1 || ff[1]==null || ff[1].isEmpty()){
		    				result = result.toLowerCase().replaceAll(ff[0], "");
		    			} else {
		    				result = result.toLowerCase().replaceAll(ff[0], ff[1]);
		    			}
		    		}
		    	}
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return result;
	}
	
}
