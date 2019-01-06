package com.accenture.tcf.bars.darren.e.b.manuel.controller;

import com.accenture.tcf.bars.darren.e.b.manuel.domain.Record;
import com.accenture.tcf.bars.darren.e.b.manuel.exception.BarsException;
import com.accenture.tcf.bars.darren.e.b.manuel.service.FileProcessorUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class BarsRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String filePath;
    private String exceptionThrown;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FileProcessorUtilService fileProcessorUtilService;

    @GetMapping("/")
    public String getHomeApi() {
        return "Hello";
    }

    @PostMapping("/sendfile")
    public void getFilePath(@RequestBody String filePath) {
        this.filePath = filePath;
        logger.info("RECEIVED FILE PATH: " + filePath);
    }

    @GetMapping("/upload")
    public List<?> getRecordsFromDatabase (HttpServletRequest request, HttpServletResponse response) {
        List<Record> searchResults = new ArrayList<>();
        String exception="No Exception";
        boolean exceptionIsThrown = false;

        try {
            File file = new File(this.filePath);
            searchResults = fileProcessorUtilService.execute(file);
            fileProcessorUtilService.getXmlFileName();
        } catch (BarsException ex) {
            exceptionIsThrown = true;
            searchResults = new ArrayList<Record>();
            logger.info("" + ex);

            if (ex.getMessage().contains(BarsException.BILLING_CYCLE_NOT_ON_RANGE)) {
               exception = ex.getMessage();
            } else if (ex.getMessage().contains(BarsException.INVALID_START_DATE_FORMAT)) {
                exception = ex.getMessage();
            } else if (ex.getMessage().contains(BarsException.INVALID_END_DATE_FORMAT)) {
                exception = ex.getMessage();
            }

            System.out.println("====================== EXCEPTION IS THROWN ======================");
            this.exceptionThrown = exception;
            Record fakeRecord = new Record(0, null, null,null,  exception,null, 0);
            searchResults.add(fakeRecord);
        }
        return searchResults;
    }


    @PostMapping("/test")
        public String postMethodSample(@RequestBody String str) {
            System.out.println("POST METHOD RECEIVED! " + str);
            return "POST METHOD RECEIVED!  " + str;
        }


    @GetMapping(value="/loginstatus")
    public CredentialWrapper getCredentials() {
        String url = "http://localhost:9999/login-client-service/security";
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<String> responseEntity
                = restTemplate.exchange(url,
                HttpMethod.GET, httpEntity, String.class);

        CredentialWrapper credentialWrapper = new CredentialWrapper(responseEntity.getBody());
        return credentialWrapper;
    }
}

class CredentialWrapper { //converts my string to class
    private String username;
    private boolean isAdmin;

    public CredentialWrapper(String string) {
        username = string.substring(0, string.length()-1);
        if (string.endsWith("1")) {
            isAdmin = true;
        }
        else
            isAdmin = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}

class StringWrapper {
    private String str;

    public StringWrapper(String str){
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}



//            if (ex.getMessage().contains(BarsException.BILLING_CYCLE_NOT_ON_RANGE)) {
//                modelAndView.addObject("exception1", ex.getMessage());
//                modelAndView.setViewName("error_billing_cycle");
//            } else if (ex.getMessage().contains(BarsException.INVALID_START_DATE_FORMAT)) {
//                modelAndView.addObject("exception2", ex.getMessage());
//                modelAndView.setViewName("error_invalid_start_date");
//            } else if (ex.getMessage().contains(BarsException.INVALID_END_DATE_FORMAT)) {
//                modelAndView.addObject("exception3", ex.getMessage());
//                modelAndView.setViewName("error_invalid_end_date");
//            }