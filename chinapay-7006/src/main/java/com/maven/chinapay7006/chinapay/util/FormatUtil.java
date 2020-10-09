package com.maven.chinapay7006.chinapay.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
/**
 * The Class FormatUtil.
 */
public class FormatUtil {


	/**
	 * The dec format.
	 */
	private static DecimalFormat decFormat = new DecimalFormat("#.##");

	/**
	 * 字符串转换到时间格式.
	 *
	 * @param dateStr 需要转换的字符串
	 * @param formatStr 需要格式的目标字符串 举例 yyyy-MM-dd,yyyyMMddhhmmss
	 * @return Date 返回转换后的时间
	 * @throws Exception the exception
	 */
	public static Date strToDate(String dateStr, String formatStr)
			throws Exception {
		if (dateStr == null)
			return null;
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {

			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			throw new Exception("");
		}
		return date;
	}

	/**
	 * 字符串转换到时间格式 默认yyyyMMddhhmmss.
	 *
	 * @param dateStr the date str
	 * @return Date 返回转换后的时间
	 * @throws Exception the exception
	 */
	public static Date strToDate(String dateStr) throws Exception {
		if (dateStr == null)
			return null;
		if (dateStr.length() == 8) {
			dateStr = dateStr + "000000";
		}
		return strToDate(dateStr, "yyyyMMddhhmmss");
	}

	/**
	 * 时间格式转到字符串 默认yyyyMMddhhmmss.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String dateToStr(Date date) {

		if (date == null)
			return null;
		return dateToStr(date, null);
	}

	/**
	 * 时间格式转到字符串 默认yyyyMMddhhmmss.
	 *
	 * @param date the date
	 * @param format the format
	 * @return the string
	 */
	public static String dateToStr(Date date, String format) {

		if (date == null)
			return null;
		if (format == null)
			format = "yyyyMMddhhmmss";
		DateFormat sdf = new SimpleDateFormat(format);
		String strDate = null;
		try {
			strDate = sdf.format(date);
		} catch (Exception e) {

		}
		return strDate;
	}

