package com.example.quartz.scheduler

import org.quartz.JobDetail
import org.quartz.SimpleTrigger
import org.quartz.Trigger
import org.quartz.simpl.SimpleJobFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean


@Configuration
class JobConfiguration {


    @Bean
    fun jobDetail(): JobDetailFactoryBean =
            JobDetailFactoryBean().apply {
                setJobClass(SampleJob::class.java)
                setDescription("Invoke SampleJob")
                setDurability(true)
            }

    @Bean
    fun trigger(job: JobDetail): SimpleTriggerFactoryBean = SimpleTriggerFactoryBean().apply {
        setJobDetail(job)
        setRepeatInterval(10_000)
        setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY)
    }

    @Bean
    fun scheduler(trigger: Trigger, trigger1: Trigger, job: JobDetail) = SchedulerFactoryBean().apply {
        setConfigLocation(ClassPathResource("quartz.properties"))
        setJobFactory(SimpleJobFactory())
        setJobDetails(job)
        setTriggers(trigger)
    }


}