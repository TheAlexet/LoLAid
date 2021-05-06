package adapters;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolaid.R;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import business_logic.data_models.MatchDto;
import business_logic.data_models.custom_pojo.MatchInfo;
import databases.LoLAidDatabase;
import databases.models.Champion;

public class MatchHistoryAdapter extends RecyclerView.Adapter<MatchHistoryAdapter.ViewHolder>{
    private List<MatchInfo> matchesList;
    private Activity historyActivity;

    public MatchHistoryAdapter(List<MatchInfo> list, Activity activity){
        matchesList = list;
        historyActivity = activity;
    }

    @NonNull
    @Override
    public MatchHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_history_row, parent, false);
        MatchHistoryAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() { return matchesList.size(); }

    @Override
    public void onBindViewHolder(@NonNull MatchHistoryAdapter.ViewHolder holder, int position) {
        int gameMinutes = (int) (matchesList.get(position).getGameDuration()/1000)/60;
        int gameSeconds = (int) (matchesList.get(position).getGameDuration()/1000)%60;
        String gameDuration = gameMinutes + ":" + gameSeconds;
        holder.tvDuration.setText(gameDuration);
        holder.tvScore.setText(matchesList.get(position).getScore());
        holder.tvLevel.setText(matchesList.get(position).getChampLevel());
        holder.tvGold.setText(matchesList.get(position).getGoldEarned());
        holder.tvMinions.setText(matchesList.get(position).getTotalMinionsKilled());
        if(matchesList.get(position).getWinnerTeam().equals("Win")){
            holder.tvWin.setText(R.string.victory_text);
        } else{
            holder.tvWin.setText(R.string.defeat_text);
        }
        Date date = new Date(matchesList.get(position).getGameCreation());
        holder.tvDate.setText(date.toString());
        setChampionIcon(holder, position);


    }

    private void setChampionIcon(@NonNull ViewHolder holder, int position){
        Thread championsThread = new Thread(() -> {
            Champion champion = LoLAidDatabase.getInstance(historyActivity).ChampionDAO().getChampion(matchesList.get(position).getChampionId());
            historyActivity.runOnUiThread(() -> {
                holder.imChampion.setImageResource(champion.getChampionIconId());
            });
        });
        championsThread.start();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvScore;
        public TextView tvDuration;
        public TextView tvLevel;
        public TextView tvGold;
        public TextView tvMinions;
        public TextView tvWin;
        public TextView tvDate;
        public ImageView imChampion;

        public ViewHolder(View view) {
            super(view);
            tvScore = (TextView) view.findViewById(R.id.tScore);
            tvDuration = (TextView) view.findViewById(R.id.tDuration);
            tvLevel = (TextView) view.findViewById(R.id.tLevel);
            tvGold = (TextView) view.findViewById(R.id.tGold);
            tvMinions = (TextView) view.findViewById(R.id.tMinions);
            tvWin = (TextView) view.findViewById(R.id.tWin);
            tvDate = (TextView) view.findViewById(R.id.tDate);
            imChampion = (ImageView) view.findViewById(R.id.iChampion);
        }

    }
}
