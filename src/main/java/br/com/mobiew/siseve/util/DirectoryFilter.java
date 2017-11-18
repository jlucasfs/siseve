package br.com.mobiew.siseve.util;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the FileFilter interface.
 * 
 */
public class DirectoryFilter implements FileFilter {

    public static final String REGEXP = "^(\\d+)$";

    private static final DirectoryFilter DIRECTORY_FILTER = new DirectoryFilter();

    /**
     * Retorna a instancia unica de ImageFileFilter.
     * 
     * @return O objeto ImageFileFilter.
     */
    public static DirectoryFilter getInstance() {
        return DIRECTORY_FILTER;
    }

    /**
     * Tests whether or not the specified abstract file should be included in a file list.
     * 
     * @param file The file.
     * @return true if the file extension is accepted.
     */
    public boolean accept( final File file ) {
        boolean result = false;
        if ( file.isDirectory() ) {
            Matcher matcher = Pattern.compile( REGEXP ).matcher( file.getName() );
            if ( matcher.find() ) {
                result = true;
            }
        }
        return result;
    }
}
