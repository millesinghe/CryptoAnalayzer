package com.cmcpredict.automate;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AutomatedTrigger implements Job {
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		AutoDataCollector autoDataCollector = new AutoDataCollector();
		autoDataCollector.automatedDataCollector();
	}
}
