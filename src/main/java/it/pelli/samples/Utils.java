package it.pelli.samples;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Utils {

    public static final String[] TOPICS = {"seventy", "eighty"};
  
    public static class TopicException extends Exception {
        public TopicException(String message) {
            super(message);
        }
    }

    public static class OperationException extends Exception {
        public OperationException(String message) {
            super(message);
        }
    }

    public static String validateFilePath(final String filePath)
        throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("'" + filePath
                + "' is not a vaild file path.\n");
        }

        return filePath;
    }

    public static String convertMessage(final String topic, final String message) 
        throws TopicException, OperationException {
        String sqlStatement = "";
        String [] messageArray = message.split(":");
        String operation = messageArray[0].trim();
        String content = messageArray[1].trim();
        if (Arrays.asList(TOPICS).contains(topic)) {
             switch (operation) {
                 case "read":
                     sqlStatement = "SELECT * FROM " + topic + " WHERE NAME = '" + content + "';";
                     break;
                 case "insert":
                     sqlStatement = "INSERT INTO " + topic + " (NAME) VALUES ('" + content + "');";
                     break;
                 case "delete":
                     sqlStatement = "DELETE FROM " + topic + " WHERE NAME = '" + content + "';";
                     break;
                 default:
                     throw new OperationException("'" + operation + "' is not a valid operation!");
             }
        } else {
            throw new TopicException("'" + topic +"' is not a valid topic!");
        }

        return sqlStatement; 
    }

}
