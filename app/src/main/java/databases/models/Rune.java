package databases.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import databases.DatabaseContract;

@Entity(tableName = DatabaseContract.RUNE_TABLE_NAME)
public class Rune {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseContract.RUNE_COLUMN_NAME_ID)
    private int _ID;

    @ColumnInfo(name = DatabaseContract.RUNE_COLUMN_NAME_RUNEID)
    @NonNull
    private long id;

    @ColumnInfo(name = DatabaseContract.RUNE_COLUMN_NAME_RUNENAME)
    @NonNull
    private String name;

    @ColumnInfo(name = DatabaseContract.RUNE_COLUMN_NAME_RUNEICON)
    @NonNull
    private int iconId;

    public Rune()
    {

    }

    public Rune(int _ID, long id, String name, int iconId) {
        this._ID = _ID;
        this.id = id;
        this.name = name;
        this.iconId = iconId;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
