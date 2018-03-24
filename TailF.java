package com.bonzoi.assignment1;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * @author Pratishtha Mishra
 * Enter the input through command line argument, pass the file path as single argument, e.g. "C:\tailRead.txt": 
 *
 */
public class TailF {

	/**
	 * This method reads file in real time. Add more contents in file and save it,
	 * it will be displayed in the console. 
	 * If you overwrite few line in the file it will display the whole file content from starting. 
	 * @param file
	 * @throws IOException
	 * @throws InterruptedException
	 */
    private static void tail(File file) throws IOException, InterruptedException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

        long lengthOfFile = randomAccessFile.length();
        long counterFromEnd = 1L;
        int newlineCounter = 0;
        long tailPosition = 0L;
        while (counterFromEnd <= lengthOfFile) {
            randomAccessFile.seek(lengthOfFile - counterFromEnd);
            if (randomAccessFile.readByte() == '\n') {
                newlineCounter++;
            }
            if (newlineCounter == 10) {
                tailPosition = randomAccessFile.getFilePointer();
                break;
            }
            counterFromEnd++;
        }

        while (true) {
            randomAccessFile.seek(tailPosition);
            int l = (int) (lengthOfFile - tailPosition);
            if (l == 0) {
                if (file.exists()) {
                    randomAccessFile.close();
                    randomAccessFile = new RandomAccessFile(file, "r");
                    Thread.sleep(1000); // takes some time to read the file
                    lengthOfFile = randomAccessFile.length();
                    if (lengthOfFile < tailPosition) {
                        // Lines in file have been deleted or file has been overwritten
                        // Reading log from start
                        tailPosition = 0;
                        continue;
                    }
                }
                continue;
            }

            byte[] buf = new byte[l];
            randomAccessFile.read(buf, 0, l);
            String out = new String(buf, 0, l);
            System.out.print(out);
            tailPosition = tailPosition + l;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 1) {
            System.out.println("Pass the file path as a single argument. Exiting...");
            System.exit(0);
        }

        File file = new File(args[0]);
        tail(file);
    }
}