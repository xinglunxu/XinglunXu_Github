import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class assembly a human-readable command to a machine-language-like data
 * @author Jiapei Yao, Xinglun Xu
 */

public class Assembler {
	/**
	 * This constants are consistent with the indexes of DataMemory.
	 */
			private final int ACC=0;
			private final int X=1; 
			private final int Y=2; 
			private final int WIDTH=3;
			private final int HEIGHT=4;
			private final int RED=5;
			private final int GREEN=6;
			private final int BLUE=7;
			
	/**
	 * programMemory Section: programMemory and its indexes.
	 */
			int[][] programMemory;
			private final int STOP=0;
			private final int SET=1;
			private final int LOAD=2;
			private final int STORE=3;
			private final int ADD=4;
			private final int ZERO=5;
			private final int GOTO=6;
			private final int SETCOLOR=7;
			private final int DRAWLINE=8;
			private final int DRAWRECT=9;
			private final int FILLRECT=10;
			private final int DRAWOVAL=11;
			private final int FILLOVAL=12;
			
		/**
		 * Three important HashMap
		 */
			private HashMap<String , Integer> toPMCode; //Find opcode
			private HashMap<String , Integer> labelNum;
			private HashMap<String , Integer> defineMap;
		/**
		 * Some variables that help operation
		 */
			private Pattern identifierPattern = Pattern.compile("[a-zA-Z_]\\w*");
			private Pattern numberPattern = Pattern.compile("[-]?[0-9]+");
			private int BuildStep = 0;
			private int NUM_OF_ERROR = 0;
			private String MESSAGE_ON_LEFT = "";
			private String ERROR_ON_RIGHT = "starting assembly ...\n";
			private double Duration = 0;
			
		
			Assembler(){
				toPMCode = new HashMap<String , Integer>();
				toPMCode.put("stop", STOP);
				toPMCode.put("set",SET);
				toPMCode.put("load",LOAD);
				toPMCode.put("store",STORE);
				toPMCode.put("add",ADD);
				toPMCode.put("zero",ZERO);
				toPMCode.put("goto",GOTO);
				toPMCode.put("setcolor",SETCOLOR);
				toPMCode.put("drawline",DRAWLINE);
				toPMCode.put("drawrect",DRAWRECT);
				toPMCode.put("fillrect",FILLRECT);
				toPMCode.put("drawoval",DRAWOVAL);
				toPMCode.put("filloval",FILLOVAL);
				labelNum = new HashMap<String , Integer>();
				defineMap = new HashMap<String , Integer>();
				defineMap.put("ACC", ACC);
				defineMap.put("X", X);
				defineMap.put("Y", Y);
				defineMap.put("WIDTH", WIDTH);
				defineMap.put("HEIGHT", HEIGHT);
				defineMap.put("RED", RED);
				defineMap.put("GREEN", GREEN);
				defineMap.put("BLUE", BLUE);	
			}
			
			/**
			 * The main tool that gets command lines and returns the machine-readable data
			 * @param commandLines
			 * @return ProgramMemory
			 */
			public int[][] Assemble(String[] commandLines){
				long StartTime = System.currentTimeMillis();
				int[][] ProgramMemory = new int[commandLines.length][];
				defineAllLabel(commandLines);
				while (commandLines.length > BuildStep){
					ProgramMemory[BuildStep] = processLine(commandLines[BuildStep]);
					BuildStep++;
				}
				Duration = (double)(System.currentTimeMillis() - StartTime)/(double)1000;
				return ProgramMemory;
			}
			
			/**
			 * this function defines all labels in the command lines
			 * @param commandLines
			 */
			private void defineAllLabel(String[] commandLines){
				for (int i=0; i<commandLines.length;i++){
					String str = commandLines[i].replaceFirst("#.*", "").trim();
					if (str.length()!=0){
						String[] operands = str.split("(\\s|\\t)+");
						if (!isOpcode(operands[0])){
							if (!labelNum.containsKey(operands[0])){
								labelNum.put(operands[0], i);
								//System.out.println(operands[0]); //<----------------------TEST
							}
						}
					}
				}
			}
			
			/**
			 * 1. puts one single command line to be printed on the left JTextArea
			 * 2. gets rid of the comment which begins with "#"
			 * 3. delete useless whitespace on the beginning and end of the line
			 * @param line
			 * @return analyzeCommand(command) if and only if the 
			 * 		command is not empty string
			 */
			private int[] processLine(String line){
				printOnLeft(line);
				String command = line.replaceFirst("#.*", "").trim();
				if (command.length()>0)
					return analyzeCommand(command);
				else{
					return new int[0];
				}
			}
			
