package entities;

import javazoom.jl.player.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import loader.Loader;


import enumerations.PlayMode;

public class EasyPlayer implements Runnable{

	private String dir;
	private PlayMode playMode;
	private Loader loader;
	private String operationHandler;  //pause, next, previous
	private int selectedSongReference;
	private static int currentSongIndexReference; //indcates currentSongIndex to main thread
	
	public EasyPlayer(PlayMode playMode, String dir)
	{
		selectedSongReference = -1;
		operationHandler = "";
		this.playMode = playMode;
		this.dir = dir;
		loader = new Loader (dir);
	}
	
	public EasyPlayer(PlayMode playMode, String dir, int songIndex)//String song)
	{
		try
		{
			selectedSongReference = songIndex;
			operationHandler = "";
			this.playMode = playMode;
			this.dir = dir;
			loader = new Loader (dir);
		}
		catch(Exception e) {}
	}
	
	//currentSongIndexReference
	
	public static int getCurrentSongIndexReference ()
	{
		return currentSongIndexReference;
	}
	
	public void setCurrentSongIndexReference ()
	{
		this.currentSongIndexReference = currentSongIndexReference;
	}
	
	
	
	public Playlist load()
	{
		loader.loadSongs();
		return loader.getMainPlaylist();
	}
	
	public void playSpecificSong(Playlist playlist)
	{
		if((selectedSongReference >= 0) && (selectedSongReference < playlist.getNumberOfSongs()))
			playlist.setCurrentSongIndex(selectedSongReference);
	}
	
	public void setPath(String dir)
	{
		this.dir = dir;
	}
	
	public void playSong(Song song)
	{
		try
		{
			Player player = new Player(song.getFileInputStream());
			player.play();
		}
		catch (Exception e)
		{}
	}
	
	public void play(Playlist playlist)
	{
		if(playMode == PlayMode.SHUFFLE)
		{
			playShuffle(playlist);
		}
		else if(playMode == PlayMode.ORDENATED)
		{
			playOrdenated(playlist);
		}
	}

	
	public void playOrdenated(Playlist playlist)
	{
		for(int cnt=selectedSongReference; cnt<playlist.getNumberOfSongs(); cnt++)
		{
			System.out.println(playlist.getCurrentSongIndex());
			try
			{
				currentSongIndexReference = playlist.getCurrentSongIndex();
				playSong(playlist.getCurrentSong());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			playlist.setCurrentSongIndex(cnt+1);
		}
	}

	
	public void playShuffle(Playlist actualList)
	{
		try
		{
			ArrayList <Integer> alreadyPlayedIndex = new ArrayList<Integer>();
			Random randomNumber = new Random();
			for(int cnt=0; cnt<actualList.getNumberOfSongs(); cnt++)
			{
				int randomSongIndex = (Math.abs(randomNumber.nextInt())%actualList.getNumberOfSongs());
				for (cnt=0; cnt<alreadyPlayedIndex.size(); cnt++)
				{
					if(randomSongIndex == alreadyPlayedIndex.get(cnt))
					{
						randomSongIndex = (Math.abs(randomNumber.nextInt())%actualList.getNumberOfSongs());
						cnt = 0;
					}
				}
				System.out.println(randomSongIndex);
				actualList.setCurrentSongIndex(randomSongIndex);
				alreadyPlayedIndex.add(randomSongIndex);
				currentSongIndexReference = actualList.getCurrentSongIndex();
				playSong(actualList.getCurrentSong());
			}
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("OUT OF BOUNDS EXCEPTION NO PLAY SHUFFLE");
		}
	}
	
	public void run()
	{
		try
		{
			EasyPlayer player;
			if (selectedSongReference <= 0)
			{
				player = new EasyPlayer (playMode, dir);
			}
			else
			{
				playMode = PlayMode.ORDENATED;
				player = new EasyPlayer (playMode, dir, selectedSongReference);
			}
			Playlist mainPlaylist = player.load();
		
			if(selectedSongReference != -1)
				player.playSpecificSong(mainPlaylist);
			
			player.play(mainPlaylist);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
