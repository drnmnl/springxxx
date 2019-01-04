package com.accenture.tcf.bars.darren.e.b.manuel.repository;

import com.accenture.tcf.bars.darren.e.b.manuel.domain.Record;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingRowMapper implements RowMapper<Record> {

    public Record mapRow(ResultSet rs, int rownum) throws SQLException {
        Record bill = new Record();
//        bill.setId(rs.getInt("billing_id"));
        bill.setBillingCycle(rs.getInt("billing_cycle"));
        bill.setStartDate(rs.getDate("start_date"));
        bill.setEndDate(rs.getDate("end_date"));
            BigDecimal bd = rs.getBigDecimal("amount");
        bill.setAmount(bd.doubleValue());
        bill.setAccountName(rs.getString("account_name"));
        bill.setFirstName(rs.getString("first_name"));
        bill.setLastName(rs.getString("last_name"));
        return bill;
    }
}
