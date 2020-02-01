package com.ch.commutils;

import java.math.BigDecimal;

public class FloatCalculator {

	public static float add(float a, float b) {
		BigDecimal bigdecimal1 = new BigDecimal(Float.toString(a));
		BigDecimal bigdecimal2 = new BigDecimal(Float.toString(b));
		float f = bigdecimal1.add(bigdecimal2).floatValue();
		return f;
	}
	/**
	 * <p>
	 * subtract
	 * </p>
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static float subtract(float a, float b) {
		BigDecimal bigdecimal1 = new BigDecimal(Float.toString(a));
		BigDecimal bigdecimal2 = new BigDecimal(Float.toString(b));
		float f = bigdecimal1.subtract(bigdecimal2).floatValue();
		return f;
	}

	/**
	 * <p>
	 * multiply
	 * </p>
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static float multiply(float a, float b) {
		BigDecimal bigdecimal1 = new BigDecimal(Float.toString(a));
		BigDecimal bigdecimal2 = new BigDecimal(Float.toString(b));
		float f = bigdecimal1.multiply(bigdecimal2).floatValue();
		return f;
	}

	/**
	 * <p>
	 * divide
	 * </p>
	 * <p>
	 * 当不整除，出现无限循环小数时，向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向上舍入, 1.55保留一位小数结果为1.6
	 * </p>
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static float divide(float a, float b) {
		return divide(a, b, 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * <p>
	 * divide
	 * </p>
	 * 
	 * @param a
	 * @param b
	 * @param scale
	 * @param roundingMode
	 * @return
	 */
	public static float divide(float a, float b, int scale, int roundingMode) {
		/*
		 * 通过BigDecimal的divide方法进行除法时就会抛异常的，异常如下： java.lang.ArithmeticException:
		 * Non-terminating decimal expansion; no exact representable decimal
		 * result. at java.math.BigDecimal.divide(Unknown Source)
		 * 解决之道：就是给divide设置精确的小数点divide(xxxxx,2, BigDecimal.ROUND_HALF_EVEN)
		 * BigDecimal.ROUND_HALF_UP : 向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向上舍入,
		 * 1.55保留一位小数结果为1.6
		 */
		BigDecimal bigdecimal1 = new BigDecimal(Float.toString(a));
		BigDecimal bigdecimal2 = new BigDecimal(Float.toString(b));
		float f = bigdecimal1.divide(bigdecimal2, scale, roundingMode).floatValue();
		return f;
	}
}
