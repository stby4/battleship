package battleship.data;


import java.io.*;

/**
 * BinaryFile
 * @author Hinrich Kaestner
 */
abstract class BinaryFile {

    byte[] read(String filename) throws IOException{
        File file = new File(filename+".bin");
        FileInputStream input = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        //noinspection ResultOfMethodCallIgnored
        input.read(data);
        input.close();
        return data;
    }

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
