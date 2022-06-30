package com.example.pdfgen.PDF_Render;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;



public class SavePDF {
    private PrintJob printJob;
    private  Context context;
    private WebView webView;
    private String filename;
    private HtmlParse htmlParse;
    private ViewGroup vg;

    public SavePDF(Context context, ViewGroup vg, String filename,HtmlParse htmlParse) {
        this.context = context;
        this.vg = vg;
        this.webView = new WebView(context);
        webView.setVisibility(View.GONE);
        vg.addView(webView);
        this.filename = filename;
        this.htmlParse = htmlParse;
        this.webView.loadData(Base64.encodeToString(htmlParse.parse().getHtml().getHtmlBytes(),Base64.NO_PADDING), "text/html", "base64");
        PrintTheWebPage(this.webView);
    }


    private void PrintTheWebPage(WebView webView) {
        PrintManager printManager = (PrintManager)context.getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(this.filename);
        assert printManager != null;
        printJob = printManager.print(this.filename, printAdapter, new PrintAttributes.Builder().build());
    }

}
