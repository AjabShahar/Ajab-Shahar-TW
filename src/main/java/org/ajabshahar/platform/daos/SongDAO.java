package org.ajabshahar.platform.daos;

import io.dropwizard.hibernate.AbstractDAO;
import org.ajabshahar.platform.models.Song;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

public class SongDAO extends AbstractDAO<Song> {

    public SongDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Song findById(Long id) {
        Criteria findSong = currentSession().createCriteria(Song.class, "song")
                .add(Restrictions.eq("id", id))
                .createCriteria("song.words", "words", JoinType.LEFT_OUTER_JOIN)
                .setFetchMode("words", FetchMode.JOIN)
                .createCriteria("song.reflections", "reflections", JoinType.LEFT_OUTER_JOIN)
                .setFetchMode("reflections", FetchMode.JOIN);

        return (Song) findSong.uniqueResult();
    }

    public Song saveSong(Song song) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();

        currentSession().saveOrUpdate(song);

        if (song.getIsAuthoringComplete()) {
            song.setPublishedDate(new Timestamp(now.getTime()));
        }
        return song;
    }

    public Set<Song> findAll() {
        Criteria findSongs = currentSession().createCriteria(Song.class, "song")
                .createCriteria("song.words", "words", JoinType.LEFT_OUTER_JOIN)
                .setFetchMode("words", FetchMode.JOIN);
        findSongs.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return new LinkedHashSet<>(findSongs.list());
    }


    public Set<Song> findBy(int songId, int personId) {
        Criteria findSongs = currentSession().createCriteria(Song.class,"song");
        if (songId != 0) {
            findSongs.add(Restrictions.eq("id", (long) songId));
        }
        if (personId != 0) {

            findSongs.createAlias("song.singers", "singers", JoinType.LEFT_OUTER_JOIN);
            findSongs.createAlias("song.poets", "poets", JoinType.LEFT_OUTER_JOIN);

            findSongs.add(Restrictions.or(Restrictions.and(Restrictions.isNotNull("singers"), Restrictions.eq("singers.id",(long)personId)), Restrictions.and(Restrictions.isNotNull("poets"),Restrictions.eq("poets.id", (long)personId))));
        }
        findSongs.add(Restrictions.eq("isAuthoringComplete", true));
        findSongs.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return new LinkedHashSet<>(findSongs.list());
    }

    public void updateSong(Song updatableSong) {
        if (updatableSong.getUmbrellaTitle() != null)
            currentSession().update(updatableSong.getUmbrellaTitle());
        if (updatableSong.getSongTitle() != null)
            currentSession().update(updatableSong.getSongTitle());
        currentSession().update(updatableSong);
    }

    public Set<Song> findSongWithVersions(int songId) {
        Criteria findSongs = currentSession().createCriteria(Song.class);
        Set<Song> songs = new LinkedHashSet<>();

        Song song = findById((long) songId);
        if (song != null) {
            findSongs.createAlias("umbrellaTitle", "titleAlias");
            if (song.getUmbrellaTitle() != null) {
                findSongs.add(Restrictions.eq("titleAlias.id", song.getUmbrellaTitle().getId()));
                findSongs.add(Restrictions.eq("isAuthoringComplete", true));
                findSongs.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                songs.addAll(findSongs.list());
            } else {
                songs.add(song);
            }
        }
        return songs;
    }
}
