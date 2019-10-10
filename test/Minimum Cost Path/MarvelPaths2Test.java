package hw6;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import hw4.Graph;
import hw5.MarvelPaths;


public class MarvelPaths2Test {

	private static final int TIMEOUT = 2000;
	private MarvelPaths2 marvel;
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void initializeNull() throws Exception{
		marvel = new MarvelPaths2();
		marvel.createNewGraph(null);
		}
	
	@Test(timeout = TIMEOUT)
	public void initializewrong() throws IOException{
		marvel = new MarvelPaths2();
		marvel.createNewGraph("well.csv");
		}
	
	@Test(timeout = TIMEOUT)
	public void initialize(){
		marvel = new MarvelPaths2();
		marvel.createNewGraph("data/marvel.csv");
		
		}
}

