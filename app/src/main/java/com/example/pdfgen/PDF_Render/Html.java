package com.example.pdfgen.PDF_Render;

public class Html {
    private String html = new String();
    public byte[] getHtmlBytes() { return html.getBytes(); }
    public Html setHtml(String html) { this.html = html; return this;}
}
