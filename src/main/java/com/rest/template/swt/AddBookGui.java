package com.rest.template.swt;

import com.rest.template.model.Book;
import com.rest.template.service.RestTemplateService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class AddBookGui {


    public static Text txtISBN;
    public static Text txtStock;
    public static Text txtId;
    public AddBookGui(Display d) {
        final Display display=d;
        final Shell shell = new Shell(display);
        shell.setSize(800, 500);
        shell.setBackgroundImage(new Image(d, "C:\\Users\\raghu\\Desktop\\add.jpg"));
        shell.setText("Adding Books into Catalog");

        final MessageBox msgBox=new MessageBox(shell, SWT.CANCEL|SWT.OK);

        Label lblAuthorName = new Label(shell, SWT.BORDER);
        lblAuthorName.setText("Author Name *");
        lblAuthorName.setSize(160, 30);
        lblAuthorName.setLocation(10, 30);

        Label lblBookTitle = new Label(shell, SWT.BORDER);
        lblBookTitle.setText("Book Title and Edition *");
        lblBookTitle.setSize(160, 30);
        lblBookTitle.setLocation(10, 80);

        Label lblIsbn = new Label(shell, SWT.BORDER);
        lblIsbn.setText("ISBN *");
        lblIsbn.setSize(160, 30);
        lblIsbn.setLocation(10, 130);


        Label lblIsAcademicOrNot = new Label(shell, SWT.BORDER);
        lblIsAcademicOrNot.setText("Is Academic Or Not *");
        lblIsAcademicOrNot.setSize(160, 30);
        lblIsAcademicOrNot.setLocation(10, 180);


        Label lblStock = new Label(shell, SWT.BORDER);
        lblStock.setText("Stock *");
        lblStock.setSize(160, 30);
        lblStock.setLocation(10, 230);




        final Text txtAuthorName = new Text(shell, SWT.BORDER);
        txtAuthorName.setText("");
        txtAuthorName.setBounds(180, 30, 180, 30);
        txtAuthorName.setTextLimit(20);
        txtAuthorName.setFocus();

        final Text txtBookTitle = new Text(shell, SWT.BORDER);
        txtBookTitle.setText("");
        txtBookTitle.setBounds(180, 80, 180, 30);
        txtBookTitle.setTextLimit(20);

        txtISBN = new Text(shell, SWT.BORDER);
        txtISBN.setText("");
        txtISBN.setBounds(180, 130, 180, 30);
        txtISBN.setTextLimit(13);

        final Combo isAcademic = new Combo(shell, SWT.READ_ONLY);
        isAcademic.setBounds(180, 180, 180, 30);
        String[] items1 = {"YES", "NO"};
        isAcademic.setItems(items1);

        txtStock = new Text(shell, SWT.BORDER);
        txtStock.setText("");
        txtStock.setBounds(180, 230, 180, 30);
        txtStock.setTextLimit(20);


        Button btnAdd = new Button(shell, SWT.PUSH);
        btnAdd.setText("Add");
        btnAdd.setLocation(230, 280);
        btnAdd.setSize(100, 30);

        verifyISBN();
        verifyStock();



        btnAdd.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                try {

                    if (!(txtAuthorName.getText().equals("")) && !(txtBookTitle.getText().equals("")) && !(txtISBN.getText().equals("")) && !(isAcademic.getText().equals("")) && !(txtStock.getText().equals(""))) {

                        Book book=new Book(Long.parseLong(txtISBN.getText()),txtAuthorName.getText(),txtBookTitle.getText(),isAcademic.getText(),Integer.parseInt(txtStock.getText()),Integer.parseInt(txtStock.getText()),0);
                        RestTemplateService restTemplateService=new RestTemplateService();
                        restTemplateService.addBook(book,display);
                        shell.dispose();

                    }else{
                        msgBox.setMessage("Please Fill All  Mandatory (*) Fields and Click Add");
                        msgBox.open();
                    }


                } catch (Exception q) {
                    q.printStackTrace();
                }
            }
        });

        shell.open();


        shell.addListener(SWT.CLOSE, new Listener() {
            @Override
            public void handleEvent(Event event) {
                shell.dispose();
            }
        });


    }
    public static void verifyISBN(){

        txtISBN.addVerifyListener(new VerifyListener() {
            public void verifyText(VerifyEvent verifyEvent) {
                if (verifyEvent.text.isEmpty()) {
                    verifyEvent.doit = true;
                } else if (verifyEvent.keyCode == SWT.ARROW_LEFT || verifyEvent.keyCode == SWT.ARROW_RIGHT || verifyEvent.keyCode == SWT.BS || verifyEvent.keyCode == SWT.DEL || verifyEvent.keyCode == SWT.CTRL || verifyEvent.keyCode == SWT.SHIFT) {
                    verifyEvent.doit = true;

                } else {
                    boolean allow = false;
                    for (int i = 0; i < verifyEvent.text.length(); i++) {
                        char x = verifyEvent.text.charAt(i);
                        allow = Character.isDigit(x) || Character.isWhitespace(x);
                        if (!allow) {
                            break;
                        }
                    }
                    verifyEvent.doit = allow;
                }

            }
        });

    }
    public static void verifyStock(){
        txtStock.addVerifyListener(new VerifyListener() {
            public void verifyText(VerifyEvent verifyEvent) {
                if (verifyEvent.text.isEmpty()) {
                    verifyEvent.doit = true;
                } else if (verifyEvent.keyCode == SWT.ARROW_LEFT || verifyEvent.keyCode == SWT.ARROW_RIGHT || verifyEvent.keyCode == SWT.BS || verifyEvent.keyCode == SWT.DEL || verifyEvent.keyCode == SWT.CTRL || verifyEvent.keyCode == SWT.SHIFT) {
                    verifyEvent.doit = true;

                } else {
                    boolean allow = false;
                    for (int i = 0; i < verifyEvent.text.length(); i++) {
                        char x = verifyEvent.text.charAt(i);
                        allow = Character.isDigit(x) || Character.isWhitespace(x);
                        if (!allow) {
                            break;
                        }
                    }
                    verifyEvent.doit = allow;
                }

            }
        });
    }


}
