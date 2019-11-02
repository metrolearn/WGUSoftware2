package wguSoftware2.utils;

import wguSoftware2.models.Active_User;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;


public class LoginLogger{

        private String filePath = null;
        private String user = null;
        private String bufferStr = null;
        private Path files = null;
        private Path path = null;
        private Active_User ac = null;


        public LoginLogger(Active_User ac, Boolean login) throws IOException {

                String fileName = "userLog.txt";
                File logFile = new File(fileName);
                logFile.createNewFile();
                String nowUTC = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).toString();
                String message;
                if(login)
                        message = "USER LOGIN "+nowUTC+": "+ac.toString();
                else
                        message = "USER LOGOFF "+nowUTC+": "+ac.toString();


                FileWriter fileWriter = new FileWriter(logFile.getCanonicalFile(), true); //Set true for append mode
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(message);  //New line
                printWriter.close();


        }



}
