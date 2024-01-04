//run here
package Main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.*;

public class HotelManagement extends JFrame{
	private static final long serialVersionUID = 1L;
	static JFrame HMS= new JFrame("Hotel Management Software");
	static Color maroon = new Color(136,0,21);
	static Font ours= new Font (null,Font.PLAIN,27);
	static Border border= new LineBorder(Color.black,2);
	static List<Component> activecomponents = new ArrayList<Component>();
	static Room[] rooms= new Room[5];
	static String Filename = "db.txt";
	static int currentroom;
	// Declare Global Buttons
	static JButton selection = new JButton("Main Menu");
	static String path = System.getProperty("user.dir");
	static JButton patronBack = new JButton("Back"); // ADDED BACK BUTTON FOR PATRON SCREEN WHEN PURCHASING ITEM***
	static JButton recent = new JButton();
	ActionListener l; 
	static JLabel name_label;
    static JTextField name_textfield;
    static double patron_total = 0.0; // Keep track of patron purchases
    
	public static void main(String[] args){
		rooms[0] = new Room(false,true,0,new ArrayList<String>());
		rooms[1] = new Room(false,false,0,new ArrayList<String>());
		rooms[2] = new Room(false,false,0,new ArrayList<String>());
		rooms[3] = new Room(false,false,0,new ArrayList<String>());
		rooms[4] = new Room(false,false,0,new ArrayList<String>());
		boot();
		HMS.setAlwaysOnTop(true);
		HMS.setSize(635,700);
		HMS.setLayout(null);
		HMS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(!path.contains("\\src")){//logic to add correct path
			path = path + "\\"+ "src" + "\\" + "Main" +"\\";
		}else{
			path = path + "\\" + "Main" +"\\"; 
		}
		JButton logo = new JButton(new ImageIcon(path + "a.png"));;
		ImageIcon icon = new ImageIcon(path + "a.png");
		HMS.setIconImage(icon.getImage());
		logo.setBounds(200,20,231,140);
		logo.setContentAreaFilled(false);
		logo.setBorderPainted(false);
		HMS.add(logo);
		makebutton(selection,0,603,175,50);
		makebutton(patronBack,0,603,175,50);
		activecomponents.remove(selection);
		selection.addActionListener(HotelManagement :: mainmenu);
		patronBack.addActionListener(HotelManagement :: patronBackButton); // ADDED BACK BUTTON FOR PATRON SCREEN
		MM();
		HMS.setVisible(true);
	}
	
	public static void boot(){
		FileRead fr = new FileRead(Filename);
		for(int i = 0;i< fr.getNumLines();i++){
			String raw = fr.getLine(i);
			StringTokenizer word = new StringTokenizer(raw,",");
			rooms[i].setreserved(Boolean.valueOf(word.nextToken().toString()));
			rooms[i].setservice(Boolean.valueOf(word.nextToken().toString()));
			rooms[i].addtotal(Integer.parseInt(word.nextToken().toString()));
			while(word.hasMoreTokens()){
				rooms[i].additems(word.nextToken().toString());
			}
			try{
				rooms[i].additems(word.nextToken().toString());
			}catch(Exception e){
				pn("OVERLOAD");
			}
		}
	}
	
	public static void save(){
		FileWrite fw = new FileWrite(Filename);
		for(int i = 0; i < 5;i++){
			String foods = "";
			for(int a = 0; a < rooms[i].getfood().size();a++){
				foods = foods + "," + rooms[i].getfood().get(a);
			}
			fw.writeLine(rooms[i].getreserved() + "," + rooms[i].getservice() + "," + rooms[i].gettotal() + foods);
		}
		fw.saveFile();
		return;
	}
	
	public static void MM(){
		clear();
		HMS.remove(selection);
		HMS.getContentPane().setBackground(new Color (247,240,214));
		JButton owner = new JButton("Owner");
		makebutton(owner,200,300,231,50);
		JButton patron = new JButton("Patron");
		makebutton(patron,200,400,231,50);
		JButton employee = new JButton("Employee");
		makebutton(employee,200,500,231,50);
		JButton sv = new JButton("Please select view");
		sv.setBounds(165,200,300,50);  //(horizontal placement,vertical placement,length right,height)
		sv.setFont(ours);
		sv.setBorderPainted(false);
		sv.setContentAreaFilled(false);
		show(sv);
		pn("successfully set up main menu");
		//creating action listeners
		owner.addActionListener(HotelManagement :: OV);
		patron.addActionListener(HotelManagement :: PV);
		employee.addActionListener(HotelManagement :: EV);
	}
	
	public static void patronBackButton(ActionEvent e){//for Main
        // Back Button for when you purchase items and want to go back to main patron screen **
        clear();
        PV2(currentroom);
    }
	
