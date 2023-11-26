package no.ous.compbio.sequence;

import java.io.BufferedReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.commons.cli.*;
import java.io.*;

public class CalcGC {
    // for teaching purposes
    private static final DecimalFormat df = new DecimalFormat("#.##");
    static Options options = new Options();
    static String faFile;
    private ArrayList<String> headerLines;
    private ArrayList<String> sequenceLines;
    
    
    public CalcGC() {
    	headerLines = new ArrayList<String>();
    	sequenceLines = new ArrayList<String>();
    }
    
    public void parseArguments(String args[]) throws ParseException{


        System.out.print("parse arguments" + System.lineSeparator());
        options.addOption("h", "help",             false,  "view help");
        options.addOption("f", "sequence file", true,   "sequence file in FASTA format");


        CommandLineParser clParser = new DefaultParser();
        CommandLine cmd = null;


        try{
            cmd = clParser.parse(options, args);

            if(cmd.hasOption("h")){
                printHelp();
            }
            if (cmd.hasOption("f")) {
                faFile = cmd.getOptionValue("f");
                System.out.printf("fasta input file is <" + faFile + ">"+ System.lineSeparator());
            }else
                throw new ParseException("no fasta file was specified") ;

        }
        catch(ParseException exPa){
        	System.out.printf("error parsing parameters:" + System.lineSeparator() + exPa);
        	System.out.printf("error parsing parameters::" + System.lineSeparator() + exPa);
            throw new ParseException("error parsing parameters");
        }
    }	
	

    /**
     * print command line usage
     *
     */
    public static void printHelp(){
        printBanner();
        HelpFormatter formatter = new HelpFormatter();

        formatter.printHelp("command line options", options);

        System.exit(0);

    }
    
    public int loadFAFile(String filename) throws IOException{
    	String line = "";
    	String headerLine = "";
    	String sequence = "";
    	int seqCount = 0;
    	BufferedReader brFA = new BufferedReader(new FileReader(new File(filename)));
    	
    		while((line = brFA.readLine())!=null) {
    			
    			if(line.startsWith(">")){

    				if(seqCount > 0) {
    					headerLines.add(headerLine);
    					sequenceLines.add(sequence);
        				headerLine = line;
        				sequence="";
    				}else {
    					headerLine = line;
    				}
    				seqCount += 1;
    				
    			}else {
    				sequence = sequence.concat(line.trim());
    			}
    		}
    	brFA.close();
    	return headerLines.size();
    }
    

    public double calcAverageGC() {
    	double avgGC = 0.0;
    	for (String seq: sequenceLines){
    		avgGC += this.calcGC(seq);
    	}
    	return avgGC/(double) this.sequenceLines.size();
    }
    
    
    public double calcGC(String seq) {
    	double gcCount = 0.0;
    	double ntCount = 0.0;
    	for(int n = 0; n <seq.length();n++) {
    		char nt = seq.charAt(n);
    		if(nt == 'G' || nt == 'C'){
    			gcCount ++;
    			ntCount ++;
    		}
    		if(nt == 'A' || nt == 'T' || nt == 'U'){
    			ntCount ++;
    		}

    	}
    	return gcCount/ntCount;
    }


    /**
     * print program info
     *
     */
    public static void printBanner(){
    	System.out.printf("\n\n\n"
                + "    =======================================================================\\" +  System.lineSeparator()
                + "    | CalcGC  :                                                            \\" +  System.lineSeparator()
                + "    |    Java code to calculate GC percentage a in FastA file              \\" +  System.lineSeparator()
                + "    |                                                                      \\" +  System.lineSeparator()
                + "    |                                                                      \\" +  System.lineSeparator()
                + "    |                                                                      \\" +  System.lineSeparator()
                + "    =======================================================================\\" +  System.lineSeparator()
                +  System.lineSeparator() +  System.lineSeparator());

    	System.out.printf("*** report bugs to simon.rayner@medisin.uio.no" +  System.lineSeparator());
    }
    
    
    
    public static void main(String[] args) {

        try {
        	System.out.printf("CalcGC"+ System.lineSeparator());
        	System.out.printf("initializing"+ System.lineSeparator());
        	CalcGC seq1 = new CalcGC();
            
            seq1.parseArguments(args);
            int noOfSequences = seq1.loadFAFile(faFile);
            System.out.printf("read <" + noOfSequences + "> sequences from file" +  System.lineSeparator());


            String formattedValue = String.format("%.2f%%", seq1.calcAverageGC()*100.0);
            System.out.printf("average GC value of all sequences is <" + formattedValue  + "%>" +  System.lineSeparator());


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
