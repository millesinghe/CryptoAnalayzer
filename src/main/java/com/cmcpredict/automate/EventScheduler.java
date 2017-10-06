package com.cmcpredict.automate;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class EventScheduler {

	public static void main(String[] args) throws SchedulerException {

		JobDetail job = JobBuilder.newJob(AutomatedTrigger.class).withIdentity("cmcdatacollector", "group1").build();

		// Trigger the job to run on the next round minute
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("hourly", "group1")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1).repeatForever()).build();

		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
	}
}
