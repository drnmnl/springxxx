package com.accenture.tcf.bars.darren.e.b.manuel.service;

import com.accenture.tcf.bars.darren.e.b.manuel.domain.Record;
import com.accenture.tcf.bars.darren.e.b.manuel.exception.BarsException;
import com.accenture.tcf.bars.darren.e.b.manuel.repository.BillingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileProcessorUtilService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private InputFile inputFile;
    private OutputFile outputFile;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String xmlFileName;

    @Autowired
    private BillingRepository billingRepository;

    public List<Record> execute (File file)  throws BarsException {

        if (file.getAbsolutePath().endsWith("txt")) {
            inputFile = new TextInputFile();
        } else if (file.getAbsolutePath().endsWith("csv")) {
//            inputFile = new CsvInputFile();
            }
        else {
            inputFile = null;
            logger.error(BarsException.NO_SUPPORTED_FILE);
        }

        // Check file; Read file contents if not empty
            List<Record> recordQueries = inputFile.readFile(file);
            List<Record> resultList = new ArrayList<>();

        // Retrieve entries from database
        if(recordQueries.isEmpty()) {
            logger.info(BarsException.NO_REQUESTS_TO_READ);
        }
        else {
            for (Record recordQuery : recordQueries) {
                System.out.println("FINDING ROW IN DATABASE......." + recordQuery.getBillingCycle());
                Record result = billingRepository.findRecordFromRequest(recordQuery);
                System.out.println("HEYYYY" + result.getStartDate());

                if (result!=null) {
                    resultList.add(result);
                }
            }
        }
        // Write results to XML File
        outputFile = new XmlOutputService();
        this.xmlFileName=outputFile.write(resultList);
        return resultList;
    }

    public String getXmlFileName() {
        return this.xmlFileName;
    }


}
