package de.hsmannheim.inf.pr2.io.compress;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;



public class DecompressingInputStream extends InputStream {
    /*aufgabe 1.3
     * diese Klasse soll eine Dekomprimierung der Daten durchf�hren,
     * die vorher mit einem CompressingOutputStream komprimiert wurden*/
    public DecompressingInputStream(byte [] b) {
        oldData = b;
    }

    private byte [] oldData;
    private byte [] newData;
    //newData=new byte [oldData.length];
    int index;
    private int x = 0;
    private int pos = 0;
    private int zaehler = 1;



    @Override
    public int read() throws IOException {
        // implementierung der abstrakten methoder der oberklasse.

        //ArrayIndexOutOfBoundsException wird geworfen, Fehler derzeitig noch nicht gefunden, Z�hler z�hlt �ber die 7 hoch


        //ArrayIndexOutOfBoundsException wird geworfen, Fehler derzeitig noch nicht gefunden, Z�hler z�hlt �ber die 7 hoch
        index = 0;
        newData=new byte [oldData.length];

        //while (index < oldData.length-1){
        while (!(oldData[pos]==(-125)&&oldData[zaehler]==(-125))){
            //Betrachtet dem Fall -1 -1
            if((oldData[pos] == -1) && (oldData[zaehler] == -1)){

                decompressing(oldData[pos]);
                zaehler+= 2;
                pos += 2;

                //index += 2;
            }

            //Betrachtet z.B -1 5 8
            else if (oldData[pos] == -1 && oldData[zaehler] != -1) {

                berechneAnzahl(oldData[zaehler], oldData[pos+2]);

                zaehler += 3;
                pos += 3;

                //index += 3;

            }
            // Betrachtet einzelne Bytes

            else if (oldData[pos] != -1 && oldData[zaehler] == -1) {

                decompressing(oldData[pos]);

                pos++;
                zaehler++;

                //index++;
            }
            //Betrachtet einzelne Bytes die hintereinander laufen und sollte ein einzelnes Byte am Ende stehen
            else if ((oldData[pos] != -1 && oldData[zaehler] != -1)) {
                if (oldData[zaehler] == -125) {
                    decompressing(oldData[pos]);
                    pos++;
                    zaehler++;

                    //index++;
                }

                else {

                    decompressing(oldData[pos]);
                    decompressing(oldData[zaehler]);

                    pos += 2;
                    zaehler += 2;

                    //index += 2;

                }

            }

        }

        return 0;
    }

    // Soll aus -1 3 2  / 3 3 machen, indem er die Schleife durchl�uft und immer wieder decompressing aufruft mit der Zahl
    public void berechneAnzahl(byte zahl, byte menge) {

        for(int i = 0; i < menge; i++) {
            decompressing(zahl);
        }

    }
    // Nimmt die Zahl und speichert sie in ein neues Array ab
    public void decompressing(byte stelle) {

        if (x >= newData.length-1) {
            newData=machGroesser(newData);
        }
        newData[x] = stelle;
        System.out.print(newData[x] + " ");
        x++;
    }

    public byte [] machGroesser(byte [] klein) {
        int alt = klein.length;
        int neu = (alt*2);
        byte temp [] = new byte [neu];

        for (int i = 0; i < alt; i++) {
            temp[i] = klein[i];
        }

        return temp;

    }
    public byte[] getoldData(){
        return oldData;
    }


    public static void main(String [] args) throws IOException {

        //byte test [] = {3, 5, 3, -1, -1, 8, -1, 2, 8, -125, -125};
        //byte test [] = {-1, 5, 10, -1, -1, -1, -1, 3, 5, -125, -125};
        //byte test [] = {-1, 3, 4, -125, -125};
        //byte test [] = {-1, -1, -1, 4, 7, -125, -125};
        //byte test [] = {-1, 2, 4, 3, -125, -125};
        //byte test [] = {-1, 9, 15, -1, -1, -125, -125};
        //byte test [] = {-1, 5, 2, -1, -1, -1, -1, 3, -125, -125};
        //byte test [] = {-1, 5, 2, -1, -1, -1, -1, 3, -125, -125};
        byte test [] = {1, 2, 3, 4};

        DecompressingInputStream in = new DecompressingInputStream(test);


        in.read();


        in.close();

    }

}