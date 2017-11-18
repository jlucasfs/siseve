package br.com.mobiew.siseve.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

public class MergePdfByteArrays {

    private ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    private Document document = null;

    private PdfCopy writer = null;

    public byte[] getMergedPdfByteArray() {
        byte[] result = null;
        if ( this.outStream != null ) {
            result = this.getOutStream().toByteArray();
        }
        return result;
    }

    public void add( byte[] pdfByteArray ) throws IOException, DocumentException {
        PdfReader reader = new PdfReader( pdfByteArray );
        int numberOfPages = reader.getNumberOfPages();
        if ( this.document == null ) {
            this.document = new Document( reader.getPageSizeWithRotation( 1 ) );
            this.writer = new PdfCopy( this.document, outStream ); // new
            this.document.open();
        }
        PdfImportedPage page;
        for ( int i = 0; i < numberOfPages; ) {
            ++i;
            page = this.writer.getImportedPage( reader, i );
            this.writer.addPage( page );
        }
        PRAcroForm form = reader.getAcroForm();
        if ( form != null ) {
            this.writer.copyAcroForm( reader );
        }
    }

    public void close() throws IOException {
        this.document.close();
        this.outStream.close();
    }

    public ByteArrayOutputStream getOutStream() {
        return outStream;
    }

    public void setOutStream( ByteArrayOutputStream outStream ) {
        this.outStream = outStream;
    }
}
