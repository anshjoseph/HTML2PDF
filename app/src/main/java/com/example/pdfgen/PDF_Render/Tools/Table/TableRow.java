package com.example.pdfgen.PDF_Render.Tools.Table;

import java.util.ArrayList;
import java.util.List;

public class TableRow extends TableElement{
    private List<String> data = new ArrayList<>();
    public TableRow add(String val){
        data.add(val);
        return this;
    }

    @Override
    public List<String> getData() {
        return data;
    }

    @Override
    public int getType() {
        return 1;
    }
}
