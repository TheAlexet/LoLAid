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
        Log.d("LIST_LENGTH_ADAPTER", matchesList.size() + "");
        historyActivity = activity;
        Log.d("INFO", "Constructor is getting called");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("INFO", "onCreateViewHolder is getting called");
        Log.d("LIST_LENGTH_onCreateVH", matchesList.size() + "");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_history_row, parent, false);
        MatchHistoryAdapter.ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("INFO", "onBindViewHolder is getting called");
        Log.d("LIST_LENGTH_onBindVH", matchesList.size() + "");
        MatchInfo matchInfo = matchesList.get(position);
        Log.d("MATCHINDO_onBind", matchInfo.toString());
        Log.d("POSITION_ADAPTER", position + "");
        Log.d("GAME_DURATION", String.valueOf(matchInfo.getGameDuration()));
        Log.d("GAME_CREATION", String.valueOf(matchInfo.getGameCreation()));
        Log.d("WINNER_TEAM", String.valueOf(matchInfo.getWinnerTeam()));
        Log.d("CHAMPION_ID", String.valueOf(matchInfo.getChampionId()));
        Log.d("KILLS", String.valueOf(matchInfo.getKills()));
        Log.d("DEATHS", String.valueOf(matchInfo.getDeaths()));
        Log.d("ASSISTS", String.valueOf(matchInfo.getAssists()));
        Log.d("CHAMP_LEVEL", String.valueOf(matchInfo.getChampLevel()));
        Log.d("GOLD_EARNED", String.valueOf(matchInfo.getGoldEarned()));
        Log.d("TOTAL_MINIONS_KILLED", String.valueOf(matchInfo.getTotalMinionsKilled()));
        //int gameMinutes = (int) (matchInfo.getGameDuration()/1000)/60;
        //int gameSeconds = (int) (matchInfo.getGameDuration()/1000)%60;
        //String gameDuration = gameMinutes + ":" + gameSeconds;
        //holder.tvDuration.setText(gameDuration);
        //holder.tvScore.setText(matchInfo.getScore());
        //holder.tvLevel.setText(String.valueOf(matchInfo.getChampLevel()));
        holder.tvGold.setText(String.valueOf(matchInfo.getGoldEarned()));
        //holder.tvMinions.setText(String.valueOf(matchInfo.getTotalMinionsKilled()));
        //if(matchesList.get(position).getWinnerTeam().equals("Win")){
          //  holder.tvWin.setText(R.string.victory_text);
        //} else{
          //  holder.tvWin.setText(R.string.defeat_text);
        //}
        //Date date = new Date(matchInfo.getGameCreation());
        //holder.tvDate.setText(date.toString());
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
            //tvScore = (TextView) view.findViewById(R.id.tScore);
            //tvDuration = (TextView) view.findViewById(R.id.tDuration);
            //tvLevel = (TextView) view.findViewById(R.id.tLevel);
            tvGold = (TextView) view.findViewById(R.id.tGold);
            //tvMinions = (TextView) view.findViewById(R.id.tMinions);
            //tvWin = (TextView) view.findViewById(R.id.tWin);
            //tvDate = (TextView) view.findViewById(R.id.tDate);
            imChampion = (ImageView) view.findViewById(R.id.iChampion);
        }

    }
}
