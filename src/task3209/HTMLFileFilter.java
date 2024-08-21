package task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {


    @Override
    public boolean accept(File f) {

        if (f.isDirectory()) {
            return true;
        }

        String fileName = f.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = fileName.substring(dotIndex + 1);

        if (extension.equalsIgnoreCase("html") || extension.equalsIgnoreCase("htm")) {
            return true;
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "HTML та HTM файли";
    }
}
