package io;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class HttpPost {
  public static void main(String args[]) throws Exception { 
    int c; 
 
    // Create a socket connected to httpbin.org, port 80.
    Socket s = new Socket("httpbin.org", 80);
 
    // Obtain input and output streams. 
    InputStream in = s.getInputStream(); //поток ввода
    OutputStream out = s.getOutputStream(); //поток вывода
 
    // Construct a request string. 
    String str =
            "Post /post HTTP/1.1" + System.lineSeparator() + //второй post - это просто такой юрл
            "Host: httpbin.org" + System.lineSeparator() +
            "Connection: close" + System.lineSeparator() +
            "Content-type: application/json" + System.lineSeparator() +
            "Content-Lenght: 14"+ System.lineSeparator() + System.lineSeparator() +  //длина сообщения + 2 перевода
            "{\"text\":true}" + System.lineSeparator();

    // Convert to bytes. 
    byte[] buf = str.getBytes();
    // Send request. 
    out.write(buf); 
 
    // Read and display response. 
    while ((c = in.read()) != -1) { 
      System.out.print((char) c); 
    } 
    s.close(); 
  } 
}