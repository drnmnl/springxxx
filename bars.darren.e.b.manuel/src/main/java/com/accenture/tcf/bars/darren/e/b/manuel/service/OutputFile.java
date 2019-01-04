package com.accenture.tcf.bars.darren.e.b.manuel.service;

import com.accenture.tcf.bars.darren.e.b.manuel.domain.Record;

import java.util.List;

public interface OutputFile {
    String write(List<Record> searchResults);
}
