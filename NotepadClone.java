import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class NotepadClone extends JFrame implements ActionListener {
    JTextArea textArea;
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, viewMenu, formatMenu, helpMenu;
    JMenuItem newItem, openItem, saveItem, exitItem;
    JMenuItem cutItem, copyItem, pasteItem, deleteItem;
    JMenuItem zoomOutItem, zoomInItem, statusBarItem;
    JMenuItem wordWrapItem, fontItem;
    JMenuItem viewHelpItem, sendFeedbackItem, aboutItem;

    private int fontSize = 14;

    public NotepadClone() {
        setTitle("Notepad");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Menu bar
        menuBar = new JMenuBar();

        // File Menu
        fileMenu = new JMenu("File");
        fileMenu.setFont(new Font("Arial", Font.PLAIN, 12));

        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        // Edit Menu
        editMenu = new JMenu("Edit");
        editMenu.setFont(new Font("Arial", Font.PLAIN, 12));

        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        deleteItem = new JMenuItem("Delete");

        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        deleteItem.addActionListener(this);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(deleteItem);
        menuBar.add(editMenu);

        // View Menu
        viewMenu = new JMenu("View");
        viewMenu.setFont(new Font("Arial", Font.PLAIN, 12));

        zoomInItem = new JMenuItem("Zoom In");
        zoomOutItem = new JMenuItem("Zoom Out");
        statusBarItem = new JMenuItem("Status Bar");

        zoomInItem.addActionListener(this);
        zoomOutItem.addActionListener(this);
        statusBarItem.addActionListener(this);

        viewMenu.add(zoomInItem);
        viewMenu.add(zoomOutItem);
        viewMenu.addSeparator();
        viewMenu.add(statusBarItem);
        menuBar.add(viewMenu);

        // Format Menu
        formatMenu = new JMenu("Format");
        formatMenu.setFont(new Font("Arial", Font.PLAIN, 12));

        wordWrapItem = new JMenuItem("Word Wrap");
        fontItem = new JMenuItem("Font");

        wordWrapItem.addActionListener(this);
        fontItem.addActionListener(this);

        formatMenu.add(wordWrapItem);
        formatMenu.addSeparator();
        formatMenu.add(fontItem);
        menuBar.add(formatMenu);

        // Help Menu
        helpMenu = new JMenu("Help");
        helpMenu.setFont(new Font("Arial", Font.PLAIN, 12));

        viewHelpItem = new JMenuItem("View Help");
        sendFeedbackItem = new JMenuItem("Send Feedback");
        aboutItem = new JMenuItem("About");

        viewHelpItem.addActionListener(this);
        sendFeedbackItem.addActionListener(this);
        aboutItem.addActionListener(this);

        helpMenu.add(viewHelpItem);
        helpMenu.add(sendFeedbackItem);
        helpMenu.addSeparator();
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);

        
        setJMenuBar(menuBar);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newItem) {
            textArea.setText("");
        } else if (e.getSource() == openItem) {
            openFile();
        } else if (e.getSource() == saveItem) {
            saveFile();
        } else if (e.getSource() == exitItem) {
            System.exit(0);
        } else if (e.getSource() == cutItem) {
            textArea.cut();
        } else if (e.getSource() == copyItem) {
            textArea.copy();
        } else if (e.getSource() == pasteItem) {
            textArea.paste();
        } else if (e.getSource() == deleteItem) {
            textArea.replaceSelection("");
        } else if (e.getSource() == aboutItem) {
            JOptionPane.showMessageDialog(this, "Notepad Latest -- Version 1.0", "About", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == zoomInItem) {
            zoomIn();
        } else if (e.getSource() == zoomOutItem) {
            zoomOut();
        }
    }

    private void zoomIn() {
        fontSize += 2;
        textArea.setFont(new Font("Arial", Font.PLAIN, fontSize));
    }

    private void zoomOut() {
        if (fontSize > 8) {
            fontSize -= 2;
            textArea.setFont(new Font("Arial", Font.PLAIN, fontSize));
        }
    }

    private void openFile() {
        FileDialog fd = new FileDialog(this, "Open File", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(fd.getDirectory() + fd.getFile()))) {
                textArea.read(br, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "File could not be opened!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        FileDialog fd = new FileDialog(this, "Save File", FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fd.getDirectory() + fd.getFile()))) {
                textArea.write(bw);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "File could not be saved!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotepadClone().setVisible(true));
    }
}
