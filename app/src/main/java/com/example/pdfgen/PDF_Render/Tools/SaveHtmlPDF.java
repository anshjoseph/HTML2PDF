package com.example.pdfgen.PDF_Render.Tools;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Base64;
import android.webkit.WebView;

import com.example.pdfgen.PDF_Render.Html;

public class SaveHtmlPDF {
    private PrintJob printJob;
    private Context context;
    private WebView webView;
    private String filename;
    private Html html;

    public SaveHtmlPDF(Context context, WebView webView, String filename,Html html) {
        this.context = context;
        this.webView = webView;
        this.filename = filename;
        this.html = html;
        this.webView.loadData(Base64.encodeToString(html.getHtmlBytes(),Base64.NO_PADDING), "text/html", "base64");
        PrintTheWebPage(this.webView);
    }


    private void PrintTheWebPage(WebView webView) {
        PrintManager printManager = (PrintManager)context.getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(this.filename);
        assert printManager != null;
        printJob = printManager.print(this.filename, printAdapter, new PrintAttributes.Builder().build());
    }
}