	/**
	 * Conver date str.
	 *
	 * @param dateStr the date str
	 * @return the string
	 */
	public static String converDateStr(String dateStr) {

		if (dateStr.length() == 8) {
			String returnStr = "";
			returnStr = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6)
					+ "-" + dateStr.substring(6);
			return returnStr;
		} else if (dateStr.length() == 14) {
			String returnStr = "";
			returnStr = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6)
					+ "-" + dateStr.substring(6, 8);
			return returnStr;
		}
		return "";
	}

	/**
	 * Convert date.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String convertDate(String date) {
		String returnDate = date;
		if (date != null && date.trim().length() > 0) {
			if (date.length() == 8) {
				returnDate = date.substring(0, 4) + "-" + date.substring(4, 6)
						+ "-" + date.substring(6, 8);
			}
		}
		return returnDate;
	}

	/**
	 * *************************************************************************
	 * 云南银商代交易处理码识别 定长2位，为兼容原有报文格式，如内容为空格，则默认为单笔代扣交易.
	 *
	 * @param trxCode the trx code
	 * @return the string
	 */
	public static String ynysTrxCode(String trxCode) {
		if (trxCode == null)
			return null;
		else if (trxCode.equals("  ")) {
			return "01";
		} else {
			return trxCode;
		}
	}

	/**
	 * *************************************************************************
	 * 国寿余额查询接口 开始查询时间，结束查询时间用到日期格式yyyyMMdd转为yyyy-MM-dd hh:mm:ss.
	 *
	 * @param strDate the str date
	 * @return the string
	 */
	public static String strDateToDate(String strDate) {
		if (strDate == null || strDate.length() < 8)
			return null;
		strDate = strDate.substring(0, 4) + "-" + strDate.substring(4, 6) + "-"
				+ strDate.substring(6, 8) + " 00:00:00";
		return strDate;
	}

	/**
	 * *************************************************************************
	 * 如果字符串为空返回"".
	 *
	 * @param str the str
	 * @return the string
	 */
	public String strClearNull(String str) {
		if (str == null)
			return "";
		else {
			return str;
		}
	}

	/**
	 * *************************************************************************
	 * 日期加天数**.
	 *
	 * @param date the date
	 * @param day 为旧数即是减天数
	 * @return the date
	 */
	public static Date dateAddDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	/**
	 * *************************************************************************
	 * 时间与日期组合成新的DATE.
	 *
	 * @param strTime hh:mm:ss
	 * @param date the date
	 * @return the date
	 */
	public static Date assembTime(String strTime, Date date) {
		String[] ps = strTime.split(":");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(ps[0]));
		calendar.set(Calendar.MINUTE, Integer.valueOf(ps[1]));
		calendar.set(Calendar.SECOND, Integer.valueOf(ps[2]));
		return calendar.getTime();

	}

	/**
	 * 补充空格到指定字节长度.
	 *
	 * @param text the text
	 * @param length the length
	 * @param encode the encode
	 * @return the string
	 */
	public static String addBlank(String text, int length, String encode) {
		// 空格
		byte b = 0x20;
		byte[] bytes = new byte[length];
		Arrays.fill(bytes, b);
		String ret = null;
		try {
			if (text != null) {
				System.arraycopy(text.getBytes(encode), 0, bytes, 0, text
						.getBytes().length > length ? length
						: text.getBytes().length);
			}
			ret = new String(bytes, encode);
		} catch (UnsupportedEncodingException e) {
		}
		return ret;
	}

	/**
	 * *************************************************************************
	 * 向左补0到指定长度.
	 *
	 * @param str the str
	 * @param len the len
	 * @return the string
	 */
	public static String addLeftZ(String str, int len) {
		byte b = 0x30;
		byte[] bytes = new byte[len];
		Arrays.fill(bytes, b);
		if (str != null) {
			System.arraycopy(str.getBytes(), 0, bytes, len
					- str.getBytes().length, str.getBytes().length > len ? len
					: str.getBytes().length);
		}
		return new String(bytes);
	}

	/**
	 * *************************************************************************
	 * 去除换行.
	 *
	 * @param source the source
	 * @return the byte[]
	 */
	public static byte[] byteCutLn(byte[] source) {
		if (source == null)
			return null;
		ByteBuffer buffer = ByteBuffer.allocate(source.length);
		for (int i = 0; i < source.length; i++) {
			if (source[i] != 0xA || source[i] != 0xD) {
				buffer.put(source[i]);
			}
		}
		buffer.flip();
		int len = buffer.limit();
		byte[] ret = new byte[len];
		buffer.get(ret);
		return ret;
	}

	/**
	 * *************************************************************************
	 * 以元为单位金额转换到以分为单位的金额字符串，且到指定长度，不足左补0.
	 *
	 * @param source the source
	 * @param len the len
	 * @return the string
	 */
	public static String moneyYanToFen(String source, int len) {
		String value =  moneyYanToFen(source);
		value = StringUtils.leftPad(value, len, '0');
		return value;
	}

	/**
	 * *************************************************************************
	 * 以元为单位金额转换到以分为单位的金额字符串，且到指定长度，不足左补0.
	 *
	 * @param source the source
	 * @return the string
	 */
	public static String moneyYanToFen(String source) {
		double val = Double.valueOf(source) * 100;
		String value = decFormat.format(val);
		if (value.indexOf(".") > 0) {
			value = value.substring(0, value.indexOf("."));
		}
		return String.valueOf(value);
	}

	/**
	 * *************************************************************************
	 * 以分为单位金额字符串转为以元为单位字符串 示例2345转为23.45 .
	 *
	 * @param strMoney the str money
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String moneyFenToYan(String strMoney) throws Exception {
		if (strMoney == null)
			return "0";
		double d = Double.valueOf(strMoney);
		d = d / 100;
		return String.valueOf(decFormat.format(d));

	}

	/**
	 * *************************************************************************
	 * 字节转字符串，GBK编码.
	 *
	 * @param b the b
	 * @param begin the begin
	 * @param end the end
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public static String assembByteToStr(byte[] b, int begin, int end)
			throws UnsupportedEncodingException {

		if (b == null)
			return "";
		int length = end - begin;
		byte[] b2 = new byte[length];
		System.arraycopy(b, begin, b2, 0, length);

		String ret = new String(b2, "GBK");
		return ret;
	}

	/**
	 * *************************************************************************
	 * 字节转字符串，GBK编码.
	 *
	 * @param b the b
	 * @param begin the begin
	 * @param end the end
	 * @return the byte[]
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public static byte[] assembByte(byte[] b, int begin, int end)
			throws UnsupportedEncodingException {

		if (b == null)
			return null;
		int length = end - begin;

		byte[] b2 = new byte[length];
		System.arraycopy(b, begin, b2, 0, length);
		return b2;
	}

	/**
	 * unicode转中文.
	 *
	 * @param dataStr the data str
	 * @return 中文
	 */
	public static String Unicode2GBK(String dataStr) {
		StringBuffer gbk = new StringBuffer();
		String[] hex = dataStr.split("\\\\u");
		for (int i = 1; i < hex.length; i++) { // 注意要从 1 开始，而不是从0开始。第一个是空。
			int data = Integer.parseInt(hex[i], 16); // 将16进制数转换为 10进制的数据。
			gbk.append((char) data); // 强制转换为char类型就是我们的中文字符了。
		}
		return gbk.toString();
	}

	public static String GBK2Unicode(String zhStr) {
		if(null == zhStr){
			zhStr ="";
		}
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < zhStr.length(); i++) {
			char c = zhStr.charAt(i);
			unicode.append("\\u" + Integer.toHexString(c));
		}
		System.out.println("从 字符转为Unicode编码: " + unicode.toString());
		return unicode.toString();
	}

	/**
	 * Checks if is need convert.
	 *
	 * @param para the para
	 * @return true, if is need convert
	 */
	public static boolean isNeedConvert(char para) {
		return ((para & (0x00FF)) != para);
	}

	/**
	 * *************************************************************************
	 * 字符串null转为"".
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String trimStr(String str) {
		if (str == null)
			return "";
		else {
			return str.trim();
		}
	}

	/**
	 * *************************************************************************
	 * byte[]数组相加.
	 *
	 * @param bs1 the bs1
	 * @param bs2 the bs2
	 * @return the byte[]
	 */
	public static byte[] bytesAddBytes(byte[] bs1, byte[] bs2) {
		if (bs1 == null || bs2 == null)
			return null;
		byte[] ret = new byte[bs1.length + bs2.length];
		System.arraycopy(bs1, 0, ret, 0, bs1.length);
		System.arraycopy(bs2, 0, ret, bs1.length, bs2.length);
		return ret;

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		String gbkStr = "20160230164444";
		System.out.println(isValidDate(gbkStr));
	}

	/**
	 * * 连接两个byte数组，之后返回一个新的连接好的byte数组 *.
	 *
	 * @param a1 *
	 * @param a2 *
	 * @return 一个新的连接好的byte数组
	 */

	public static byte[] joinByte(byte[] a1, byte[] a2) {
		byte[] result = new byte[a1.length + a2.length];
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}

	/**
	 * Format string.
	 *
	 * @param targetStr the target str
	 * @param strLength the str length
	 * @return the string
	 */
	public static String FormatString(String targetStr, int strLength) {
		int curLength = targetStr.getBytes().length;
		if (targetStr != null && curLength > strLength) {
			targetStr = targetStr.substring(0, strLength);
		}
		String newString = "";
		int cutLength = strLength - targetStr.getBytes().length;
		for (int i = 0; i < cutLength; i++)
			newString += " ";
		return targetStr + newString;
	}


	/**
	 * Gets the string lenth.
	 *
	 * @param value the value
	 * @return the string lenth
	 */
	public static int getStringLenth(String value){
		int valueLength = 0;
		if(StringUtils.isEmpty(value)){
			return valueLength;
		}
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
			String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

	/**
	 * 判断时间格式 格式必须为“YYYY-MM-dd”
	 * 2004-2-30 是无效的
	 * 2003-2-29 是无效的.
	 *
	 * @param str the str
	 * @return true, if is valid date
	 */
	public static boolean isValidDate(String str) {
		if(StringUtils.isEmpty(str)){
			return false;
		}
		try{
			SimpleDateFormat sdfTime = new java.text.SimpleDateFormat(
					"yyyyMMddHHmmss");
			Date date = (Date)sdfTime.parse(str);
			return str.equals(sdfTime.format(date));
		}catch(Exception e){
			return false;
		}
	}
}
