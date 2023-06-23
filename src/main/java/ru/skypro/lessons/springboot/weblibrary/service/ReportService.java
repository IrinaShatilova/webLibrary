package ru.skypro.lessons.springboot.weblibrary.service;

import java.io.File;

public interface ReportService {
    int formReportAndGetId();

    byte[] getReportById(int id);
}
