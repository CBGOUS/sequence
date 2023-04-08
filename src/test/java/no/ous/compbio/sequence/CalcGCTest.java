package no.ous.compbio.sequence;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalcGCTest {

	@Test
	public void test() {
		CalcGC seq1 = new CalcGC();
		assertEquals(seq1.calcGC("CCCCGGGG"), 1.0);
		assertEquals(seq1.calcGC("AACCGGTT"), 0.50);
	}

}
