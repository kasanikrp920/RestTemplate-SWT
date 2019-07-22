
package com.rest.template.swt;

import com.rest.template.bookRentalAction.SearchResultOnAuthorName;
import com.rest.template.bookRentalAction.SearchResultOnBookTitle;
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

public class SearchByBookTitle {

    public SearchByBookTitle(Display d, String source){
        final String btnSource=source;
        final Display display=d;
        final Shell shell = new Shell(display);
        shell.setSize(400, 300);
        shell.setText("Searching a Book");


        Label lblIsbn = new Label(shell, SWT.BORDER);
        lblIsbn.setText("Book Title and Edition");
        lblIsbn.setSize(150, 30);
        lblIsbn.setLocation(10, 30);


        final Text txtBookTitle = new Text(shell, SWT.BORDER);
        txtBookTitle.setBounds(170, 30, 180, 30);
        txtBookTitle.setTextLimit(30);

        Button btnSearch = new Button(shell, SWT.PUSH);
        btnSearch.setText("Search");
        btnSearch.setBounds(100, 80, 100, 30);


        btnSearch.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource() + "Button3 was clicked");
                try {
                    String bookTitle =  txtBookTitle.getText();

                    if(!(bookTitle.equals(""))){
                        RestTemplateService restTemplateService=new RestTemplateService();
                        List<Book> bookList=restTemplateService.getAllBook("http://localhost:8080/book/byTitle/"+bookTitle,d);
                        if(bookList!=null){
                            new SearchResultOnBookTitle(display,bookTitle,btnSource);
                            shell.dispose();
                        }

                         else{

                            txtBookTitle.setText("");
                            txtBookTitle.setFocus();

                        }
                    }else{
                        MessageBox msgBox=new MessageBox(shell,SWT.CANCEL|SWT.OK);
                        msgBox.setMessage("Please Enter Book Title and Click Search");
                        msgBox.open();
                        txtBookTitle.setFocus();

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

