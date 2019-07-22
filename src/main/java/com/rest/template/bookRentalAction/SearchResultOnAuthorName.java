package com.rest.template.bookRentalAction;

import com.rest.template.model.Book;
import com.rest.template.service.RestTemplateService;
import com.rest.template.swt.BookTable;
import com.rest.template.swt.BorrowerGui;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SearchResultOnAuthorName {

    String available;
    String ISBN;
    String author;
    String bookTitle;
    public SearchResultOnAuthorName(Display display, String s, String source) {
        final String authorName = s;
        final String btnSource = source;
        final Display d = display;
        final Shell shell = new Shell(d);

        MessageBox msgBox=new MessageBox(shell, SWT.CANCEL|SWT.OK);

        final Table table = BookTable.bookTable(shell);

        table.addListener(SWT.SetData, new Listener() {
            public void handleEvent(Event event) {

                RestTemplateService restTemplateService=new RestTemplateService();
                List<Book> bookList=restTemplateService.getAllBook("http://localhost:8080/book/byAuthorName/"+authorName,d);
                for (Book book:bookList) {
                    TableItem item=new TableItem(table,SWT.NONE);
                    item.setText(new String[]{book.getAuthorName(),book.getTitle(),String.valueOf(book.getISBN()),
                            book.getIsAcademic(),String.valueOf(book.getTotalCount()),String.valueOf(book.getAvailable()),String.valueOf(book.getIsDeleted())});

                }

            }
        });

        table.addListener(SWT.MouseDown, new Listener(){
            public void handleEvent(Event event){
                Point pt = new Point(event.x, event.y);
                TableItem item = table.getItem(pt);
                if(item != null) {
                    author=item.getText(0);
                    bookTitle=item.getText(1);
                    ISBN=item.getText(2);

                    available=item.getText(5);
                }
            }
        });


        if(btnSource.equals("Button {Search Book}")) {
            Button btnGoHome = new Button(shell, SWT.PUSH);
            btnGoHome.setBounds(500, 600, 200, 20);
            btnGoHome.setText("Goto Home");

            btnGoHome.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    System.out.println(e.getSource() + "Button4 was clicked");
                    try {
                        shell.dispose();
                    } catch (Exception a) {
                    }
                }
            });
        }else if(btnSource.equals("Button {Delete Book}")){
            Button btnDeleteBook = new Button(shell, SWT.PUSH);
            btnDeleteBook.setBounds(500, 600, 200, 20);
            btnDeleteBook.setText("Delete Book");

            btnDeleteBook.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    System.out.println(e.getSource() + "Button4 was clicked");
                    try {
                        RestTemplateService restTemplateService=new RestTemplateService();
                        long isbn=Long.parseLong(ISBN);
                        String status=restTemplateService.deleteOneBook(isbn);
                        if(status!=null){

                            msgBox.setMessage("The Book has deleted successfully..");
                            msgBox.open();
                        }
                        shell.dispose();
                    } catch (Exception a) {
                        msgBox.setMessage("Please Select the Record and Click Delete Book");
                        msgBox.open();
                    }
                }
            });
        }else{
            Button btnRent = new Button(shell, SWT.PUSH);
            btnRent.setBounds(500, 600, 200, 20);
            btnRent.setText("Rent Book");

            btnRent.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    System.out.println(e.getSource() + "Button4 was clicked");
                    try {
                        int avail=Integer.parseInt(available);
                        if(avail==0){
                            msgBox.setMessage("Sorry,sir Your Requested book is Out of Stock");
                            msgBox.open();
                            shell.dispose();
                        }else{
                            new BorrowerGui(d,ISBN,author,bookTitle,available);
                            shell.dispose();
                        }
                    } catch (Exception q) {

                        msgBox.setMessage("Please Select the Record and Click Rent");
                        msgBox.open();
                    }
                }
            });
        }
        shell.open();
        shell.addListener(SWT.CLOSE, new Listener() {
            @Override
            public void handleEvent(Event event) {
                shell.dispose();
            }
        });


    }

}
