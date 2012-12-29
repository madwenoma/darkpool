package com.tadu.client.log;

import org.apache.log4j.Priority;
import org.apache.log4j.RollingFileAppender;

public class MyDailyRollingFileAppender extends RollingFileAppender{

	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		return this.getThreshold().equals(priority);  
	}

}
