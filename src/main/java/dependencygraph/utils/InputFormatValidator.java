/**
 * Copyright 2014 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dependencygraph.utils;

import dependencygraph.ApplicationConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * Methods for validating the format of the given input
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class InputFormatValidator {

    /**
     * Validates the given input line is in valid format
     * Expected format is A->B
     *
     * @param inputLine the input line to validate
     * @return true if file in valid format
     */
    public static boolean validateInputLine(String inputLine) {
        String[] splitLine = inputLine.split(ApplicationConstants.VERTEX_SEPARATOR_INDICATOR);
        return splitLine.length == 2 && StringUtils.isNotBlank(splitLine[0]);
    }
}
