package com.example.quartz.scheduler

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.stereotype.Component

@Component
class SampleJob : Job {
    override fun execute(p0: JobExecutionContext?) {
        println("SampleJob running")
    }
}