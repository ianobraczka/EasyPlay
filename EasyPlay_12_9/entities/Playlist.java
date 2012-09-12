package entities;

import java.util.ArrayList;

public class Playlist {

	private String name;
	private int numberOfSongs;
	private ArrayList<Song> songList;
	private int currentSongIndex;
	private Song currentSong;
	
	
	public Playlist(String name)
	{
		this.name = name;
		this.songList = new ArrayList<Song>();
		this.currentSongIndex = 0;
		numberOfSongs = 0;
	}
	
	public Playlist(String name, int currentSongIndex)
	{
		this.name = name;
		this.songList = new ArrayList<Song>();
		this.currentSongIndex = currentSongIndex;
		numberOfSongs=0;
	}

	public void addSong(Song song)
	{
		songList.add(song);
		numberOfSongs++;
	}
	
	public void removeSong(String songName)
	{
		for(int cnt=0;cnt<numberOfSongs;cnt++)
		{
			Song songToDelete = songList.get(cnt);
			if (songToDelete.getName() == songName)
			{
				songList.remove(cnt);
				numberOfSongs--;
			}
		}
	}
	
	public void setCurrentSongIndex(int index)
	{
		currentSongIndex = index;
	}
	
	public int getCurrentSongIndex()
	{
		return currentSongIndex;
	}
	
	public Song getCurrentSong()
	{
		return songList.get(currentSongIndex);
	}
	
	public Song getNextSong()
	{
		currentSong = songList.get(currentSongIndex+1);
		return currentSong;
	}
	
	public Song getPreviousSong()
	{
		currentSong = songList.get(currentSongIndex-1);
		return currentSong;
	}
	
	public int getNumberOfSongs()
	{
		return numberOfSongs;
	}
	
	public ArrayList <Song> getSongList()
	{
		return songList;
	}
	
	public String getName()
	{
		return name;
	}
	
	
}
