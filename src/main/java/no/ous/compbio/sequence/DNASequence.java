package no.ous.compbio.sequence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.*;
import org.apache.commons.lang3.StringUtils;

public class DNASequence {
	
	private String sequence;
	private double aCount;
	private double cCount;
	private double gCount;
	private double tCount;
	
	
	
	
	/**
	 * calculate occurrence of nucleotide N using slow method
	 * 
	 * @param qSeq: the query sequence
	 * @param qNT:  the query nucleotide
	 * 
	 * @return: number of times the
	 */
	public static int countNucleotideBoring(String qSeq, char qNT) {
		int ntCount = 0;
		for (int i = 0; i < qSeq.length(); i++) {
		    if (qSeq.charAt(i) == qNT) {
		    	ntCount++;
		    }
		}
		return ntCount;
	}
	
	
	public static long countNucleotideRegEx(String qSeq, char qNT) {	
		long ntCount = qSeq.chars().filter(ch -> ch == qNT).count();
		return ntCount;
	}
	
	/**
	 * calc using StringUtils (need org.apache.commons/commons-lang3)
	 * @param qSeq
	 * @param qNT
	 * @return
	 */
	public static long countNucleotideStringUtils(String qSeq, char qNT) {
		long ntCount = StringUtils.countMatches("elephant", "e");
		return ntCount;
	}
	
	
	
	
	public double gettCount() {
		return tCount;
	}
	public void settCount(double tCount) {
		this.tCount = tCount;
	}
	public double getgCount() {
		return gCount;
	}
	public void setgCount(double gCount) {
		this.gCount = gCount;
	}
	public double getcCount() {
		return cCount;
	}
	public void setcCount(double cCount) {
		this.cCount = cCount;
	}
	public double getaCount() {
		return aCount;
	}
	public void setaCount(double aCount) {
		this.aCount = aCount;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	
	
	
}
