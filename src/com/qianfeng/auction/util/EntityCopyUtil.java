package com.qianfeng.auction.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 对象属性值的复制工具类
 * </p>
 * <ul>
 * <li>全复制</li>
 * <li>部分复制</li>
 * <li>部分不复制</li>
 * </ul>
 * 
 * @author: qinzhong
 * @createTime: 2016-8-16
 */
public class EntityCopyUtil {

	/**
	 * <p>
	 * 参数列表转换
	 * </p>
	 * <p>
	 * 将多个字符串转成List集合
	 * </p>
	 * 
	 * @param excludeFields
	 *            字符串参数
	 * @return List<String> 字符串集合
	 */
	public static List<String> paramList(String... excludeFields) {
		List<String> exParams = null;
		if (excludeFields != null && excludeFields.length > 0) {
			exParams = new ArrayList<String>();
			for (String excludeField : excludeFields) {
				exParams.add(excludeField);
			}
		}
		return exParams;
	}

	/**
	 * 相同属性值拷贝,忽略不存在以及类型不一致的属性
	 * 
	 * @param soure
	 *            源对象
	 * @param target
	 *            目标对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyPropertys(Object soure, Object target) {

		if (soure == null || target == null)
			return;

		// 访问源对象所有字段，并返回一个Field[]数组
		Field[] sfields = soure.getClass().getDeclaredFields();

		// 访问目标对象所有字段，并返回一个Field[]数组
		Field[] tfields = target.getClass().getDeclaredFields();

		// 返回此源对象的运行时类
		Class scls = soure.getClass();

		// 返回此目标对象的运行时类
		Class tcls = target.getClass();

		try {
			for (Field sfield : sfields) {

				// 对于final修饰的字段，不进行复制。（sfield.getModifiers()返回字段的修饰）
				if (Modifier.isFinal(sfield.getModifiers()))
					continue;

				// 源属性
				String sName = sfield.getName();

				// 源属性类型
				Class sType = sfield.getType();

				String sfieldName = sName.substring(0, 1).toUpperCase()
						+ sName.substring(1);

				Method sGetMethod = scls.getMethod("get" + sfieldName);
				// 源属性值
				Object value = sGetMethod.invoke(soure);

				for (Field tfield : tfields) {

					// 对于final修饰的字段，不进行复制。（sfield.getModifiers()返回字段的修饰）
					if (Modifier.isFinal(tfield.getModifiers()))
						continue;

					// 目标对象属性
					String tName = tfield.getName();
					// 目标对象属性类型
					Class tType = tfield.getType();

					if (tName.equals(sName)
							&& sType.toString().equals(tType.toString())) {
						Method dSetMethod = tcls.getMethod("set" + sfieldName,
								sType);
						// AccessibleTest类中的成员变量为private,故必须进行此操作
						sfield.setAccessible(true);
						tfield.setAccessible(true);
						// 目标对象属性赋值
						dSetMethod.invoke(target, value);
						break;
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 指定参数的相同属性值拷贝，其他的不拷贝
	 * 
	 * @param soure
	 *            源对象
	 * @param target
	 *            目标对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyPropertysByIncParams(Object soure, Object target,
			String... params) {
		if (soure == null || target == null) {
			return;
		}

		// 特定参数列表
		List<String> paramList = paramList(params);

		Field[] sfields = soure.getClass().getDeclaredFields();
		Field[] tfields = target.getClass().getDeclaredFields();
		Class scls = soure.getClass();
		Class tcls = target.getClass();
		try {
			for (Field sfield : sfields) {
				// 对于final修饰的字段，不进行复制。（sfield.getModifiers()返回字段的修饰）
				if (Modifier.isFinal(sfield.getModifiers()))
					continue;
				// 源：属性
				String sName = sfield.getName();
				// 源：属性类型
				Class sType = sfield.getType();
				String sfieldName = sName.substring(0, 1).toUpperCase()
						+ sName.substring(1);
				Method sGetMethod = scls.getMethod("get" + sfieldName);
				// 源：值
				Object value = sGetMethod.invoke(soure);

				for (Field tfield : tfields) {

					// 对于final修饰的字段，不进行复制。（sfield.getModifiers()返回字段的修饰）
					if (Modifier.isFinal(tfield.getModifiers()))
						continue;

					String tName = tfield.getName();
					Class tType = tfield.getType();

					if (tName.equals(sName)
							&& sType.toString().equals(tType.toString())) {
						if (paramList != null && paramList.contains(sName)) {
							Method dSetMethod = tcls.getMethod("set"
									+ sfieldName, sType);
							// AccessibleTest类中的成员变量为private,故必须进行此操作
							sfield.setAccessible(true);
							tfield.setAccessible(true);
							dSetMethod.invoke(target, value);
							continue;
						}
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 指定参数的相同属性值不拷贝，其他的相同属性值拷贝
	 * 
	 * @param soure
	 *            源对象
	 * @param target
	 *            目标对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyPropertysByExcParams(Object soure, Object target,
			String... params) {
		if (soure == null || target == null) {
			return;
		}

		// 特定参数列表
		List<String> paramList = paramList(params);

		Field[] sfields = soure.getClass().getDeclaredFields();
		Field[] tfields = target.getClass().getDeclaredFields();
		Class scls = soure.getClass();
		Class tcls = target.getClass();
		try {
			for (Field sfield : sfields) {

				// 对于final修饰的字段，不进行复制。（sfield.getModifiers()返回字段的修饰）
				if (Modifier.isFinal(sfield.getModifiers()))
					continue;

				// 源：属性
				String sName = sfield.getName();
				// 源：属性类型
				Class sType = sfield.getType();
				String sfieldName = sName.substring(0, 1).toUpperCase()
						+ sName.substring(1);
				Method sGetMethod = scls.getMethod("get" + sfieldName);
				// 源：值
				Object value = sGetMethod.invoke(soure);

				for (Field tfield : tfields) {

					// 对于final修饰的字段，不进行复制。（sfield.getModifiers()返回字段的修饰）
					if (Modifier.isFinal(tfield.getModifiers()))
						continue;

					String tName = tfield.getName();
					Class tType = tfield.getType();

					if (tName.equals(sName)
							&& sType.toString().equals(tType.toString())) {
						if (paramList != null && paramList.contains(sName)) {
							break;
						}
						Method dSetMethod = tcls.getMethod("set" + sfieldName,
								sType);
						// AccessibleTest类中的成员变量为private,故必须进行此操作
						sfield.setAccessible(true);
						tfield.setAccessible(true);
						dSetMethod.invoke(target, value);
						continue;
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数. 支持级联属性
	 */
	public static void setFieldValue(Object obj, String fieldName, Object value) {
		if (obj == null || fieldName == null)
			return;
		Object o = obj;
		int i = fieldName.lastIndexOf('.');
		if (i > -1) {
			o = getFieldValue(o, fieldName.substring(0, i));
			fieldName = fieldName.substring(i + 1);
		}

		Field field = getAccessibleField(o, fieldName);
		if (field == null)
			return;

		try {
			field.set(o, value);
		} catch (IllegalAccessException e) {
			// LOG.error("不可能抛出的异常:{}", e.getMessage());
		}
	}

	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数. 支持级联属性
	 */
	public static Object getFieldValue(Object obj, String fieldName) {
		if (obj == null || fieldName == null)
			return null;
		Object result = obj;
		while (true) {
			int i = fieldName.indexOf(".");
			String parentName = (i > -1 ? fieldName.substring(0, i) : fieldName);
			Field field = getAccessibleField(result, parentName);
			if (field == null)
				break;
			try {
				result = field.get(result);
			} catch (IllegalAccessException e) {
				break;
			}
			if (i > -1)
				fieldName = fieldName.substring(i + 1);
			else
				break;
		}
		return result;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问. 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Field getAccessibleField(final Object obj,
			final String fieldName) {
		// Assert.notNull(obj, "object不能为空");
		// Assert.hasText(fieldName, "fieldName");
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {// NOSONAR
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}
}