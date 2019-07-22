package com.rest.template.swt;

import com.rest.template.model.Borrower;
import com.rest.template.service.RestTemplateService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

import java.util.Date;

public class BorrowerGui {

        public static Text txtISBN;
        public static Text txtBookTitle;
        public static Text txtAuthor;
        public static Text txtAvailable;
        public BorrowerGui(Display d, String isbn, String title, String author, String available) {
            final Display display=d;
            final String ISBN=isbn;

            final Shell shell = new Shell(display);
            shell.setSize(800, 600);
            shell.setText("Inserting Borrower Details.");
            shell.setBackgroundImage(new Image(display,"C:\\Users\\raghu\\Desktop\\borrowBook.jpg"));

            final MessageBox msgBox=new MessageBox(shell, SWT.CANCEL|SWT.OK);


            Label lblBookIsbn = new Label(shell, SWT.BORDER);
            lblBookIsbn.setText("Book ISBN *");
            lblBookIsbn.setSize(150, 30);
            lblBookIsbn.setLocation(10, 30);


            Label lblBookTitle = new Label(shell, SWT.BORDER);
            lblBookTitle.setText("Book Title ");
            lblBookTitle.setSize(150, 30);
            lblBookTitle.setLocation(10, 80);

            Label lblAuthor = new Label(shell, SWT.BORDER);
            lblAuthor.setText("Author Name ");
            lblAuthor.setSize(150, 30);
            lblAuthor.setLocation(10, 130);

            Label lblAvailable = new Label(shell, SWT.BORDER);
            lblAvailable.setText("Available");
            lblAvailable.setSize(150, 30);
            lblAvailable.setLocation(10, 180);

            Label lblFirstName= new Label(shell, SWT.BORDER);
            lblFirstName.setText("First Name *");
            lblFirstName.setSize(150, 30);
            lblFirstName.setLocation(10, 230);

            Label lblLastName = new Label(shell, SWT.BORDER);
            lblLastName.setText("Last Name *");
            lblLastName.setSize(150, 30);
            lblLastName.setLocation(10, 280);


            Label lblEmailId= new Label(shell, SWT.BORDER);
            lblEmailId.setText("Email Id *");
            lblEmailId.setSize(150, 30);
            lblEmailId.setLocation(10, 330);


            Label lblNumOfBooks = new Label(shell, SWT.BORDER);
            lblNumOfBooks.setText("Number of Days *");
            lblNumOfBooks.setSize(150, 30);
            lblNumOfBooks.setLocation(10, 380);


            txtISBN = new Text(shell, SWT.READ_ONLY|SWT.BORDER);
            txtISBN.setText(ISBN);
            txtISBN.setBounds(170, 30, 180, 30);
            txtISBN.setTextLimit(20);

            txtBookTitle= new Text(shell, SWT.READ_ONLY|SWT.BORDER);
            txtBookTitle.setText( title);
            txtBookTitle.setBounds(170, 80, 180, 30);
            txtBookTitle.setTextLimit(20);

            txtAuthor= new Text(shell, SWT.READ_ONLY|SWT.BORDER);
            txtAuthor.setText(author);
            txtAuthor.setBounds(170, 130, 180, 30);
            txtAuthor.setTextLimit(20);

            txtAvailable= new Text(shell, SWT.READ_ONLY|SWT.BORDER);
            txtAvailable.setText(available);
            txtAvailable.setBounds(170, 180, 180, 30);
            txtAvailable.setTextLimit(20);

            final Text txtFirstName = new Text(shell, SWT.BORDER);
            txtFirstName.setText("");
            txtFirstName.setBounds(170, 230, 180, 30);
            txtFirstName.setTextLimit(20);
            txtFirstName.setFocus();


            final Text txtLastName = new Text(shell, SWT.BORDER);
            txtLastName.setText("");
            txtLastName.setBounds(170, 280, 180, 30);
            txtLastName.setTextLimit(30);

            final Text txtEmailId = new Text(shell, SWT.BORDER);
            txtEmailId.setText("");
            txtEmailId.setBounds(170, 330, 180, 30);
            txtEmailId.setTextLimit(20);

            final Text txtNumOfDays = new Text(shell, SWT.BORDER);
            txtNumOfDays.setText("");
            txtNumOfDays.setBounds(170, 380, 180, 30);
            txtNumOfDays.setTextLimit(10);


            Button btnRentBook = new Button(shell, SWT.PUSH);
            btnRentBook.setText("Rent Book");
            btnRentBook.setLocation(160, 430);
            btnRentBook.setSize(100, 30);

            // AddBookGui.verifyISBN();

            txtISBN.addVerifyListener(new VerifyListener() {
                @Override
                public void verifyText(VerifyEvent verifyEvent) {
                    if(verifyEvent.text.isEmpty()){
                        verifyEvent.doit=true;
                    }else if(verifyEvent.keyCode==SWT.ARROW_LEFT||verifyEvent.keyCode==SWT.ARROW_RIGHT||verifyEvent.keyCode==SWT.BS||verifyEvent.keyCode==SWT.CTRL||verifyEvent.keyCode==SWT.DEL||verifyEvent.keyCode==SWT.SHIFT)
                    {
                        verifyEvent.doit=true;
                    }else{
                        boolean allow=false;
                        for(int k=0;k<verifyEvent.text.length();k++){
                            char c = verifyEvent.text.charAt(k);
                            allow=Character.isDigit(c)||Character.isWhitespace(c);
                            if(!allow){
                                break;
                            }
                        }
                        verifyEvent.doit=allow;
                    }
                }
            });

            txtNumOfDays.addVerifyListener(new VerifyListener() {
                @Override
                public void verifyText(VerifyEvent verifyEvent) {
                    if(verifyEvent.text.isEmpty()){
                        verifyEvent.doit=true;
                    }else if(verifyEvent.keyCode==SWT.ARROW_LEFT||verifyEvent.keyCode==SWT.ARROW_RIGHT||verifyEvent.keyCode==SWT.BS||verifyEvent.keyCode==SWT.CTRL||verifyEvent.keyCode==SWT.DEL||verifyEvent.keyCode==SWT.SHIFT)
                    {
                        verifyEvent.doit=true;
                    }else{
                        boolean allow=false;
                        for(int k=0;k<verifyEvent.text.length();k++){
                            char c = verifyEvent.text.charAt(k);
                            allow=Character.isDigit(c)||Character.isWhitespace(c);
                            if(!allow){
                                break;
                            }
                        }
                        verifyEvent.doit=allow;
                    }
                }
            });



            btnRentBook.addSelectionListener(new SelectionAdapter() {
                Borrower borrower = new Borrower();

                public void widgetSelected(SelectionEvent e) {
                    try{
                        if(!(txtISBN.getText().equals(""))&&!(txtFirstName.getText().equals(""))&&!(txtLastName.getText().equals(""))&&!(txtNumOfDays.getText().equals(""))) {
                            Date date=new Date();
                            String currentDate=date.toString();
                            Borrower borrower=new Borrower(Long.parseLong(txtISBN.getText()),txtFirstName.getText(),txtLastName.getText(),
                                    txtEmailId.getText(),Integer.parseInt(txtNumOfDays.getText()),currentDate,0);

                            RestTemplateService restTemplateService=new RestTemplateService();
                           String status= restTemplateService.rentBook(borrower,d);
                            if (status!=null) {
                                msgBox.setMessage("The Borrower Details are Inserted..\n Book Rented successfully");
                                msgBox.open();
                                shell.dispose();
                            } else {
                                txtEmailId.setText("");
                                txtEmailId.setFocus();
                            }

                        }else{
                            msgBox.setMessage("Please Fill All Mandatory  Fields(*) and Click Rent Book");
                            msgBox.open();

                        }
                    }catch (Exception q){
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


    }
