package com.rest.template.swt;

import com.rest.template.model.Book;
import com.rest.template.model.Borrower;
import com.rest.template.service.RestTemplateService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class BorrowerDetails {

    public BorrowerDetails(Display d){

        final Display display=d;
        final Shell shell = new Shell(d);

        final Table table= BorrowerTable.borrowerTable(shell);

        table.addListener(SWT.SetData, new Listener() {
            public void handleEvent(Event event) {
                RestTemplateService restTemplateService=new RestTemplateService();
               List<Borrower>borrowerList = restTemplateService.getAllBorrowers();
                for (Borrower borrower:borrowerList) {
                    TableItem item=new TableItem(table,SWT.NONE);
                    item.setText(new String[]{String.valueOf(borrower.getBookIsbn()),borrower.getFirstName(),borrower.getLastName(),
                    borrower.getEmail(),borrower.getDate(),String.valueOf(borrower.getNunOfDaysLoan()),String.valueOf(borrower.getIsDeleted())});
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
        shell.addListener(SWT.CLOSE, new Listener() {
            @Override
            public void handleEvent(Event event) {
                shell.dispose();
            }
        });
    }

}
