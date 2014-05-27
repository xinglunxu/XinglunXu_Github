
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Graphics Virtual Machine that choose a file that contain command lines 
 * and draw the picture.
 * @author Jiapei Yao, Xinglun Xu
 */

public class GVM {
	public static final int IMAGE_SIZE = 800;
	
	/**
	 * DataMemory and its indexes
	 */
		int[] DataMemory;
		public final int ACC=0;
		public final int X=1; 
		public final int Y=2; 
		public final int WIDTH=3;
		public final int HEIGHT=4;
		public final int RED=5;
		public final int GREEN=6;
		public final int BLUE=7;
		
	/**
	 * programMemory and its indexes
	 */
		int[][] programMemory;
		public final int STOP=0;
		public final int SET=1;
		public final int LOAD=2;
		public final int STORE=3;
		public final int ADD=4;
		public final int ZERO=5;
		public final int GOTO=6;
		public final int SETCOLOR=7;
		public final int DRAWLINE=8;
		public final int DRAWRECT=9;
		public final int FILLRECT=10;
		public final int DRAWOVAL=11;
		public final int FILLOVAL=12;

	private Image image;
	Graphics graphics;
	
	private String ContentOfFile = "";
	private int InstructionAddress = 0;
	
	/**
	 * variables that need to directly pass to other program
	 */
	public boolean STOPSIGNAL = false;
	public String MESSAGE_ON_LEFT = "";
	public String ERROR_ON_RIGHT = "";
	public boolean ASSEMBLY_SUCCESSFUL = false;
			
 	GVM(){

		image= new BufferedImage( IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
		graphics = image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0,0,IMAGE_SIZE, IMAGE_SIZE);
		graphics.setColor(Color.BLACK);
		
	}
	
	public Image getImage(){ return image; }
	
	/**
	 * all loading work: initiate variables, read a file, assembly it, and, initiate environment 
	 */
	public void load() {
		initiate();
		String[] commandLines;
		ContentOfFile = convertFiletoString(chooseFile());
		if (ContentOfFile.trim().length()==0)
			return;
		commandLines = ContentOfFile.split("\n");
		Assembler assembler = new Assembler();
		programMemory = assembler.Assemble(commandLines);
		MESSAGE_ON_LEFT = assembler.getAssemMessage();
		ERROR_ON_RIGHT = assembler.getErrorMessage();
		ASSEMBLY_SUCCESSFUL=assembler.isSuccessful();
		DataMemory=new int[200];
		Arrays.fill(DataMemory, 0);
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0,0,IMAGE_SIZE, IMAGE_SIZE);
	}
	
	public void run(){
		while (!STOPSIGNAL){
			executeInstruction(programMemory[InstructionAddress++]);
		}
	}
	
	public void step(){
		if (STOPSIGNAL) return;
		if (programMemory[InstructionAddress].length!=0){
			executeInstruction(programMemory[InstructionAddress++]);
		}
	}
	
	/**
	 * initiate all variables
	 */
	private void initiate(){
		InstructionAddress = 0;
		ContentOfFile = "";
		STOPSIGNAL = false;
		MESSAGE_ON_LEFT = "";
		ERROR_ON_RIGHT = "";
		ASSEMBLY_SUCCESSFUL = false;
	}
	
	/**
	 * helper functions for load(), choose a text file
	 * @return File
	 */
	private File chooseFile(){
		JFileChooser chooser = new JFileChooser();
		String[] fileFormats = {"txt", "html", "inf", "ini"};
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILE", fileFormats);
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal != JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose not to open a file.");
	       return null;
	    }
		return chooser.getSelectedFile();
	}
	
	/**
	 * it converts all the content in file to String
	 * @param file
	 * @return a String of the same content in file
	 */
	private String convertFiletoString(File file){
		if (file == null)
			return "";
		String retStr = "", to_add;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				while ((to_add=br.readLine())!=null){
						retStr+=(to_add+"\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retStr;
	}
	
	/**
	 * execute the Instruction, which is in the form of int[]
	 * @param instruction
	 */
	private void executeInstruction( int[] instruction ){
		if (instruction.length==0)
			return;
		switch(instruction[0]){
		case STOP:
			STOPSIGNAL=true;
			break;
		case SET: 
			DataMemory[0]=instruction[1];
			break;
		case LOAD:
			DataMemory[0]= DataMemory[instruction[1]];
			break;
		case STORE:
			DataMemory[instruction[1]]=DataMemory[0];
			break;
		case ADD:
			DataMemory[0]+=DataMemory[instruction[1]];
			break;
		case ZERO:
			if (DataMemory[0]!=0)
				InstructionAddress=instruction[1];
			break;
		case GOTO:
			InstructionAddress=instruction[1];
			break;
		case SETCOLOR:
			graphics.setColor(new Color(DataMemory[RED], DataMemory[GREEN], DataMemory[BLUE]));
			break;
		case DRAWLINE:
			graphics.drawLine(DataMemory[X], DataMemory[Y], DataMemory[X]+DataMemory[WIDTH],DataMemory[Y]+DataMemory[HEIGHT]);
			break;
		case DRAWRECT:
			graphics.drawRect(DataMemory[X], DataMemory[Y], DataMemory[WIDTH], DataMemory[HEIGHT]);
			break;
		case FILLRECT:
			graphics.fillRect(DataMemory[X], DataMemory[Y], DataMemory[WIDTH], DataMemory[HEIGHT]);
			break;
		case DRAWOVAL:
			graphics.drawOval(DataMemory[X], DataMemory[Y], DataMemory[WIDTH], DataMemory[HEIGHT]);
			break;
		case FILLOVAL:
			graphics.fillOval(DataMemory[X], DataMemory[Y], DataMemory[WIDTH], DataMemory[HEIGHT]);
			break;
		}
	}
	
}
