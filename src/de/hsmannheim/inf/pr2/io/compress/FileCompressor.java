package de.hsmannheim.inf.pr2.io.compress;

import java.io.*;

public class FileCompressor {
	/*aufgabe 1.4
	 *Diese nimmt von der Kommandozeile zwei
	Argumente an, die zwei Dateien bezeichnen. Die zuerst angegebene Datei wird mit-
	hilfe eines CompressingOutputStream s komprimiert und das Ergebnis wird in die als
	zweite Option angegebene Datei geschrieben. Denken Sie an eine sinnvolle Fehlerbe-
	handlung.
	Alternativ kann man die Klasse auch ohne Optionen aufrufen. In diesem Fall werden
	die Daten von der Standard-Eingabe gelesen und das komprimierte Ergebnis auf die
	Standard-Ausgabe geschrieben */
    public static void main(String[] args) throws IOException {

        // Adressen der Dateien
        String quelle = "C:\\Users\\alena\\Google Drive\\IB3\\PR2\\Code\\Programmieren2\\src\\de\\hsmannheim\\inf\\pr2\\io\\compress\\test.txt"; // Quell und
        String ziel = "C:\\Users\\alena\\Google Drive\\IB3\\PR2\\Code\\Programmieren2\\src\\de\\hsmannheim\\inf\\pr2\\io\\compress\\write.txt";     // Zieldatei

        OutputStream out=new FileOutputStream("example");
        CompressingOutputStream cos;

        byte []result = makeFiletoByteArry(quelle);
        cos= new CompressingOutputStream(result, new FilterOutputStream( out));
        cos.setRawData(result);
        cos.resizeArray(result);
        cos.compressToArray();
        byte [] finsh = cos.getZipData();

        writeByteinFile(finsh,ziel);


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
        File data = new File(dateiname);
        byte[] array = new byte[(int) data.length()];
        InputStream fis = new FileInputStream(dateiname);
        try {
            int daten;
            int i= 0;
            while ((daten = fis.read()) > -1) {
                byte b = (byte) daten;
                array[i]= b;
                i++;
            }
            fis.close();
        }
        catch (FileNotFoundException e){
            e.getMessage();
        }
        return array;
    }
}
