package loader;

import java.io.*;
import entities.Playlist;
import entities.Song;

public class Loader {
	
	private String directory;  //folder where music files are allocated.
	private Playlist mainPlaylist;  //playlist that will contain files for reproduction.
	
	public Loader(String directory)
	{
		this.directory = directory;
		mainPlaylist = new Playlist("main");
	}
	
	public Loader(String directory, int headSongIndex)
	{
		this.directory = directory;
		mainPlaylist = new Playlist("main", headSongIndex);
	}
	
	//TERMINAR METODO
	public boolean isMP3 (String file)   //checks if file has ".mp3" extension.
	{
		if (file.indexOf(".mp3") == file.length() - 4)  //if String's last four characters are ".mp3", file has ".mp3" extension
			return true;
		return false;
	}
	
	public void loadSongs()  //adds all folder's files to playlist.
	{
		int songIndexCounter = 0;
		
		File files = new File(directory);
		String[] fileNames = files.list();
		for(int songIndex=0; songIndex<fileNames.length; songIndex++)
		{
			try
			{
				if (isMP3(fileNames[songIndex]))  //checks if file has ".mp3" extension, if it does, adds it to the playlist.
				{
					String songDir = directory + "\\" + fileNames[songIndex];
					FileInputStream songPath = new FileInputStream(songDir);
					System.out.println(songIndexCounter + " - " + songDir);
					Song songToAdd = new Song(songPath);
					mainPlaylist.addSong(songToAdd);
					songIndexCounter++;
				}
			}
			catch(Exception e){}
		}
		System.out.println("to play a specific song, type its index number, as indicated on the list above this text, and press 'Enter'");
	}
	
	public Playlist getMainPlaylist()  //returns a playlist contining files.
	{
		return mainPlaylist;
	}

}
