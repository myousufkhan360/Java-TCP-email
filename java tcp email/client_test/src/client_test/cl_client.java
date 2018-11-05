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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lab1
 */
 import java.io.*;
   import java.net.*;
    import java.util.*;

 class mx {
    public static void show_msg(String x_str) {
        System.out.println(x_str);
    }
}

public class cl_client {
    private int read_timeout = 500;
    private boolean sv_fail;
    private Socket conn_sk;
    private String prompt = "Enter request text.";
    private Scanner input_local;
    private Scanner in_sv;
    private PrintStream out_sv;
    
    public cl_client(){
        do_client();
    }
    public void connect_sv() throws Exception {
        conn_sk = new Socket("127.0.0.1",1341);
        in_sv = new Scanner (conn_sk.getInputStream());
        out_sv = new PrintStream (conn_sk.getOutputStream());
    }
    public String get_str() {
        input_local = new Scanner(System.in);
        mx.show_msg(prompt);
        return input_local.nextLine();
    }
    
    public String get_response() throws Exception {
        String x_result;
        x_result = in_sv.nextLine();
        return x_result;
    }
    public void do_client() {
        String x_request, x_result;
        
        try {
            x_request = get_str();
            connect_sv();
            out_sv.println(x_request);
            mx.show_msg("CLIENT : request has been sent.");
            
            x_result = get_response();
           // if(sv_fail == false)
                mx.show_msg("CLIENT : Recieved from server : ["+x_result+"]");
        }
        catch(Exception e){
            mx.show_msg("ERROR : "+e);
        }
    }
}