			/**
			 * Analyze the command:
			 * 1. if label is found, validate the label, delete it from the command line
			 * 2. pass command to processOpcode(command) and go on
			 * @param command
			 * @return integer[] that returned by processOpcode(command),
			 * 		or new int[0] if something is invalid
			 */
			private int[] analyzeCommand(String command){
				String[] operands = command.split("(\\s|\\t)+");
				if (labelNum.containsKey(operands[0])){ //there are label
					if (validLabel(operands[0])){
						String NewCommand="";
						for (int i=1; i<operands.length;i++)
							NewCommand+=(operands[i]+" ");
						NewCommand = NewCommand.trim();
						return processOpcode(NewCommand);
					}
					else {
						return new int[0];
					}
				}
				else if (!isOpcode(operands[0])){ //the first word is not an opcode; it's a label
					// it's a label without valid opcode
					printOnRight("labeled statement is missing an opcode");
					return new int[0];
				}
				else{ //the first one is an opcode
					return processOpcode(new String(command));
				}
			}
			
			/**
			 * A helper function which validates the label:
			 * @param label
			 * @return true if and only if:
			 * 		1. the label is identifier
			 * 	&&	2. the label is not duplicate
			 */
			private boolean validLabel(String label){
				if (!isValidID(label)){ //validate the label
					printOnRight(label+" is an in valid statement label.");
					return false;
				}
				else if (labelNum.get(label)!=BuildStep){ //label is duplicate
					printOnRight(label+" has been defined previously.");	
					return false;
				}
				else { // label is both valid and not duplicate, need to be defined
					return true;
				}
			}
			
			/**
			 * Process a command without any #command and label:
			 * 1. validate opcode
			 * 2. distinguish define and other opcode
			 * @param command
			 * @return the integer[] return by processDefine(command) or processPMOPCode(Command)
			 * 		new integer[0] if something is invalid.
			 */
			private int[] processOpcode(String command){
				final String COMMAND = command;
				String[] operands = command.split("(\\s|\\t)+");
				if (operands[0].length()==0){ //empty string (only label)
					printOnRight("labeled statement is missing an opcode.");
					return new int[0];
				}
				else if (operands[0].equals("define")){ //define
					return processDefine(new String(COMMAND));
				}
				else if (!toPMCode.containsKey(operands[0])){//invalid opcode
					printOnRight(operands[0]+" is an invalid opcode.");
					return new int[0];
				}
				else //process all valid opcode except "define"
					return processPMOPCode(new String(COMMAND));
			}
			
			/**
			 * process the define opcode:
			 * 1. validate the command
			 * 2. if everything is valid, define the opcode, using the HashMap defineMap
			 * @param command
			 * @return integer[0]
			 */
			private int[] processDefine(String command){
				String[] operands = command.split("(\\s|\\t)+");
				if (operands.length!=3){ //Check the number of Operands
					printOnRight("The number of operands for this opcode is incorrect");
					return new int[0];
				}
				else if (!isValidID(operands[1])){ //invalid identifier
					printOnRight("define opcode's 1st operand must be an identifier.");
					return new int[0]; 
				}
				else if (!isNum(operands[2])){ //invalid define value 
					printOnRight(operands[2]+" is not interpretbale as a number.");
					return new int[0];
				}
				else{ //3 operands: define + id + number
					if (defineMap.containsKey(operands[1])){ //this id has been defined
						printOnRight(operands[1] + " has been defined previously.");
					}
					else {//firstly defined
						int defineNum = getNum(operands[2]);
						defineMap.put(operands[1], defineNum);
					}
					return new int[0];
				}
			}
			
			/**
			 * process all the valid opcode except define:
			 * 1. distinguish the opcode by its running pattern
			 * 2. pass the distinguished command to different functions
			 * @param command
			 * @return integer[] return by processGOTOZERO(command), processTwoOP(command), 
			 * 		or processOneOP(command)
			 */
			private int[] processPMOPCode(String command){
				String[] operands = command.split("(\\s|\\t)+");
				if (operands[0].equals("goto") || operands[0].equals("zero")){
					return processGOTOZERO(command);
				}
				else if (operands[0].equals("set") || operands[0].equals("load")
						|| operands[0].equals("store") || operands[0].equals("add"))
					return processTwoOP(command);
				else
					return processOneOP(command);
			}
			
			/**
			 * process goto and zero:
			 * 1. validate the command;
			 * 2. find the machine codes of opcode and operands using HashMap 
			 * 	for opcode and defined number;
			 * @param command
			 * @return integer[2] consist of machine codes, or
			 *  	new integer[0] if something is invalid
			 */
			private int[] processGOTOZERO(String command){
				String[] operands = command.split("(\\s|\\t)+");
				if (operands.length!=2){
					printOnRight("The number of operands for this opcode is incorrect");
					return new int[0];
				}
				if (isValidID(operands[1])){ //2nd operand is an identifier
					if (labelNum.containsKey(operands[1])){ //A defined label
						int[] ins = new int[2];
						ins[0] = toPMCode.get(operands[0]);
						ins[1] = labelNum.get(operands[1]);
						return ins;
					}
					else{
						printOnRight(operands[1]+" is undefined.");
						return new int[0];
					}
				}
				else { //2nd operand is a valid or invalid number
					if (isNum(operands[1])){ //A valid Number
						int[] ins = new int[2];
						ins[0] = toPMCode.get(operands[0]);
						ins[1] = getNum(operands[1]);
						return ins;
					}
					else{
						printOnRight(operands[1]+" is not interpretable as a number.");
						return new int[0];
					}
				}
			}
			
