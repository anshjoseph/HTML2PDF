package com.example.pdfgen.PDF_Render;

import android.content.Context;
import android.graphics.Bitmap;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.pdfgen.PDF_Render.Tools.SaveHtmlPDF;


public class SavePDF {
    private PrintJob printJob;
    private  Context context;
    private WebView webView;
    private String filename;
    private HtmlParse htmlParse;
    private ViewGroup vg;
    boolean loadingFinished = true;
    boolean redirect = false;

    public SavePDF(Context context, ViewGroup vg, String filename,HtmlParse htmlParse) {
        this.context = context;
        this.vg = vg;
        this.webView = new WebView(context);
        webView.setVisibility(View.GONE);
        vg.addView(webView);
        this.filename = filename;
        this.htmlParse = htmlParse;
        this.webView.loadData(Base64.encodeToString(htmlParse.parse().getHtml().getHtmlBytes(),Base64.NO_PADDING), "text/html", "base64");
        this.webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
                if (!loadingFinished) {
                    redirect = true;
                }

                loadingFinished = false;
                view.loadUrl(urlNewString);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                loadingFinished = false;
                //SHOW LOADING IF IT ISNT ALREADY VISIBLE
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(!redirect){
                    loadingFinished = true;
                    PrintTheWebPage(SavePDF.this.webView);
                }

                if(loadingFinished && !redirect){
                    //HIDE LOADING IT HAS FINISHED
                } else{
                    redirect = false;
                }

            }
        });
        PrintTheWebPage(this.webView);
    }


    private void PrintTheWebPage(WebView webView) {
        PrintManager printManager = (PrintManager)context.getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(this.filename);
        assert printManager != null;
        printJob = printManager.print(this.filename, printAdapter, new PrintAttributes.Builder().build());
    }

}
