package no.ous.compbio.sequence;

import static org.junit.Assert.*;

import org.junit.Test;

public class GCCalcTest {

	@Test
	public void testCalcGC() {
		GCCalc seq1 = new GCCalc();
		assertEquals(seq1.calcGC("CCCCGGGG"), 1.0, 0.000);
		assertEquals(seq1.calcGC("AACCGGTT"), 0.50, 0.000);

	}

}
