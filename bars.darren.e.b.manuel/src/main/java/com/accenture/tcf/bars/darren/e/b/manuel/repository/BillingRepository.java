package com.accenture.tcf.bars.darren.e.b.manuel.repository;

import com.accenture.tcf.bars.darren.e.b.manuel.domain.Account;
import com.accenture.tcf.bars.darren.e.b.manuel.domain.Billing;
import com.accenture.tcf.bars.darren.e.b.manuel.domain.Customer;
import com.accenture.tcf.bars.darren.e.b.manuel.domain.Record;

import java.util.List;

public interface BillingRepository {
        Record findRecordFromRequest(Record record);
        List<Record> readAllRecordsFromRequest();
        List<Billing> retrieveAllBillingTableContents();
        List<Account> retrieveAllAccountTableContents();
        List<Customer> retrieveAllCustomerTableContents();
        void updateRecordInBillingTable(int id);
        void updateRecordInAccountTable(int id);
        void updateRecordInCustomerTable(int id);
        void deleteRecordInBillingTable(int id);
        void deleteRecordInAccountTable(int id);
        void deleteRecordInCustomerTable(int id);
}
