package com.rest.template.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class BookTable {

    public static Table bookTable(Shell s) {
        Shell shell=s;
        shell.setLayout(new GridLayout(1, false));
        shell.setSize(700, 700);
        shell.setText("Your Book Details Are..");
        Table table = new Table(shell, SWT.VIRTUAL|SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setItemCount(1);


        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        TableColumn c1 = new TableColumn(table, SWT.NONE);
        c1.setWidth(100);
        c1.setText("AuthorName");


        TableColumn c2 = new TableColumn(table, SWT.NONE);
        c2.setWidth(100);
        c2.setText("BookTitle and Edition");


        TableColumn c3 = new TableColumn(table, SWT.NONE);
        c3.setWidth(100);
        c3.setText("ISBN");


        TableColumn c4 = new TableColumn(table, SWT.NONE);
        c4.setWidth(100);
        c4.setText("Academic or Not");


        TableColumn c5 = new TableColumn(table, SWT.NONE);
        c5.setWidth(100);
        c5.setText("Stock");


        TableColumn c6 = new TableColumn(table, SWT.NONE);
        c6.setWidth(100);
        c6.setText("Available");



        TableColumn c7 = new TableColumn(table, SWT.NONE);
        c7.setWidth(100);
        c7.setText("Is Deleted");



        return table;

    }

}
