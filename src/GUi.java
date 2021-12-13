import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

class GUI extends JFrame implements ActionListener {
    JPanel Panel, pop;
    JButton center, shortPath, load, isConnected, Save, tsp, Enterr;
    JTextField tsplist, src, dest;
    JFrame connectedpop, popup;
    DirectedWeightedGraphAlgorithms_ans g1, g2;
    int src_1, dest_1;
    NodeData start, end, center_node;
    ArrayList<NodeData> route, tsp_node;
    int counter = 0;


    public GUI(DirectedWeightedGraph_ans gr) {
        INIT(gr);

    }

    public class graphPanel extends JPanel {
        double minx, miny, maxx, maxy;
        double scale = 1;
        double scale1 = 8;

        public graphPanel(DirectedWeightedGraph_ans gr) {
            g1 = new DirectedWeightedGraphAlgorithms_ans();
            g2 = new DirectedWeightedGraphAlgorithms_ans();
            g1.init(gr);
            g2.init(g1.copy());
            try {
                Range();
            } catch (Exception e) {
                e.printStackTrace();
            }
            repaint();

        }

        public void Range() throws Exception //we will do scaling to our graph, so that we will se all the Verticles&Edges in the right place
        {
            minx = miny = Integer.MAX_VALUE;
            maxx = maxy = Integer.MIN_VALUE;
            Iterator<NodeData> itn = g1.getGraph().nodeIter();
            while (itn.hasNext()) {
                NodeData curr = itn.next();
                GeoLocation g = curr.getLocation();
                if (g.x() < minx) {
                    minx = g.x();
                }
                if (g.y() < miny) {
                    miny = g.y();
                }
                if (g.x() > maxx) {
                    maxx = g.x();
                }
                if (g.y() > maxy) {
                    maxy = g.y();
                }
            }

        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            double AbX = Math.abs(minx - maxx);
            double AbY = Math.abs(miny - maxy);
            double scalex = (getWidth() / AbX) * 0.7;
            double scaley = (getHeight() / AbY) * 0.7;

            NodeDI prev = null;
            try {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                double theta = 0;
                Iterator<NodeData> it = g1.getGraph().nodeIter();
                while (it.hasNext()) {
                    NodeData n = (NodeDI) it.next();
                    Iterator<EdgeData> eiter = g1.getGraph().edgeIter(n.getKey());
                    while (eiter.hasNext()) {
                        EdgeData e = (Edge) eiter.next();
                        double srcx = (g1.getGraph().getNode(e.getSrc()).getLocation().x() - minx) * scalex + 30;
                        double srcy = (g1.getGraph().getNode(e.getSrc()).getLocation().y() - miny) * scaley + 30;
                        double destx = (g1.getGraph().getNode(e.getDest()).getLocation().x() - minx) * scalex + 30;
                        double desty = (g1.getGraph().getNode(e.getDest()).getLocation().y() - miny) * scaley + 30;
                        g.setColor(new Color(0, 0, 0));
                        int x1 = (int) srcx;
                        int y1 = (int) srcy;
                        int x2 = (int) destx;
                        int y2 = (int) desty;
                        g2.setStroke(new BasicStroke(1));
                        g2.draw(new Line2D.Double(x1, y1, x2, y2));
                        g.setColor(new Color(94, 0, 255, 224));//set color as blue
                        g.setColor(new Color(127, 30, 30));//set color as red
                        drawArr(g2, theta, x2, y2);
                        x1 = (int) srcx + (int) (scalex / scale1);
                        y1 = (int) srcy + (int) (scaley / scale1);
                        x2 = (int) destx + (int) (scalex / scale1);
                        y2 = (int) desty + (int) (scaley / scale1);
                    }
                    g.setColor(new Color(130, 11, 148, 255));
                    double x = (n.getLocation().x() - minx) * scalex * 0.97 + 30;
                    double y = (n.getLocation().y() - miny) * scaley * 0.97 + 30;
                    String xs = "" + n.getLocation().x();
                    String ys = "" + n.getLocation().y();
                    String coord = "(" + xs + "," + ys + ")" + ", id:" + n.getKey();
                    g.fillOval((int) x - 2, (int) y - 2, 20, 20);
                    g.setColor(new Color(0, 0, 0));
                    Font f = new Font("ariel", Font.BOLD, 16);
                    g.setFont(f);
                    g.drawString(n.getKey() + "", (int) x, (int) y - 10);
                }
                if (center_node != null) {
                    double x = (center_node.getLocation().x() - minx) * scalex * 0.97 + 30;
                    double y = (center_node.getLocation().y() - miny) * scaley * 0.97 + 30;
                    g.setColor(new Color(127, 30, 30));//set color as red
                    g.fillOval((int) x - 2, (int) y - 2, 20, 20);
                    g.setColor(new Color(0, 0, 0));
                    g.drawString("center", (int) x - 10, (int) y + 50);
                    JFrame CenterG = new JFrame();
                    CenterG.setSize(350, 200);
                    JLabel labelc = new JLabel();
                    labelc.setFont(new Font("Courier", Font.PLAIN, 20));
                    NodeData ans = g1.center();

                    labelc.setText("The Center of the graph is: " + ans.getKey());
                    CenterG.add(labelc);
                    CenterG.setVisible(true);
                }
                if (start != null) {
                    double x = (start.getLocation().x() - minx) * scalex * 0.97 + 30;
                    double y = (start.getLocation().y() - miny) * scaley * 0.97 + 30;
                    g.setColor(new Color(127, 30, 30));//set color as red
                    g.fillOval((int) x - 2, (int) y - 2, 20, 20);
                    g.setColor(new Color(0, 0, 0));
                    g.drawString("start", (int) x - 10, (int) y + 50);
                }
                if (end != null) {
                    double x = (end.getLocation().x() - minx) * scalex * 0.97 + 30;
                    double y = (end.getLocation().y() - miny) * scaley * 0.97 + 30;
                    g.setColor(new Color(127, 30, 30));//set color as red
                    g.fillOval((int) x - 2, (int) y - 2, 20, 20);
                    g.setColor(new Color(0, 0, 0));
                    g.drawString("end", (int) x - 10, (int) y + 50);
                }
                if (route != null) {
                    for (int i = 0; i < route.size() - 1; i++) {
                        EdgeData curredge = g1.getGraph().getEdge(route.get(i).getKey(), route.get(i + 1).getKey());
                        double srcx = (g1.getGraph().getNode(curredge.getSrc()).getLocation().x() - minx) * scalex + 30;
                        double srcy = (g1.getGraph().getNode(curredge.getSrc()).getLocation().y() - miny) * scaley + 30;
                        double destx = (g1.getGraph().getNode(curredge.getDest()).getLocation().x() - minx) * scalex + 30;
                        double desty = (g1.getGraph().getNode(curredge.getDest()).getLocation().y() - miny) * scaley + 30;
                        int x1 = (int) srcx;
                        int y1 = (int) srcy;
                        int x2 = (int) destx;
                        int y2 = (int) desty;
                        g.setColor(new Color(30, 83, 127, 255));//set color as red
                        g2.draw(new Line2D.Double(x1, y1, x2, y2));
                        g.setColor(new Color(0, 0, 0));//set color as black
                        ((Graphics2D) g).setStroke(new BasicStroke(5));
                        g2.drawString(curredge.getWeight() + "", (x1 + x2) / 2, (y1 + y2) / 2);

                    }
                    g2.drawString("Shortest Path Distance: " + g1.shortestPathDist(src_1, dest_1), 5, this.getWidth() - 50);
                }
                if (tsp_node != null) {
                    for (int i = 0; i < tsp_node.size() - 1; i++) {
                        EdgeData curredge = g1.getGraph().getEdge(tsp_node.get(i).getKey(), tsp_node.get(i + 1).getKey());
                        double srcx = (g1.getGraph().getNode(curredge.getSrc()).getLocation().x() - minx) * scalex + 30;
                        double srcy = (g1.getGraph().getNode(curredge.getSrc()).getLocation().y() - miny) * scaley + 30;
                        double destx = (g1.getGraph().getNode(curredge.getDest()).getLocation().x() - minx) * scalex + 30;
                        double desty = (g1.getGraph().getNode(curredge.getDest()).getLocation().y() - miny) * scaley + 30;
                        int x1 = (int) srcx;
                        int y1 = (int) srcy;
                        int x2 = (int) destx;
                        int y2 = (int) desty;
                        g.setColor(new Color(0, 255, 255));//set color as blue
                        g2.draw(new Line2D.Double(x1, y1, x2, y2));
                        g.setColor(new Color(0, 0, 0));//set color as black
                        ((Graphics2D) g).setStroke(new BasicStroke(5));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == center) //Now we will check  which button from the menu is selected
        {
            try {
                if (counter % 2 == 0) {
                    NodeData a = g1.center();
                    this.center_node = a;
                    counter++;
                    repaint();
                } else {
                    this.center_node = null;
                    counter++;
                    repaint();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == shortPath) {
            shortPath();

        } else if (e.getSource() == load) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showOpenDialog(null); // select file to Open.
            if (response == JFileChooser.APPROVE_OPTION) {
                String jsonPath = fileChooser.getSelectedFile().getAbsolutePath();
                this.g1.load(jsonPath);
                try {
                    runGUI((DirectedWeightedGraph_ans) this.g1.getGraph());
                    setVisible(false); //you can't see me!
                    dispose();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == tsp) {
            tsp();


        } else if (e.getSource() == Enterr) {
            enter();

        } else if (e.getSource() == isConnected) {
            connected();


        }
    }

    public void INIT(DirectedWeightedGraph_ans graph) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();//define the size of the screen as it appears in Toolkit.getScreenSize()
        JPanel p = new JPanel(new BorderLayout());//3,1
        Panel = new JPanel();
        Panel.setBackground(Color.pink);
        Panel.setBounds(size.width / 2 - 150, 0, 150, size.width / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation((size.width / 2 - this.getSize().width) / 2, (size.height / 2 - this.getSize().height) / 3);
        this.setTitle("Best- Graph");
        int width = (int) size.width;
        int height = (int) size.height;
        this.setSize(width / 2, width / 2);
        this.setResizable(false);
        src = new JTextField();
        src.setPreferredSize(new Dimension(40, 20));
        dest = new JTextField();
        dest.setPreferredSize(new Dimension(40, 20));
        Save = new JButton("Save");
        center = new JButton("Center");
        shortPath = new JButton("ShortestPath");
        load = new JButton("load");
        tsp = new JButton("TSP");
        isConnected = new JButton("isConnected");


        isConnected.addActionListener(this);
        load.addActionListener(this); // Adding select button to actionPreformed.
        shortPath.addActionListener(this);
        center.addActionListener(this);
        Save.addActionListener(this);
        tsp.addActionListener(this);
        this.setLayout(new BorderLayout(1, 3));
        this.add(Panel);
        this.add(p, BorderLayout.WEST);
        Panel.add(shortPath);
        Panel.add(src, BorderLayout.EAST);
        Panel.add(dest, BorderLayout.WEST);
        Panel.add(center);
        Panel.add(load);
        Panel.add(Save);
        Panel.add(tsp);
        Panel.add(isConnected);
        this.add(new graphPanel(graph)); //// FIX
        this.setVisible(true);
        this.setTitle("Best Graph");
        this.setResizable(false);

    }

    private void tsp() {
        popup = new JFrame("TSP");

        pop = new JPanel();
        popup.setContentPane(pop);
        pop.setBackground(Color.lightGray);
        popup.setBackground(Color.lightGray);
        popup.setLocationRelativeTo(null);
        //popup.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        popup.setSize((int) d.getWidth() / 3, (int) d.getHeight() / 5);
        popup.setResizable(false);
        popup.setVisible(true);
        tsplist = new JTextField();
        Enterr = new JButton("Enter Cities (seperated by , )");
        Enterr.setPreferredSize(new Dimension(400, 40));//set the size
        JLabel inst = new JLabel("Please enter keys \\\"city1 city2 city3 ...\\\" ");
        popup.add(Enterr);
        popup.add(inst);
        Enterr.addActionListener(this);
        popup.setResizable(true);
        tsplist.setPreferredSize(new Dimension(150, 30));
        popup.add(tsplist);
    }


    private void enter() {
        String cities = tsplist.getText();//get text from user box
        popup.setVisible(false);
        popup.dispose();
        String[] list = cities.split(",");//split the string we got from the user by ','
        tsp_node = new ArrayList<>();

        ArrayList<NodeData> Cities = new ArrayList<>();//define new ArrayList of nodedata

        for (int i = 0; i < list.length; i++)//we will move over all array of string we splitted before
        {
            Cities.add(g1.getGraph().getNode(Integer.parseInt(list[i])));//we will add to cities the int values
        }
        Cities = (ArrayList) g1.tsp(Cities);

        for (int i = 0; i < Cities.size() - 1; i++)//we will move over all Cities ArrayList
        {
            ArrayList<NodeData> tmp = (ArrayList) g1.shortestPath(Cities.get(i).getKey(), Cities.get(i + 1).getKey());
            if (i != Cities.size() - 2) {
                tmp.remove(tmp.get(tmp.size() - 1));
            }
            for (int k = 0; k < tmp.size(); k++) {
                tsp_node.add(tmp.get(k));
            }
            start = tsp_node.get(0);
            end = tsp_node.get(tsp_node.size() - 1);
        }
        repaint();
    }


    private void shortPath() {
        if (src.getText().length() == 0 || dest.getText().length() == 0) //we will check if there is text in user box
        {
            route = null;//if there is not we will define the route as null
            repaint();
        } else //if there is text in the user box
        {
            src_1 = Integer.parseInt(src.getText());//get text from user box
            dest_1 = Integer.parseInt(dest.getText());//get text from user box
            //and compute the shortestPath with this data
            route = (ArrayList<NodeData>) g1.shortestPath(src_1, dest_1);
            repaint();
        }
    }

    private void connected() {
        connectedpop = new JFrame();//we will create new JFrame
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();// now we will define Dimensions
        connectedpop.setSize((int) d.getWidth() / 3, (int) d.getHeight() / 10);//and set the size
        connectedpop.setResizable(false);
        try {
            if (g1.isConnected()) //if the graph is connected
            {
                JLabel PopMsg = new JLabel(" The graph is connected");
                JPanel a = new JPanel();
                connectedpop.setContentPane(a);
                connectedpop.setLocationRelativeTo(null);
                a.add(PopMsg);
            } else //if the graph is connected
            {
                JLabel PopMsg = new JLabel(" The graph is not connected");
                JPanel a = new JPanel();
                connectedpop.setContentPane(a);
                connectedpop.setLocationRelativeTo(null);
                a.add(PopMsg);
            }
            connectedpop.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // by writing this we used the next website: https://coderanch.com/t/339505/java/drawing-arrows
    private void drawArr(Graphics2D g2, double theta, double x0, double y0) {
        double barb = 10;// barb length
        double phi = Math.PI / 6;  //30 degrees barb angle
        double x1 = x0 - barb * Math.cos(theta + phi);
        double y1 = y0 - barb * Math.sin(theta + phi);
        g2.setStroke(new BasicStroke(3));
        g2.draw(new Line2D.Double(x0, y0, x1, y1));
        x1 = x0 - barb * Math.cos(theta - phi);
        y1 = y0 - barb * Math.sin(theta - phi);
        g2.draw(new Line2D.Double(x0, y0, x1, y1)); // draw this arrow head at point x1, y1

    }


    public static void runGUI(DirectedWeightedGraph_ans gr) {
        new GUI(gr);
    }
}