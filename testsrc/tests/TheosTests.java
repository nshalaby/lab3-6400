package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import paragraphing.Paragrapher;
import paragraphing.ParagrapherI;

public class TheosTests {
	
	ParagrapherI out ;
	MockDestination dest ;

	@Before
	public void setUp() throws Exception {
		dest = new MockDestination() ;
		out = new Paragrapher( dest ) ;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmpty() {
		String[] expected = new String[0] ;
		out.ship();
		String[] result = dest.getResult() ;
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test () {
		out.setWidth( 7 ) ;
		out.addWord( new String[] { "This" } );
		out.addWord( new String[] { "is" } );
		out.addWord( new String[] { "a" } );
		out.addWord( new String[] { "para", "graph." } );
		out.ship();
		String[] expected = new String[] {
				//     1234567
				"<p>",
				"<line>This is</line>",
				"<line>a para-</line>",
				"<line>graph.</line>",
				"</p>"} ;
		String[] result = dest.getResult() ;
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test6 () {
		out.setWidth( 6 ) ;
		out.addWord( new String[] { "I" } );
		out.addWord( new String[] { "am" } );
		out.addWord( new String[] { "a" } );
		out.addWord( new String[] { "para", "graph." } );
		out.ship();
		String[] expected = new String[] {
				//     123456
				"<p>",
				"<line>I am a</line>",
				"<line>para-</line>",
				"<line>graph.</line>",
				"</p>"} ;
		String[] result = dest.getResult() ;
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test3PartWith5 () {
		out.setWidth( 5 ) ;
		out.addWord( new String[] { "123", "ab", "xy" } );
		out.ship();
		String[] expected = new String[] {
				//     12345
				"<p>",
				"<line>123-</line>",
				"<line>abxy</line>",
				"</p>"} ;
		String[] result = dest.getResult() ;
		assertArrayEquals(expected, result) ;
	}
	
	@Test
	public void test3PartsWidth6 () {
		out.setWidth( 6 ) ;
		out.addWord( new String[] { "123", "ab", "xy" } );
		out.ship();
		String[] expected = new String[] {
				//     123456
				"<p>",
				"<line>123ab-</line>",
				"<line>xy</line>",
				"</p>"} ;
		String[] result = dest.getResult() ;
		assertArrayEquals(expected, result) ;
	}
}

