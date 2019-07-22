package com.rest.template.bookRentalAction;

import com.rest.template.service.RestTemplateService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteByIsbn {

    public DeleteByIsbn(Display display){
        Display d = display;
        final Shell shell = new Shell(d);
        shell.setSize(400, 300);
        shell.setText("Deleting a Book");

        final MessageBox msgBox = new MessageBox(shell, SWT.CANCEL | SWT.OK);


        Label lblIsbn = new Label(shell, SWT.BORDER);
        lblIsbn.setText("Enter ISBN number");
        lblIsbn.setSize(150, 20);
        lblIsbn.setLocation(10, 30);


        final Text txtIsbn = new Text(shell, SWT.BORDER);
        txtIsbn.setText("");
        txtIsbn.setBounds(170, 30, 180, 20);
        txtIsbn.setTextLimit(10);
        txtIsbn.setFocus();

        Button btnDelete = new Button(shell, SWT.PUSH);
        btnDelete.setText("Delete");
        btnDelete.setBounds(100, 60, 100, 20);

        txtIsbn.addVerifyListener(new VerifyListener() {
            public void verifyText(VerifyEvent verifyEvent) {
                if (verifyEvent.text.isEmpty()) {
                    verifyEvent.doit = true;
                } else if (verifyEvent.keyCode == SWT.ARROW_LEFT || verifyEvent.keyCode == SWT.ARROW_RIGHT || verifyEvent.keyCode == SWT.BS || verifyEvent.keyCode == SWT.DEL || verifyEvent.keyCode == SWT.CTRL || verifyEvent.keyCode == SWT.SHIFT) {
                    verifyEvent.doit = true;

                } else {
                    boolean allow = false;
                    for (int i = 0; i <verifyEvent.text.length(); i++) {
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


        btnDelete.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource() + "Button3 was clicked");
                if(!(txtIsbn.getText().equals(""))){
                    int isbn = Integer.parseInt(txtIsbn.getText());
                    try{
                        RestTemplateService restTemplateService=new RestTemplateService();
                       String status= restTemplateService.deleteOneBook( isbn);
                        if(status!=null) {
                            msgBox.setMessage("The Book Has Deleted");
                            msgBox.open();
                            shell.dispose();

                        }else{
                            msgBox.setMessage("No Suitable Record Found please Give Us a Valid ISBN ");
                            msgBox.open();
                            txtIsbn.setText("");
                            txtIsbn.setFocus();

                        }


                    }catch(Exception ae){
                        ae.printStackTrace();

                    }
                }  else {
                    MessageBox msgBox = new MessageBox(shell, SWT.CANCEL | SWT.OK);
                    msgBox.setMessage("Please Enter ISBN and Click Deleted");
                    msgBox.open();
                    txtIsbn.setFocus();
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
