package databases;

import android.provider.BaseColumns;

public class DatabaseContract {

    static final String CHAMPION_TABLE_NAME = "champion_table";
    static final String CHAMPION_COLUMN_NAME_ID = "_ID";
    static final String CHAMPION_COLUMN_NAME_CHAMPIONID = "champion_id";
    static final String CHAMPION_COLUMN_NAME_CHAMPIONNAME = "champion_name";
    static final String CHAMPION_COLUMN_NAME_CHAMPIONICON = "champion_icon";

    static final String RUNE_TABLE_NAME = "rune_table";
    static final String RUNE_COLUMN_NAME_ID = "_ID";
    static final String RUNE_COLUMN_NAME_RUNEID = "rune_id";
    static final String RUNE_COLUMN_NAME_RUNEICON = "rune_icon";

    static final String SUMMONER_SPELL_TABLE_NAME = "summoner_spell_table";
    static final String SUMMONER_SPELL_COLUMN_NAME_ID = "_ID";
    static final String SUMMONER_SPELL_COLUMN_NAME_SUMMONERSPELLID = "summoner_spell_id";
    static final String SUMMONER_SPELL_COLUMN_NAME_SUMMONERSPELLICON = "summoner_spell_icon";

}
