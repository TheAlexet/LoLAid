package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lolaid.R;

import java.util.List;

import business_logic.data_models.CurrentGameParticipant;
import business_logic.data_models.custom_pojo.LiveMatchInfo;

public class LiveGameAdapter extends RecyclerView.Adapter<LiveGameAdapter.ViewHolder>{

    private static LiveMatchInfo matchInfo;

    public LiveGameAdapter(LiveMatchInfo matchInf){
        this.matchInfo = matchInf;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_game_player_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //CurrentGameParticipant participant = participantList.get(position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView championIcon;
        private TextView summonerName;
        private ImageView summSpell1;
        private ImageView summSpell2;
        private ImageView runesMain;
        private ImageView runesSecondary;

        public ViewHolder(View v){
            super(v);
            championIcon = v.findViewById(R.id.championPortrait);
            summonerName = v.findViewById(R.id.summonerName);
            summSpell1 = v.findViewById(R.id.summSpell1);
            summSpell2 = v.findViewById(R.id.summSpell2);
            runesMain = v.findViewById(R.id.runesMain);
            runesSecondary = v.findViewById(R.id.runesSecondary);
        }
    }
}
