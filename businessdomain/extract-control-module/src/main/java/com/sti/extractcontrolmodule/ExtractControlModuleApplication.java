package com.sti.extractcontrolmodule;

import com.sti.extractcontrolmodule.model.ExtractedLogModel;
import com.sti.extractcontrolmodule.model.status.ModelStatus;
import com.sti.extractcontrolmodule.repository.ExtraxtedLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ExtractControlModuleApplication {



    public static void main(String[] args) {
        SpringApplication.run(ExtractControlModuleApplication.class, args);
    }

}
