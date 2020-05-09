package com.saberking.utils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;

/**
 * 自定义 Bean 操作工具类
 * 
 * @author Saber污妖王
 */
public class BeanUtil {
	/**
	 * BeanCopier的缓存
	 */
	static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();
	/**
	 * BeanMap的缓存
	 */
	static final ConcurrentHashMap<String, BeanMap> BEAN_MAP_CACHE = new ConcurrentHashMap<>();

	/**
	 * BeanCopier的copy
	 * 
	 * @param source 源文件的
	 * @param target 目标文件
	 */
	public static void copyProperties(Object source, Object target) {
		String key = genKey(source.getClass(), target.getClass());
		BeanCopier beanCopier;
		if (BEAN_COPIER_CACHE.containsKey(key)) {
			beanCopier = BEAN_COPIER_CACHE.get(key);
		} else {
			beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
			BEAN_COPIER_CACHE.put(key, beanCopier);
		}
		beanCopier.copy(source, target, null);
	}

	/**
	 * 生成key
	 * 
	 * @param srcClazz 源文件的class
	 * @param tgtClazz 目标文件的class
	 * @return string
	 */
	private static String genKey(Class<?> srcClazz, Class<?> tgtClazz) {
		return srcClazz.getName() + tgtClazz.getName();
	}

	/**
	 * Map<String, Object> 转为对象，字段格式要一致
	 */
	public static <T> T mapObjTrasnToObject(Map<String, Object> map, Class<T> clazz) throws IOException {
		byte[] jsonBytes = JSON.toJSONBytes(map, SerializerFeature.WriteNullStringAsEmpty);
		T t = JSON.parseObject(jsonBytes, clazz, Feature.IgnoreNotMatch);
		return t;
	}

	/**
	 * 使用 CGLIB 的 BeanMap 将 Map<String, String[]> 转为 Bean 对象
	 * @param <T>			返回目标对象类型
	 * @param map			源Map
	 * @param target source 目标对象
	 * @return
	 */
	public static <T> T map2BeanByBeanMap(Map<String, String[]> map, T target) {
		String key = genKey(map.getClass(), target.getClass());
		BeanMap beanMap;
		if (BEAN_MAP_CACHE.containsKey(key)) {
			beanMap = BEAN_MAP_CACHE.get(key);
		} else {
			beanMap = BeanMap.create(target);
			BEAN_MAP_CACHE.put(key, beanMap);
		}
		beanMap.putAll(map);
		return target;
	}
}