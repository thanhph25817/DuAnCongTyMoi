/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author linhlh2
 */
public class ListUtil {

	public static <T> List<T> copy(List<T> master) {
		if (master == null) {
			return null;
		}

		return new ArrayList<T>(master);
	}

	public static <T> void copy(List<T> master, List<T> copy) {
		if ((master == null) || (copy == null)) {
			return;
		}

		copy.clear();

		Iterator<T> itr = master.iterator();

		while (itr.hasNext()) {
			T obj = itr.next();

			copy.add(obj);
		}
	}

	public static <T> void distinct(List<T> list) {
		distinct(list, null);
	}

	public static <T> void distinct(List<T> list, Comparator<T> comparator) {
		if ((list == null) || (list.size() == 0)) {
			return;
		}

		Set<T> set = null;

		if (comparator == null) {
			set = new TreeSet<>();
		} else {
			set = new TreeSet<T>(comparator);
		}

		Iterator<T> itr = list.iterator();

		while (itr.hasNext()) {
			T obj = itr.next();

			if (set.contains(obj)) {
				itr.remove();
			} else {
				set.add(obj);
			}
		}
	}

	public static <T> List<T> fromArray(T[] array) {
		if ((array == null) || (array.length == 0)) {
			return new ArrayList<T>();
		}

		List<T> list = new ArrayList<T>(array.length);

		for (T element : array) {
			list.add(element);
		}

		return list;
	}

	public static <T> List<T> fromCollection(Collection<T> c) {
		if ((c != null) && (c instanceof List)) {
			return (List<T>) c;
		}

		if ((c == null) || (c.size() == 0)) {
			return new ArrayList<T>();
		}

		List<T> list = new ArrayList<T>(c.size());

		Iterator<T> itr = c.iterator();

		while (itr.hasNext()) {
			list.add(itr.next());
		}

		return list;
	}

	public static <T> List<T> fromEnumeration(Enumeration<T> enu) {
		List<T> list = new ArrayList<T>();

		while (enu.hasMoreElements()) {
			T obj = enu.nextElement();

			list.add(obj);
		}

		return list;
	}

	public static List<String> fromFile(File file) throws IOException {
		List<String> list = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader(file));

		String s = StringPool.BLANK;

		while ((s = br.readLine()) != null) {
			list.add(s);
		}

		br.close();

		return list;
	}

	public static List<String> fromFile(String fileName) throws IOException {
		return fromFile(new File(fileName));
	}

	public static List<String> fromString(String s) {
		return fromArray(StringUtil.split(s, StringPool.NEW_LINE));
	}

	public static <T> List<T> subList(List<T> list, int start, int end) {
		List<T> newList = new ArrayList<T>();

		int normalizedSize = list.size() - 1;

		if ((start < 0) || (start > normalizedSize) || (end < 0) || (start > end)) {

			return newList;
		}

		for (int i = start; i < end && i <= normalizedSize; i++) {
			newList.add(list.get(i));
		}

		return newList;
	}

	public static List<Boolean> toList(boolean[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Boolean> list = new ArrayList<>(array.length);

		for (boolean value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<Boolean> toList(Boolean[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Boolean> list = new ArrayList<>(array.length);

		for (Boolean value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<Double> toList(double[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Double> list = new ArrayList<>(array.length);

		for (double value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<Double> toList(Double[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Double> list = new ArrayList<>(array.length);

		for (Double value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<Float> toList(float[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Float> list = new ArrayList<>(array.length);

		for (float value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<Float> toList(Float[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Float> list = new ArrayList<>(array.length);

		for (Float value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<Integer> toList(int[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Integer> list = new ArrayList<>(array.length);

		for (int value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<Integer> toList(Integer[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Integer> list = new ArrayList<>(array.length);

		for (Integer value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<Long> toList(long[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Long> list = new ArrayList<>(array.length);

		for (long value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<Long> toList(Long[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Long> list = new ArrayList<>(array.length);

		for (Long value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<Short> toList(short[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Short> list = new ArrayList<>(array.length);

		for (short value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<Short> toList(Short[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<Short> list = new ArrayList<>(array.length);

		for (Short value : array) {
			list.add(value);
		}

		return list;
	}

	public static List<String> toList(String[] array) {
		if ((array == null) || (array.length == 0)) {
			return Collections.emptyList();
		}

		List<String> list = new ArrayList<>(array.length);

		for (String value : array) {
			list.add(value);
		}

		return list;
	}
}
