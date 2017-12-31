package de.hsmannheim.inf.pr2.io.compress;

import java.io.*;
import java.util.Scanner;

public class FileDecompressor extends DecompressingInputStream {

    public FileDecompressor(byte[] b) {
        super(b);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Wo soll die byts gelesen werden? ");
            String quelle = sc.nextLine();
            System.out.println("Wo soll die komprimierten geschriebnen werden? ");
            String ziel = sc.nextLine();

            fileDeompress(quelle, ziel);


        } catch (FileNotFoundException e) {
            //Gibt dem benutzer bescheid, dass er die Parrameter eingeben muss
            if (args.length == 0) {
                System.out.println("Geben Sie beim Start in Kommandozeilenargumente an!");
                //Argumente werden aufegenommen und verarbeitet
            } else {

                String text = "";
                // Ausgabe der Kommandozeilenargumente
                for (int i = 0; i < args.length; i++) {
                    text += args[i];
                }

                byte[] alternativArray = text.getBytes();

                System.out.println("Wo sollen die eingegebenen Byts reingeschrieben werden?  ");
                String freadCommends = sc.nextLine();
                System.out.println("Wo soll die komprimierten geschrieben werden? ");
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
                fileDeompress(freadCommends, fwriteCommends);
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
    public static void fileDeompress(String read, String write) throws IOException {

        byte[] result = makeFiletoByteArry(read);
        DecompressingInputStream dis = new DecompressingInputStream(result);
        dis.machGroesser(result);
        dis.read();
        byte[] finsh = dis.getoldData();

        writeByteinFile(finsh, write);


        dis.close();
    }

    /**
     * Schreibt das Array in die angegebenen Datei.
     *
     * @param array
     * @param name
     * @throws IOException
     */
    public static void writeByteinFile(byte[] array, String name) throws IOException {

        File file = new File(name);
        FileOutputStream out = new FileOutputStream(file);

        for (int i = 0; i < array.length; i++) {
            out.write(array[i]);
        }
        out.close();
    }

    /**
     * Macht aus der angegebenen Datei ein Array.
     *
     * @param dateiname
     * @return
     * @throws IOException
     */
    public static byte[] makeFiletoByteArry(String dateiname) throws IOException {
        File data = new File(dateiname);
        byte[] array = new byte[(int) data.length()];
        InputStream fis = new FileInputStream(dateiname);
        try {
            int daten;
            int i = 0;
            while ((daten = fis.read()) > -1) {
                byte b = (byte) daten;
                array[i] = b;
                i++;
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return array;
    }
}

	/*
	 * aufgabe 1.5
	 * Diese nimmt von der Kommandozeile zwei
	Argumente an, die zwei Dateien bezeichnen. Die zuerst angegebene Datei wird mit-
	hilfe eines DecompressingInputStream s dekomprimiert und das Ergebnis wird in die
	als zweite Option angegebene Datei geschrieben. Denken Sie an eine sinnvolle Fehler-
	behandlung.
	Alternativ kann man die Klasse auch ohne Optionen aufrufen. In diesem Fall werden
	die Daten von der Standard-Eingabe gelesen und das dekomprimierte Ergebnis auf die
	*/