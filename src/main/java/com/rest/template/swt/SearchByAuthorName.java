package com.rest.template.swt;

import com.rest.template.bookRentalAction.SearchResultOnAuthorName;
import com.rest.template.bookRentalAction.SearchResultOnIsbn;
import com.rest.template.model.Book;
import com.rest.template.service.RestTemplateService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SearchByAuthorName {

    public SearchByAuthorName(Display d, String source){

        final Display display=d;
        final Shell shell = new Shell(display);
        shell.setSize(400, 300);
        shell.setText("Searching a Book ");
        final String btnSource=source;

        Label lblIsbn = new Label(shell, SWT.BORDER);
        lblIsbn.setText("Enter Author Name");
        lblIsbn.setSize(150, 20);
        lblIsbn.setLocation(10, 30);


        final Text txtAuthorName = new Text(shell, SWT.BORDER);
        txtAuthorName.setBounds(170, 30, 180, 20);
        txtAuthorName.setTextLimit(10);

        Button btnSearch = new Button(shell, SWT.PUSH);
        btnSearch.setText("Search");
        btnSearch.setBounds(100, 60, 100, 20);


        btnSearch.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource() + "Button3 was clicked");
                try {
                    String authorName = txtAuthorName.getText();
                    System.out.println(authorName);
                    if(!(authorName.equals(""))){
                        List<Book> bookList=null;
                        RestTemplateService restTemplateService=new RestTemplateService();
                          bookList=restTemplateService.getAllBook("http://localhost:8080/book/byAuthorName/"+authorName,d);
                        if(bookList!=null){
                            new SearchResultOnAuthorName(display, authorName,btnSource);
                            shell.dispose();
                        }else{
                           /* MessageBox msgBox=new MessageBox(shell,SWT.CANCEL|SWT.OK);
                            msgBox.setMessage("No Suitable record found,Please give a valid Author Name ");
                            msgBox.open();
                            txtAuthorName.setText("");
                            txtAuthorName.setFocus();
*/
                            txtAuthorName.setText("");
                            txtAuthorName.setFocus();

                        }
                    }else{
                        MessageBox msgBox=new MessageBox(shell,SWT.CANCEL|SWT.OK);
                        msgBox.setMessage("Please Enter Author Name and Click Search");
                        msgBox.open();
                        txtAuthorName.setFocus();

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
