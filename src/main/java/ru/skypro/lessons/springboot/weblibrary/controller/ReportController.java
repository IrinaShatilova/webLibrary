package ru.skypro.lessons.springboot.weblibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.service.ReportService;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping()
    public int formReportAndGetId() {
        return reportService.formReportAndGetId();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getReportById(@PathVariable("id") int id) {
        return reportService.getReportById(id);
    }

    @PostMapping("/disk")
    public int formReportFromDisk() {
        return reportService.formReportFromDisk();
    }

    @GetMapping(value = "{id}/disk", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getReportFromDiskById(@PathVariable("id") int id){
        return reportService.getReportFromDiskById(id);
    }
}
