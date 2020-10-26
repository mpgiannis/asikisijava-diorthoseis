import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class askisi {
    public static void main(String[] args)  {

        System.out.println("Δώσε αρχείο dataset");
        Scanner eisodos_xristi = new Scanner(System.in);
        String eisodos_data;
        eisodos_data = eisodos_xristi.nextLine();
        File arxeio_data = new File(eisodos_data);
        if(arxeio_data.exists()) {
            System.out.println("Δώσε αρχείο config");
            String eisodos_config;
            eisodos_config = eisodos_xristi.nextLine();
            File arxeio_config = new File(eisodos_config);
            if(arxeio_config.exists()) {
                System.out.println("Δώσε παραγώμενο αρχείο");
                String eisodos_paragomeno;
                eisodos_paragomeno = eisodos_xristi.nextLine();




            try {
                Scanner data = new Scanner(new FileReader(arxeio_data));
                data.useDelimiter("\\t");
                int nooflines = 0;
                Scanner data1 = new Scanner(new FileReader(arxeio_config));
                while (data1.hasNextLine()) {
                    data1.nextLine();
                    nooflines++;
                }
                data1.close();
                Scanner config = new Scanner(new FileReader(arxeio_config));
                int[] array = new int[nooflines];
                String line = data.nextLine();

                int k = 0;
                int arithmos_stilon=line.split("\t").length;
                String[] pinakas =line.split("\t");

                while (config.hasNextLine()) {
                    String confi = config.nextLine();
                    System.out.println(confi);
                    for (int i = 0; i < arithmos_stilon; i++) {
                        if (pinakas[i].equals(confi)) {
                            array[k] = i;
                        }
                    }
                    k++;
                }

                FileWriter newfile = new FileWriter(eisodos_paragomeno);
                newfile.write(line + '\n');


                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                SecretKey skey = kgen.generateKey();
                Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
                ci.init(Cipher.ENCRYPT_MODE, skey);

                while (data.hasNextLine()) {
                    line = data.nextLine();
                    String[] pinakas1 =line.split("\t");

                    for (int i = 0; i < arithmos_stilon; i++) {
                        if (i == array[0] || i == array[1] || i == array[2]) {
                            byte[] input = pinakas1[i].getBytes("UTF-8");
                            byte[] encoded = ci.doFinal(input);
                            newfile.write(String.valueOf(encoded) + '\t');

                        } else newfile.write(pinakas1[i] + '\t');

                    }
                    newfile.write('\n');



                }

                newfile.close();


                data.close();
                config.close();

            }
            catch (IOException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException e) {
                e.printStackTrace();
            }
            catch (BadPaddingException e) {
                e.printStackTrace();
            }
            catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }










        }
            else System.out.println("Δεν βρέθηκε το αρχείο");
        }
        else{
            System.out.println("Δεν βρέθηκε το αρχείο");
            System.exit(0); }
        }


    }