	public static void mainmenu(ActionEvent e){//for Main
		clear();
		MM();
	}
	
	public static void OV(ActionEvent e){//for owner view
		clear();
		HMS.add(selection);
		JButton revenue = new JButton("Total : 123123$ made today");
		makebutton(revenue,120,200,400,50);
		revenue.setBorderPainted(false);
		revenue.setContentAreaFilled(false);
		revenue.setForeground(maroon);
		JButton working = new JButton("Who is working?");
		makebutton(working,200,300,231,50);
		JButton pending = new JButton("Pending Requests");
		makebutton(pending,200,400,231,50);
		pending.addActionListener(HotelManagement :: ORequests);
		working.addActionListener(HotelManagement :: whoworking);
		return;
	}
	
	public static void whoworking(ActionEvent e){
		clear();
		JButton scheduler = new JButton(new ImageIcon(path + "Daily Schedule.png"));
		makebutton(scheduler,180,-60,300,900);
		scheduler.setContentAreaFilled(false);
		scheduler.setBorderPainted(false);
		HMS.add(scheduler);
		HMS.remove(selection);
		JButton back = new JButton("Back");
		makebutton(back,0,603,175,50);
		HMS.add(back);
		back.addActionListener(HotelManagement :: OV);
		return;
	}

	public static void ORequests(ActionEvent e){
		clear();

		JButton servRoom0 = new JButton("Room 1 Requests");
		JButton servRoom1 = new JButton("Room 2 Requests");
		JButton servRoom2 = new JButton("Room 3 Requests");
		JButton servRoom3 = new JButton("Room 4 Requests");
		JButton servRoom4 = new JButton("Room 5 Requests");

		makebutton(servRoom0,120,200,400,50);
		makebutton(servRoom1,120,260,400,50);
		makebutton(servRoom2,120,320,400,50);
		makebutton(servRoom3,120,380,400,50);
		makebutton(servRoom4,120,440,400,50);

		servRoom0.addActionListener(HotelManagement :: oseerequests);
		servRoom1.addActionListener(HotelManagement :: oseerequests);
		servRoom2.addActionListener(HotelManagement :: oseerequests);
		servRoom3.addActionListener(HotelManagement :: oseerequests);
		servRoom4.addActionListener(HotelManagement :: oseerequests);

		requestCheck(servRoom0, 0);
		requestCheck(servRoom1, 1);
		requestCheck(servRoom2, 2);
		requestCheck(servRoom3, 3);
		requestCheck(servRoom4, 4);					


		JButton back = new JButton("Back");
		makebutton(back,0,603,175,50);

		HMS.remove(selection);
		HMS.add(back);
		back.addActionListener(HotelManagement :: OV);
		
		return;
	}

	public static void oseerequests(ActionEvent e){
		clear();
		if(e.toString().contains("Room 1")){
			show(0);
		}else if(e.toString().contains("Room 2")){
			show(1);
		}else if(e.toString().contains("Room 3")){
			show(2);
		}else if(e.toString().contains("Room 4")){
			show(3);
		}else if(e.toString().contains("Room 5")){
			show(4);
		}
		JButton back = new JButton("Back");
		makebutton(back,0,603,175,50);
		HMS.remove(selection);
		HMS.add(back);
		back.addActionListener(HotelManagement :: ORequests);
	}
	
	public static void show(int room){
		JTextArea requests = new JTextArea();
		try{
			requests.setText(rooms[room].getfood().get(0));
			for(int i = 1; i < rooms[room].getfood().size();i++){
				requests.append("\n" + rooms[room].getfood().get(i));
			}
		}catch(Exception e){
			requests.setText("Currently no requests");	
		}
		requests.setEditable(false);
		requests.setBounds(200,200,300,300);
		requests.setOpaque(false);
		requests.setFont(ours);
		activecomponents.add(requests);
		HMS.add(requests);
	}
	
	public static void requestCheck(JButton j, int n){
		if(rooms[n].getservice() == false){
			j.setEnabled(false);

		}else{
			j.setEnabled(true);
		}
		return;
	}
	

    // The FIRST patron screen
    public static void PV(ActionEvent e){ //for patron view
        clear();
        HMS.add(selection);

        // Create TextField
        name_textfield = new JTextField();
        name_textfield.setForeground(Color.black);
        name_textfield.setBackground(Color.lightGray);
        name_textfield.setFont(ours);
        name_textfield.setCaretColor(Color.black);
        name_textfield.setBounds(205,250,300,50);

        // Create Button
        JButton name_button = new JButton("Submit Name: ");
        makebutton(name_button,60,250,200,50);
        name_button.addActionListener(HotelManagement::PV1);


        // Create label
        name_label = new JLabel();
        name_label.setBackground(new Color(247,240,214));
        name_label.setBounds(60,0,500,500);
        name_label.setOpaque(true);
        name_label.setVisible(true);
        name_label.setFocusable(false);
        name_label.add(name_textfield);

        HMS.add(name_label);
        activecomponents.add(name_label);
        return;
    }

