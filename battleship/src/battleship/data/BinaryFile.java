package battleship.data;

import java.io.*;

/**
 * Data abstraction object for reading and writing binary files
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
abstract class BinaryFile {

    /**
     * reads the content of the given file
     *
     * @param filename The file name of the file that will be read from, without the ".bin" ending
     * @return byte array
     * @throws IOException
     */
    byte[] read(String filename) throws IOException{
        File file = new File(filename+".bin");
        FileInputStream input = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        //noinspection ResultOfMethodCallIgnored
        input.read(data);
        input.close();
        return data;
    }

    /**
     * writes the given content to the specified file
     *
     * @param filename The file name of the file that will be written to, without the ".bin" ending.
     * @param data bye array that will be written into the file
     * @throws IOException
     */
    void write(String filename, byte[] data) throws IOException {
        File file = new File(filename+".bin");
        FileOutputStream output = new FileOutputStream(file);
        output.write(data, 0, data.length);
        output.flush();
        output.close();
    }

    //abstract public void store(Object object);
    //abstract public Object get(Object object);

}
