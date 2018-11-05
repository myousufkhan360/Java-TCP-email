/* This program implements a server java application
that uses the TCP protocol to communicate with multiple
clients. When the execution of the server application 
is started, the server application waits for suucessive
clients to establish a TCP connection with the server.
After a client has established a TCP connection with 
the server, the SERVER waits FOR A REQUEST from that
client. The server receives the request in the form of
an input message.  A REQUEST may REQUIRE that the server 
should perform SOME TASK based on the contents of the 
input message. A SIMPLE EXAMPLE of a requested task is 
the conversion of input string into uppercase format. 
After receiving a request from some client, the server 
performs the required task and sends back the result to 
the client (in the form of an output message). After 
sending the result to a client the server closes the
connection with that client and waits for another client
to connect with the server.
The LOGIC of the SERVER APPLICATION has been SIMPLIFIED so
that the student can EASILY UNDERSTANDS the BASICS of
communication between a CLIENT java application and SERVER
java applications via TCP protocol. */

package tcp_server_test;
import java.io.*;
import java.net.*;
import java.util.*;
class mx {
    public static void show_msg(String x_str) {
        System.out.println(x_str);
    }
}

public class cl_server {
     private ServerSocket svsk;
     private Scanner in_client;
     private PrintStream out_client;
     private int client_count;
     public cl_server() {
         do_server();
     }
     public void intro_sv() {
         try {
            InetAddress x_addr = InetAddress.getLocalHost();
            mx.show_msg("SERVER ADDRESS IS: " + x_addr.getHostAddress());
            mx.show_msg("SERVER HOST NAME IS: " + x_addr.getHostName());
         }
         catch(Exception e) {
            mx.show_msg("ERROR: " + e); 
         }
     }
     public void set_sv() throws Exception {
        intro_sv();
        svsk = new ServerSocket(1341);
        
     }
     
     public void set_io(Socket x_sk) {
        try {
            in_client = new Scanner(x_sk.getInputStream());
            out_client = new PrintStream(x_sk.getOutputStream());
        }
        catch(Exception e) {
            mx.show_msg("ERROR: " + e);
        }
    }
    public void proc_client(Socket x_sk) {
        String x_request, x_result;
        try {
            client_count++;
            set_io(x_sk);
            x_request=in_client.nextLine();
            mx.show_msg("SERVER: Received request: [" + x_request + "]");
            x_result = x_request.toUpperCase();
            //Thread.sleep(1000);
            out_client.println(x_result);
            mx.show_msg("SERVER: Result has been sent.");
            x_sk.close();
        }
        catch(Exception e) {
            mx.show_msg("ERROR: " + e);
        }
                
    }
    
     
     public void do_server() {
         
        Socket x_sk;
        try { 
            set_sv();
            client_count = 0;
            while (client_count < 6) {
                try {
                    x_sk = svsk.accept();
                    proc_client(x_sk);
                }
                catch(Exception e) {
               
                }
            }
        }
        catch(Exception e) {
            mx.show_msg("SERVER: ERROR");
        }
        mx.show_msg("SERVER: Server stopped.");
        
     }
     
     
 }