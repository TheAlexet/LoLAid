package databases;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String CHAMPION_TABLE_NAME = "champion_table";
    public static final String CHAMPION_COLUMN_NAME_ID = "_ID";
    public static final String CHAMPION_COLUMN_NAME_CHAMPIONID = "champion_id";
    public static final String CHAMPION_COLUMN_NAME_CHAMPIONNAME = "champion_name";
    public static final String CHAMPION_COLUMN_NAME_CHAMPIONICON = "champion_icon";

    public static final String RUNE_TABLE_NAME = "rune_table";
    public static final String RUNE_COLUMN_NAME_ID = "_ID";
    public static final String RUNE_COLUMN_NAME_RUNEID = "rune_id";
    public static final String RUNE_COLUMN_NAME_RUNEICON = "rune_icon";

    public static final String SUMMONER_SPELL_TABLE_NAME = "summoner_spell_table";
    public static final String SUMMONER_SPELL_COLUMN_NAME_ID = "_ID";
    public static final String SUMMONER_SPELL_COLUMN_NAME_SUMMONERSPELLID = "summoner_spell_id";
    public static final String SUMMONER_SPELL_COLUMN_NAME_SUMMONERSPELLICON = "summoner_spell_icon";

}
