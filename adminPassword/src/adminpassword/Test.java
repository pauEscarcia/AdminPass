/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpassword;

/**
 *
 * @author Exelsion
 */
public class Test {
    
    public static void main(String[] args) throws Exception{
   
        Encryption en=new Encryption();
        //String encryptedWord=en.encrypt("Test");
        //System.out.println("Encrypted word is : " + encryptedWord);
       
        //Decryption de =new Decryption();        
        //System.out.println("Decrypted word is : "+de.decrypt(encryptedWord));
    }
    /*
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
 
        AESDemo d = new AESDemo();
             
        System.out.println("Encrypted string:" + d.encrypt("Hello"));           
        String encryptedText = d.encrypt("Hello");
        System.out.println("Data Type:" + encryptedText.getClass().getName());
        System.out.println(encryptedText.getBytes().toString());
        System.out.println("Decrypted string:" + d.decrypt(encryptedText));         
 
    }*/
    
}
