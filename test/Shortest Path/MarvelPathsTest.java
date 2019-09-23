package hw5;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import hw4.Graph;
import hw5.MarvelPaths;


public class MarvelPathsTest {

	private static final int TIMEOUT = 2000;
	private MarvelPaths marvel;
	
	@Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
	public void initializeNull() throws Exception{
		marvel = new MarvelPaths();
		marvel.createNewGraph(null);
		}
	
	@Test(timeout = TIMEOUT)
	public void initializewrong() throws IOException{
		marvel = new MarvelPaths();
		marvel.createNewGraph("well.csv");
		}
	
	@Test(timeout = TIMEOUT)
	public void initialize(){
		marvel = new MarvelPaths();
		marvel.createNewGraph("data/marvel.csv");
		
		}
	
	@Test(timeout = TIMEOUT)
	public void initialize2(){
		marvel = new MarvelPaths();
		marvel.createNewGraph("data/marvel copy.csv");
		String s = marvel.findPath("VISION ", "MADCAP");
		
		}
	
	@Test(timeout = TIMEOUT)
	public void DoesNotContainNode1(){
		marvel = new MarvelPaths();
		marvel.createNewGraph("data/marvel copy.csv");
		String s = marvel.findPath("wao", "X-RAY");
		}
	
	@Test(timeout = TIMEOUT)
	public void findpath(){
		marvel = new MarvelPaths();
		marvel.createNewGraph("data/marvel copy.csv");
		String s = marvel.findPath("ORKA", "X-RAY");
		}
	
	@Test(timeout = TIMEOUT)
	public void DoesNotContainNode2(){
		marvel = new MarvelPaths();
		marvel.createNewGraph("data/marvel copy.csv");
		String s = marvel.findPath("ORKA", "wao");
		}
	
	@Test(timeout = TIMEOUT)
	public void DoesNotContainNode4(){
		marvel = new MarvelPaths();
		marvel.createNewGraph("data/marvel copy.csv");
		String s = marvel.findPath("HUMAN ROBOT", "wao");
		}
	
	@Test(timeout = TIMEOUT)
	public void nopath(){
		marvel = new MarvelPaths();
		marvel.createNewGraph("data/marvel copy.csv");
		String s = marvel.findPath("G'RATH", "VENUS II");
		}
	
	@Test(timeout = TIMEOUT)
	public void duplicate(){
		marvel = new MarvelPaths();
		marvel.createNewGraph("data/marvel copy.csv");
		String s = marvel.findPath("Money", "Elephant");
		}
	
	@Test(timeout = TIMEOUT)
	public void reflexive(){
		marvel = new MarvelPaths();
		marvel.createNewGraph("data/marvel copy.csv");
		String s = marvel.findPath("Elephant", "Elephant");
		}
	
	@Test(timeout = TIMEOUT)
	public void test3(){
		marvel = new MarvelPaths();
		marvel.createNewGraph("data/marvel copy.csv");
		String s = marvel.findPath("Koala", "Koala");
		}
}




