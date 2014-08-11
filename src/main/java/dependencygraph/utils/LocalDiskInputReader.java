package dependencygraph.utils;

import dependencygraph.ApplicationConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads the file info from local disk
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class LocalDiskInputReader implements InputReader {

    public String read(String location) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(location));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(ApplicationConstants.LINE_SEPARATOR);
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
