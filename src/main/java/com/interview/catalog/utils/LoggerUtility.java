/**
 * 
 */
package com.interview.catalog.utils;

import org.apache.log4j.Logger;

/**
 * @author Utkarsh Thusoo
 *
 */
public class LoggerUtility {

	public static void logError(Logger log, Object message){
		log.error(message);
	}
	
	public static void logDebug(Logger log, Object message){
		log.debug(message);
	}
	
	public static void logInfo(Logger log, Object message){
		log.info(message);
	}
}
