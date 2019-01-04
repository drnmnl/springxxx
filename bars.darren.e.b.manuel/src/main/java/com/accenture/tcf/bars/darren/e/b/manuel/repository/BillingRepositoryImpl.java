package com.accenture.tcf.bars.darren.e.b.manuel.repository;

import com.accenture.tcf.bars.darren.e.b.manuel.domain.Account;
import com.accenture.tcf.bars.darren.e.b.manuel.domain.Billing;
import com.accenture.tcf.bars.darren.e.b.manuel.domain.Customer;
import com.accenture.tcf.bars.darren.e.b.manuel.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BillingRepositoryImpl implements BillingRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Record findRecordFromRequest(Record recordQuery) {
        Record result = null;
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(recordQuery.getStartDate().getTime() );
            java.sql.Date sqlEndDate = new java.sql.Date(recordQuery.getEndDate().getTime() );
//            String sqlQuery = "SELECT * FROM bars_db.billing WHERE (billing_cycle=? AND start_date=? AND end_date=?);";
            String query =
                    "SELECT billing.billing_cycle, billing.start_date, billing.end_date, customer.first_name, customer.last_name, billing.amount, account.account_name " +
                    " FROM billing " +
                    " JOIN account ON billing.account_id = account.account_id " +
                    " JOIN customer ON account.customer_id = customer.customer_id " +
                    "WHERE ( (billing.start_date = ?) AND (billing.end_date = ?) AND (billing.billing_cycle = ?) );";

            Object[] args = {sqlStartDate.toString(), sqlEndDate.toString(), recordQuery.getBillingCycle(),};

            System.out.println(recordQuery.getBillingCycle());
            System.out.println(sqlStartDate);
            System.out.println(sqlEndDate);

            result = jdbcTemplate.queryForObject(query, args, new BillingRowMapper());
//
            System.out.println("$$$$$$$$$$$$$$$$$$$ RESULT ID: " + result.getId());
            System.out.println("$$$$$$$$$$$$$$$$$$$ RESULT FN: " + result.getFirstName());
            System.out.println("$$$$$$$$$$$$$$$$$$$ RESULT SD: " + result.getStartDate());
            System.out.println("$$$$$$$$$$$$$$$$$$$ RESULT ED: " + result.getEndDate());
            System.out.println("$$$$$$$$$$$$$$$$$$$ RESULT BC: " + result.getBillingCycle());
        }
        catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            System.out.println("RESULT IS NULL");
        }

        return result;
    }
    @Override
    public List<Record> readAllRecordsFromRequest() {

        String sqlJoinQuery = "SELECT billing.billing_cycle, billing.start_date, billing.end_date, customer.first_name, customer.last_name, billing.amount, account.account_name\n" +
                " FROM request \n" +
                " JOIN billing ON  ( (billing.start_date = request.start_date) AND (billing.end_date = request.end_date) AND (billing.billing_cycle = request.billing_cycle) ) \n" +
                " JOIN account ON billing.account_id = account.account_id\n" +
                " JOIN customer ON account.customer_id = customer.customer_id;";

        List<Map<String, Object>> recordsRaw = (List<Map<String, Object>>) jdbcTemplate.queryForList(sqlJoinQuery);
        List<Record> records = new ArrayList<>();

        for (Map<String,Object> row : recordsRaw) {
            Record record = new Record();
            record.setBillingCycle((int) row.get("billing_cycle"));
            record.setAccountName((String) row.get("account"));
            BigDecimal bd = (BigDecimal) row.get("amount");
            record.setAmount(bd.doubleValue());
            record.setFirstName((String) row.get("first_name"));
            record.setLastName((String) row.get("last_name"));
            record.setStartDate((Date) row.get("start_date"));
            record.setEndDate((Date) row.get("end_date"));
            records.add(record);
        }

        return records;
    }


    @Override
    public List<Billing> retrieveAllBillingTableContents() {
       return null;
    }

    @Override
    public List<Account> retrieveAllAccountTableContents() {

        return null;
    }

    @Override
    public List<Customer> retrieveAllCustomerTableContents() {
        return null;
    }

    @Override
    public void updateRecordInBillingTable(int id) {

    }

    @Override
    public void updateRecordInAccountTable(int id) {

    }

    @Override
    public void updateRecordInCustomerTable(int id) {

    }

    @Override
    public void deleteRecordInBillingTable(int id) {

    }

    @Override
    public void deleteRecordInAccountTable(int id) {

    }

    @Override
    public void deleteRecordInCustomerTable(int id) {

    }
}
