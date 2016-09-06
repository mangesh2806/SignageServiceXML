package com.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;



@Configuration
public class BatchConfiguration {
	public static final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);
	
	static
	{
		BatchConfiguration.execute();
	}
	public static void execute()
	{
		String[] springConfig = { "spring/batch/jobs/job-partitioner.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		logger.info("Before JOB Configuration RUN::::::::::::::::::::::::::::::::::::::::::::::::");
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("partitionJob");

		try {

			logger.info("Before JOB Launcher RUN::::::::::::::::::::::::::::::::::::::::::::::::");
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			logger.info("Exit Status : " + execution.getStatus());
			logger.info("Exit Status : " + execution.getAllFailureExceptions());
			logger.info("AFter JOB Launcher RUN::::::::::::::::::::::::::::::::::::::::::::::::");

		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		logger.info("Done");

	}
	
}
