package com.accenture.tcf.bars.darren.e.b.manuel.service;

import com.accenture.tcf.bars.darren.e.b.manuel.domain.Record;
import com.accenture.tcf.bars.darren.e.b.manuel.exception.BarsException;

import java.io.File;
import java.util.List;

public interface InputFile {
    List<Record> readFile(File file) throws BarsException;
}
