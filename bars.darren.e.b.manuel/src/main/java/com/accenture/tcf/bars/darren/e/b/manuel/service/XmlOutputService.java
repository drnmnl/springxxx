package com.accenture.tcf.bars.darren.e.b.manuel.service;

//import com.accenture.sef.xml.impl.BarsWriteXmlUtils;
//import com.accenture.sef.xml.interfce.BarsWriteXMLUtilsInterface;
import com.accenture.tcf.bars.darren.e.b.manuel.domain.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class XmlOutputService implements OutputFile {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String write(List<Record> records) {
        String xmlFileName="";
//        Date date = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("MMddyyyy_HHmmss");
//        String formattedDate= dateFormat.format(date);
//
//        String xmlFileName = "BARS_Report-" + formattedDate;
//        String path = "C:" + File.separator + "BARS" + File.separator + "Report" + File.separator + "BARS_Report-" + formattedDate + ".xml";
//        System.out.println(path);
//        File f = new File(path);
//
//        try{
//            f.createNewFile(); }
//        catch(Exception ex) {
//            logger.info(ex.getMessage());
//        }
//
//        BarsWriteXMLUtilsInterface x = new BarsWriteXmlUtils();
//        Document doc = x.createXMLDocument();
//        Element rootElement = x.createDocumentElement(doc, "BARS");
//        int ctr=0;
//        for(Record billing : records) {
//            ctr++;
//            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//            String formattedStartDate= dateFormatter.format(billing.getStartDate());
//            String formattedEndDate= dateFormatter.format(billing.getEndDate());
//
//            Element staffElement = x.createChildElement(doc, rootElement, "request");
//            x.createElementAttribute(doc, staffElement, "billing-id", "ctr");
//            x.createElementTextNode(doc, staffElement, "billing-cycle", Integer.toString(((billing.getBillingCycle() ))) );
//            x.createElementTextNode(doc, staffElement, "start-date", formattedStartDate);
//            x.createElementTextNode(doc, staffElement, "end-date", formattedEndDate);
//            x.createElementTextNode(doc, staffElement, "first-name", billing.getFirstName());
//            x.createElementTextNode(doc, staffElement, "last-name", billing.getLastName());
//            x.createElementTextNode(doc, staffElement, "amount", Double.toString(billing.getAmount()) );
//
//        }
//        x.transformToXML(doc, path);
        return xmlFileName;

    }


}
