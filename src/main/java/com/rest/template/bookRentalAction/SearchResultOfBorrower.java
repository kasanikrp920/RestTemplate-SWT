package com.rest.template.bookRentalAction;

import com.rest.template.model.Borrower;
import com.rest.template.service.RestTemplateService;
import com.rest.template.swt.BorrowerTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;


public class SearchResultOfBorrower {

    String mailId=null;

    public SearchResultOfBorrower(Display d,String emailId) {
        final Display display=d;
        final String emailId1=emailId;


        final Shell shell = new Shell(d);

        final MessageBox msgBox=new MessageBox(shell, SWT.CANCEL|SWT.OK);

        RestTemplateService restTemplateService= new RestTemplateService();

        final Table table= BorrowerTable.borrowerTable(shell);



        table.addListener(SWT.SetData, new Listener() {
            public void handleEvent(Event event) {


                Borrower borrower=restTemplateService.getBorrower(emailId,display);
                TableItem item=new TableItem(table,SWT.NONE);
                item.setText(new String[]{
                        String.valueOf(borrower.getBookIsbn()),borrower.getFirstName(),borrower.getLastName(),borrower.getEmail(),
                                borrower.getDate(),String.valueOf(borrower.getNunOfDaysLoan()),String.valueOf(borrower.getIsDeleted())});
            }
        });




        table.addListener(SWT.MouseDown, new Listener(){
            public void handleEvent(Event event){
                Point pt = new Point(event.x, event.y);
                TableItem item = table.getItem(pt);
                if(item != null) {
                    mailId=item.getText(3);
                }
            }
        });

        Button  update=new Button(shell,SWT.PUSH);
        update.setText("Update");
        update.setBounds(500,600,300,30);

        update.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {


                    if (mailId != null) {
                        try {
                            String response = restTemplateService.returnBook(mailId, d);
                            if (response != null) {
                                msgBox.setMessage("You Have Returned the Book Successfully");
                                msgBox.open();
                                shell.dispose();
                            } else {
                                System.out.println("borrower details are not  deleted");
                            }

                        } catch (Exception a) {
                             a.printStackTrace();
                        }
                    }else{
                        msgBox.setMessage("Please select the Record and Click Update");
                        msgBox.open();
                    }

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