			/**
			 * process all command with only opcode:
			 * 1. validate the command
			 * 2. find the machine code of the opcode using HashMap 
			 * @param command
			 * @return integer[1] of the machine code, or
			 * 		new integer[0] if something is invalid.
			 */
			private int[] processOneOP(String command){
				String[] operands = command.split("(\\s|\\t)++");
				if (operands.length!=1){
					printOnRight("The number of operands for this opcode is incorrect");
					return new int[0];
				}
				int[] ins = new int[] {toPMCode.get(operands[0])};
				return ins;
			}
			
			/**
			 * process command with opcode and operands (except goto and zero):
			 * 1. validate the command;
			 * 2. find the machine codes of opcode and operands using HashMap 
			 * 	for opcode and defined number;
			 * @param command
			 * @return integer[2] consist of machine codes, or
			 *  	new integer[0] if something is invalid
			 */
			private int[] processTwoOP(String command){
				String[] operands = command.split("(\\s|\\t)++");
				if (operands.length!=2){
					printOnRight("The number of operands for this opcode is incorrect");
					return new int[0];
				}
				if (isValidID(operands[1])){ //2nd operand is an identifier
					if (defineMap.containsKey(operands[1])){ //A defined label
						int[] ins = new int[2];
						ins[0] = toPMCode.get(operands[0]);
						ins[1] = defineMap.get(operands[1]);
						return ins;
					}
					else{
						printOnRight(operands[1]+" is undefined.");
						return new int[0];
					}
				}
				else { //2nd operand is a valid or invalid number
					if (isNum(operands[1])){ //A valid Number
						int[] ins = new int[2];
						ins[0] = toPMCode.get(operands[0]);
						ins[1] = getNum(operands[1]);
						return ins;
					}
					else{
						printOnRight(operands[1]+" is not interpretable as a number.");
						return new int[0];
					}
				}
			}
			
			/**
			 * A helper function that find if a word is an opcode
			 * @param word
			 * @return true if word is an opcode
			 */
			private boolean isOpcode(String word){
				return toPMCode.containsKey(word)||word.equals("define");
			}
			
			/**
			 * A helper function that find the string is a valid number
			 * @param str
			 * @return true if str is consist of only valid number
			 */
			private boolean isNum(String str){
				Matcher numP = numberPattern.matcher(str);
				String numStr;
				if (numP.find()){
					numStr = numP.group();
					return numStr.equals(str);
				}
				else
					return false;
			}
			
			/**
			 * A helper function that converts a valid string that contain only number
			 * @param numStr
			 * @return number in numStr
			 */
			private int getNum(String numStr){
				int sign = 1;
				if (numStr.charAt(0)=='-'){
					sign = -1;
					numStr = numStr.substring(1, numStr.length());
				}
				return Integer.parseInt(numStr)*sign;
			}
			
			/**
			 * A helper function that validate an identifier
			 * @param word
			 * @return true if and only if the word is a valid identifier
			 * 		false if invalid
			 */
			private boolean isValidID(String word){
				Matcher id = identifierPattern.matcher(word);
				if (id.find())
					if (id.start()==0)
						return true;
				return false;
			}
			
			/**
			 * Collect all the error messages that will be put in the JTextArea on the right
			 * @param errorReason
			 */
			private void printOnRight(String errorReason){
				NUM_OF_ERROR++;
				ERROR_ON_RIGHT += ((BuildStep+1) + ": "+errorReason+"\n");
			}
			
			/**
			 * Collect the messages about all step of assembly
			 * @param Message
			 */
			private void printOnLeft(String Message){
				MESSAGE_ON_LEFT += ((BuildStep+1) + "      "+Message+"\n");
			}
			
			/**
			 * pass MESSAGE_ON_LEFT to outer program to print
			 * @return MESSAGE_ON_LEFT
			 */
			public String getAssemMessage(){ return MESSAGE_ON_LEFT; }
			
			/**
			 * pass MESSAGE_ON_RIGHT to outer program to print
			 * @return return ERROR_ON_RIGHT;
			 */
			public String getErrorMessage(){
				if (NUM_OF_ERROR==0)
					ERROR_ON_RIGHT+=("BUILD SUCCESSFUL. Assembly time: " + Duration + " sec.");
				else{
					ERROR_ON_RIGHT+=("BUILD FAILED. Assembly time: " + Duration + " sec. ");
					ERROR_ON_RIGHT+=("Number of invalid statement: "+NUM_OF_ERROR);
				}
				return ERROR_ON_RIGHT;
			}
			
			/**
			 * find if build is successful
			 * @return true if build is successful
			 * 		false if failed
			 */
			public boolean isSuccessful(){
				return NUM_OF_ERROR==0;
			}
}
