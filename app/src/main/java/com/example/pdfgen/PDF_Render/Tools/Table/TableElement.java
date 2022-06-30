package com.example.pdfgen.PDF_Render.Tools.Table;

import java.util.List;

public abstract class TableElement {
    /*
    * getDate return the data of TableHead and TableRow
    * */
    public abstract List<String> getData();
    /*
    * getType return the type of row
    * :- 0 for table head
    * :- 1 for table row
    * */
    public abstract int getType();
}
