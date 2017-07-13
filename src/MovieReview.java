/**
 *  @author Ariana Fairbanks
 *  @author Harrison Crisman
 *  @description Uses reviews to give movies a score.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MovieReview
{
	//THESE ARE BORING WORDS NOBODY LIKES
	private static String[] list = {"a", "of", "series", "the", "that", "what", "is", 
			"for", "also", "some", "which",
			 "but", "i", "even", "through", "story", "all", "and", "you", "with", 
			 "or", "movie", "film", "this", "an"
			};
	
	public static void main(String args[])
	{
		HashMap<String, WordEntry> words = new HashMap<String, WordEntry>();
		
		if (args.length != 1) 
		{
			System.err.println("Must pass name of movie reviews file.");
			System.exit(0);
		}

		String line;
		int score;
		List<String> fileLines;
		
		String review = " ";
		Scanner keyboard = new Scanner(System.in);

		try
		{
			fileLines = Files.readAllLines(Paths.get(args[0]));
		} 
		catch(IOException e) 
		{
			System.err.println("File " + args[0] + " not found.");
			return;
		}

		Iterator<String> itr = fileLines.iterator();

		while (itr.hasNext()) 
		{
			line = itr.next();
			score = Integer.parseInt(line.substring(0, 1));
			line = line.substring(2).trim();
			line = line.replaceAll("[^a-zA-Z]+", " ");
			line = line.toLowerCase();
			String[] tokens = line.split(" ");
			
			for(String word : tokens)
			{
				if(words.containsKey(word))
				{
					WordEntry previous = words.get(word);
					previous.update(score);
				}
				else
				{
					words.put(word, new WordEntry(word, score, 1));
				}
			}
			
		}

		int totalReviews = fileLines.size();
		System.out.println("Just read " + totalReviews + " reviews.");

		while (review.length() > 0)
		{
			System.out.println("Enter a review -- Press return to exit");
			totalReviews++;
			review = keyboard.nextLine();
			review = review.replaceAll("[^a-zA-Z]+", " ");
			review = review.toLowerCase();
			String[] tokens = review.split(" ");
			int total = 0;

			for(String word : tokens)
			{
				if(word.equals("better")||word.equals("best") || word.equals("great") || word.equals("super") || word.equals("awesome") || word.equals("greatest")  || word.equals("good"))
				{
					total += 3.5;
				}
				else if(word.equals("worst") || word.equals("worse") || word.equals("boring") || word.equals("aweful") || word.equals("stupid") || word.equals("horrible") || word.equals("terrible") || word.equals("bad"))
				{
					total += .5;
				}
				else if(words.containsKey(word) && !isUnimportant(word))
				{
					total += words.get(word).getScore();
				}
				else
				{
					total += 2;
				}
			}
			
			double average = .5 + total/(1.0000 * tokens.length);
			
			if(average < 2)
			{
				System.out.println("This review has an average value of " + average );
				System.out.println("This is a negative review.");
			}
			else if(average > 2)
			{
				System.out.println("This review has an average value of " + average );
				System.out.println("This is a positive review.");
			}
			else
			{
				System.out.println("This review has an average value of " + average );
				System.out.println("This is a neutral review.");
			}

		}
	}
	
	private static boolean isUnimportant(String word)
	{
		boolean result = false;
		
		return result;
	}
}
