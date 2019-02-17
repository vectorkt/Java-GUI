package main_package;


import java.util.ArrayList;
import java.util.List;

public class Playlist {
	
	
	String playlistName;
	String startTime;
	Integer minDuration;//seconds
	Integer maxDuration;//seconds
	List<Advertisement> adverts;
	
	
	Playlist(String playlistName, String startTime,	Integer minDuration,Integer maxDuration){
		
		this.playlistName=playlistName;
		this.startTime=startTime;
		this.minDuration=minDuration;
		this.maxDuration=maxDuration;
		this.adverts = new ArrayList<>();
		
	}
	
	
	public List<Advertisement> getAdverts() {
		return adverts;
	}


	public void setAdverts(List<Advertisement> adverts) {
		this.adverts = adverts;
	}


	public String getPlaylistName() {
		return playlistName;
	}


	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getMinDuration() {
		return minDuration;
	}

	public void setMinDuration(Integer minDuration) {
		this.minDuration = minDuration;
	}

	public Integer getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(Integer maxDuration) {
		this.maxDuration = maxDuration;
	}
	
	
	

}
