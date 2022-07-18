import javax.crypto.spec.*;
import javax.crypto.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
class image_cipher { 
    public static void main(String[] args){
        int menu = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Key string (16 characters) or enter 0 to random key: ");
        String key_string = scanner.nextLine();
        Random rand = new Random();
        if(key_string.equals("0")){
            key_string = "";
            for(int i=0;i<16;i++)
                key_string = key_string + (char)(rand.nextInt(93) + 33);
        }
        System.out.println("Your key: " + key_string);
        //change your image path here
        String input_path = "cipher_image.jpg";
        String output_path = "Encrypt.jpg";
        while(true){
            System.out.print("Enter 0 to encrypt,any number to decrypt, -1 to exit: ");
            menu = scanner.nextInt();
            if(menu == -1){
                break;
            }
            //for output path as encrypted or decrypted image
            else if(menu != 0){
                input_path = "Encrypt.jpg";
                output_path = "Decrypt.jpg";
            }
            try{
                FileInputStream file = new FileInputStream(input_path);
                FileOutputStream outStream = new FileOutputStream(output_path);
                byte k[] = key_string.getBytes();
                SecretKeySpec key = new SecretKeySpec(k, "AES");
                Cipher enc = Cipher.getInstance("AES");
                if(menu == 0)
                    enc.init(Cipher.ENCRYPT_MODE, key);
                else
                    enc.init(Cipher.DECRYPT_MODE, key);
                CipherOutputStream cos = new CipherOutputStream(outStream, enc);
                byte[] buf = new byte[1024];
                int read;
                while((read=file.read(buf))!=-1){
                    cos.write(buf,0,read);
                }
                file.close();
                outStream.flush();
                cos.close();   
            }catch(Exception e){
            
            }
        }
        scanner.close(); 
    }
}