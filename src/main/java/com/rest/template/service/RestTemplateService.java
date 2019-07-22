package com.rest.template.service;


import com.rest.template.model.Book;
import com.rest.template.model.Borrower;
import com.rest.template.model.ExceptionFormat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RestTemplateService {



    private  RestTemplate restTemplate =null;

    public RestTemplateService() {
        this.restTemplate=new RestTemplate();
        MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(converter);

    }

    public static HttpHeaders getHeaders(){
        HttpHeaders headers= new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    public void  addBook(Book book, Display display){

        Shell shell=new Shell(display);
        MessageBox msgBox=new MessageBox(shell, SWT.CANCEL|SWT.OK);
        String url="http://localhost:8080/books";
        HttpHeaders headers=getHeaders();

         try {
             HttpEntity<Book> request = new HttpEntity<>(book, headers);
             ResponseEntity<Book> response = restTemplate.postForEntity(url, request, Book.class);
             System.out.println("response body ::  " + response.getBody());
             System.out.println("response status code  :: " + response.getStatusCode());
             System.out.println("response Headers ::  " + response.getHeaders());

             msgBox.setMessage("Book Inserted Successfully  ");
             msgBox.open();

         }catch(HttpStatusCodeException e){
             msgBox.setMessage("Book Insertion Failed \n"
                     +e.getResponseBodyAsString());
             msgBox.open();
         }

    }

    public List<Book> getAllBook(String url,Display display){
        Shell shell=new Shell(display);

        HttpHeaders headers=getHeaders();
        HttpEntity<String>request=new HttpEntity<>(headers);

        try {
            ResponseEntity<List<Book>> response = restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<Book>>() {
            });
            List<Book> bookList = response.getBody();
            for (Book book : bookList) {
                System.out.println(book.toString());
            }
            System.out.println("response status code ::  " + response.getStatusCode());
            System.out.println("response Headers ::  " + response.getHeaders());

            return bookList;
        }catch(HttpStatusCodeException e){
            MessageBox msgBox=new MessageBox(shell, SWT.CANCEL|SWT.OK);
            msgBox.setMessage(e.getResponseBodyAsString());
            msgBox.open();
        }catch (RestClientException ex){}
        return null;
    }

    public Book getOneBookByIsbn(long isbn,Display display) {

        Shell shell=new Shell(display);

        String url = "http://localhost:8080/book/byIsbn/"+isbn;

        HttpHeaders headers = getHeaders();

        HttpEntity<String> request = new HttpEntity<>(headers);
        try{
            ResponseEntity<Book> response=restTemplate.exchange(url, HttpMethod.GET, request, Book.class);
            System.out.println("response body  :: " + response.getBody().toString());
            System.out.println("response status code ::  " + response.getStatusCode());
            System.out.println("response Headers ::  " + response.getHeaders());

            return response.getBody();
        }catch(HttpStatusCodeException e){
            String exception=e.getResponseBodyAsString();
            MessageBox msgBox=new MessageBox(shell, SWT.CANCEL|SWT.OK);
            msgBox.setMessage(e.getResponseBodyAsString());
            msgBox.open();


            System.out.println(exception);
            System.out.println("response status code  :: "+e.getStatusCode());
            System.out.println("response status code  :: "+e.getResponseHeaders());
        }catch(RestClientException e){
            System.out.println("Connection time out");
        }

        return null;
    }

    public String deleteOneBook(long isbn) {
        String url = "http://localhost:8080/book/"+isbn;

        HttpHeaders headers=getHeaders();

        HttpEntity<Book> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
            System.out.println("response body ::  " + response.getBody());
            System.out.println("response status code  :: " + response.getStatusCode());
            System.out.println("response Headers ::  " + response.getHeaders());
            return response.getBody();
        }catch(HttpStatusCodeException e){

            String exception=e.getResponseBodyAsString();
            System.out.println(exception);
            System.out.println("response status code  :: "+e.getStatusCode());
            System.out.println("response status code  :: "+e.getResponseHeaders());
        }catch(RestClientException ex){
            System.out.println("connection time out");
        }
        return null;
    }

    public  String rentBook(Borrower borrower,Display display){
        String url = "http://localhost:8080/rent";

        HttpHeaders headers=getHeaders();

        HttpEntity<Borrower> request = new HttpEntity<>(borrower,headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            System.out.println("response body        ::  " + response.getBody());
            System.out.println("response status code ::  " + response.getStatusCode());
            System.out.println("response Headers     :: " + response.getHeaders());

            return response.getBody();

        }catch (HttpStatusCodeException e){
            String exception=e.getResponseBodyAsString();
            Shell shell = new Shell(display);
            MessageBox msgBox=new MessageBox(shell, SWT.CANCEL|SWT.OK);
            msgBox.setMessage(exception);
            msgBox.open();


        }catch(RestClientException ex){
            System.out.println("connection time out");
        }

        return null;
    }

    public String returnBook(String emailId,Display display){
        String url = "http://localhost:8080/return/"+emailId;

        HttpHeaders headers=getHeaders();

        HttpEntity<String> request = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
            System.out.println("response body        ::  " + response.getBody());
            System.out.println("response status code ::  " + response.getStatusCode());
            System.out.println("response Headers     :: " + response.getHeaders());
            return response.getBody();
        }catch(HttpStatusCodeException e){

            String exception=e.getResponseBodyAsString();
            Shell shell = new Shell(display);
            MessageBox msgBox=new MessageBox(shell, SWT.CANCEL|SWT.OK);
            msgBox.setMessage(exception);
            msgBox.open();

            System.out.println(exception);
            System.out.println("response status code  :: "+e.getStatusCode());
            System.out.println("response status code  :: "+e.getResponseHeaders());
        }catch(RestClientException ex){
            System.out.println("connection time out");
        }

        return  null;
    }

    public Borrower getBorrower(String emailId,Display display){
        Shell shell=new Shell(display);
        String url = "http://localhost:8080/borrower/"+emailId;
        HttpHeaders headers=getHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);
        try {
            ResponseEntity<Borrower> response = restTemplate.exchange(url, HttpMethod.GET, request,Borrower.class);
            System.out.println("response body        ::  " + response.getBody());
            System.out.println("response status code ::  " + response.getStatusCode());
            System.out.println("response Headers     :: " + response.getHeaders());
            return response.getBody();
        }catch(HttpStatusCodeException e){
            MessageBox msgBox=new MessageBox(shell, SWT.CANCEL|SWT.OK);
            msgBox.setMessage(e.getResponseBodyAsString());
            msgBox.open();

            String exception=e.getResponseBodyAsString();
            System.out.println(exception);
            System.out.println("response status code  :: "+e.getStatusCode());
            System.out.println("response status code  :: "+e.getResponseHeaders());
        }catch(RestClientException ex){
            System.out.println("connection time out");
        }
        return null;
    }

    public List<Borrower> getAllBorrowers(){
        String url="http://localhost:8080/borrowers";

        HttpHeaders headers=getHeaders();

        HttpEntity<String>request=new HttpEntity<>(headers);

        ResponseEntity<List<Borrower>>response=restTemplate.exchange(url, HttpMethod.GET,request, new ParameterizedTypeReference<List<Borrower>>(){});
        List<Borrower>bookList=response.getBody();
        for (Borrower borrower:bookList) {
            System.out.println(borrower.toString());
        }
        System.out.println("response status code ::  "+response.getStatusCode());
        System.out.println("response Headers ::  "+response.getHeaders());

        return response.getBody();
    }




}
