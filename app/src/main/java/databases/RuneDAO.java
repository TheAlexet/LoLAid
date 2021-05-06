package databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import databases.models.Champion;
import databases.models.Rune;

@Dao
public interface RuneDAO {

    @Insert
    void insertRune(Rune rune);

    @Query("SELECT * FROM " + DatabaseContract.RUNE_TABLE_NAME)
    List<Rune> getRuneList();

    @Query("SELECT * FROM " + DatabaseContract.RUNE_TABLE_NAME + " WHERE " + DatabaseContract.RUNE_COLUMN_NAME_RUNEID + "= :runeId")
    Rune getRune(long runeId);

    @Delete
    void deleteRune(Rune rune);

    @Query("DELETE FROM " + DatabaseContract.RUNE_TABLE_NAME)
    void deleteAllRunes();
}
