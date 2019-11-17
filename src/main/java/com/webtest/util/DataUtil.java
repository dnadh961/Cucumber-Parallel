/*
 *
 * Copyright (c) 2019 Pegasystems Inc.
 * All rights reserved.
 *
 * This  software  has  been  provided pursuant  to  a  License
 * Agreement  containing  restrictions on  its  use.   The  software
 * contains  valuable  trade secrets and proprietary information  of
 * Pegasystems Inc and is protected by  federal   copyright law.  It
 * may  not be copied,  modified,  translated or distributed in  any
 * form or medium,  disclosed to third parties or used in any manner
 * not provided for in  said  License Agreement except with  written
 * authorization from Pegasystems Inc.
*/

package com.webtest.util;

import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sachin
 * @since 10th August 2014
 *
 */
public class DataUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataUtil.class.getName());
	
	/**
	 * utility method to return a new string by appending some random number to the given string
	 * @param value string to which random number has to be appended
	 * @return a new string which has random number appended
	 * @author Sachin
	 */
	public static String getRandomNumberString(String value){
		SecureRandom  rn = new SecureRandom ();
		int randomNum =  rn.nextInt(9999) + 1000;
		return value+randomNum+"";
	}
}
