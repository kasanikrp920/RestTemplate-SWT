package com.rest.template.swt;

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

public class BookDetails {

    public BookDetails(Display d){

        final Display display=d;
        final Shell shell = new Shell(display);


        final Table table= BookTable.bookTable(shell);


        table.addListener(SWT.SetData, new Listener() {
            public void handleEvent(Event event) {

                RestTemplateService restTemplateService=new RestTemplateService();
                List<Book>bookList=restTemplateService.getAllBook("http://localhost:8080/books",display);
                for (Book book:bookList) {
                    TableItem item=new TableItem(table,SWT.NONE);
                    item.setText(new String[]{book.getAuthorName(),book.getTitle(),String.valueOf(book.getISBN()),
                    book.getIsAcademic(),String.valueOf(book.getTotalCount()),String.valueOf(book.getAvailable()),String.valueOf(book.getIsDeleted())});

                }

            }
        });



        Button btnGoHome=new Button(shell,SWT.PUSH);
        btnGoHome.setText("Goto Home");
        btnGoHome.setBounds(500,600,100,30);

        btnGoHome.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {

                shell.dispose();
            } });



        shell.open();
        shell.layout();
        shell.addListener(SWT.CLOSE, new Listener() {
            @Override
            public void handleEvent(Event event) {
                shell.dispose();
            }
        });
    }


}
