package Cau1;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.awt.Dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

public class Cau1 {

	protected Shell shell;
	private Text xInput;
	private Text xResult;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Cau1 window = new Cau1();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(null);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 261);
		fd_composite.right = new FormAttachment(0, 434);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		
		Label xlabel1 = new Label(composite, SWT.NONE);
		xlabel1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		xlabel1.setBounds(70, 29, 57, 15);
		xlabel1.setText("Nhập số n:");
		
		xInput = new Text(composite, SWT.BORDER | SWT.RIGHT);
		xInput.setBounds(189, 26, 171, 21);
		
		Label xlabel2 = new Label(composite, SWT.NONE);
		xlabel2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		xlabel2.setText("Sắp xếp theo chi�?u:");
		xlabel2.setBounds(70, 71, 107, 15);
		
		org.eclipse.swt.widgets.Button radiox = new org.eclipse.swt.widgets.Button(composite, SWT.RADIO);
		radiox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		radiox.setBounds(190, 70, 90, 16);
		radiox.setText("Tăng dần");
		
		org.eclipse.swt.widgets.Button radioy = new org.eclipse.swt.widgets.Button(composite, SWT.RADIO);
		radioy.setText("Giảm dần");
		radioy.setBounds(288, 70, 90, 16);
		
		org.eclipse.swt.widgets.Button btnView = new org.eclipse.swt.widgets.Button(composite, SWT.NONE);
		btnView.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(radiox.getSelection()){
					String number = xInput.getText(); 
					xResult.setText(XuLy.oddNumber(number));
				}else if(radioy.getSelection()){
					String number = xInput.getText(); 
					xResult.setText(XuLy.evenNumber(number));
				}
			}
		});
		btnView.setBounds(189, 92, 75, 25);
		btnView.setText("Hiển thị");
		
		Label xlabel3 = new Label(composite, SWT.NONE);
		xlabel3.setText("Kết quả:");
		xlabel3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		xlabel3.setBounds(70, 141, 107, 15);
		
		xResult = new Text(composite, SWT.BORDER);
		xResult.setBounds(189, 138, 172, 61);
		
		Button btnXoa = new Button(composite, SWT.NONE);
		btnXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				xInput.setText("");
				xResult.setText("");
			}
		});
		btnXoa.setText("Xóa");
		btnXoa.setBounds(189, 214, 75, 25);
	}
}
