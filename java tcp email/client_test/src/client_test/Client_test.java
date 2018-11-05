/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client_test;

/**
 *
 * @author lab1
 */
public class Client_test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        try {
        cl_client ab = new cl_client();
        ab.do_client();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    
}
