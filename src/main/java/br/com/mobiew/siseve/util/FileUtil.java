package br.com.mobiew.siseve.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import br.com.mobiew.siseve.exceptions.SistemaException;

public final class FileUtil {

//    private static final Logger LOG = Logger.getLogger( FileUtil.class );

    private FileUtil() {
        super();
    }

    public static File convertToFile( byte[] arquivo ) {
        File file = null;
        try {
            file = File.createTempFile( "boleto.pdf", null );
            OutputStream outputStream = new FileOutputStream( file );
            InputStream inputStream = new ByteArrayInputStream( arquivo );
            byte buf[] = new byte[1024];
            int len = 0;
            while ( ( len = inputStream.read( buf ) ) != -1 ) {
                outputStream.write( buf, 0, len );
            }
            outputStream.close();
            inputStream.close();
            file.createNewFile();
        } catch ( Exception e ) {
            throw new SistemaException( "Erro ao converter arquivo pdf." );
        }
        return file;
    }

}
