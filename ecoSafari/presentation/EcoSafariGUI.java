package presentation;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EcoSafariGUI extends JFrame{  
    public static final int SIDE=20;

    public final int SIZE;
    private JButton ticTacButton;
    private JPanel  controlPanel;
    private PhotoEcoSafari photo;
    private EcoSafari theEcoSafari;

    private JMenuItem optionNew;
    private JMenuItem optionSaveAs;
    private JMenuItem optionOpen;
    private JMenuItem optionImport;
    private JMenuItem optionExportAs;
    private JMenuItem optionExit;

    
    private EcoSafariGUI() {
        theEcoSafari=new EcoSafari();
        SIZE=theEcoSafari.getSize();
        prepareElements();
        prepareActions();

        prepareView();
        prepareController();
    }

    private void prepareElements() {
        setTitle("EcoSafari");
        photo=new PhotoEcoSafari(this);
        ticTacButton=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo,BorderLayout.NORTH);
        add(ticTacButton,BorderLayout.SOUTH);
        setSize(new Dimension(SIDE*SIZE+15,SIDE*SIZE+72)); 
        setResizable(false);
        photo.repaint();
    }

    private void prepareActions(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        ticTacButton.addActionListener(e-> ticTacButtonAction());
    }

    public void prepareView(){
        prepareViewMenu();
    }

    public void prepareViewMenu(){
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu menu1 = new JMenu("Archivo");
        menuBar.add(menu1);

        optionNew = new JMenuItem("Nuevo");
        optionOpen = new JMenuItem("Abrir");
        optionSaveAs = new JMenuItem("Salvar como");
        optionImport = new JMenuItem("Importar");
        optionExportAs = new JMenuItem("Exportar");
        optionExit = new JMenuItem("Salir");

        menu1.add(optionNew);
        menu1.add(optionOpen);
        menu1.add(optionSaveAs);
        menu1.add(optionImport);
        menu1.add(optionExportAs);
        menu1.addSeparator();
        menu1.add(optionExit);
    }

    public void prepareController(){
        prepareControllerMenu();
    }

    public void prepareControllerMenu(){
        
        optionExit.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        optionNew.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                theEcoSafari=new EcoSafari();
                prepareElements();
            }
        });

        optionImport.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                try{
                    JFileChooser selector = new JFileChooser();
                    int result = selector.showSaveDialog(null);

                    if (result == 0){
                        File fileToImport = selector.getSelectedFile();
                        EcoSafari.import_(fileToImport);
                    }
                }catch(EcoSafariException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        optionExportAs.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                try{
                    JFileChooser selector = new JFileChooser();
                    int result = selector.showSaveDialog(null);

                    if (result == 0){
                        File fileToImport = selector.getSelectedFile();
                        EcoSafari.exportAs(fileToImport);
                    }
                }catch(EcoSafariException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        optionExportAs.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                try{
                    JFileChooser selector = new JFileChooser();
                    int result = selector.showSaveDialog(null);

                    if (result == 0){
                        File fileToImport = selector.getSelectedFile();
                        EcoSafari.exportAs(fileToImport);
                    }
                }catch(EcoSafariException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        optionSaveAs.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                try{
                    JFileChooser selector = new JFileChooser();
                    int result = selector.showSaveDialog(null);

                    if (result == 0){
                        File fileToImport = selector.getSelectedFile();
                        theEcoSafari.saveAs(fileToImport, theEcoSafari);
                    }
                }catch(EcoSafariException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    private void ticTacButtonAction() {
        theEcoSafari.ticTac();
        photo.repaint();
    }

    public EcoSafari gettheEcoSafari(){
        return theEcoSafari;
    }
    
    public static void main(String[] args){
        EcoSafariGUI cg=new EcoSafariGUI();
        cg.setVisible(true);
    }  


    class PhotoEcoSafari extends JPanel{
        private EcoSafariGUI gui;
    
        public PhotoEcoSafari(EcoSafariGUI gui) {
            this.gui=gui;
            setBackground(Color.white);
            setPreferredSize(new Dimension(gui.SIDE*gui.SIZE+10, gui.SIDE*gui.SIZE+10));         
        }
    
    
        public void paintComponent(Graphics g){
            EcoSafari theEcoSafari=gui.gettheEcoSafari();
            super.paintComponent(g);
            
            for (int c=0;c<=theEcoSafari.getSize();c++){
                g.drawLine(c*gui.SIDE,0,c*gui.SIDE,theEcoSafari.getSize()*gui.SIDE);
            }
            for (int f=0;f<=theEcoSafari.getSize();f++){
                g.drawLine(0,f*gui.SIDE,theEcoSafari.getSize()*gui.SIDE,f*gui.SIDE);
            }       
            for (int f=0;f<theEcoSafari.getSize();f++){
                for(int c=0;c<theEcoSafari.getSize();c++){
                    if (theEcoSafari.get(f,c)!=null){
                        g.setColor(theEcoSafari.get(f,c).getColor());
                        if (theEcoSafari.get(f,c).shape()==Entity.SQUARE){                  
                            g.fillRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);   
                        }else {
                            g.fillOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        }
                        if (theEcoSafari.get(f,c).isOrganism()){
                            g.setColor(Color.red);
                            if (((Organism)theEcoSafari.get(f,c)).getEnergy()>=50){
                                g.drawString("+",gui.SIDE*c+6,gui.SIDE*f+15);
                            } else {
                                g.drawString("~",gui.SIDE*c+6,gui.SIDE*f+17);
                            }
                        }    
                    }
                }
            }
        }
    }
}