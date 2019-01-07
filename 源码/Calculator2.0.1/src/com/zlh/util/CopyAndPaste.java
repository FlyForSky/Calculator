package com.zlh.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * 该类用于复制和粘贴.
 * @author 李飞
 *
 */
public class CopyAndPaste {

	private static CopyAndPaste copyAndPaste;
	
	private Clipboard clipboard;
	
	private CopyAndPaste(){
		super();
	}
	
	public static CopyAndPaste getCopyAndPaste(){
		if(copyAndPaste==null){
			copyAndPaste = new CopyAndPaste();
		}
		return copyAndPaste;
	}
	
	private Clipboard getClipboard(){
		if(clipboard==null){
			clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		}
		return clipboard;
	}
	
	public String getSysClipboardText(){
		String src = "";
		Transferable clipTf = getClipboard().getContents(null);
		if(clipTf!=null){
			if(clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)){
				try{
					src = (String)clipTf.getTransferData(DataFlavor.stringFlavor);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return src;
	}
	
	public void setSysClipboardText(String src){
		Transferable clipTf = new StringSelection(src);
		getClipboard().setContents(clipTf, null);
	}
	
}
