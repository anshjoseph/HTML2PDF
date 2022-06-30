package com.example.pdfgen.PDF_Render;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlParse {
    private String html;
    private HashMap<String,String> vars;
    private String specialPrefix = "{";
    private String specialPostfix = "}";


    // inti
    public HtmlParse(String html,HashMap<String,String> vars){this.html=html;this.vars=vars;}
    public HtmlParse(String html){this.html=html;this.vars=new HashMap<>();}

    // prase functiuon
    public HtmlParse parse(){
        for (Map.Entry<String, String> e : vars.entrySet()){
            String sp = specialPrefix+e.getKey()+specialPostfix;
            html = html.replace(sp,e.getValue());
        }
        return this;
    }

    public String getHtml(){ return  this.html; }

    // data entry part
    public void putall(List<String> key,List<String> val)    {
        if(key.size()== val.size())
        {
            int count = 0;
            for(String k: key){
                vars.put(k,val.get(count));
                count+=1;
            }
        }

    }
}
