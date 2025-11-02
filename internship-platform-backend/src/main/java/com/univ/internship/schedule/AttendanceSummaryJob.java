package com.univ.internship.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AttendanceSummaryJob {
    // 演示：每天 2:00 汇总（这里仅打印日志，实际应统计写库）
    @Scheduled(cron = "0 0 2 * * ?")
    public void run() {
        System.out.println("Attendance summary job executed.");
    }
}
