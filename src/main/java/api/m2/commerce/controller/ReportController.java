package api.m2.commerce.controller;

import api.m2.commerce.records.reports.DailySummaryRecord;
import api.m2.commerce.services.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/daily-summary")
    public ResponseEntity<DailySummaryRecord> getDailySummary(
            @RequestParam Long workspaceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok(reportService.getDailySummary(workspaceId, date));
    }

    @GetMapping("/today-summary")
    public ResponseEntity<DailySummaryRecord> getTodaySummary(@RequestParam Long workspaceId) {
        return ResponseEntity.ok(reportService.getTodaySummary(workspaceId));
    }
}
