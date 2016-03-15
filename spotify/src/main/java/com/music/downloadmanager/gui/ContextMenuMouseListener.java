package com.music.downloadmanager.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.text.JTextComponent;

public class ContextMenuMouseListener extends MouseAdapter {
    private JPopupMenu popup = new JPopupMenu();

    private Action cutAction;
    private Action copyAction;
    private Action pasteAction;
    private Action undoAction;
    private Action selectAllAction;

    private JTable textComponent;
    private String savedString = "";
    private Actions lastActionSelected;

    private enum Actions { UNDO, CUT, COPY, PASTE, SELECT_ALL };

    public ContextMenuMouseListener() {
        undoAction = new AbstractAction("Undo") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                   lastActionSelected = Actions.UNDO;
            }
        };

        popup.add(undoAction);
        popup.addSeparator();

        cutAction = new AbstractAction("Cut") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                lastActionSelected = Actions.CUT;
            }
        };

        popup.add(cutAction);

        copyAction = new AbstractAction("Copy") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                lastActionSelected = Actions.COPY;
                
            }
        };

        popup.add(copyAction);

        pasteAction = new AbstractAction("Paste") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                lastActionSelected = Actions.PASTE;

            }
        };

        popup.add(pasteAction);
        popup.addSeparator();

        selectAllAction = new AbstractAction("Select All") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                lastActionSelected = Actions.SELECT_ALL;
                textComponent.selectAll();
            }
        };

        popup.add(selectAllAction);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getModifiers() == 4) {
            if (!(e.getSource() instanceof JScrollPane)) {
                return;
            }

            JScrollPane scrollPane = (JScrollPane) e.getSource();
            if(scrollPane.getComponentCount() > 0 && scrollPane.getComponent(0) instanceof JTable){
            
            textComponent = (JTable) scrollPane.getComponent(0);
            	
            textComponent.requestFocus();

            boolean enabled = textComponent.isEnabled();
            boolean nonempty = textComponent.getRowCount() != 0;
            

            boolean pasteAvailable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).isDataFlavorSupported(DataFlavor.stringFlavor);

            undoAction.setEnabled(enabled && (lastActionSelected == Actions.CUT || lastActionSelected == Actions.PASTE));
            //cutAction.setEnabled(enabled && editable && marked);
           // copyAction.setEnabled(enabled && marked);
            pasteAction.setEnabled(enabled && pasteAvailable);
            selectAllAction.setEnabled(enabled && nonempty);

            int nx = e.getX();

            if (nx > 500) {
                nx = nx - popup.getSize().width;
            }

            popup.show(e.getComponent(), nx, e.getY() - popup.getSize().height);
            }
        }
    }
}
