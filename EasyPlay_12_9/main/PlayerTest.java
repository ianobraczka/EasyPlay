package main;

import java.util.Scanner;
import entities.EasyPlayer;
import enumerations.PlayMode;

public class PlayerTest {

	static boolean pauseToogle = false;
	/**
	 * @param args
	 */
	
	public static void pause(Thread playerThread)
	{
		if (pauseToogle == false)
		{
			pauseToogle = true;
			playerThread.suspend();
			
			Scanner scanner = new Scanner(System.in);
			String pauser = scanner.nextLine();
			
			if(pauser.equals("play"))
			{
				pauseToogle = false;
				playerThread.resume();
			}
		}
		else
		{
			pauseToogle = false;
			playerThread.resume();
		}
		
	}
	
	public static void next(Thread playerThread)
	{
		playerThread.suspend();
		Runnable playerNext = new EasyPlayer(PlayMode.ORDENATED, "C:\\Users\\katia\\Desktop\\temp\\songs", EasyPlayer.getCurrentSongIndexReference() + 1);
		playerThread = new Thread(playerNext);
		playerThread.start();
	}
	
	public static void previous(Thread playerThread)
	{
		playerThread.suspend();
		Runnable playerPrevious = new EasyPlayer(PlayMode.ORDENATED, "C:\\Users\\katia\\Desktop\\temp\\songs", EasyPlayer.getCurrentSongIndexReference() - 1);
		playerThread = new Thread(playerPrevious);
		playerThread.start();
	}
	
	public static void main(String[] args) {

		try
		{	
			Runnable player = new EasyPlayer(PlayMode.SHUFFLE, "C:\\Users\\katia\\Desktop\\temp\\songs");
			Thread playerThread = new Thread(player);
			playerThread.start();
			
			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner(System.in);
						
			String reader = keyboard.nextLine();
			
			if(reader.equals("play specific"))
			{
			
				int selectedSongIndex = keyboard.nextInt();
			
			
				if(selectedSongIndex >= 0)
				{
					playerThread.suspend();
					Runnable playerSpecific = new EasyPlayer(PlayMode.ORDENATED, "C:\\Users\\katia\\Desktop\\temp\\songs", selectedSongIndex);
					playerThread = new Thread(playerSpecific);
					playerThread.start();
				}  
			}
			
			else
			{
				
				switch(reader)
				{
					case "pause":
						pause(playerThread);
						break;
						
					case "next":  //plays specific song with index = currentSongIndex + 1
						next(playerThread);
						break;
						
					case "previous":  //plays specific song with index = currentSongIndex - 1
						previous(playerThread);
						break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("terminated");
		}

	}

}

