package com.example.pdfgen.PDF_Render.Tools.Table;

import java.util.ArrayList;
import java.util.List;

public class TableHead extends  TableElement{
    private List<String> data = new ArrayList<>();
    public TableHead add(String val){
        data.add(val);
        return this;
    }

    @Override
    public List<String> getData() {
        return data;
    }

    @Override
    public int getType() {
        return 0;
    }
}
