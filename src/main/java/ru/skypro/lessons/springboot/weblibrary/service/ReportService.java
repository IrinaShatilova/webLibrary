package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.web.multipart.MultipartFile;

public interface ReportService {
 void upload (MultipartFile employees);
}
