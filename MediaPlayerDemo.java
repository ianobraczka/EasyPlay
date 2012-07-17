//needs JMF installed and configured to run properly

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Component; 
import java.awt.Container; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.io.File; 

import javax.media.ControllerEvent; 
import javax.media.ControllerListener; 
import javax.media.Manager; 
import javax.media.Player; 
import javax.media.RealizeCompleteEvent; 
import javax.swing.JButton; 
import javax.swing.JFileChooser; 
import javax.swing.JFrame; 
import javax.swing.JOptionPane; 

public class MediaPlayerDemo extends JFrame { 
    private Player player; 

    private File file; 

    public MediaPlayerDemo() { 
        super("Demonstrating the Java Media Player"); 

        JButton openFile = new JButton("Open file to play"); 
        openFile.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                openFile(); 
                createPlayer(); 
            } 
        }); 
        getContentPane().add(openFile, BorderLayout.NORTH); 

        setSize(300, 300); 
        show(); 
    } 

    private void openFile() { 
        JFileChooser fileChooser = new JFileChooser(); 

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
        int result = fileChooser.showOpenDialog(this); 

        // user clicked Cancel button on dialog 
        if (result == JFileChooser.CANCEL_OPTION) 
            file = null; 
        else 
            file = fileChooser.getSelectedFile(); 
    } 

    private void createPlayer() { 
        if (file == null) 
            return; 

        removePreviousPlayer(); 

        try { 
            // create a new player and add listener 
            player = Manager.createPlayer(file.toURL()); 
            player.addControllerListener(new EventHandler()); 
            player.start(); // start player 
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Invalid file or location", 
                    "Error loading file", JOptionPane.ERROR_MESSAGE); 
        } 
    } 

    private void removePreviousPlayer() { 
        if (player == null) 
            return; 

        player.close(); 

        Component visual = player.getVisualComponent(); 
        Component control = player.getControlPanelComponent(); 

        Container c = getContentPane(); 

        if (visual != null) 
            c.remove(visual); 

        if (control != null) 
            c.remove(control); 
    } 

    public static void main(String args[]) { 
        MediaPlayerDemo app = new MediaPlayerDemo(); 

        app.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) { 
                System.exit(0); 
            } 
        }); 
    } 

    // inner class to handler events from media player 
    private class EventHandler implements ControllerListener { 
        public void controllerUpdate(ControllerEvent e) { 
            if (e instanceof RealizeCompleteEvent) { 
                Container c = getContentPane(); 
                c.setBackground(new Color(255, 255, 204)); 

                // load Visual and Control components if they exist 
                Component visualComponent = player.getVisualComponent(); 

                if (visualComponent != null) 
                    c.add(visualComponent, BorderLayout.CENTER); 
                c.setBackground(new Color(255, 255, 204)); 
                Component controlsComponent = player.getControlPanelComponent(); 

                if (controlsComponent != null) 
                    c.add(controlsComponent, BorderLayout.SOUTH); 

                c.doLayout(); 
            } 
        } 
    } 
}