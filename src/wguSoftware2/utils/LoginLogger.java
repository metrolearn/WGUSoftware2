package wguSoftware2.utils;


import java.io.File;
import java.io.FileWriter;

public class LoginLogger {

        private File file =null;
        private String content = null;
        private FileWriter fileWriter =null;
        private
        String content = "This is my content which would be appended " +
                "at the end of the specified file";
        //Specify the file name and path here
         file =new File("C://myfile.txt");

        /* This logic is to create the file if the
         * file is not already present
         */
        if(!file.exists()){
            file.createNewFile();
        }

        //Here true is to append the content to file
        this.f = new FileWriter(file,true);
        //BufferedWriter writer give better performance
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        //Closing BufferedWriter Stream
        bw.close();

        System.out.println("Data successfully appended at the end of file");

    }catch(IOException ioe){
        System.out.println("Exception occurred:");
        ioe.printStackTrace();
    }


}
