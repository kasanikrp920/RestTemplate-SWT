package com.rest.template.swt;

import com.rest.template.bookRentalAction.SearchResultOfBorrower;
import com.rest.template.model.Borrower;
import com.rest.template.service.RestTemplateService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchBorrowerGui {

    public SearchBorrowerGui(Display display) {

        final Display d=display;
        final Shell shell=new Shell(d);
        shell.setSize(800,550);
        shell.setText("Searching Borrower details");

        Label lblEmailId = new Label(shell, SWT.BORDER);
        lblEmailId.setText("Email Id *");
        lblEmailId.setSize(150, 30);
        lblEmailId.setLocation(10, 30);



        final Text txtEmailId = new Text(shell, SWT.BORDER);
        txtEmailId.setBounds(170, 30, 180, 30);
        txtEmailId.setTextLimit(30);



        Button btnSearch = new Button(shell, SWT.PUSH);
        btnSearch.setText("Search");
        btnSearch.setBounds(250, 130, 100, 20);

        btnSearch.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource() + "Button3 was clicked");
                try {
                    String emailId = txtEmailId.getText();


                    if(!(txtEmailId.getText().equals(""))){
                        RestTemplateService restTemplateService= new RestTemplateService();
                        Borrower borrower =restTemplateService.getBorrower(emailId,d);

                        if(borrower!=null){
                            new SearchResultOfBorrower(d,emailId);
                            shell.dispose();
                        }else{
                            txtEmailId.setText("");
                        }
                    }else{
                        MessageBox msgBox=new MessageBox(shell,SWT.CANCEL|SWT.OK);
                        msgBox.setMessage("Please Enter Email Id and Click Search");
                        msgBox.open();
                        txtEmailId.setFocus();

                    }
                } catch (Exception a) {a.printStackTrace(); }
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
