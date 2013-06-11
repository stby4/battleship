package battleship.data;


import java.io.*;

/**
 * binaryFile
 * @author Hinrich Kaestner
 */
abstract public class binaryFile {

    public byte[] read(String filename) throws IOException{
        File file = new File(filename+".bin");
        FileInputStream input = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        input.read(data);
        input.close();
        return data;
    }

    public void write(String filename, byte[] data) throws IOException {
        File file = new File(filename+".bin");
        FileOutputStream output = new FileOutputStream(file);
        output.write(data, 0, data.length);
        output.flush();
        output.close();
    }

    //abstract public void store(Object object);
    //abstract public Object get(Object object);

}
