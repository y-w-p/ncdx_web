package com.qianfeng.auction.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * ��������ֵ�ĸ��ƹ�����
 * </p>
 * <ul>
 * <li>ȫ����</li>
 * <li>���ָ���</li>
 * <li>���ֲ�����</li>
 * </ul>
 * 
 * @author: qinzhong
 * @createTime: 2016-8-16
 */
public class EntityCopyUtil {

	/**
	 * <p>
	 * �����б�ת��
	 * </p>
	 * <p>
	 * ������ַ���ת��List����
	 * </p>
	 * 
	 * @param excludeFields
	 *            �ַ�������
	 * @return List<String> �ַ�������
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
	 * ��ͬ����ֵ����,���Բ������Լ����Ͳ�һ�µ�����
	 * 
	 * @param soure
	 *            Դ����
	 * @param target
	 *            Ŀ�����
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyPropertys(Object soure, Object target) {

		if (soure == null || target == null)
			return;

		// ����Դ���������ֶΣ�������һ��Field[]����
		Field[] sfields = soure.getClass().getDeclaredFields();

		// ����Ŀ����������ֶΣ�������һ��Field[]����
		Field[] tfields = target.getClass().getDeclaredFields();

		// ���ش�Դ���������ʱ��
		Class scls = soure.getClass();

		// ���ش�Ŀ����������ʱ��
		Class tcls = target.getClass();

		try {
			for (Field sfield : sfields) {

				// ����final���ε��ֶΣ������и��ơ���sfield.getModifiers()�����ֶε����Σ�
				if (Modifier.isFinal(sfield.getModifiers()))
					continue;

				// Դ����
				String sName = sfield.getName();

				// Դ��������
				Class sType = sfield.getType();

				String sfieldName = sName.substring(0, 1).toUpperCase()
						+ sName.substring(1);

				Method sGetMethod = scls.getMethod("get" + sfieldName);
				// Դ����ֵ
				Object value = sGetMethod.invoke(soure);

				for (Field tfield : tfields) {

					// ����final���ε��ֶΣ������и��ơ���sfield.getModifiers()�����ֶε����Σ�
					if (Modifier.isFinal(tfield.getModifiers()))
						continue;

					// Ŀ���������
					String tName = tfield.getName();
					// Ŀ�������������
					Class tType = tfield.getType();

					if (tName.equals(sName)
							&& sType.toString().equals(tType.toString())) {
						Method dSetMethod = tcls.getMethod("set" + sfieldName,
								sType);
						// AccessibleTest���еĳ�Ա����Ϊprivate,�ʱ�����д˲���
						sfield.setAccessible(true);
						tfield.setAccessible(true);
						// Ŀ��������Ը�ֵ
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
	 * ָ����������ͬ����ֵ�����������Ĳ�����
	 * 
	 * @param soure
	 *            Դ����
	 * @param target
	 *            Ŀ�����
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyPropertysByIncParams(Object soure, Object target,
			String... params) {
		if (soure == null || target == null) {
			return;
		}

		// �ض������б�
		List<String> paramList = paramList(params);

		Field[] sfields = soure.getClass().getDeclaredFields();
		Field[] tfields = target.getClass().getDeclaredFields();
		Class scls = soure.getClass();
		Class tcls = target.getClass();
		try {
			for (Field sfield : sfields) {
				// ����final���ε��ֶΣ������и��ơ���sfield.getModifiers()�����ֶε����Σ�
				if (Modifier.isFinal(sfield.getModifiers()))
					continue;
				// Դ������
				String sName = sfield.getName();
				// Դ����������
				Class sType = sfield.getType();
				String sfieldName = sName.substring(0, 1).toUpperCase()
						+ sName.substring(1);
				Method sGetMethod = scls.getMethod("get" + sfieldName);
				// Դ��ֵ
				Object value = sGetMethod.invoke(soure);

				for (Field tfield : tfields) {

					// ����final���ε��ֶΣ������и��ơ���sfield.getModifiers()�����ֶε����Σ�
					if (Modifier.isFinal(tfield.getModifiers()))
						continue;

					String tName = tfield.getName();
					Class tType = tfield.getType();

					if (tName.equals(sName)
							&& sType.toString().equals(tType.toString())) {
						if (paramList != null && paramList.contains(sName)) {
							Method dSetMethod = tcls.getMethod("set"
									+ sfieldName, sType);
							// AccessibleTest���еĳ�Ա����Ϊprivate,�ʱ�����д˲���
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
	 * ָ����������ͬ����ֵ����������������ͬ����ֵ����
	 * 
	 * @param soure
	 *            Դ����
	 * @param target
	 *            Ŀ�����
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyPropertysByExcParams(Object soure, Object target,
			String... params) {
		if (soure == null || target == null) {
			return;
		}

		// �ض������б�
		List<String> paramList = paramList(params);

		Field[] sfields = soure.getClass().getDeclaredFields();
		Field[] tfields = target.getClass().getDeclaredFields();
		Class scls = soure.getClass();
		Class tcls = target.getClass();
		try {
			for (Field sfield : sfields) {

				// ����final���ε��ֶΣ������и��ơ���sfield.getModifiers()�����ֶε����Σ�
				if (Modifier.isFinal(sfield.getModifiers()))
					continue;

				// Դ������
				String sName = sfield.getName();
				// Դ����������
				Class sType = sfield.getType();
				String sfieldName = sName.substring(0, 1).toUpperCase()
						+ sName.substring(1);
				Method sGetMethod = scls.getMethod("get" + sfieldName);
				// Դ��ֵ
				Object value = sGetMethod.invoke(soure);

				for (Field tfield : tfields) {

					// ����final���ε��ֶΣ������и��ơ���sfield.getModifiers()�����ֶε����Σ�
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
						// AccessibleTest���еĳ�Ա����Ϊprivate,�ʱ�����д˲���
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
	 * ֱ�����ö�������ֵ, ����private/protected���η�, ������setter����. ֧�ּ�������
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
			// LOG.error("�������׳����쳣:{}", e.getMessage());
		}
	}

	/**
	 * ֱ�Ӷ�ȡ��������ֵ, ����private/protected���η�, ������getter����. ֧�ּ�������
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
	 * ѭ������ת��, ��ȡ�����DeclaredField, ��ǿ������Ϊ�ɷ���. ������ת�͵�Object���޷��ҵ�, ����null.
	 */
	public static Field getAccessibleField(final Object obj,
			final String fieldName) {
		// Assert.notNull(obj, "object����Ϊ��");
		// Assert.hasText(fieldName, "fieldName");
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {// NOSONAR
				// Field���ڵ�ǰ�ඨ��,��������ת��
			}
		}
		return null;
	}
}