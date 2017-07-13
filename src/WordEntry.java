/**
 * @author Ariana Fairbanks
 * @author Harrison Crisman
 */

public class WordEntry 
{
	private String word;
	private double score;
	private int occurrence;

	public WordEntry(String word, double score, int occurrence)
	{
		this.word = word;
		this.score = score;
		this.occurrence = occurrence;
	}
	
	public void update(double average)
	{
		this.score += average;
		this.occurrence++;
	}
	
	public double getScore()
	{
		return score/occurrence;
	}
	
	public int getOccurrence()
	{
		return occurrence;
	}
}
