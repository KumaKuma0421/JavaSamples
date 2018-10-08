package Execute;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteSample {
    public boolean processSample(String[] executeFile) {
        try {
            var myProcessBuilder = new ProcessBuilder(executeFile);
            myProcessBuilder.redirectErrorStream(true);

            var environment = myProcessBuilder.environment();
            var homeDir = environment.get("HOME");
            myProcessBuilder.directory(new File(homeDir));

            System.out.println("HOME=" + environment.get("HOME"));

            var myProcess = myProcessBuilder.start();
            var myInputStream = myProcess.getInputStream();
            try {
                var myInputStreamReader = new InputStreamReader(myInputStream);
                var myBufferedReader = new BufferedReader(myInputStreamReader);
                String line;
                do {
                    line = myBufferedReader.readLine();
                    System.out.println(line);
                } while (line != null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            myProcess.waitFor();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return true;
    }
}
