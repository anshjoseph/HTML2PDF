package com.example.pdfgen.PDF_Render.Tools.Table;

import com.example.pdfgen.PDF_Render.Html;
import java.util.List;

public class TableHtml {
    private List<TableElement> elements;
    private String html = "";
    public TableHtml(List<TableElement> elements){ this.elements = elements; }
    public Html build(){
        html+="<table border='1'>";
        for(TableElement tableElement:elements){
            html+="<tr>";
            for(String data:tableElement.getData()){
                html+="<td>"+data+"</td>";
            }
            html+="</tr>";
//            switch (tableElement.getType()){
//                case 0:
//                    html+="<thead><tr>";
//                    for(String data:tableElement.getData()){
//                        html+="<td>"+data+"</td>";
//                    }
//                    html+="</tr></thead><tbody>";
//                    break;
//                case 1:
//                    html+="<tr>";
//                    for(String data:tableElement.getData()){
//                        html+="<td>"+data+"</td>";
//                    }
//                    html+="</tr>";
//                    break;
//            }
        }
        html+="</table>";
        return new Html().setHtml(html);
    }
}
