package player.music.lisboa.musicplayer.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Lisboa on 15-Jul-17.
 */

public class MusicRepository {

	private List<String> albums;

	public MusicRepository() {
		albums = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			albums.add("Album " + i);
		}
	}

	public void test() {
		System.out.println(this);
	}

	public List<String> getAlbums() {
		return albums;
	}
}
