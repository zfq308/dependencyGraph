package dependencygraph.utils;

import java.io.IOException;

/**
 * Interface for reading input for the application
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public interface InputReader {

    String read(String location) throws IOException;
}