    // The SECOND patron screen after user inputs name
    public static void PV1(ActionEvent e){//for patron view
        HMS.remove(name_label);
        HMS.remove(name_textfield);
        clear();
        HMS.add(selection);

        // Create Buttons for Patron Screen
        JButton reserveroom = new JButton("Reserve a room");
        makebutton(reserveroom,200,300,231,50);
        reserveroom.addActionListener(HotelManagement :: PRR);
        return;
    }


    // This Keeps track of patron room reservation, the "third step"
    public static void PRR(ActionEvent e){
        for(int i = 0; i < rooms.length; i++)
        {
            if(!rooms[i].getreserved())
            {
                rooms[i].setreserved(true);
                currentroom = i;
                save();
                PV2(i);
                return;
            }
        }
    
        JButton reservedmessage = new JButton("We are currently at maximum capacity");
        makebutton(reservedmessage,-125,200,900,50);//(horizontal placement,vertical placement,length right,height)
        reservedmessage.setContentAreaFilled(false);
        reservedmessage.setBorderPainted(false);
        reservedmessage.setForeground(Color.black);
    }

    // The Fourth Patron Screen, allows Patron to access other services
    public static void PV2(int room){
        clear();
        HMS.add(selection);
        recent.setText("");
        // Create a JLabel to hold a welcome message
        JLabel welcome_label = new JLabel();
        welcome_label.setBounds(197,135,300,100);
        welcome_label.setText("Welcome, " + name_textfield.getText() + "!");
        welcome_label.setFont(ours);
        welcome_label.setForeground(Color.black);
        activecomponents.add(welcome_label);
        HMS.add(welcome_label);

        // Create Buttons for Patron Screen
        makebutton(recent,145,240,350,50);
        recent.setContentAreaFilled(false);
        recent.setBorderPainted(false);
        recent.setForeground(Color.black);
        
        JButton reservedmessage = new JButton("You have reserved room " + (room + 1));
        makebutton(reservedmessage,150,200,331,50);//(horizontal placement,vertical placement,length right,height)
        reservedmessage.setContentAreaFilled(false);
        reservedmessage.setBorderPainted(false);
        reservedmessage.setForeground(Color.black);

        JButton viewrequests = new JButton("View requests");
        makebutton(viewrequests,200,300,231,50);
        viewrequests.addActionListener(HotelManagement::pseerequests);
        
        JButton requestService;
        requestService = new JButton("Request Service");
        makebutton(requestService, 200,400,231,50);
        requestService.addActionListener(HotelManagement :: checkoutservice);
        
        JButton checkout;
        checkout = new JButton("Purchase Items");
        makebutton(checkout, 200,500,231,50);
        checkout.addActionListener(HotelManagement::PVCheckout);

        // Create fake "button" that displays a running total message, using global variable patron_total
        JButton total_button = new JButton("Your total is $" + rooms[currentroom].gettotal());
        makebutton(total_button,180,560,260,50);//(horizontal placement,vertical placement,length right,height)
        total_button.setContentAreaFilled(false);
        total_button.setBorderPainted(false);
        total_button.setForeground(Color.black);
    }

    public static void checkoutservice(ActionEvent e){
        rooms[currentroom].additems("Cleaning");
        recent.setText("Service has been requested");
        save();
    }
    
    public static void PVCheckout(ActionEvent e){
        clear();
        HMS.remove(selection);
        HMS.add(patronBack); // PATRON BACK BUTTON
        recent.setText("");
        makebutton(recent,50,150,500,50);
        recent.setContentAreaFilled(false);
        recent.setBorderPainted(false);
        recent.setForeground(Color.black);
        
        activecomponents.add(patronBack);
        JButton towel_button = new JButton("Towels: $5");
        makebutton(towel_button,210,220,200,100);
        towel_button.addActionListener(HotelManagement::checkoutTowel);

        JButton pillow_button = new JButton("Pillows: $4");
        makebutton(pillow_button,210,350,200,100);
        pillow_button.addActionListener(HotelManagement::checkoutPillow);

        JButton toothbrush_button = new JButton("Toothbrush: $3");
        makebutton(toothbrush_button, 210, 480,200,100);
        toothbrush_button.addActionListener(HotelManagement::checkoutToothbrush);
    }
    

	public static void pseerequests(ActionEvent e){
		clear();
		HMS.remove(selection);
		HMS.add(patronBack);
		activecomponents.add(patronBack);
		patronBack.addActionListener(HotelManagement :: patronBackButton);
		show(currentroom);
	}

