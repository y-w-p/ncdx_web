package com.qianfeng.job;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class SendEmailScheduler extends Thread {

	@Override
	public void run() {
		try {
			JobDetail jobDetail = new JobDetail("emailjobDetail",
					"emailjobDetailGroup", SendEmailJobDetail.class);
			CronTrigger cronTrigger = new CronTrigger("emailjobTrigger",
					"emailjobTriggerGroup", "0 0 0/1 * * ? ");
			Scheduler emailSenderScheduler = StdSchedulerFactory
					.getDefaultScheduler();
			emailSenderScheduler.scheduleJob(jobDetail, cronTrigger);
			emailSenderScheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
