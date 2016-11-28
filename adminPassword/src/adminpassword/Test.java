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
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
 
        AESDemo d = new AESDemo();
             
        System.out.println("Encrypted string:" + d.encrypt("Hello"));           
        String encryptedText = d.encrypt("Hello");
        System.out.println("Decrypted string:" + d.decrypt(encryptedText));         
 
    }
    
}
