package adapters;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolaid.R;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_history_row, parent, false);
        MatchHistoryAdapter.ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MatchInfo matchInfo = matchesList.get(position);

        int gameMinutes = (int) matchInfo.getGameDuration()/60;
        int gameSeconds = (int) matchInfo.getGameDuration()%60;

        String secondsFormatted = gameSeconds >= 10 ? Integer.toString(gameSeconds) : "0" + gameSeconds;
        String gameDuration = gameMinutes + ":" + secondsFormatted;

        holder.tvDuration.setText(gameDuration);
        holder.tvScore.setText("KDA: " + matchInfo.getScore());
        holder.tvLevel.setText("Level: " + String.valueOf(matchInfo.getChampLevel()));
        holder.tvGold.setText("Gold: " + String.valueOf(matchInfo.getGoldEarned()));
        holder.tvMinions.setText("CS: " + String.valueOf(matchInfo.getTotalMinionsKilled()));

        if(matchesList.get(position).getWinnerTeam().equals("WIN")){
            holder.tvWin.setText(R.string.victory_text);
        } else{
            holder.tvWin.setText(R.string.defeat_text);
        }
        Timestamp ts=new Timestamp(matchInfo.getGameCreation());
        Date date = new Date(ts.getTime());
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(date);
        holder.tvDate.setText(formattedDate);
        setChampionIcon(holder, matchInfo);
    }

    @Override
    public int getItemCount() { return matchesList.size(); }


    private void setChampionIcon(@NonNull ViewHolder holder, MatchInfo matchInfo){
        Thread championsThread = new Thread(() -> {
            Champion champion = LoLAidDatabase.getInstance(historyActivity).ChampionDAO().getChampion(matchInfo.getChampionId());
            historyActivity.runOnUiThread(() -> {
                holder.imChampion.setImageResource(champion.getChampionIconId());
            });
        });

        championsThread.start();

        try
        {
            championsThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void setMatches(List<MatchInfo> matches)
    {
        matchesList.clear();
        matchesList.addAll(matches);
        notifyDataSetChanged();
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
