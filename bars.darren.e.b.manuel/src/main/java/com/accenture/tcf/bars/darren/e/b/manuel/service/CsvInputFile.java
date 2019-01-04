package com.accenture.tcf.bars.darren.e.b.manuel.service;

import com.accenture.tcf.bars.darren.e.b.manuel.domain.Record;
import com.accenture.tcf.bars.darren.e.b.manuel.exception.BarsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CsvInputFile implements InputFile {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Record> readFile(File file)throws BarsException{
        logger.info("CSV FILE : READFILE() IS CALLED");
        List<Record> recordRequestListCsv=new ArrayList<>();
        Scanner scan=null;

        try{
            DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false);
            String filePath=file.getAbsolutePath();
            scan=new Scanner(new BufferedReader(new FileReader(filePath)));
            int row=0;
            while(scan.hasNextLine()){
                Record recordRequest=new Record();
                row++;
                String input=scan.nextLine();
                String start_date=input.substring(2,10);

                if((Integer.parseInt(input.substring(0,2))<=0)||(Integer.parseInt(input.substring(0,2))>12)){
                    throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE+row);
                }
                else{
                    recordRequest.setBillingCycle(Integer.parseInt(input.substring(0,2)));
                }

                // Parse Start Date
                try{
                    Date parsedStartDate=dateFormat.parse(start_date);
                    recordRequest.setStartDate(parsedStartDate);
                }catch(ParseException e){
                    throw new BarsException(BarsException.INVALID_START_DATE_FORMAT+row,e);
                }

                // Parse End Date
                try{
                    String end_date=input.substring(10,18);
                    Date parsedEndDate=dateFormat.parse(end_date);
                    recordRequest.setEndDate(parsedEndDate);
                }catch(ParseException e){
                    throw new BarsException(BarsException.INVALID_END_DATE_FORMAT+row);
                }catch(StringIndexOutOfBoundsException s){
                    logger.info("===============STRING INDEX OUT OF BOUNDS=================");
                    throw new BarsException(BarsException.INVALID_END_DATE_FORMAT+row);
                }


                recordRequestListCsv.add(recordRequest);
//                }
            }

        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            if(scan!=null){
                scan.close();
            }
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@ DONE!!!! @@@@@@@@@@@@@@@@@@@@");
        return recordRequestListCsv;

    }

}


