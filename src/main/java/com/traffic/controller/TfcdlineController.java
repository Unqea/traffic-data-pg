package com.traffic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traffic.entity.Tfcdline;
import com.traffic.service.TfcdlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tfcdline")
public class TfcdlineController {

    @Autowired
    private TfcdlineService tfcdlineService;

    @GetMapping("/export")
    public ResponseEntity<?> exportJson() {
        List<Tfcdline> data = tfcdlineService.getTfcdlineData();

        ObjectMapper mapper = new ObjectMapper();
        File file = new File("/Users/yin/Downloads/tfcdline_data.json");  // Save to the specified path
        
        try {
            mapper.writeValue(file, data);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error exporting data");
        }

        return ResponseEntity.ok("Data exported to tfcdline_data.json");
    }
}
