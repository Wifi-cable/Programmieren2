package de.hsmannheim.inf.pr2.io.compress;

import java.io.*;
import java.util.Scanner;

public class FileCompressor extends CompressingOutputStream{

    public FileCompressor(OutputStream out) throws FileNotFoundException, IOException {
        super(out);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Wo soll die byts gelesen werden? ");
            String quelle = sc.nextLine();
            System.out.println("Wo soll die komprimierten geschriebnen werden? ");
            String ziel = sc.nextLine();

            fileCompress(quelle,ziel);

        }catch (FileNotFoundException e){
            //Gibt dem benutzer bescheid, dass er die Parrameter eingeben muss
            if (args.length == 0) {
                System.out.println("Geben Sie beim Start in Kommandozeilenargumente an!");
            }
            //Argumente werden aufegenommen und verarbeitet
            else {
                String text = "";
                // Ausgabe der Kommandozeilenargumente
                for (int i = 0; i < args.length; i++) {
                    text += args[i];
                }
                byte[] alternativArray = text.getBytes();
                System.out.println("Wo sollen die eingegebenen Byts reingeschrieben werden? ");
                String freadCommends = sc.nextLine();
                System.out.println("Wo soll die komprimierten geschriebnen werden? ");
                String fwriteCommends = sc.nextLine();
                File fread = new File(freadCommends);
                File fwrite = new File((fwriteCommends));
                //Da er keine Files gab werden neue erstellt
                if (fread.createNewFile() & fwrite.createNewFile()) {
                    System.out.println("File is created!");
                } else {
                    System.out.println("File already exists.");
                }
                writeByteinFile(alternativArray, freadCommends);
                fileCompress(freadCommends, fwriteCommends);
            }
        }

    }

    /**
     * Nutz von CompressingOutputStream die methoden um damit das Array, dass gelesen wird, zu Kompremiren und
     * schreibt in eine andere Datei
     * @param read
     * @param write
     * @throws IOException
     */
    public static void fileCompress (String read, String write) throws IOException{

        OutputStream out=new FileOutputStream(write);
        CompressingOutputStream cos;

        byte []result = makeFiletoByteArry(read);
        cos= new CompressingOutputStream(result, new FilterOutputStream( out));
        cos.setRawData(result);
        cos.resizeArray(result);
        cos.compressToArray();
        byte [] finsh = cos.getZipData();

        writeByteinFile(finsh,write);


        cos.close();
        out.close();
    }

    /**
     * Schreibt das Array in die angegebenen Datei.
     * @param array
     * @param name
     * @throws IOException
     */
    public static void writeByteinFile(byte[] array, String name) throws IOException {

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(name)));

        for (int i = 0; i < array.length; i++){
            out.writeByte(array[i]);
           System.out.print(array[i]+" ");
        }
        out.close();
    }

    /**
     * Macht aus der angegebenen Datei ein Array.
     * @param dateiname
     * @return
     * @throws IOException
     */
    public static byte[] makeFiletoByteArry (String dateiname) throws IOException {
        File datei = new File(dateiname);
        byte[] array = new byte[(int) datei.length()];
        InputStream fis = new FileInputStream(datei);
        try {
            int daten;
            int i= 0;
            while ((daten = fis.read(array)) > -1) {

            }
            fis.close();
        }
        catch (FileNotFoundException e){
            e.getMessage();
        }
        return array;
    }
}
