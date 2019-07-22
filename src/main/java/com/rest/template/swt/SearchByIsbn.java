package com.rest.template.swt;

import com.rest.template.bookRentalAction.SearchResultOnIsbn;
import com.rest.template.model.Book;
import com.rest.template.service.RestTemplateService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchByIsbn {

    public SearchByIsbn(Display d, String source) {
        final Display display=d;
        final String btnSource=source;
        final Shell shell = new Shell(display);
        shell.setSize(400, 300);
        shell.setText("Searching a Book");

        MessageBox msgBox=new MessageBox(shell, SWT.CANCEL|SWT.OK);


        Label lblIsbn = new Label(shell, SWT.BORDER);
        lblIsbn.setText("Enter ISBN number");
        lblIsbn.setSize(150, 20);
        lblIsbn.setLocation(10, 30);


        final Text txtIsbn = new Text(shell, SWT.BORDER);
        txtIsbn.setText("");
        txtIsbn.setBounds(170, 30, 180, 20);
        txtIsbn.setTextLimit(13);

        Button btnSearch = new Button(shell, SWT.PUSH);
        btnSearch.setText("Search");
        btnSearch.setBounds(100, 60, 100, 20);

        txtIsbn.addVerifyListener(new VerifyListener() {
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

        btnSearch.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource() + "Button3 was clicked");
                if(!(txtIsbn.getText().equals(""))){
                        long isbn=Long.parseLong(txtIsbn.getText());
                        RestTemplateService restTemplateService=new RestTemplateService();
                        Book book=restTemplateService.getOneBookByIsbn(isbn,display);
                        if(book!=null){
                            new SearchResultOnIsbn(display, book,btnSource);
                            shell.dispose();
                        }else {

                            txtIsbn.setText("");
                            txtIsbn.setFocus();
                        }
                }else{

                    msgBox.setMessage("Please enter  ISBN and click Search.");
                    msgBox.open();
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
