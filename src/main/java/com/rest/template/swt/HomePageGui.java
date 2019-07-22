package com.rest.template.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class HomePageGui {

    public static void main(String[]args){
        final Display d= Display.getDefault();
        Shell shell =new Shell();
        shell.setSize(1200,800);
        shell.setBackground(d.getSystemColor(SWT.COLOR_WHITE));


        org.eclipse.swt.widgets.Label lblHeading = new Label(shell, SWT.CENTER);
        lblHeading.setText("WELCOME TO BOOK RENTAL SYSTEM");
        lblHeading.setSize(1000,40);
        lblHeading.setLocation(500,40);
        lblHeading.setFont( new Font(d,"Arial", 24, SWT.BOLD ) );
        lblHeading.setBackground(d.getSystemColor(SWT.COLOR_DARK_CYAN));
        lblHeading.setForeground(d.getSystemColor(SWT.COLOR_WHITE));



        Button btnAddBooks=new Button(shell, SWT.TOGGLE);
        btnAddBooks.setBounds(60,90,150,30);
        btnAddBooks.setText("Add Books");
        btnAddBooks.setBackground(d.getSystemColor(SWT.COLOR_DARK_GREEN));
        btnAddBooks.setForeground(d.getSystemColor(SWT.COLOR_DARK_GREEN));

        Button btnPrintBooks=new Button(shell, SWT.TOGGLE);
        btnPrintBooks.setBounds(60,160,150,30);
        btnPrintBooks.setText("Print Books");
        btnPrintBooks.setBackground(d.getSystemColor(SWT.COLOR_DARK_RED));
        btnPrintBooks.setForeground(d.getSystemColor(SWT.COLOR_DARK_RED));

        Button btnSearchBook=new Button(shell, SWT.TOGGLE);
        btnSearchBook.setBounds(60,230,150,30);
        btnSearchBook.setText("Search Book");
        btnSearchBook.setBackground(d.getSystemColor(SWT.COLOR_DARK_GREEN));
        btnSearchBook.setForeground(d.getSystemColor(SWT.COLOR_BLUE));

        Button btnDeleteBooks=new Button(shell, SWT.TOGGLE);
        btnDeleteBooks.setBounds(60,300,150,30);
        btnDeleteBooks.setText("Delete Book");
        btnDeleteBooks.setBackground(d.getSystemColor(SWT.COLOR_DARK_GREEN));
        btnDeleteBooks.setForeground(d.getSystemColor(SWT.COLOR_DARK_YELLOW));

        Button btnRentBooks=new Button(shell, SWT.TOGGLE);
        btnRentBooks.setBounds(60,370,150,30);
        btnRentBooks.setText("Rent Book");
        btnRentBooks.setBackground(d.getSystemColor(SWT.COLOR_DARK_GREEN));
        btnRentBooks.setForeground(d.getSystemColor(SWT.COLOR_DARK_RED));

        Button btnBorrowerList=new Button(shell, SWT.TOGGLE);
        btnBorrowerList.setBounds(60,440,150,20);
        btnBorrowerList.setText("Print BorrowerList");
        btnBorrowerList.setBackground(d.getSystemColor(SWT.COLOR_DARK_RED));
        btnBorrowerList.setForeground(d.getSystemColor(SWT.COLOR_DARK_MAGENTA));

        Button btnReturnBook=new Button(shell, SWT.TOGGLE);
        btnReturnBook.setBounds(60,510,150,30);
        btnReturnBook.setText("Return Book");
        btnReturnBook.setBackground(d.getSystemColor(SWT.COLOR_DARK_GREEN));
        btnReturnBook.setForeground(d.getSystemColor(SWT.COLOR_DARK_GREEN));

        btnAddBooks.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource()+"Button2 was clicked");
                try{
                    new AddBookGui(d);

                }catch(Exception a){}
            }
        });

        btnPrintBooks.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource()+"Button2 was clicked");
                try{
                    new BookDetails(d);

                }catch(Exception a){}
            }
        });

        btnSearchBook.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource()+"Button3 was clicked");
                String btnSource=String.valueOf(e.getSource());
                System.out.println(btnSource);
                try{
                    new SearchButtons(d,btnSource);
                }catch(Exception a){}
            }
        });

        btnDeleteBooks.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource()+"Button4 was clicked");
                try{
                    String btnSource=String.valueOf(e.getSource());
                    System.out.println(btnSource);
                    new SearchButtons(d,btnSource);

                }catch(Exception a){}
            }
        });

       btnRentBooks.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource()+"Button5 was clicked");
                try{
                    String btnSource=String.valueOf(e.getSource());
                    System.out.println(btnSource);
                    new SearchButtons(d,btnSource);
                }catch(Exception a){}
            }
        });

        btnBorrowerList.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource()+"Button5 was clicked");
                try{
                    new BorrowerDetails(d);
                }catch(Exception a){}
            }
        });
        btnReturnBook.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e.getSource()+"Button5 was clicked");
                try{
                    new SearchBorrowerGui(d);
                }catch(Exception a){}
            }
        });

        Button btnLogOut = new Button(shell, SWT.PUSH);
        btnLogOut.setText("LogOut");
        btnLogOut.setLocation(70, 600);
        btnLogOut.setSize(100, 30);

        btnLogOut.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                d.dispose();
            }
        });

        shell.open();
        while(!shell.isDisposed()){
            if(!d.readAndDispatch())
                d.sleep();
        }


    }


}
