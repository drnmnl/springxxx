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

public class TextInputFile implements InputFile  {
        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        public List<Record> readFile(File file) throws BarsException {
            logger.info("TXT FILE : READFILE() IS CALLED");
            List<Record> recordRequestList = new ArrayList<>();
            Scanner scan = null;

            try {
                DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
                dateFormat.setLenient(false);
                String filePath = file.getAbsolutePath();
                scan = new Scanner(new BufferedReader(new FileReader(filePath)));
                int row = 0;
                while (scan.hasNextLine()) {
                    Record recordRequest = new Record();
                    row++;
                    System.out.println("=========== ROW: " + row);
                    String input = scan.nextLine();
                    String start_date = input.substring(2, 10);

                    // Parse Billing Cycle
                    try {
                        logger.info("===============PARSING BILLING CYCLE================= row: "  + row);
                        int billingCycleInt= Integer.parseInt(input.substring(0, 2));
                        if ((billingCycleInt <= 0) || (billingCycleInt > 12) || (input.substring(0, 2).contains("[a-zA-Z]+") == true)) {
                            throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE + " " + row);
                        } else {
                            recordRequest.setBillingCycle(Integer.parseInt(input.substring(0, 2)));
                        }
                    } catch (NumberFormatException ex) {
                        throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE + " " + row);
                    }

                    // Parse Start Date
                    try {
                        logger.info("===============PARSING START DATE================= row: "  + row);
                        Date parsedStartDate = dateFormat.parse(start_date);
                        recordRequest.setStartDate(parsedStartDate);
                    } catch (ParseException e) {
                        throw new BarsException(BarsException.INVALID_START_DATE_FORMAT + " " +row);
                    }catch (StringIndexOutOfBoundsException s) {
                        logger.info("===============STRING INDEX OUT OF BOUNDS=================");
                        throw new BarsException(BarsException.INVALID_START_DATE_FORMAT + " " + row);
                    }
                    // Parse End Date
                    try {
                        logger.info("===============PARSING END DATE================= row: "  + row);
                        String end_date = input.substring(10, 18);
                        Date parsedEndDate = dateFormat.parse(end_date);
                        recordRequest.setEndDate(parsedEndDate);
                    } catch (ParseException e) {
                        throw new BarsException(BarsException.INVALID_END_DATE_FORMAT + " "+ row);
                    } catch (StringIndexOutOfBoundsException s) {
                        logger.info("===============STRING INDEX OUT OF BOUNDS=================");
                        throw new BarsException(BarsException.INVALID_END_DATE_FORMAT + " "  +row);
                    }

                    recordRequestList.add(recordRequest);
//                }
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (scan != null) {
                    scan.close();
                }
            }
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@ DONE!!!! @@@@@@@@@@@@@@@@@@@@");
            return recordRequestList;

        }
    }
