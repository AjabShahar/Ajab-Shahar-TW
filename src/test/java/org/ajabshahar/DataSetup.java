package org.ajabshahar;

import com.ninja_squad.dbsetup.operation.Operation;
import org.ajabshahar.authentication.PasswordAuthenticator;
import org.ajabshahar.authentication.PasswordEncryptor;
import org.ajabshahar.platform.controller.LoginController;

import static com.ninja_squad.dbsetup.Operations.*;

public class DataSetup {
    public static String HASHED_PASSWORD = PasswordEncryptor.getEncryptedPassword("password", PasswordAuthenticator.SALT, PasswordAuthenticator.ALGORITHM);

    public static final Operation DELETE_ALL =
            deleteAllFrom("REFLECTION_SONG", "REFLECTION_PERSON", "SONG_WORD", "SONG_SINGER", "WORD_REFLECTION", "WORD_SYNONYMS", "WORD_WRITER", "WORD_INTRODUCTION",
                    "RELATED_WORDS", "WORD", "TRANSCRIPT", "REFLECTION", "PERSON", "SONG", "TITLE", "CATEGORY", "GATHERING");

    public static final Operation INSERT_ADMIN_USER =
            sequenceOf(
                    insertInto("USERS")
                            .columns("id", "username", "password", "role")
                            .values(11, "admin", HASHED_PASSWORD, LoginController.AUTH_VALUE)
                            .build());

    public static final Operation DELETE_SONGS = deleteAllFrom("SONG");

    public static final Operation DELETE_WORDS = deleteAllFrom("WORD");

    public static final Operation DELETE_WORD_INTRODUCTION = deleteAllFrom("WORD_INTRODUCTION");

    public static final Operation DELETE_SONG_SINGER = deleteAllFrom("SONG_SINGER");

    public static final Operation DELETE_SONG_WORD = deleteAllFrom("SONG_WORD");

    public static final Operation DELETE_CATEGORY = deleteAllFrom("CATEGORY");

    public static final Operation DELETE_PERSON = deleteAllFrom("PERSON");

    public static final Operation DELETE_REFLECTION = deleteAllFrom("REFLECTION");

    public static final Operation DELETE_REFLECTION_WORDS = deleteAllFrom("WORD_REFLECTION");

    public static final Operation DELETE_USERS = deleteAllFrom("USERS");

    public static final Operation DELETE_GATHERINGS = deleteAllFrom("GATHERING");


    public static final Operation INSERT_SONGS =
            sequenceOf(
                    insertInto("SONG")
                            .columns("id", "show_on_landing_page", "is_authoring_complete", "song_category", "youtube_video_id", "soundcloud_track_id", "THUMBNAIL_URL", "gathering_id")
                            .values(1, true, true, 1, "123456", "12345", "thumbURL", 11)
                            .build());

    public static final Operation INSERT_SONG_TITLE =
            sequenceOf(
                    insertInto("TITLE")
                            .columns("id", "original_title", "english_translation", "english_transliteration", "category_id")
                            .values(1, "original", "translation", "transliteration", 2)
                            .build());

    public static final Operation INSERT_UMBRELLA_TITLE =
            sequenceOf(
                    insertInto("TITLE")
                            .columns("id", "original_title", "english_translation", "english_transliteration", "category_id")
                            .values(2, "original", "translation", "transliteration", 3)
                            .build());

    public static final Operation INSERT_SONG_TITLE_CATEGORY =
            sequenceOf(
                    insertInto("CATEGORY")
                            .columns("id", "name", "category_type")
                            .values(2, "Song Title", "Song Title")
                            .build());

    public static final Operation INSERT_UMBRELLA_TITLE_CATEGORY =
            sequenceOf(
                    insertInto("CATEGORY")
                            .columns("id", "name", "category_type")
                            .values(3, "Umbrella Title", "Umbrella Title")
                            .build());

    public static final Operation INSERT_WORDS =
            sequenceOf(
                    insertInto("WORD")
                            .columns("id", "word_original", "word_translation", "word_transliteration", "meaning", "show_on_landing_page", "hindi_intro_excerpt", "english_intro_excerpt", "is_root_word", "diacritic", "default_reflection_id", "display_ajab_shahar_team", "publish")
                            .values(1, "word original", "word translation", "word transliteration", "meaning", true, "some Hindi text", "some English text", true, "some diacritic", null, true, false)
                            .values(2, "word original no 2", "word translation no 2", "word transliteration no 2", "no meaning", true, "some Hindi text", "some English text", true, "some diacritic", 2, false, false)
                            .build());
    public static final Operation INSERT_WORD_INTRODUCTION =
            sequenceOf(
                    insertInto("word_introduction")
                            .columns("word_id", "word_intro_hindi", "word_intro_english", "content_type", "poet_id")
                            .values(1, "word intro hindi", "word intro english", "text", null)
                            .build());
    public static final Operation INSERT_WORD_INTRODUCTION_WITH_COUPLET_CONTENT_TYPE =
            sequenceOf(
                    insertInto("word_introduction")
                            .columns("word_id", "word_intro_hindi", "word_intro_english", "content_type", "poet_id")
                            .values(2, "word intro hindi", "word intro english", "couplet", null)
                            .build());
    public static final Operation INSERT_SONG_WORD =
            sequenceOf(
                    insertInto("SONG_WORD")
                            .columns("song_id", "word_id")
                            .values(1, 1)
                            .build());

    public static final Operation INSERT_CATEGORY =
            sequenceOf(
                    insertInto("CATEGORY")
                            .columns("id", "name", "category_type")
                            .values(1, "SOng", "song")
                            .build());

    public static final Operation INSERT_REFLECTIONS =
            sequenceOf(
                    insertInto("REFLECTION")
                            .columns("id", "title")
                            .values(1, "Oh that wonderful song!")
                            .values(2, "I hate that word!")
                            .build());

    public static final Operation INSERT_PERSON =
            sequenceOf(
                    insertInto("PERSON")
                            .columns("id", "first_name", "last_name")
                            .values(1, "Ravi", "Das")
                            .values(2, "Shabnam", "Virmani")
                            .build());

    public static final Operation INSERT_SONG_SINGER =
            sequenceOf(
                    insertInto("SONG_SINGER")
                            .columns("singer_id", "song_id")
                            .values(1, 1)
                            .build());

    public static final Operation INSERT_WORD_REFLECTIONS =
            sequenceOf(
                    insertInto("word_reflection")
                            .columns("word_id", "reflection_id")
                            .values(1, 1)
                            .build());

    public static final Operation INSERT_GATHERINGS =
            sequenceOf(
                    insertInto("GATHERING")
                            .columns("id", "english_text", "hindi_text")
                            .values(11, "Rajasthan", "rajasthan")
                            .build());

    public static final Operation INSERT_COMPLETE_STARTER_SET =
            sequenceOf(
                    INSERT_CATEGORY,
                    INSERT_PERSON,
                    INSERT_GATHERINGS,
                    INSERT_UMBRELLA_TITLE_CATEGORY,
                    INSERT_UMBRELLA_TITLE,
                    INSERT_SONGS,
                    INSERT_SONG_TITLE_CATEGORY,
                    INSERT_SONG_TITLE,
                    INSERT_SONG_SINGER,
                    INSERT_REFLECTIONS,
                    INSERT_WORDS,
                    INSERT_WORD_INTRODUCTION,
                    INSERT_WORD_INTRODUCTION_WITH_COUPLET_CONTENT_TYPE,
                    INSERT_WORD_REFLECTIONS
            );
}
