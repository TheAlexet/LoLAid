package databases;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import databases.models.Champion;

@Dao
public interface ChampionDAO {

    @Insert
    long addChampion(Champion champion);

    @Query("SELECT * FROM " + DatabaseContract.CHAMPION_TABLE_NAME)
    List<Champion> getChampionList();

    @Query("SELECT * FROM " + DatabaseContract.CHAMPION_TABLE_NAME + " WHERE " + DatabaseContract.CHAMPION_COLUMN_NAME_CHAMPIONID + "= :championId")
    String getChampionName(long championId);

    @Query("SELECT * FROM " + DatabaseContract.CHAMPION_TABLE_NAME + " WHERE " + DatabaseContract.CHAMPION_COLUMN_NAME_CHAMPIONICON + "= :championId")
    int getChampionIcon(long championId);

}
