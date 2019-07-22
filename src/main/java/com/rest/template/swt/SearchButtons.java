package com.rest.template.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

public class SearchButtons {

    public SearchButtons(Display d, String source) {
        final Display display=d;
        final String btnSource=source;
        final Shell s = new Shell(display);
        s.setSize(800,500);
        s.setBackgroundImage(new Image(d, "C:\\Users\\raghu\\Desktop\\7.jpg"));
        s.setText("Searching..");



        Button btnIsbn=new Button(s, SWT.RADIO);
        btnIsbn.setBounds(400,20,300,30);
        btnIsbn.setBackground(d.getSystemColor(SWT.COLOR_GREEN));
        btnIsbn.setText("Search By ISBN");


        Button btnAuthorName=new Button(s, SWT.RADIO);
        btnAuthorName.setBounds(400,140,300,30);
        btnAuthorName.setText("Search By Author Name");
        btnAuthorName.setBackground(d.getSystemColor(SWT.COLOR_GRAY));


        Button btnBookTitle=new Button(s, SWT.RADIO);
        btnBookTitle.setBounds(400,260,300,30);
        btnBookTitle.setText("Search By Book Title");
        btnBookTitle.setBackground(d.getSystemColor(SWT.COLOR_DARK_CYAN));

        Button btnFake= new Button(s,SWT.RADIO);
        btnFake.setSelection(true);
        btnFake.setBounds(400,360,300,30);
        btnFake.setText("None Of These");
        btnFake.setVisible(true);

        btnIsbn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                new SearchByIsbn(display,btnSource);
                s.dispose();
            }
        });

        btnAuthorName.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                new SearchByAuthorName(display,btnSource);
                s.dispose();
            }
        });

        btnBookTitle.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                new SearchByBookTitle(display,btnSource);
                s.dispose();
            }
        });

        s.open();
        s.addListener(SWT.CLOSE, new Listener() {
            @Override
            public void handleEvent(Event event) {
                s.dispose();
            }
        });

    }


}