    // Updates patron_total when they purchase item
    public static void checkoutTowel(ActionEvent e)
    {
    	recent.setText("Towels have been requested");
    	rooms[currentroom].addtotal(5);
        rooms[currentroom].additems("Towel");
        save();
    }
    
    // Updates patron_total when they purchase item
    public static void checkoutPillow(ActionEvent e)
    {
    	recent.setText("Pillows have been requested");
        rooms[currentroom].addtotal(4);
        rooms[currentroom].additems("Pillow");
        save();
    }
    
    // Updates patron_total when they purchase item
    public static void checkoutToothbrush(ActionEvent e)
    {
    	recent.setText("Toothbrushes have been requested");
    	rooms[currentroom].addtotal(3);
        rooms[currentroom].additems("Toothbrush");
        save();
    }

	
	public static void EV(ActionEvent e){//for employee view
		clear();
		HMS.add(selection);
		JButton ci = new JButton("Clock In");
		makebutton(ci,200,300,231,50);
		ci.addActionListener(HotelManagement :: EV1);
		return;
	}
	
	public static void EV1(ActionEvent e){
		HMS.remove(selection);
		clear();
		JButton revenue = new JButton("Total : 1231233$ made this month");
		makebutton(revenue,-200,200,1000,50);
		revenue.setBorderPainted(false);
		revenue.setContentAreaFilled(false);
		revenue.setForeground(maroon);
		JButton co = new JButton("Clock Out");
		makebutton(co,0,603,175,50);
		JButton pending = new JButton("Pending Requests");
		makebutton(pending,200,300,231,50);
		pending.addActionListener(HotelManagement :: eRequests);
		co.addActionListener(HotelManagement :: EV);
	}
	
	public static void eRequests(ActionEvent e){
		clear();

		JButton servRoom0 = new JButton("Room 1 Requests");
		JButton servRoom1 = new JButton("Room 2 Requests");
		JButton servRoom2 = new JButton("Room 3 Requests");
		JButton servRoom3 = new JButton("Room 4 Requests");
		JButton servRoom4 = new JButton("Room 5 Requests");

		makebutton(servRoom0,120,200,400,50);
		makebutton(servRoom1,120,260,400,50);
		makebutton(servRoom2,120,320,400,50);
		makebutton(servRoom3,120,380,400,50);
		makebutton(servRoom4,120,440,400,50);

		servRoom0.addActionListener(HotelManagement :: eseerequests);
		servRoom1.addActionListener(HotelManagement :: eseerequests);
		servRoom2.addActionListener(HotelManagement :: eseerequests);
		servRoom3.addActionListener(HotelManagement :: eseerequests);
		servRoom4.addActionListener(HotelManagement :: eseerequests);

		requestCheck(servRoom0, 0);
		requestCheck(servRoom1, 1);
		requestCheck(servRoom2, 2);
		requestCheck(servRoom3, 3);
		requestCheck(servRoom4, 4);					


		JButton back = new JButton("Back");
		makebutton(back,0,603,175,50);

		HMS.remove(selection);
		HMS.add(back);
		back.addActionListener(HotelManagement :: EV1);
		
		return;
	}

	public static void eseerequests(ActionEvent e){
		clear();
		if(e.toString().contains("Room 1")){
			show(0);
		}else if(e.toString().contains("Room 2")){
			show(1);
		}else if(e.toString().contains("Room 3")){
			show(2);
		}else if(e.toString().contains("Room 4")){
			show(3);
		}else if(e.toString().contains("Room 5")){
			show(4);
		}
		JButton back = new JButton("Back");
		makebutton(back,0,603,175,50);
		HMS.remove(selection);
		HMS.add(back);
		back.addActionListener(HotelManagement :: eRequests);
	}
	
	public static void show(Component comp){
		HMS.add(comp);
		activecomponents.add(comp);
		pn(comp.toString() + " is now showing on HMS");
	}
	
	public static void clear(){
		while(activecomponents.isEmpty()==false){
			HMS.remove(activecomponents.get(0));
			pn("cleared " + activecomponents.get(0) + " successfully");
			activecomponents.remove(0);
		}
		HMS.repaint();
		pn("cleared screen successfully");
	}
	
	public static void makebutton(JButton button,int x, int y, int length, int height){//custom method to clean our program for main menu
		button.setBounds(x,y,length,height);  //(horizontal placement,vertical placement,length right,height)
		button.setBackground(maroon);
		button.setBorder(border);
		button.setForeground(Color.white);
		button.setFont(ours);
		button.setFocusable(false);
		show(button);
		pn("made button " + button.toString() + " at height" + height);
	}
	
	// Helper method for easy printing on same line 
	public static <E> void p(E item){
		System.out.print(item + " ");
	}	
	
	// Helper method for easy printing with line return
	public static <E> void pn(E item){
		System.out.println(item);
	}
}