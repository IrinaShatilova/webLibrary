package ru.skypro.lessons.springboot.weblibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReportServiceImpl implements ReportService {
    private final ObjectMapper objectMapper;
    @Autowired

    public ReportServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void upload(MultipartFile employees) {

    }
}
