package br.com.mobiew.siseve.util;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the FileFilter interface.
 * 
 */
public class ImageFileFilter implements FileFilter {

    public static final String REGEXP = "^fig_(\\d+)_(\\d+)_(\\d+)(\\.)((?i)jpg|jpeg|pdf|doc)$";

    private static final ImageFileFilter IMAGE_FILE_FILTER = new ImageFileFilter();

    /**
     * Retorna a instancia unica de ImageFileFilter.
     * 
     * @return O objeto ImageFileFilter.
     */
    public static ImageFileFilter getInstance() {
        return IMAGE_FILE_FILTER;
    }

    /**
     * Tests whether or not the specified abstract file should be included in a file list.
     * 
     * @param file The file.
     * @return true if the file extension is accepted.
     */
    public boolean accept( final File file ) {
        boolean result = false;
        if ( !file.isDirectory() ) {
            Matcher matcher = Pattern.compile( REGEXP ).matcher( file.getName() );
            if ( matcher.find() ) {
                result = true;
            }
        }
        return result;
    }
}
