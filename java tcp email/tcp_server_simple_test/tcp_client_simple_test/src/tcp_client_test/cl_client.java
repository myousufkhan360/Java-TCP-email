/* This program implements a client java application that uses
the TCP protocol to communicate with a server java application
First the client establishes a TCP connection with the server.
After that, the CLIENT SENDS A REQUEST (in the form of an
output message) to the server application for performing SOME
TASK. A SIMPLE EXAMPLE of a TASK performed by the server may 
be the conversion of the message string into uppercase format.
After receiving a request the server performs the required 
task and sends back the result to the client. The client 
receives the result in the form of an input message.
Normallly, the client runs on a network location (computer) that
is different from the network location of the server. However, it
is possible that the client and server both execute on the same 
network location (especially during testing). 
The client and server applications are created as separate 
NetBeans projects. */


package  tcp_client_test;
import java.io.*;
import java.net.*;
import java.util.*;


class mx {
    public static void show_msg(String x_str) {
        System.out.println(x_str);
    }
}
public class cl_client  {
    private int read_timeout = 500;
    private boolean sv_fail;
    private Socket conn_sk;
    private String prompt = "Enter request text.";
    private Scanner input_local;
    private Scanner in_sv;
    private PrintStream out_sv;
    
    public cl_client() {
        do_client();
    }
    public void connect_sv() throws Exception {
        conn_sk = new Socket("127.0.0.1",1341);
        in_sv=new Scanner(conn_sk.getInputStream()) ;
        out_sv=new PrintStream(conn_sk.getOutputStream());
        conn_sk.setSoTimeout(500); 
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
            mx.show_msg("CLIENT: Request has been sent.");
        
            x_result = get_response();
            mx.show_msg("CLIENT: Received from server: [" 
                                                + x_result+"]");
        
        }
        catch(Exception e) {
            mx.show_msg("ERROR: " + e);
        }
    }
}
