package org.example.WorkingWithDBs.music;
//video 379: JPA challenge
/*
- create new Entity named SONG for the music.songs table
- edit album Entity adding a list of your Song entity as a
  field and include this list in the toString output
- edit the main class retrieving a single artist by id, confirming
  songs are now part of the artist data that's retrieved
- create a SongQuery that's similar to the MainQuery class
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @Column(name="song_id")
    private int songId;

    @Column(name="song_title")
    private String songTitle;

    @Column(name="track_number")
    private int trackNumber;

    public Song() {
    }

    public String getSongTitle() {
        return songTitle;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songTitle='" + songTitle + '\'' +
                ", trackNumber=" + trackNumber +
                '}';
    }
}

