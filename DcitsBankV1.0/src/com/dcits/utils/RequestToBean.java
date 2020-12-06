package com.dcits.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 从请求中获取参数封装到JavaBean实例中
 * @author saber-kings
 *
 */
public class RequestToBean {

	/**
	 * ConvertUtilsBean convertUtils = new ConvertUtilsBean();
	 * DateConverter dateConverter = new DateConverter();
	 * convertUtils.register(dateConverter,Date.class);
	 */

	/**
	 * @param <T>
	 * @param newSource 现将要设置新值的对象
	 * @param source    源数据对象
	 */
	public static <T> void beanConvert(T newSource, T source) {
		try {
			BeanUtils.copyProperties(newSource, source);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request 请求对象
	 * @param obj     要设置Bean的类型,传入试为: Bean.class
	 * @return
	 * @throws UnsupportedEncodingException 编码不支持异常
	 */
	public static <T> T getBeanToRequest(HttpServletRequest request, Class<T> clazz)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		T beanObj = getObjectByClass(clazz);
		try {
			BeanUtils.populate(beanObj, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return beanObj;
	}

	private static <T> T getObjectByClass(Class<T> clazz) {
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return t;
	}

	/*
	 * @param request
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
		int contentLength = request.getContentLength();
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {
			int readlen = request.getInputStream().read(buffer, i, contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return buffer;
	}

	/**
	 * 描述:获取 post 请求内容
	 * 
	 * <pre>
	 * 举例
	 * </pre>
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getRequestPostStr(HttpServletRequest request) throws IOException {
		byte buffer[] = getRequestPostBytes(request);
		String charEncoding = request.getCharacterEncoding();
		if (charEncoding == null) {
			charEncoding = "UTF-8";
		}
		return new String(buffer, charEncoding);
	}
}